package com.example.hospital_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NurseActivity extends AppCompatActivity {
    EditText etNurseID, etNurseName;
    Button btnNurseInfo, userInfo_btn, doctor_btn, nurse_btn,home_btn;
    Intent intent;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse);

        etNurseID = findViewById(R.id.etNurseID);
        etNurseName = findViewById(R.id.etNurseName);
        btnNurseInfo = findViewById(R.id.btnNurseInfo);
        userInfo_btn = findViewById(R.id.userInfo_btn);
        doctor_btn = findViewById(R.id.doctor_btn);
        nurse_btn = findViewById(R.id.nurse_btn);
        home_btn = findViewById(R.id.home_btn);

        btnNurseInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(NurseActivity.this, NurseInfoActivity.class);
                intent.putExtra("name", etNurseName.getText().toString());
                intent.putExtra("number", etNurseID.getText().toString());
                finish();
                startActivity(intent);
            }
        });

        userInfo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(NurseActivity.this, UserActivity.class);
                finish();
                startActivity(intent);
            }
        });

        doctor_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                intent = new Intent(NurseActivity.this, DoctorActivity.class);
                finish();
                startActivity(intent);
            }
        });

        nurse_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(NurseActivity.this, NurseActivity.class);
                finish();
                startActivity(intent);
            }
        });

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(NurseActivity.this, AppointmentRequest.class);
                finish();
                startActivity(intent);
            }
        });
    }
}