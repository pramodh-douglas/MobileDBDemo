package com.example.dbdemo.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dbdemo.databinding.LayoutStudentitemBinding;
import com.example.dbdemo.model.Student;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    List<Student> adapterStudents;
    LayoutStudentitemBinding itemBinding;

    public StudentAdapter(List<Student> adapterStudents) {
        this.adapterStudents = adapterStudents;
    }

    @Override
    public int getCount() {
        return (adapterStudents.size() + 1); //count returns 1 more than data to do header
    }

    @Override
    public Object getItem(int i) {
        return adapterStudents.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null){
            itemBinding = LayoutStudentitemBinding
                                .inflate(
                                        LayoutInflater.from(
                                                viewGroup.getContext()),
                                                viewGroup,false);
        }

        if (i == 0){
            itemBinding.txtViewStudId.setText("StudId");
            itemBinding.txtViewStudName.setText("StudName");
            itemBinding.txtViewDept.setText("StudDept");
            itemBinding.txtViewStudId.setTextColor(Color.RED);
            itemBinding.txtViewStudName.setTextColor(Color.RED);
            itemBinding.txtViewDept.setTextColor(Color.RED);
        } else {
            itemBinding.txtViewStudId.setText(adapterStudents.get(i-1).getStudId());
            itemBinding.txtViewStudName.setText(adapterStudents.get(i-1).getStudName());
            itemBinding.txtViewDept.setText(adapterStudents.get(i-1).getStudDept());
            itemBinding.txtViewStudId.setTextColor(Color.BLACK);
            itemBinding.txtViewStudName.setTextColor(Color.BLACK);
            itemBinding.txtViewDept.setTextColor(Color.BLACK);
        }
        return itemBinding.getRoot();
    }
}
