package com.example.hospital_app.apiService;

import com.example.hospital_app.Nurse.Nurse;
import com.example.hospital_app.chart.Chart;
import com.example.hospital_app.diagnosis.Diagnosis;
import com.example.hospital_app.doctor.Doctor;
import com.example.hospital_app.user.Patient;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    // 의사 전부 Get
    @GET("/doctor/doctor_getAll")
    Call<List<Doctor>> getDoctor();

    @GET("/doctor/doctorOne")
    Call<List<Doctor>> getDoctorOne(@Query("name")String name, @Query("number") Integer number);

    @POST("/doctor/doctorPost")
    Call<ResponseBody> saveDoctor();

    @GET("/nurse/nurse_getAll")
    Call<List<Nurse>> getNurse();

    @GET("/nurse/nurseOne")
    Call<List<Nurse>> getNurseOne(@Query("name")String name, @Query("number") Integer number);

    @GET("/Patient/patient_getAll")
    Call<List<Patient>> getpatient_getAll();

    @POST("/nurse/nursePost")
    Call<ResponseBody> saveNurse();


    @GET("/Patient/patientOne")
    Call<List<Patient>> getpatientOne(@Query("name") String name, @Query("tel") String tel);

    @GET("/Patient/patientIdOne")
    Call<List<Patient>> getpatientIdOne(@Query("number") Integer integer);

    @GET("/chart/chart_getAll")
    Call<List<Chart>> getchart_getAll();

    @GET("/chart/chartOne")
    Call<List<Chart>> getChartOne(@Query("number") Integer integer);


    @GET("/diagnosis/diagnosis_getAll")
    Call<List<Diagnosis>> getdiagnosis_getAll();

    @GET("/diagnosis/diagnosisOne_patientId")
    Call<List<Diagnosis>> getdiagnosisOne_patientId(@Query("number") Integer integer);

    @GET("/diagnosis/diagnosisOne_doctorId")
    Call<List<Diagnosis>> getdiagnosisOne_doctorId(@Query("number") Integer integer);

    // 진료 신청
    @POST("/sign/PatientSave")
    Call<ResponseBody> savePatientData(@Query("nurseID") int nurseID, @Query("doctorID") int doctorID, @Body Patient patient);

    @POST("/sign/DiagnosisSave")
    Call<ResponseBody> saveDiagnosisData(@Body Diagnosis diagnosis);

    @POST("/sign/ChartSave")
    Call<ResponseBody> saveChartData(@Body Chart chart);

}
