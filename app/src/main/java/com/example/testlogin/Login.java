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

public class Login extends AppCompatActivity {

    TextInputEditText emailObject,passwordObject;
    Button btnLoginObject;
    TextView signupLinkObject;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailObject = findViewById(R.id.emailLog);
        passwordObject = findViewById(R.id.passwordLog);

        btnLoginObject = findViewById(R.id.btnLogin);

        signupLinkObject = findViewById(R.id.signupLink);

        progressBar = findViewById(R.id.prograssBarLog);


        signupLinkObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
                fileList();
            }
        });

        btnLoginObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email,password;
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                email = String.valueOf(emailObject.getText());
                password = String.valueOf(passwordObject.getText());

                if(!email.equals("") && !password.equals("")) {

                    if (!email.matches(emailPattern)){
                        Toast.makeText(getApplicationContext(),"Invalid email address!",Toast.LENGTH_SHORT).show();
                    }else {

                        progressBar.setVisibility(View.VISIBLE);

                        Handler handler = new Handler();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                String[] field = new String[2];
                                field[0] = "email";
                                field[1] = "password";

                                String[] data = new String[2];
                                data[0] = email;
                                data[1] = password;

                                PutData putData = new PutData("http://192.168.8.143/android/php/login.php", "POST", field, data);
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {

                                        progressBar.setVisibility(View.GONE);

                                        String result = putData.getResult();

                                        if (result.equals("Login Success")) {
                                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            startActivity(intent);
                                            fileList();
                                        } else {
                                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                        });
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"All fields required!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
