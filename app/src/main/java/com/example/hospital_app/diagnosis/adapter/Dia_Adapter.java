package com.example.hospital_app.diagnosis.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospital_app.R;
import com.example.hospital_app.diagnosis.Diagnosis;
import com.example.hospital_app.doctor.Doctor;
import com.example.hospital_app.user.Patient;

import java.util.ArrayList;
import java.util.List;


public class Dia_Adapter extends RecyclerView.Adapter<Dia_Adapter.ViewHolder> {
    private List<Diagnosis> diagnosisList;

    public Dia_Adapter(List<Diagnosis> diagnosisList) {
        this.diagnosisList = diagnosisList;
    }

    public void setData(List<Diagnosis> diagnosisList) {
        this.diagnosisList = diagnosisList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Diagnosis diagnosis = diagnosisList.get(position);
        holder.bind(diagnosis);
    }

    @Override
    public int getItemCount() {
        return diagnosisList.size();
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
            Log.d("nameTecst", String.valueOf(nameTextView));
        }

        public void bind(Diagnosis diagnosis) {
            nameTextView.setText("진료 내용 : " + diagnosis.getDiagnosisContent() + " 진료 ID : " + diagnosis.getDiagnosisNum().toString());
            patientNumTextView.setText("환자 이름 : " + diagnosis.getPatient().getPatientName() + " 환자 ID : " + diagnosis.getPatient().getPatientNum().toString());
            tvName.setText("진료 날짜 :  " + diagnosis.getDiagnosisDate().toString() + "\n" + "의사 이름 : " + diagnosis.getDoctor().getDoctorName() + " 의사 ID : " + diagnosis.getDoctor().getDoctorId().toString()  + "\n" + "------------------------------------------------------------------------");
            Log.d("nameTecst", String.valueOf(nameTextView));
        }
    }
}
