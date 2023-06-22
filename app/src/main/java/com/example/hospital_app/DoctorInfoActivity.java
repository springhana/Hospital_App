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
import com.example.hospital_app.doctor.Doctor;
import com.example.hospital_app.doctor.adapter.Doc_Adapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorInfoActivity extends AppCompatActivity {
    Intent intent;
    String name;
    Integer id;
    Button btnDiagnosis, userInfo_btn, doctor_btn, nurse_btn, home_btn;
    private RecyclerView recyclerView, recyclerView23;
    private List<Doctor> doctorList = new ArrayList<>();
    private List<Diagnosis> diagnosisList = new ArrayList<>();
    private ApiClient apiClient; // ApiClient 객체 초기화
    private Doc_Adapter doc_adapter;
    private Dia_Adapter dia_adapter;
    Integer doctorId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_info);

        intent = getIntent(); // intent 객체 초기화

        apiClient = new ApiClient();
        name = intent.getStringExtra("name");
        id = Integer.parseInt(intent.getStringExtra("id"));
        Log.d("name/id", id + " " + name);

        createRecyclerView(); // RecyclerView 생성
        fetchDoctorOneData();

        btnDiagnosis = findViewById(R.id.btnDiagnosis);
        recyclerView23.setVisibility(View.GONE);

        btnDiagnosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchGetdiagnosisOne_doctorIdData();
                btnDiagnosis.setVisibility(View.GONE);
                recyclerView23.setVisibility(View.VISIBLE);
            }
        });


        userInfo_btn = findViewById(R.id.userInfo_btn);
        doctor_btn = findViewById(R.id.doctor_btn);
        nurse_btn = findViewById(R.id.nurse_btn);
        home_btn = findViewById(R.id.home_btn);

        userInfo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(DoctorInfoActivity.this, UserActivity.class);
                finish();
                startActivity(intent);
            }
        });

        doctor_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                intent = new Intent(DoctorInfoActivity.this, DoctorActivity.class);
                finish();
                startActivity(intent);
            }
        });

        nurse_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(DoctorInfoActivity.this, NurseActivity.class);
                finish();
                startActivity(intent);
            }
        });

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(DoctorInfoActivity.this, AppointmentRequest.class);
                finish();
                startActivity(intent);
            }
        });
    }

    private void fetchGetdiagnosisOne_doctorIdData() {
        apiClient.fetchGetdiagnosisOne_doctorIdData(new Callback<List<Diagnosis>>() {
            @Override
            public void onResponse(Call<List<Diagnosis>> call, Response<List<Diagnosis>> response) {
                if (response.isSuccessful()) {
                    List<Diagnosis> responseData = response.body(); // 응답으로 받은 데이터를 변수에 저장
                    Log.i("responseData", responseData.toString());
                    if (responseData != null) {
                        diagnosisList = responseData; // 변수에 저장한 데이터를 patientList에 할당
                        dia_adapter.setData(diagnosisList);
                        dia_adapter.notifyDataSetChanged();
                        Log.d("API call Patient", "API call Patient");
                    }
                } else {
                    Log.d("API", "API call failed");
                }
            }

            @Override
            public void onFailure(Call<List<Diagnosis>> call, Throwable t) {
                Log.d("API", "API call failed: " + t.getMessage());
            }
        }, doctorId);
    }

    private void fetchDoctorOneData() {
        apiClient.fetchDoctorOneData(new Callback<List<Doctor>>() {
            @Override
            public void onResponse(Call<List<Doctor>> call, Response<List<Doctor>> response) {
                if (response.isSuccessful()) {
                    List<Doctor> responseData = response.body(); // 응답으로 받은 데이터를 변수에 저장
                    Log.i("responseData", responseData.toString());
                    if (responseData != null) {
                        doctorList = responseData; // 변수에 저장한 데이터를 patientList에 할당
                        doc_adapter.setData(doctorList);
                        doc_adapter.notifyDataSetChanged();
                        Log.d("API call doctor", "API call success");
                        for (Doctor d : doctorList
                        ) {
                            doctorId = d.getDoctorId();
                        }
                    }
                } else {
                    Log.d("API", "API call failed");
                }
            }

            @Override
            public void onFailure(Call<List<Doctor>> call, Throwable t) {
                Log.d("API", "API call failed: " + t.getMessage());
            }
        }, name, id);
    }

    private void createRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViews);
        recyclerView23 = findViewById(R.id.recyclerViews23);

        doc_adapter = new Doc_Adapter(doctorList);
        dia_adapter = new Dia_Adapter(diagnosisList);

        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setAdapter(doc_adapter);

        recyclerView23.setLayoutManager(layoutManager2);
        recyclerView23.setAdapter(dia_adapter);
        Log.d("사이클 성공", "사이클 성공");
    }
}