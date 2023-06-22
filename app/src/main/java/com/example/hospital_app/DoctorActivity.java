package com.example.hospital_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DoctorActivity extends AppCompatActivity {
    EditText etDoctorID, etDoctorName;
    Button btnDoctorInfo, userInfo_btn, doctor_btn, nurse_btn,home_btn;
    Intent intent;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        etDoctorID = findViewById(R.id.etDoctorID);
        etDoctorName = findViewById(R.id.etDoctorName);
        btnDoctorInfo = findViewById(R.id.btnDoctorInfo);
        userInfo_btn = findViewById(R.id.userInfo_btn);
        doctor_btn = findViewById(R.id.doctor_btn);
        nurse_btn = findViewById(R.id.nurse_btn);
        home_btn = findViewById(R.id.home_btn);

        btnDoctorInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(DoctorActivity.this, DoctorInfoActivity.class);
                intent.putExtra("id", etDoctorID.getText().toString());
                intent.putExtra("name", etDoctorName.getText().toString());
                finish();
                Log.i("onClick","클릭");
                startActivity(intent);
            }
        });
        userInfo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(DoctorActivity.this, UserActivity.class);
                finish();
                startActivity(intent);
            }
        });

        doctor_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                intent = new Intent(DoctorActivity.this, DoctorActivity.class);
                finish();
                startActivity(intent);
            }
        });

        nurse_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(DoctorActivity.this, NurseActivity.class);
                finish();
                startActivity(intent);
            }
        });

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(DoctorActivity.this, AppointmentRequest.class);
                finish();
                startActivity(intent);
            }
        });
    }
}