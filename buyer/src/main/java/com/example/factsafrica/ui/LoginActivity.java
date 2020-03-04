package com.example.factsafrica.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.factsafrica.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.mail) EditText mEd1;  //bind email text
    @BindView(R.id.password) EditText mEd2;//bind password text



    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        findViewById(R.id.textView2).setOnClickListener(this);
        findViewById(R.id.button).setOnClickListener(this);

        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.textView2:

                finish();

                startActivity(new Intent(this, ForgotPasswordActivity.class));  //switch between activity
                break;

            case R.id.button:

                userLogin();
                break;
        }


    }
    private void userLogin() {
        String email = mEd1.getText().toString().trim();
        String password = mEd2.getText().toString().trim();
        if (email.isEmpty()) {
            mEd1.setError("Email is required"); //mail validation
            mEd1.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEd1.setError("Please enter a valid email");
            mEd1.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            mEd2.setError("Password is required"); //password validation
            mEd2.requestFocus();
            return;
        }


        if (password.length() < 6) {
            mEd2.setError("Minimum length of password should be 6"); //password length
            mEd2.requestFocus();
            return;
        }

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                    Intent intent = new Intent (LoginActivity.this,MainActivity.class);

                    startActivity(intent);

                }else {Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();

        if(auth.getCurrentUser()!=null){
            finish();
        }
        startActivity(new Intent(this,MainActivity.class));


    }



}
