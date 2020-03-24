package com.example.factsafrica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;


public class StatusChanger extends AppCompatActivity {
    @BindView(R.id.radioGroup) RadioGroup mRadioGroup;
    @BindView(R.id.radioButton)
    RadioButton mDecline;
    @BindView(R.id.radioButton2) RadioGroup mApprove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_changer);
    }
}
/*RadioGroup radiogroup =  (RadioGroup) findViewById(R.id.groupid);
Button bt = (Button) findViewById(R.id.btnDisplay);

bt.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
            // get selected radio button from radioGroup
            int selectedId = radiogroup .getCheckedRadioButtonId();

            // find the radio button by returned id
            RadioButton radioButton = (RadioButton) findViewById(selectedId);

           Toast.makeText(MainActivity.this,
                radioButton.getText(), Toast.LENGTH_SHORT).show();
        }
});*/