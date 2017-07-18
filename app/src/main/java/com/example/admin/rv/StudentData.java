package com.example.admin.rv;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 1/30/2017.
 */

public class StudentData extends RecyclerView.Adapter<StudentData.ViewHolder> {

    Context c;
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> ids = new ArrayList<>();
    Integer[] ar = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    public StudentData(Context c, ArrayList<String> names, ArrayList<String> ids) {
        this.c = c;
        this.names = names;
        this.ids = ids;
    }

    @Override
    public StudentData.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_student, parent, false);

        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(StudentData.ViewHolder holder, int position) {
        holder.tvName.setText(names.get(position));
        holder.tvID.setText(ids.get(position));
        holder.imageView.setImageResource(ar[position]);

    }


    @Override
    public int getItemCount() {

        return names.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvID;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvID = (TextView) itemView.findViewById(R.id.tvID);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
