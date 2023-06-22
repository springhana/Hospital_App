package com.example.hospital_app.chart;

import com.example.hospital_app.Nurse.Nurse;
import com.example.hospital_app.diagnosis.Diagnosis;

public class Chart {
    private Integer chartNum;
    private String chartContent;
    private Diagnosis diagnosis;
    private Nurse nurse;
    public Chart(Integer chartNum, String chartContent) {
        this.chartNum = chartNum;
        this.chartContent = chartContent;
    }

    public Integer getChartNum() {
        return chartNum;
    }

    public void setChartNum(Integer chartNum) {
        this.chartNum = chartNum;
    }

    public String getChartContent() {
        return chartContent;
    }

    public void setChartContent(String chartContent) {
        this.chartContent = chartContent;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }
}
