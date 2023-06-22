package com.example.hospital_app.doctor;


public class Doctor {
    private Integer doctorId;
    private String doctorName;
    private String tel;
    private String fieldOfMedicine;

    public Doctor(Integer doctorId, String doctorName, String tel, String fieldOfMedicine) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.tel = tel;
        this.fieldOfMedicine = fieldOfMedicine;
    }


    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


    public String getFieldOfMedicine() {
        return fieldOfMedicine;
    }

    public void setFieldOfMedicine(String fieldOfMedicine) {
        this.fieldOfMedicine = fieldOfMedicine;
    }

//    @Override
//    public String toString() {
//        return "Doctor{" +
//                "doctorId=" + doctorId +
//                ", doctorName='" + doctorName + '\'' +
//                ", tel='" + tel + '\'' +
//                ", fieldOfMedicine='" + fieldOfMedicine + '\'' +
//                '}';
//    }


}
