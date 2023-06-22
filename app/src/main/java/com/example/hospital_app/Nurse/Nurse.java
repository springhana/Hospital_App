package com.example.hospital_app.Nurse;

public class Nurse {
    private Integer nurseNumber;
    private String nurseName;
    private String tel;
    private String functions;

    public Nurse(Integer nurseNumber, String nurseName, String tel, String functions) {
        this.nurseNumber = nurseNumber;
        this.nurseName = nurseName;
        this.tel = tel;
        this.functions = functions;
    }

    public Integer getNurseNumber() {
        return nurseNumber;
    }
    public void setNurseNumber(Integer nurseNumber) {
        this.nurseNumber = nurseNumber;
    }

    public String getNurseName() {
        return nurseName;
    }
    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFunctions() {
        return functions;
    }
    public void setFunctions(String functions) {
        this.functions = functions;
    }


}
