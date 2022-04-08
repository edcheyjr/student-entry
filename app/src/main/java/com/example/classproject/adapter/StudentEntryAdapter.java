package com.example.classproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classproject.R;
import com.example.classproject.StudentModel;

import java.util.List;

public class StudentEntryAdapter extends RecyclerView.Adapter<StudentEntryAdapter.StudentViewHolder> {
    @NonNull private List<StudentModel> data;

    public StudentEntryAdapter(@NonNull  List<StudentModel> data) {
        this.data = data;
    }

    public class StudentViewHolder  extends RecyclerView.ViewHolder{
        private TextView firstame, lastname, regno, course, gender, id;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            firstame= itemView.findViewById(R.id.tvfirstname);
            lastname = itemView.findViewById(R.id.tvlastname);
            course= itemView.findViewById(R.id.tvcourse);
           regno= itemView.findViewById(R.id.tvregno);
           gender= itemView.findViewById(R.id.tvgender);
           id= itemView.findViewById(R.id.tvNo);
        }
    }
    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.student, parent, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        StudentModel studentModel = data.get(position);
        holder.id.setText(String.valueOf(studentModel.getId()));
        holder.firstame.setText(studentModel.getFirstname());
        holder.lastname.setText(studentModel.getLastname());
        holder.regno.setText(studentModel.getRegno());
        holder.course.setText(studentModel.getCourse());
        holder.gender.setText(studentModel.getGender());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void removeItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(StudentModel item, int position) {
        data.add(position, item);
        notifyItemInserted(position);
    }

    public List<StudentModel> getData() {
        return  data;
    }
}
