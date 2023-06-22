package com.example.hospital_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hospital_app.Nurse.Nurse;
import com.example.hospital_app.apiService.ApiClient;
import com.example.hospital_app.apiService.ApiService;
import com.example.hospital_app.doctor.Doctor;
import com.example.hospital_app.user.Patient;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientMainActivity extends AppCompatActivity {
    private EditText etTel;
    private EditText etName;
    private Button btnSubmit, btnSign, btnLogin;
    private ApiService apiService;


    // spinner 에 담아오는 작업
    private Spinner spinnerDoctor;
    private Spinner spinnerNurse;

    private ApiClient apiClient;
    private Doctor selectedDoctor;
    private Nurse selectedNurse;

    private List<Doctor> doctorList;
    private List<Nurse> nurseList;
    private List<Patient> patientList;
    private Integer PatientNum = 100000;

    String name;
    String tel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_main);

        // API 서비스 인스턴스 초기화

        apiClient = new ApiClient();
        apiService = apiClient.getApiService();
        etTel = findViewById(R.id.etTel);
        etName = findViewById(R.id.etName);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSign = findViewById(R.id.btnSign);
        btnLogin = findViewById(R.id.btnLogin);


        // ====== spinner 에 담아오는 작업 =====
        apiClient = new ApiClient();
        // 1. 의사
        spinnerDoctor = findViewById(R.id.spinnerDoctor);
        // 2. 간호사
        spinnerNurse = findViewById(R.id.spinnerNurse);


        spinnerDoctor.setVisibility(View.GONE);
        spinnerNurse.setVisibility(View.GONE);
        fetchDataList();
        fetchPatientGelAllData();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchPatientData();
                btnSign.setVisibility(View.GONE);
                btnLogin.setVisibility(View.GONE);
            }
        });

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinnerDoctor.setVisibility(View.VISIBLE);
                spinnerNurse.setVisibility(View.VISIBLE);
                btnSign.setVisibility(View.GONE);
                btnLogin.setVisibility(View.GONE);
            }
        });
        // ==================================

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePatientData();
            }
        });
    }

    // 로그인
    private void fetchPatientData() {
        name = etName.getText().toString();
        tel = etTel.getText().toString();
        Log.d("name//tel", name + " " + tel);
        apiClient.fetchPatientOneData(new Callback<List<Patient>>() {
            @Override
            public void onResponse(Call<List<Patient>> call, Response<List<Patient>> response) {
                if (response.isSuccessful()) {
                    List<Patient> responseData = response.body(); // 응답으로 받은 데이터를 변수에 저장
                    Log.i("responseData", responseData.toString());
                    if (responseData != null) {
                        patientList = responseData; // 변수에 저장한 데이터를 patientList에 할당
                        if (patientList.size() != 0) {
                            Log.d("API call success patient", "API call success");
                            for (Patient p : patientList
                            ) {
                                PatientNum = p.getPatientNum();
                            }
                            etName.setVisibility(View.GONE);
                            etTel.setVisibility(View.GONE);
                            spinnerDoctor.setVisibility(View.VISIBLE);
                            spinnerNurse.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(PatientMainActivity.this, "등록이 되지 않았습니다.", Toast.LENGTH_SHORT).show();
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

    // PatientNum 중복 처리를 위한 함수 (Patient를 모두 Get 한 후 처리)
    private void fetchPatientGelAllData() {
        apiClient.fetchPatientGelAllData(new Callback<List<Patient>>() {
            @Override
            public void onResponse(Call<List<Patient>> call, Response<List<Patient>> response) {
                if (response.isSuccessful()) {
                    patientList = response.body();
                    Log.d("fetch patient list:", "fetch patient list");
                    for (Patient p : patientList
                    ) {
                        if (PatientNum.equals(p.getPatientNum())) {
                            PatientNum++;
                        } else {
                            return;
                        }
                        Log.i("PatientList", String.valueOf(p.getPatientNum()));
                    }

                } else {
//                    Toast.makeText(PatientMainActivity.this, "Failed to fetch patient list", Toast.LENGTH_SHORT).show();
                    Log.d("Failed to fetch patient list:", "Failed to fetch patient list");
                }
            }

            @Override
            public void onFailure(Call<List<Patient>> call, Throwable t) {
//                Toast.makeText(PatientMainActivity.this, "Failed to fetch patient list: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Failed to fetch patient list:", "Failed to fetch patient list");
            }
        });
    }

    // spinner 에 의사 정보를 담아오는 작업
    private void fetchDataList() {
        apiClient.fetchDoctorData(new Callback<List<Doctor>>() {
            @Override
            public void onResponse(Call<List<Doctor>> call, Response<List<Doctor>> response) {
                if (response.isSuccessful()) {
                    doctorList = response.body();
                    setupDoctorSpinner();
                } else {
                    Toast.makeText(PatientMainActivity.this, "Failed to fetch doctor list", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Doctor>> call, Throwable t) {
                Toast.makeText(PatientMainActivity.this, "Failed to fetch doctor list: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        apiClient.fetchNurseData(new Callback<List<Nurse>>() {
            @Override
            public void onResponse(Call<List<Nurse>> call, Response<List<Nurse>> response) {
                nurseList = response.body();
                setupNurseSpinner();
            }

            @Override
            public void onFailure(Call<List<Nurse>> call, Throwable t) {
                Toast.makeText(PatientMainActivity.this, "Failed to fetch doctor list", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // ========== spinner(의사) 선택 시 정보를 넘겨주기 위한 함수 ==========
    private void setupDoctorSpinner() {
        List<String> doctorNames = new ArrayList<>();
        for (Doctor doctor : doctorList) {
            doctorNames.add(doctor.getDoctorName());
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, doctorNames);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDoctor.setAdapter(spinnerAdapter);

        spinnerDoctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDoctor = doctorList.get(position);
                Log.d("SelectedDoctor", String.valueOf(selectedDoctor.getDoctorId())); // 추가
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nothing to do
                selectedDoctor = null;
                Log.d("SelectedDoctor", "실패"); // 추가
            }
        });
    }
    // =======================================================

    // ========== spinner(간호사) 선택 시 정보를 넘겨주기 위한 함수 ==========
    private void setupNurseSpinner() {
        List<String> nurseNames = new ArrayList<>();
        for (Nurse nurse : nurseList) {
            nurseNames.add(nurse.getNurseName());
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, nurseNames);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNurse.setAdapter(spinnerAdapter);

        spinnerNurse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedNurse = nurseList.get(position);
                Log.d("selectedNurse", String.valueOf(selectedNurse.getNurseNumber())); // 추가
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nothing to do
                selectedNurse = null;
                Log.d("selectedNurse", "실패"); // 추가
            }
        });
    }
    // =======================================================


    // ========== Patient의 데이터를 post 하기 위한 함수 ==========
    private void savePatientData() {
        fetchPatientData();
        name = etName.getText().toString();
        tel = etTel.getText().toString();

        // 이름, 전화번호 받아오기
        Patient patient = new Patient(PatientNum, name, tel);
        Log.d("patient", name + " " + tel + " " + PatientNum);
        // String nurseID = editTextNurseID.getText().toString();
        // 의사 ID, 간호사 ID 받아오기
        int doctorID = selectedDoctor.getDoctorId();
        int nurseID = selectedNurse.getNurseNumber();
        Log.d("doc/nur ID", String.valueOf(doctorID) + " " + String.valueOf(nurseID));

        Call<ResponseBody> call = apiService.savePatientData(nurseID, doctorID, patient);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // POST 요청 성공 처리
                    Toast.makeText(PatientMainActivity.this, "Patient data saved successfully", Toast.LENGTH_SHORT).show();

                    // Chart Post로 이동
                    Intent intent = new Intent(PatientMainActivity.this, Dia_ChrActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // POST 요청 실패 처리
                    Toast.makeText(PatientMainActivity.this, "Failed to save patient data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // POST 요청 실패 처리
                Toast.makeText(PatientMainActivity.this, "Failed to save patient data: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    // ===========================================================

}