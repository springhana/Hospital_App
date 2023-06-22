package com.example.hospital_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hospital_app.apiService.ApiClient;
import com.example.hospital_app.apiService.ApiService;
import com.example.hospital_app.user.Patient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppointmentRequest extends AppCompatActivity {

    Button appointment_btn, userInfo_btn, doctor_btn, nurse_btn;
    Intent intent;

    private ApiService apiService;
    private ApiClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointmentrequest);

        appointment_btn = findViewById(R.id.appointment_btn);
        userInfo_btn = findViewById(R.id.userInfo_btn);
        doctor_btn = findViewById(R.id.doctor_btn);
        nurse_btn = findViewById(R.id.nurse_btn);

        apiClient = new ApiClient();
        apiService = apiClient.getApiService();

        appointment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDoctor();
                saveNurse();
                intent = new Intent(AppointmentRequest.this, PatientMainActivity.class);
                startActivity(intent);
            }
        });

        userInfo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(AppointmentRequest.this, UserActivity.class);
                startActivity(intent);
            }
        });

        doctor_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                intent = new Intent(AppointmentRequest.this, DoctorActivity.class);
                startActivity(intent);
            }
        });

        nurse_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(AppointmentRequest.this, NurseActivity.class);
                startActivity(intent);
            }
        });

    }

    private void saveDoctor() {

        Call<ResponseBody> call = apiService.saveDoctor();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // POST 요청 성공 처리
                    Toast.makeText(AppointmentRequest.this, "Patient data saved successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // POST 요청 실패 처리
                    Toast.makeText(AppointmentRequest.this, "Failed to save patient data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // POST 요청 실패 처리
                Toast.makeText(AppointmentRequest.this, "Failed to save patient data: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveNurse() {

        Call<ResponseBody> call = apiService.saveNurse();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // POST 요청 성공 처리
                    Toast.makeText(AppointmentRequest.this, "Patient data saved successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // POST 요청 실패 처리
                    Toast.makeText(AppointmentRequest.this, "Failed to save patient data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // POST 요청 실패 처리
                Toast.makeText(AppointmentRequest.this, "Failed to save patient data: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}