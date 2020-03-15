package com.example.vendor.ui;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.example.vendor.R;
import com.example.vendor.models.Invoice;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
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
    @BindView(R.id.business_email)
    TextView mBusinessEmail;
    @BindView(R.id.business_address)
    TextView mBusinessAddress;
    @BindView(R.id.add_client)
    TextView mAddClient;
    @BindView(R.id.item_one)
    TextView mItemOne;
    @BindView(R.id.price_one)
    TextView mPriceOne;
    @BindView(R.id.quantity_one)
    TextView mQuantityOne;
    @BindView(R.id.add_photo_to_invoice)
    TextView mAddPhotoToInvoice;
    @BindView(R.id.invoice_photo)
    ImageView mInvoicePhoto;
    @BindView(R.id.wrap)
    TextView mSubmitInvoice;


    ScrollView scrollView;
    private Invoice mInvoice;
    private String title;
    private int page;
    private View rootView;
    private String dirpath;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    //private InvoiceFragmentListener listener;

    //Calender
    private Calendar myCal = Calendar.getInstance();

    private int year = myCal.get(Calendar.YEAR);
    private int month = myCal.get(Calendar.MONTH);
    private int day = myCal.get(Calendar.DAY_OF_WEEK);


    public InvoiceFragment() {
        // Required empty public constructor
    }

//    public interface InvoiceFragmentListener{
//        void updateName(CharSequence name);
//        void updateEmail(CharSequence email);
//        void updateAddress(CharSequence address);
//    }
//
//    public void updateNameField(CharSequence newName){
//        mBusinessName.setText(newName);
//    }
//    public void updateEmailField(CharSequence newEmail){
//        mBusinessEmail.setText(newEmail);
//    }
//    public void updateAddressField(CharSequence newAddress){
//        mBusinessAddress.setText(newAddress);
//    }

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
        scrollView = rootView.findViewById(R.id.scroll_view);
        ButterKnife.bind(this, rootView);

        Log.d(TAG, "onCreateView: " + mBusinessName.getText().toString().trim());

        mPickDate.setOnClickListener(this);
        mAddClient.setOnClickListener(this);
        mAddPhotoToInvoice.setOnClickListener(this);
        mSubmitInvoice.setOnClickListener(this);

        Bundle args = getArguments();
        String personName = args.getString("foundPersonName");
        String personEmail = args.getString("foundPersonEmail");
        String personAddress = args.getString("foundPersonAddress");
//
        Log.d(TAG, "onCreateView: abc " + personName);
//
        String item = args.getString("item");
        Double price = args.getDouble("price");
        Integer quantity = args.getInt("quantity");
//
//
        mBusinessName.setText(personName);
        mBusinessEmail.setText(personEmail);
        mBusinessAddress.setText(personAddress);
//
        mItemOne.setText(item);
        mPriceOne.setText(price.toString().trim());
        mQuantityOne.setText(quantity.toString().trim());

        Log.d(TAG, "onCreateView: abcd" + quantity);

//        listener.updateName(mBusinessName.getText().toString().trim());
//        listener.updateEmail(mBusinessEmail.getText().toString().trim());
//        listener.updateAddress(mBusinessAddress.getText().toString().trim());

        return rootView;
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

        //    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        if(context instanceof InvoiceFragmentListener){
//            listener = (InvoiceFragmentListener) context;
//        } else {
//            throw new RuntimeException(context.toString() + "Must Implement Invoice Fragment Interface");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        listener = null;
//    }

        //date picker


    }
}
