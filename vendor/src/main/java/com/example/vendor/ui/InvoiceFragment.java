package com.example.vendor.ui;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.example.vendor.R;
import com.example.vendor.db.InvoiceContract;
import com.example.vendor.db.InvoiceDbHelper;
import com.example.vendor.models.Invoice;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvoiceFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "InvoiceFragment";
    private static final int REQUEST_IMAGE_CAPTURE = 111;


    //BindViews
    @BindView(R.id.date_today)
    TextView mPickDate;
    @BindView(R.id.business_name_details)
    TextView mBusinessName;
    @BindView(R.id.add_client)
    TextView mAddClient;
    @BindView(R.id.item_one)
    TextView mItemOne;
    @BindView(R.id.add_photo_to_invoice)
    TextView mAddPhotoToInvoice;
    @BindView(R.id.invoice_photo)
    ImageView mInvoicePhoto;
    @BindView(R.id.wrap)
    TextView mSubmitInvoice;
    @BindView(R.id.subtotal_amount)
            TextView mSubtotalAmount;
    @BindView(R.id.total_amount_to_be_paid)
            TextView mTotalAmountToBePaid;



    ScrollView scrollView;
    private Invoice mInvoice;
    private String title;
    private int page;
    private View rootView;
    private String dirpath;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    InvoiceDbHelper dbHelper;

    //Calender
    private Calendar myCal = Calendar.getInstance();

    private int year = myCal.get(Calendar.YEAR);
    private int month = myCal.get(Calendar.MONTH);
    private int day = myCal.get(Calendar.DAY_OF_WEEK);


    public InvoiceFragment() {
        // Required empty public constructor
    }


    public static InvoiceFragment newInstance(int page, String title) {
        InvoiceFragment invoiceFragment = new InvoiceFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        invoiceFragment.setArguments(args);
        return invoiceFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");

        mDateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;

            String date = month + "/" + day + "/" + year;


            mPickDate.setText(date);

        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_invoice, container, false);
        dbHelper = new InvoiceDbHelper(rootView.getContext());
        scrollView = rootView.findViewById(R.id.scroll_view);
        ButterKnife.bind(this, rootView);

        //Log.d(TAG, "onCreateView: " + mBusinessName.getText().toString().trim());

        mPickDate.setOnClickListener(this);
        mAddClient.setOnClickListener(this);
        mAddPhotoToInvoice.setOnClickListener(this);
        mSubmitInvoice.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    @Override
    public void onClick(View v) {
        DatePickerDialog dialog = new DatePickerDialog(
                getActivity(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year, month, day);
        if (v == mPickDate) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }
        if (v == mAddPhotoToInvoice) {
            Log.d(TAG, "onClick: Add Photo");
            startGallery();
        } if(v==mSubmitInvoice){
            takeScreenShot();
        }

    }
    public static Bitmap getBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),
                view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        view.layout(view.getLeft(), view.getTop(), view.getRight(),
                view.getBottom());
        view.draw(canvas);

        return bitmap;
    }

    private void takeScreenShot() {
//First Check if the external storage is writable
        String state = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(state)) {
            Toast.makeText(getActivity(), "......", Toast.LENGTH_SHORT).show();
        }

//Create a directory for your PDF
        File pdfDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), "MyApp");
        if (!pdfDir.exists()){
            pdfDir.mkdir();
        }

//Then take the screen shot
        LayoutInflater inflater = (LayoutInflater) rootView.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        ScrollView root = (ScrollView) inflater.inflate
                (R.layout.fragment_invoice, null); //RelativeLayout is root view of my UI(xml) file.
        root.setDrawingCacheEnabled(true);
        Bitmap screen= getBitmapFromView(getActivity().getWindow().findViewById
                (R.id.invoice_constraint_layout));

