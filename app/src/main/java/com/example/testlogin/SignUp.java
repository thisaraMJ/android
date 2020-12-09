package com.example.testlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUp extends AppCompatActivity {

    TextInputEditText fullnameObject,emailObject,usernameObject,passwordObject;
    Button btnSignInObject;
    TextView loginLinkObject;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fullnameObject = findViewById(R.id.fullname);
        emailObject = findViewById(R.id.email);
        usernameObject = findViewById(R.id.username);
        passwordObject = findViewById(R.id.password);

        btnSignInObject = findViewById(R.id.btnSignIn);

        loginLinkObject = findViewById(R.id.loginLink);

        progressBar = findViewById(R.id.prograssBar);

        btnSignInObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String fullname,email,username,password;

                fullname = String.valueOf(fullnameObject.getText());
                email = String.valueOf(emailObject.getText());
                username = String.valueOf(usernameObject.getText());
                password = String.valueOf(passwordObject.getText());

                if(!fullname.equals("") && !email.equals("") && !username.equals("") && !password.equals("")) {

                    progressBar.setVisibility(View.VISIBLE);

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[4];
                            field[0] = "fullname";
                            field[1] = "email";
                            field[2] = "username";
                            field[3] = "password";

                            //Creating array for data
                            String[] data = new String[4];
                            data[0] = fullname;
                            data[1] = email;
                            data[2] = username;
                            data[3] = password;

                            PutData putData = new PutData("http://192.168.8.143/android/php/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {

                                    progressBar.setVisibility(View.GONE);

                                    String result = putData.getResult();
                                    //End ProgressBar (Set visibility to GONE)

                                    if (result.equals("Sign Up Success")){
                                        Intent intent = new Intent(getApplicationContext(),Login.class);
                                        startActivity(intent);
                                        fileList();
                                    }else {
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }else {
                    Toast.makeText(getApplicationContext(),"All fields required!",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
