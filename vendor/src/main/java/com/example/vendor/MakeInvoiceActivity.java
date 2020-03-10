package com.example.vendor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MakeInvoiceActivity extends AppCompatActivity  implements View.OnClickListener{


    private TextView mText1;

    @BindView(R.id.editImageDate) TextView mInvoiceDate;
    @BindView(R.id.payBy) TextView mDue;
    @BindView(R.id.customer) TextView mCustomer;
    @BindView(R.id.items) TextView mPickItems;
    @BindView(R.id.sendInvoice) Button mSend;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_invoice);
        ButterKnife.bind(this);

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


//        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                month = month+1;
//
//                String date = month  + "/" + day + "/" + year;
//
//
//                mText1.setText(date);
//
//            }
//        };

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.editImageDate:
                /// Do this

                break;

            case R.id.payBy:
                ////Do this

                break;
        }

    }
}
