package com.example.factsafrica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class StatusChanger extends AppCompatActivity {

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