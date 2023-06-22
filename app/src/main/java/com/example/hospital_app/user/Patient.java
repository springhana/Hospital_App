package com.example.hospital_app.user;

import com.example.hospital_app.Nurse.Nurse;
import com.example.hospital_app.doctor.Doctor;

public class Patient {

    private Integer patientNum;
    private String patientName;
    private String tel;
    private Doctor doctor;
    private Nurse nurse;

    public Patient(Integer patientNum, String patientName, String tel) {
        this.patientNum = patientNum;
        this.patientName = patientName;
        this.tel = tel;

    }


    public Integer getPatientNum() {
        return patientNum;
    }

    public void setPatientNum(Integer patientNum) {
        this.patientNum = patientNum;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

}
