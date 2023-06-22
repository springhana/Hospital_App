package com.example.hospital_app.diagnosis;

import com.example.hospital_app.doctor.Doctor;
import com.example.hospital_app.user.Patient;

import java.util.Date;

public class Diagnosis {
    private Integer diagnosisNum;
    private String diagnosisContent;
    private Date diagnosisDate;
    private Doctor doctor;
    private Patient patient;


    public Diagnosis(Integer diagnosisNum, String diagnosisContent) {
        this.diagnosisNum = diagnosisNum;
        this.diagnosisContent = diagnosisContent;
    }

    public Integer getDiagnosisNum() {
        return diagnosisNum;
    }

    public void setDiagnosisNum(Integer diagnosisNum) {
        this.diagnosisNum = diagnosisNum;
    }

    public String getDiagnosisContent() {
        return diagnosisContent;
    }

    public void setDiagnosisContent(String diagnosisContent) {
        this.diagnosisContent = diagnosisContent;
    }

    public Date getDiagnosisDate() {
        return diagnosisDate;
    }

    public void setDiagnosisDate(Date diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
