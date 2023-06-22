package com.example.hospital_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserActivity extends AppCompatActivity {
    EditText etUserName, etUserTel;
    Button btnUserInfo, userInfo_btn, doctor_btn, nurse_btn, home_btn;
    Intent intent;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        etUserName = findViewById(R.id.etUserName);
        etUserTel = findViewById(R.id.etUserTel);
        btnUserInfo = findViewById(R.id.btnUserInfo);
        userInfo_btn = findViewById(R.id.userInfo_btn);
        doctor_btn = findViewById(R.id.doctor_btn);
        nurse_btn = findViewById(R.id.nurse_btn);
        home_btn = findViewById(R.id.home_btn);

        btnUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(UserActivity.this, UserInfoActivity.class);
                intent.putExtra("name", etUserName.getText().toString());
                intent.putExtra("tel", etUserTel.getText().toString());
                finish();
                startActivity(intent);
            }
        });


        userInfo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(UserActivity.this, UserActivity.class);
                finish();
                startActivity(intent);
            }
        });

        doctor_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                intent = new Intent(UserActivity.this, DoctorActivity.class);
                finish();
                startActivity(intent);
            }
        });

        nurse_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(UserActivity.this, NurseActivity.class);
                finish();
                startActivity(intent);
            }
        });

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(UserActivity.this, AppointmentRequest.class);
                finish();
                startActivity(intent);
            }
        });
    }
}