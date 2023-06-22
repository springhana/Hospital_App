package com.example.hospital_app.chart.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospital_app.R;
import com.example.hospital_app.chart.Chart;
import com.example.hospital_app.diagnosis.Diagnosis;

import java.util.List;


public class Chr_Adapter extends RecyclerView.Adapter<Chr_Adapter.ViewHolder> {
    private List<Chart> chartList;

    public Chr_Adapter(List<Chart> chartList) {
        this.chartList = chartList;
    }

    public void setData(List<Chart> chartList) {
        this.chartList = chartList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chart chart = chartList.get(position);
        holder.bind(chart);
    }

    @Override
    public int getItemCount() {
        return chartList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView patientNumTextView;
        private TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textView);
            patientNumTextView = itemView.findViewById(R.id.specialtyTextView);
            tvName = itemView.findViewById(R.id.tvName);
            Log.d("nameTect", String.valueOf(nameTextView));
        }

        public void bind(Chart chart) {
            nameTextView.setText("이름 : " + chart.getDiagnosis().getPatient().getPatientName() + " 회원님 ID : " + chart.getDiagnosis().getPatient().getPatientName());
            patientNumTextView.setText("차트 번호 : " + chart.getChartNum().toString());
            tvName.setText("간호사 이름 : " + chart.getNurse().getNurseName() + " 간호사 ID : " + chart.getNurse().getNurseNumber().toString());
        }
    }
}
