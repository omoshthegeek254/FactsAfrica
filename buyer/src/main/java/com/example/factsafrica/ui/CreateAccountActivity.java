package com.example.factsafrica.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.factsafrica.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.userName)
    EditText mUserName;
    @BindView(R.id.emailId)
    EditText mEmailId;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.ConfrimPassword)
    EditText mConfrimPassword;
    @BindView(R.id.signUpButton)
    Button mSignUpButton;
    @BindView(R.id.logIn)
    TextView mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        ButterKnife.bind(this);

        mSignUpButton.setOnClickListener(this);
        mLogin.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        if (view == mLogin) {
            Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (view == mSignUpButton) {
            createNewUser();
        }
    }

    private void createNewUser() {
    }
}
