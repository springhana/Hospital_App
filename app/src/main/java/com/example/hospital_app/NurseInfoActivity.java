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

import com.example.hospital_app.Nurse.Nurse;
import com.example.hospital_app.Nurse.adapter.Nur_Adapter;
import com.example.hospital_app.apiService.ApiClient;
import com.example.hospital_app.chart.Chart;
import com.example.hospital_app.chart.adapter.Chr_Adapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NurseInfoActivity extends AppCompatActivity {

    Intent intent;
    String name;
    Integer number;
    Button btnChart, userInfo_btn, doctor_btn, nurse_btn, home_btn;
    private RecyclerView recyclerView21, recyclerView24;
    private List<Nurse> nurseList = new ArrayList<>();
    private List<Chart> chartList = new ArrayList<>();
    private ApiClient apiClient; // ApiClient 객체 초기화
    private Nur_Adapter nur_adapter;
    private Chr_Adapter chr_adapter;
    private Integer nurseId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_info);

        intent = getIntent(); // intent 객체 초기화

        apiClient = new ApiClient();
        name = intent.getStringExtra("name");
        number = Integer.parseInt(intent.getStringExtra("number"));
        Log.d("name/number", number + " " + name);

        createRecyclerView(); // RecyclerView 생성
        fetchNurseOneData();

        btnChart = findViewById(R.id.btnChart);
        recyclerView24.setVisibility(View.GONE);

        btnChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchChartOneData();
                btnChart.setVisibility(View.GONE);
                recyclerView24.setVisibility(View.VISIBLE);
            }
        });

        userInfo_btn = findViewById(R.id.userInfo_btn);
        doctor_btn = findViewById(R.id.doctor_btn);
        nurse_btn = findViewById(R.id.nurse_btn);
        home_btn = findViewById(R.id.home_btn);

        userInfo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(NurseInfoActivity.this, UserActivity.class);
                finish();
                startActivity(intent);
            }
        });

        doctor_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                intent = new Intent(NurseInfoActivity.this, DoctorActivity.class);
                finish();
                startActivity(intent);
            }
        });

        nurse_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(NurseInfoActivity.this, NurseActivity.class);
                finish();
                startActivity(intent);
            }
        });

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(NurseInfoActivity.this, AppointmentRequest.class);
                finish();
                startActivity(intent);
            }
        });
    }

    private void fetchChartOneData() {
        apiClient.fetchChartOneData(new Callback<List<Chart>>() {
            @Override
            public void onResponse(Call<List<Chart>> call, Response<List<Chart>> response) {
                if (response.isSuccessful()) {
                    List<Chart> responseData = response.body(); // 응답으로 받은 데이터를 변수에 저장
                    Log.i("responseData", responseData.toString());
                    if (responseData != null) {
                        chartList = responseData; // 변수에 저장한 데이터를 patientList에 할당
                        chr_adapter.setData(chartList);
                        chr_adapter.notifyDataSetChanged();
                        Log.d("API call doctor", "API call success");
                    }
                } else {
                    Log.d("API", "API call failed");
                }
            }

            @Override
            public void onFailure(Call<List<Chart>> call, Throwable t) {
                Log.d("API", "API call failed: " + t.getMessage());
            }
        }, nurseId);
    }


    private void fetchNurseOneData() {
        apiClient.fetchNurseOneData(new Callback<List<Nurse>>() {
            @Override
            public void onResponse(Call<List<Nurse>> call, Response<List<Nurse>> response) {
                if (response.isSuccessful()) {
                    List<Nurse> responseData = response.body(); // 응답으로 받은 데이터를 변수에 저장
                    Log.i("responseData", responseData.toString());
                    if (responseData != null) {
                        nurseList = responseData; // 변수에 저장한 데이터를 patientList에 할당
                        nur_adapter.setData(nurseList);
                        nur_adapter.notifyDataSetChanged();
                        Log.d("API call nurse", "API call success");
                        for (Nurse n : nurseList
                        ) {
                            nurseId = n.getNurseNumber();

                        }
                    }
                } else {
                    Log.d("API", "API call failed");
                }
            }

            @Override
            public void onFailure(Call<List<Nurse>> call, Throwable t) {
                Log.d("API", "API call failed: " + t.getMessage());
            }
        }, name, number);
    }

    private void createRecyclerView() {
        recyclerView21 = findViewById(R.id.recyclerViews21);
        recyclerView24 = findViewById(R.id.recyclerViews24);

        nur_adapter = new Nur_Adapter(nurseList);
        chr_adapter = new Chr_Adapter(chartList);

        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(this);

        recyclerView21.setLayoutManager(layoutManager1);
        recyclerView21.setAdapter(nur_adapter);

        recyclerView24.setLayoutManager(layoutManager2);
        recyclerView24.setAdapter(chr_adapter);
        Log.d("사이클 성공", "사이클 성공");
    }
}