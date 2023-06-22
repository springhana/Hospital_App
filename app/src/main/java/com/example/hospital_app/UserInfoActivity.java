package com.example.hospital_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.hospital_app.apiService.ApiClient;
import com.example.hospital_app.diagnosis.Diagnosis;
import com.example.hospital_app.diagnosis.adapter.Dia_Adapter;
import com.example.hospital_app.user.Patient;
import com.example.hospital_app.user.adapter.Pat_Adapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInfoActivity extends AppCompatActivity {
    Intent intent;

    private RecyclerView recyclerView, recyclerView2;
    private Pat_Adapter pat_adapter;
    private Dia_Adapter dia_adapter;
    private List<Patient> patientList = new ArrayList<>();
    private List<Diagnosis> diagnosisList = new ArrayList<>();
    String name;
    String tel;
    Button btnUserDiaginosis, userInfo_btn, doctor_btn, nurse_btn, home_btn;

    Integer patientNum;
    private ApiClient apiClient; // ApiClient 객체 초기화

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        apiClient = new ApiClient();

        intent = getIntent(); // intent 객체 초기화
        name = intent.getStringExtra("name");
        tel = intent.getStringExtra("tel");
        Log.d("name/tel", tel + " " + name);

        createRecyclerView(); // RecyclerView 생성
        fetchPatientData();
        recyclerView2.setVisibility(View.GONE);
        btnUserDiaginosis = findViewById(R.id.btnUserDiaginosis);
        userInfo_btn = findViewById(R.id.userInfo_btn);
        doctor_btn = findViewById(R.id.doctor_btn);
        nurse_btn = findViewById(R.id.nurse_btn);
        home_btn = findViewById(R.id.home_btn);

        btnUserDiaginosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchDiagnosisData();
                btnUserDiaginosis.setVisibility(View.GONE);
                recyclerView2.setVisibility(View.VISIBLE);

            }
        });

        userInfo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(UserInfoActivity.this, UserActivity.class);
                finish();
                startActivity(intent);
            }
        });

        doctor_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                intent = new Intent(UserInfoActivity.this, DoctorActivity.class);
                finish();
                startActivity(intent);
            }
        });

        nurse_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(UserInfoActivity.this, NurseActivity.class);
                finish();
                startActivity(intent);
            }
        });

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(UserInfoActivity.this, AppointmentRequest.class);
                finish();
                startActivity(intent);
            }
        });
    }

    private void fetchDiagnosisData() {
        apiClient.fetchGetdiagnosisOne_patientIdData(new Callback<List<Diagnosis>>() {
            @Override
            public void onResponse(Call<List<Diagnosis>> call, Response<List<Diagnosis>> response) {
                if (response.isSuccessful()) {
                    List<Diagnosis> responseData = response.body(); // 응답으로 받은 데이터를 변수에 저장
                    Log.i("responseData", responseData.toString());
                    if (responseData != null) {
                        diagnosisList = responseData; // 변수에 저장한 데이터를 patientList에 할당
                        dia_adapter.setData(diagnosisList);
                        dia_adapter.notifyDataSetChanged();
                        Log.d("API call diagnosis", "API call success");
                    }
                } else {
                    Log.d("API", "API call failed");
                }
            }

            @Override
            public void onFailure(Call<List<Diagnosis>> call, Throwable t) {
                Log.d("API", "API call failed: " + t.getMessage());
            }
        }, patientNum);
    }

    private void fetchPatientData() {
        apiClient.fetchPatientOneData(new Callback<List<Patient>>() {
            @Override
            public void onResponse(Call<List<Patient>> call, Response<List<Patient>> response) {
                if (response.isSuccessful()) {
                    List<Patient> responseData = response.body(); // 응답으로 받은 데이터를 변수에 저장
                    Log.i("responseData", responseData.toString());
                    if (responseData != null) {
                        patientList = responseData; // 변수에 저장한 데이터를 patientList에 할당
                        pat_adapter.setData(patientList);
                        pat_adapter.notifyDataSetChanged();
                        Log.d("API call success", "API call success");
                        for (Patient p : patientList
                        ) {
                            patientNum = p.getPatientNum();
                        }
                    }
                } else {
                    Log.d("API", "API call failed");
                }
            }

            @Override
            public void onFailure(Call<List<Patient>> call, Throwable t) {
                Log.d("API", "API call failed: " + t.getMessage());
            }
        }, name, tel);
    }

    private void createRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViews);
        recyclerView2 = findViewById(R.id.recyclerViews2);

        pat_adapter = new Pat_Adapter(patientList);
        dia_adapter = new Dia_Adapter(diagnosisList);

        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setAdapter(pat_adapter);

        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setAdapter(dia_adapter);

    }
}