package com.example.factsafrica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StatusChanger extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.radioGroup) RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_changer);
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        // get selected radio button from radioGroup
        int selectedId = mRadioGroup .getCheckedRadioButtonId();


        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        Toast.makeText(StatusChanger.this,
                radioButton.getText(), Toast.LENGTH_SHORT).show();

    }

}
