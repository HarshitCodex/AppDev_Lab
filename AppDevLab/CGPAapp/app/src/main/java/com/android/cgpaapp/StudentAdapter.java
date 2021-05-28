package com.android.cgpaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private ArrayList<StudentItem> mStudentList;
    public static class StudentViewHolder extends RecyclerView.ViewHolder{
        public TextView mText;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            mText = itemView.findViewById(R.id.tvInfo);
        }
    }

    public void updateList(ArrayList<StudentItem> studentList)
    {
        mStudentList=studentList;
        notifyDataSetChanged();
    }
    public StudentAdapter(ArrayList<StudentItem> studentList)
    {
        mStudentList=studentList;
    }
    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item,parent,false);
        StudentViewHolder evh = new StudentViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        StudentItem currentItem = mStudentList.get(position);
        holder.mText.setText(currentItem.getTextResource());
    }

    @Override
    public int getItemCount() {
        return mStudentList.size();
    }
}
