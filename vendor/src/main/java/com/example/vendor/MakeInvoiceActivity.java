package com.example.vendor;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vendor.db.InvoiceContract;
import com.example.vendor.db.InvoiceDbHelper;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MakeInvoiceActivity extends AppCompatActivity  implements View.OnClickListener{




    @BindView(R.id.editImageDate) TextView mInvoiceDate;
    @BindView(R.id.payBy) TextView mDue;
    @BindView(R.id.customer) TextView mCustomer;
    @BindView(R.id.items) TextView mPickItems;
    @BindView(R.id.customerName) TextView mPickedName;
    @BindView(R.id.sendInvoice) Button mSend;
    @BindView(R.id.invoice_ref_display)
    TextView mInvoiceRef;
    @BindView(R.id.invoice_name)
    EditText mInvoiceName;
    @BindView(R.id.add_invoice_ref)
    ImageView mAddInvoiceRef;
    @BindView(R.id.itemDetailsOne)
    EditText mAddItemsEditor;
    @BindView(R.id.itemDetailsTwo)
    EditText mItemsPrice;
    @BindView(R.id.itemDetailsOneDisplay)
    TextView mItemsDisplay;
    @BindView(R.id.itemDetailsTwoDisplay)
    TextView mPriceDisplay;
    @BindView(R.id.subTotal)
    TextView mSubTotal;
    @BindView(R.id.finalTotal)
    TextView mFinalTotal;

    static final int REQUEST_SELECT_PHONE_NUMBER = 1;



    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatePickerDialog.OnDateSetListener mDateSetListener2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_invoice);


        ButterKnife.bind(this);
        mInvoiceDate.setOnClickListener(this);
        mDue.setOnClickListener(this);
        mCustomer.setOnClickListener(this);
        mPickItems.setOnClickListener(this);
        mSend.setOnClickListener(this);
        mAddInvoiceRef.setOnClickListener(this);




//        mText1= (TextView) findViewById(R.id.editImageDate);

//        mText1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar myCal = Calendar.getInstance();
//                int year =myCal.get(Calendar.YEAR);
//                int month = myCal.get(Calendar.MONTH);
//                int day = myCal.get(Calendar.DAY_OF_WEEK);
////Date Picker
//                DatePickerDialog dialog = new DatePickerDialog(
//                        MakeInvoiceActivity.this,
//                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
//                        mDateSetListener,
//                        year,month,day);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.show();
//
//
//            }
//        });


        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;

                String date = month  + "/" + day + "/" + year;


                mInvoiceDate.setText(date);


            }
        };
        mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;

                String date = month  + "/" + day + "/" + year;


                mDue.setText(date);
            }
        };

    }
    Calendar myCal = Calendar.getInstance();

    int year =myCal.get(Calendar.YEAR);
    int month = myCal.get(Calendar.MONTH);
    int day = myCal.get(Calendar.DAY_OF_WEEK);
    @Override
    public void onClick(View v) {

        DatePickerDialog dialog = new DatePickerDialog(
                MakeInvoiceActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year,month,day);
        switch(v.getId()){
            case R.id.editImageDate:
                /// Do this


                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();



                break;

            case R.id.payBy:

                DatePickerDialog mDialog = new DatePickerDialog(
                        MakeInvoiceActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener2,
                        year,month,day);
                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mDialog.show();

                break;
            case R.id.customer:

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, REQUEST_SELECT_PHONE_NUMBER);

                }



//                Snackbar.make(v, "Contact intent", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                break;

            case R.id.items:
                Snackbar.make(v, "Items List to be populated", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                mItemsDisplay.setText(mAddItemsEditor.getText().toString().trim());
                mPriceDisplay.setText(mItemsPrice.getText().toString().trim());
                double amount = Double.parseDouble(mItemsPrice.getText().toString().trim());
                Double amount_due = amount+(amount * 0.16);
                mSubTotal.setText(mItemsPrice.getText().toString().trim());
                mFinalTotal.setText(amount_due.toString());
                break;

            case R.id.sendInvoice:
                Snackbar.make(v, "Send above detail to pdf", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                insertInvoiceValuesToDb();
                break;

            case R.id.add_invoice_ref:
                mInvoiceRef.setText(mInvoiceName.getText().toString().trim());
                break;


        }


    }
//    public String getEmail(String contactId) {
//        String emailStr = "";
//        final String[] projection = new String[]{ContactsContract.CommonDataKinds.Email.DATA,
//                ContactsContract.CommonDataKinds.Email.TYPE};
//
//        Cursor emailq = managedQuery(ContactsContract.CommonDataKinds.Email.CONTENT_URI, projection, ContactsContract.Data.CONTACT_ID + "=?", new String[]{contactId}, null);
//
//        if (emailq.moveToFirst()) {
//            final int contactEmailColumnIndex = emailq.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA);
//
//            while (!emailq.isAfterLast()) {
//                emailStr = emailStr + emailq.getString(contactEmailColumnIndex) + ";";
//                emailq.moveToNext();
//            }
//        }
//        return emailStr;
//    }

    public void insertInvoiceValuesToDb(){
    String invoiceRef = mInvoiceName.getText().toString().trim();
    String items = mAddItemsEditor.getText().toString().trim();
    String contacts = mPickedName.toString().trim();
    int itemsPrice = Integer.parseInt(mItemsPrice.getText().toString().trim());
    int subTotal = Integer.parseInt(mItemsPrice.getText().toString().trim());
    double finalTotal = subTotal + (Integer.parseInt(mItemsPrice.getText().toString().trim()) *0.16);

        InvoiceDbHelper dbHelper = new InvoiceDbHelper(this);

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(InvoiceContract.InvoiceEntry.COLUMN_ITEM, items);
        values.put(InvoiceContract.InvoiceEntry.COLUMN_CUSTOMER_CONTACT, contacts);
        values.put(InvoiceContract.InvoiceEntry.COLUMN_PAYMENT_DUE, itemsPrice);
        values.put(InvoiceContract.InvoiceEntry.COLUMN_SUB_TOTAL, subTotal);
        values.put(InvoiceContract.InvoiceEntry.COLUMN_AMOUNT_DUE, finalTotal);

        long newRowId = database.insert(InvoiceContract.InvoiceEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error saving invoice", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Invoice  saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SELECT_PHONE_NUMBER && resultCode == RESULT_OK) {
            // Get the URI and query the content provider for the phone number
            Uri contactUri = data.getData();
            String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};
            Cursor cursor = getContentResolver().query(contactUri, projection,
                    null, null, null);
//            Cursor cursor = getContentResolver().query(emailUri, null, null, null, null);
//            String email = cursor.getString(cursor.getColumnIndex(Email.DATA)); // get the email itself
//
//            DatabaseUtils.dumpCursor(cursor); // dump the cursor so you can see the fields and data you can access
//            cursor.close();


            // If the cursor returned is valid, get the phone number
            if (cursor != null && cursor.moveToFirst()) {
                int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(numberIndex);
                // Do something with the phone number
                mPickedName.setText(number);
                //...
            }
        }
    }
}
