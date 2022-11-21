package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {


    TextInputLayout InputEmail,InputPassword;
    EditText Email,Password;
    Button Login,Signup;
    DBhelper DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InputEmail =findViewById(R.id.signup_email);
        InputPassword=findViewById(R.id.entredpassword);
        Email=new EditText(InputEmail.getContext());
        Password=new EditText(InputPassword.getContext());

        Login=findViewById(R.id.login);
        Signup=findViewById(R.id.signup);
        DB=new DBhelper(this);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String email = Email.getText().toString();
               String password=Password.getText().toString();
               if(email.equals("") || password.equals("")){

                   Toast.makeText(Login.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
               }
               else{
                   Boolean UserExist =DB.CheckUserExistence(email);
                   if (UserExist ==true){

                       Boolean ConfirmPassword =DB.CheckPassword(email,password);
                       if (ConfirmPassword ==true){
                           Toast.makeText(Login.this, "User registered", Toast.LENGTH_SHORT).show();
                       }else {
                           Toast.makeText(Login.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                       }
                   }else {
                       Toast.makeText(Login.this, "User "+ email+" doesn't exist", Toast.LENGTH_SHORT).show();
                   }


               }



            }
        });


        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }
}