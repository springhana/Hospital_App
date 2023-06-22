package com.example.hospital_app.Nurse.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospital_app.Nurse.Nurse;
import com.example.hospital_app.R;
import com.example.hospital_app.doctor.Doctor;

import java.util.ArrayList;
import java.util.List;


public class Nur_Adapter extends RecyclerView.Adapter<Nur_Adapter.ViewHolder> {
    private List<Nurse> nurseList;

    public Nur_Adapter(List<Nurse> nurseList) {
        this.nurseList = new ArrayList<>(nurseList);
    }

    public void setData(List<Nurse> nurseList) {
        this.nurseList = nurseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Nurse nurse = nurseList.get(position);
        holder.bind(nurse);
    }

    @Override
    public int getItemCount() {
        return nurseList.size();
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

        public void bind(Nurse nurse) {
            nameTextView.setText("이름 : " + nurse.getNurseName() + " 간호사 ID : " +  nurse.getNurseNumber());
            s.setText("직종 : " + nurse.getFunctions());
            tvName.setText("전화번호 : " + nurse.getTel()  + "\n" + "------------------------------------------------------------------------");
        }
    }

}
