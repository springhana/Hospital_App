package com.example.hospital_app.doctor.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.hospital_app.R;
import com.example.hospital_app.doctor.Doctor;

import java.util.ArrayList;
import java.util.List;


public class Doc_Adapter extends RecyclerView.Adapter<Doc_Adapter.ViewHolder> {
    private List<Doctor> doctorList;

    public Doc_Adapter(List<Doctor> doctorList) {
        this.doctorList = new ArrayList<>(doctorList);
    }

    public void setData(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Doctor doctor = doctorList.get(position);
        holder.bind(doctor);
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView s;

        private TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textView);
            s = itemView.findViewById(R.id.specialtyTextView);
            tvName = itemView.findViewById(R.id.tvName);
        }

        public void bind(Doctor doctor) {
            nameTextView.setText("이름 : " + doctor.getDoctorName() + " 의사 ID : " + doctor.getDoctorId());
            s.setText("직종 :  " + doctor.getFieldOfMedicine());
            tvName.setText("전화번호 : " + doctor.getTel()  + "\n" + "------------------------------------------------------------------------");
        }
    }

}
