package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    TextInputLayout InputFirstName,InputLastName,InputEmail,InputPassword,InputRe_Password;
    EditText FirstName,LastName,Email,Password,Re_Password;
    Button Register;
    DBhelper DB;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputFirstName=findViewById(R.id.firstname);
        InputLastName=findViewById(R.id.lastname);
        InputEmail=findViewById(R.id.signup_email);
        InputPassword=findViewById(R.id.signup_password);
        InputRe_Password=findViewById(R.id.re_password);
        Register=findViewById(R.id.register);
        //Extract EditText
        FirstName=new EditText(InputFirstName.getContext());
        LastName=new EditText(InputLastName.getContext());
        Email=new EditText(InputEmail.getContext());
        Password=new EditText(InputPassword.getContext());
        Re_Password=new EditText(InputRe_Password.getContext());



        DB=new DBhelper(this);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Firstname=FirstName.getText().toString();
                String Lastname=LastName.getText().toString();
                String fullname=Firstname+" "+Lastname;
                String email=Email.getText().toString();
                String password=Password.getText().toString();
                String re_password=Re_Password.getText().toString();


                Boolean ConfirmPassword=password.equals(re_password);

                if(ConfirmPassword){
                    Boolean checkSignUp=DB.InsertUser(fullname,email,password);
                    if(checkSignUp){

                        Toast.makeText(MainActivity.this,"New User Added",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Something Goes Wrong",Toast.LENGTH_LONG).show();

                    }

                }else {


                    Toast.makeText(MainActivity.this,"Passwords doesn't match",Toast.LENGTH_LONG).show();

                }









            }
        });

    }
}