//Now create the name of your PDF file that you will generate
        File pdfFile = new File(pdfDir, "myPdfFile.pdf");
        try {
            Document  document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            screen.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            addImage(document,byteArray);
            document.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        Uri uri = FileProvider.getUriForFile(getContext(), getContext().getApplicationContext().getPackageName() + ".provider", new File(pdfDir, "pdfFileName"));
//        intent.setDataAndType(uri, "application/pdf");
//        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//        startActivity(intent);

        //Uri uri = Uri.fromFile(new File(pdfDir,  "pdfFileName"));

        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, "receiver_email_address");
        email.putExtra(Intent.EXTRA_SUBJECT, "subject");
        email.putExtra(Intent.EXTRA_TEXT, "email body");
        Uri uri1 = FileProvider.getUriForFile(getContext(), getContext().getApplicationContext().getPackageName() + ".provider", new File(pdfDir, "pdfFileName"));
        email.putExtra(Intent.EXTRA_STREAM, uri1);
        email.setType("application/pdf");
        email.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(email);

    }
    private static void addImage(Document document,byte[] byteArray)
    {
        Image image = null;
        try
        {
            image = Image.getInstance(byteArray);
        }
        catch (BadElementException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (MalformedURLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // image.scaleAbsolute(150f, 150f);
        try
        {
            document.add(image);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void startGallery() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mInvoicePhoto.setImageBitmap(imageBitmap);
        }


    }

    private void displayDatabaseInfo(){

        SQLiteDatabase db = dbHelper.getReadableDatabase();


        String [] projection = {InvoiceContract.AddressEntry.COLUMN_BUSINESS_NAME,
                InvoiceContract.AddressEntry.COLUMN_PHONE_NUMBER,
                InvoiceContract.AddressEntry.COLUMN_EMAIL,
                InvoiceContract.AddressEntry.COLUMN_ADDRESS

        };
        String [] projection1 = {InvoiceContract.ItemsEntry.COLUMN_ITEM_NAME,
                InvoiceContract.ItemsEntry.COLUMN_QUANTITY,
                InvoiceContract.ItemsEntry.COLUMN_AMOUNT,
                InvoiceContract.ItemsEntry.COLUMN_MULTIPLIED_TOTAL,
                InvoiceContract.ItemsEntry.COLUMN_SUB_TOTAL,
                InvoiceContract.ItemsEntry.COLUMN_NET_TOTAL
        };
        Cursor cursor = db.query(InvoiceContract.AddressEntry.TABLE_NAME, projection, null, null, null, null, null );
        Cursor cursor1 = db.query(InvoiceContract.ItemsEntry.TABLE_NAME, projection1, null, null, null, null, null );

        int nameColumnIndex = cursor.getColumnIndex(InvoiceContract.AddressEntry.COLUMN_BUSINESS_NAME);
        int phoneColumnIndex = cursor.getColumnIndex(InvoiceContract.AddressEntry.COLUMN_PHONE_NUMBER);
        int emailColumnIndex = cursor.getColumnIndex(InvoiceContract.AddressEntry.COLUMN_EMAIL);
        int addressColumnIndex = cursor.getColumnIndex(InvoiceContract.AddressEntry.COLUMN_ADDRESS);
        int itemNameColumnIndex = cursor1.getColumnIndex(InvoiceContract.ItemsEntry.COLUMN_ITEM_NAME);
        int quantityColumnIndex = cursor1.getColumnIndex(InvoiceContract.ItemsEntry.COLUMN_QUANTITY);
        int priceColumnIndex = cursor1.getColumnIndex(InvoiceContract.ItemsEntry.COLUMN_AMOUNT);
        int multipliedPriceColumnIndex = cursor1.getColumnIndex(InvoiceContract.ItemsEntry.COLUMN_MULTIPLIED_TOTAL);
        int subTotalPriceColumnIndex = cursor1.getColumnIndex(InvoiceContract.ItemsEntry.COLUMN_SUB_TOTAL);
        int netColumnIndex = cursor1.getColumnIndex(InvoiceContract.ItemsEntry.COLUMN_NET_TOTAL);

        cursor.moveToLast();
        cursor1.moveToLast();

        String currentName = cursor.getString(nameColumnIndex);
        String currentPhone = cursor.getString(phoneColumnIndex);
        String currentEmail = cursor.getString(emailColumnIndex);
        String currentAddress = cursor.getString(addressColumnIndex);
        String currentItemName = cursor1.getString(itemNameColumnIndex);
        String currentPrice = cursor1.getString(quantityColumnIndex);
        String currentQuantity = cursor1.getString(priceColumnIndex);
        String currentMultipliedPrice = cursor1.getString(multipliedPriceColumnIndex);
        String currentSubTotal = cursor1.getString(subTotalPriceColumnIndex);
        String currentNet = cursor1.getString(netColumnIndex);

        try {
            mBusinessName.append("\n"+ currentName + "\n" + currentPhone + "\n" + currentEmail + "\n" + currentAddress);
            mItemOne.append("\n"+ currentItemName + "\t\t\t\t\t\t\t\t" + currentPrice + "\t\t\t\t\t\t\t\t\t" + currentQuantity + "\t\t\t\t\t\t\t\t\t"+currentMultipliedPrice);
            mSubtotalAmount.setText(currentSubTotal);
            mTotalAmountToBePaid.setText(currentNet);


        } finally {
            cursor.close();
            cursor1.close();
        }
    }
}
