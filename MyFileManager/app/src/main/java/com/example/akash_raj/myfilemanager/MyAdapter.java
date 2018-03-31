package com.example.akash_raj.myfilemanager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by akash on 6/4/17.
 */

public class MyAdapter  extends ArrayAdapter<Files_Subfolders> {

    private ArrayList<Files_Subfolders> list;
    public MyAdapter(@NonNull Context context, ArrayList<Files_Subfolders> thisfile) {
        super(context,R.layout.custom_layout_files,thisfile);
        list=thisfile;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View view=inflater.inflate(R.layout.custom_layout_files,parent,false);
        Files_Subfolders files_subfolders=list.get(position);
        TextView name=(TextView)view.findViewById(R.id.name);
        TextView date=(TextView)view.findViewById(R.id.date);
        TextView size=(TextView)view.findViewById(R.id.size);
        TextView path=(TextView)view.findViewById(R.id.path);
        String s=new String(files_subfolders.getDt_modified());
        date.setText(s);
        size.setText(Long.toString(files_subfolders.getSize()));
        path.setText(files_subfolders.getLocation().toString());
        name.setText(files_subfolders.getname());
        return view;
    }
}
