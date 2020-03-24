package com.example.factsafrica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.factsafrica.ui.ConstantsBuyer;
import com.example.factsafrica.ui.models.Invoice;
import com.example.factsafrica.ui.models.User;
import com.example.factsafrica.ui.network.FactsAfricaApi;
import com.example.factsafrica.ui.network.FactsAfricaClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
public void statusUpdate(){
        //to update
}


}
