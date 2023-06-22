package com.example.hospital_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hospital_app.apiService.ApiClient;
import com.example.hospital_app.apiService.ApiService;
import com.example.hospital_app.chart.Chart;
import com.example.hospital_app.diagnosis.Diagnosis;
import com.example.hospital_app.user.Patient;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dia_ChrActivity extends AppCompatActivity {
    EditText editText1;
    EditText editText2;
    Button button22;

    private ApiService apiService;
    private ApiClient apiClient;

    private Integer DiagnosisNum = 200000;
    private Integer ChartNum = 300000;

    private List<Diagnosis> diagnosisList;
    private List<Chart> chartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_chr);

        apiClient = new ApiClient();
        apiService = apiClient.getApiService();

        editText1 = findViewById(R.id.editTextText);
        editText2 = findViewById(R.id.editTextText2);
        button22 = findViewById(R.id.button22);



        button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("btn", "onClick");

                fetchGetdiagnosis_getAllData();


            }
        });
    }


    // PatientNum 중복 처리를 위한 함수 (Patient를 모두 Get 한 후 처리)
    private void fetchGetchart_getAllData() {
        apiClient.fetchGetchart_getAllData(new Callback<List<Chart>>() {
            @Override
            public void onResponse(Call<List<Chart>> call, Response<List<Chart>> response) {
                if (response.isSuccessful()) {
                    chartList = response.body();
                    Log.d("fetch chart list:", "fetch chart list");
                    for (Chart c : chartList
                    ) {
                        if (ChartNum.equals(c.getChartNum())) {
                            ChartNum++;
                        } else {
                            return;
                        }
                        Log.i("chartList", String.valueOf(c.getChartNum()));
                    }
                    saveDiagnosisData();
                } else {
//                    Toast.makeText(PatientMainActivity.this, "Failed to fetch patient list", Toast.LENGTH_SHORT).show();
                    Log.d("Failed to fetch chart list:", "Failed to fetch chart list");
                }
            }

            @Override
            public void onFailure(Call<List<Chart>> call, Throwable t) {
//                Toast.makeText(PatientMainActivity.this, "Failed to fetch patient list: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Failed to fetch chart list:", "Failed to fetch chart list");
            }
        });
    }

    // DiagnosisNum 중복 처리를 위한 함수 (Diagnosis 모두 Get 한 후 처리)
    private void fetchGetdiagnosis_getAllData() {
        apiClient.fetchGetdiagnosis_getAllData(new Callback<List<Diagnosis>>() {
            @Override
            public void onResponse(Call<List<Diagnosis>> call, Response<List<Diagnosis>> response) {
                if (response.isSuccessful()) {
                    diagnosisList = response.body();
                    Log.d("fetch diagnosis list:", "fetch diagnosis list");
                    for (Diagnosis d : diagnosisList
                    ) {
                        if (DiagnosisNum.equals(d.getDiagnosisNum())) {
                            DiagnosisNum++;
                        } else {
                            return;
                        }
                        Log.i("DiagnosisList", String.valueOf(d.getDiagnosisNum()));
                    }
                    fetchGetchart_getAllData();
                } else {
//                    Toast.makeText(PatientMainActivity.this, "Failed to fetch patient list", Toast.LENGTH_SHORT).show();
                    Log.d("Failed to fetch diagnosis list:", "Failed to fetch diagnosis list");
                }
            }

            @Override
            public void onFailure(Call<List<Diagnosis>> call, Throwable t) {
//                Toast.makeText(PatientMainActivity.this, "Failed to fetch patient list: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Failed to fetch patient list:", "Failed to fetch patient list");
            }
        });
    }


    private void saveDiagnosisData() {
        // 이름, 전화번호 받아오기
        String dia = editText1.getText().toString();
        String content = editText2.getText().toString();
//        Log.d("textsss", dia + " " + content);

        Diagnosis diagnosis = new Diagnosis(DiagnosisNum, dia);
        Call<ResponseBody> call = apiService.saveDiagnosisData(diagnosis);

        Chart chart = new Chart(ChartNum, content);
        Call<ResponseBody> call2 = apiService.saveChartData(chart);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // POST 요청 성공 처리
                    Toast.makeText(Dia_ChrActivity.this, "diagnosis data saved successfully", Toast.LENGTH_SHORT).show();

                } else {
                    // POST 요청 실패 처리
                    Toast.makeText(Dia_ChrActivity.this, "Failed to save diagnosis data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // POST 요청 실패 처리
                Toast.makeText(Dia_ChrActivity.this, "Failed to save diagnosis data: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
        call2.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // POST 요청 성공 처리
                    Toast.makeText(Dia_ChrActivity.this, "chart data saved successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    // POST 요청 실패 처리
                    Toast.makeText(Dia_ChrActivity.this, "Failed to save chart data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // POST 요청 실패 처리
                Toast.makeText(Dia_ChrActivity.this, "Failed to save chart data: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}