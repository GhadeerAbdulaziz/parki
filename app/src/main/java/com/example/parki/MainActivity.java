package com.example.parki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button _btnlog;
    EditText _txtEmail, _txtPass;
    TextView _txtsign;
    boolean isEmailValid, isPasswordValid;
DatabaseHelper db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db =new DatabaseHelper(this);
        _btnlog = (Button) findViewById(R.id.btnlog);
        _txtEmail = (EditText) findViewById(R.id.txtEmail);
        _txtPass = (EditText) findViewById(R.id.txtPass);
        _txtsign = (TextView) findViewById(R.id.txtsign);
        _btnlog.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SetValidation();
            }


        });
        _txtsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent (MainActivity.this,register.class);
                startActivity(intent);
            }
        });

    }
    public void SetValidation() {
        // Check for a valid email address.
        if (_txtEmail.getText().toString().isEmpty()) {
            _txtEmail.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(_txtEmail.getText().toString()).matches()) {
            _txtEmail.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else  {
            isEmailValid = true;
        }

        // Check for a valid password.
        if (_txtPass.getText().toString().isEmpty()) {
            _txtPass.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (_txtPass.getText().length() < 6) {
            _txtPass.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
        }

        if (isEmailValid && isPasswordValid) {
            String email=_txtEmail.getText().toString();
            String pass=_txtPass.getText().toString();
            Boolean res =db.checkUser(email,pass);
            if(res==true){
                Toast.makeText(MainActivity.this,"Successfully Logged in ",Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(MainActivity.this,"Email or password is incorrect",Toast.LENGTH_LONG).show();

            }

        }

    }
    }
