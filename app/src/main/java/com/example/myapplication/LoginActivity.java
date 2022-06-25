package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

//    cd C:\Users\Abhishek\AppData\Local\Android\Sdk\platform-tools
    EditText userName,passWord;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.driver_UN);
        passWord = findViewById(R.id.driver_PW);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == loginButton.getId()){
            String Un = userName.getText().toString();
            String Pw = passWord.getText().toString();

//            LoginBackgroundTask loginBackgroundTask = new LoginBackgroundTask(this, this);
//            loginBackgroundTask.execute(Un,Pw);

            Intent intent = new Intent(this, MainMapActivity.class);
            intent.putExtra("parent_username",Un);
            startActivity(intent);
        }
    }
}