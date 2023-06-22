package com.example.hospital_app.user.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospital_app.R;
import com.example.hospital_app.user.Patient;

import java.util.List;


public class Pat_Adapter extends RecyclerView.Adapter<Pat_Adapter.ViewHolder> {
    private List<Patient> patientList;

    public Pat_Adapter(List<Patient> patientList) {
        this.patientList = patientList;
    }

    public void setData(List<Patient> patientList) {
        this.patientList = patientList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Patient patient = patientList.get(position);
        holder.bind(patient);
    }

    @Override
    public int getItemCount() {
        return patientList.size();
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

        public void bind(Patient patient) {
            nameTextView.setText("이름 : " + patient.getPatientName());
            patientNumTextView.setText("ID : " + patient.getPatientNum().toString());
            tvName.setText("전화번호 : " + patient.getTel() + "\n" + "------------------------------------------------------------------------");
        }
    }
}
