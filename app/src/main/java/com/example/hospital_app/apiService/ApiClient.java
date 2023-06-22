package com.example.hospital_app.apiService;

import com.example.hospital_app.Nurse.Nurse;
import com.example.hospital_app.chart.Chart;
import com.example.hospital_app.diagnosis.Diagnosis;
import com.example.hospital_app.doctor.Doctor;
import com.example.hospital_app.user.Patient;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://192.168.68.51:8080/"; // 쓰고 있는 IP 주소 *
    private static ApiService apiService;

    public static ApiService getApiService() {
        if (apiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient())
                    .build();

            apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }

    public ApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    // Doctor
    public void fetchDoctorData(Callback<List<Doctor>> callback) {
        Call<List<Doctor>> call = apiService.getDoctor();
        call.enqueue(callback);
    }

    public void fetchDoctorOneData(Callback<List<Doctor>> callback, String name, Integer number) {
        Call<List<Doctor>> call = apiService.getDoctorOne(name, number);
        call.enqueue(callback);
    }


    // Nurse
    public void fetchNurseData(Callback<List<Nurse>> callback) {
        Call<List<Nurse>> call = apiService.getNurse();
        call.enqueue(callback);
    }

    public void fetchNurseOneData(Callback<List<Nurse>> callback, String name, Integer number) {
        Call<List<Nurse>> call = apiService.getNurseOne(name, number);
        call.enqueue(callback);
    }


    // Patient
    public void fetchPatientGelAllData(Callback<List<Patient>> callback) {
        Call<List<Patient>> call = apiService.getpatient_getAll();
        call.enqueue(callback);
    }
    public void fetchPatientOneData(Callback<List<Patient>> callback, String name, String tel) {
        Call<List<Patient>> call = apiService.getpatientOne(name, tel);
        call.enqueue(callback);
    }

    public void fetchPatientOneIdData(Callback<List<Patient>> callback,Integer integer) {
        Call<List<Patient>> call = apiService.getpatientIdOne(integer);
        call.enqueue(callback);
    }

    // Diagnosis

    public void fetchGetdiagnosis_getAllData(Callback<List<Diagnosis>> callback) {
        Call<List<Diagnosis>> call = apiService.getdiagnosis_getAll();
        call.enqueue(callback);
    }
    public void fetchGetdiagnosisOne_patientIdData(Callback<List<Diagnosis>> callback, Integer integer) {
        Call<List<Diagnosis>> call = apiService.getdiagnosisOne_patientId(integer);
        call.enqueue(callback);
    }
    public void fetchGetdiagnosisOne_doctorIdData(Callback<List<Diagnosis>> callback, Integer integer) {
        Call<List<Diagnosis>> call = apiService.getdiagnosisOne_doctorId(integer);
        call.enqueue(callback);
    }

    // Chart
    public void fetchGetchart_getAllData(Callback<List<Chart>> callback) {
        Call<List<Chart>> call = apiService.getchart_getAll();
        call.enqueue(callback);
    }
    public void fetchChartOneData(Callback<List<Chart>> callback, Integer integer) {
        Call<List<Chart>> call = apiService.getChartOne(integer);
        call.enqueue(callback);
    }
}
