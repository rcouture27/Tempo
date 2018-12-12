package com.example.ryan.temporaryname;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.courseViewHolder> {

    private Context c;
    private List<Course> courseList;
    private View v;

    public CourseAdapter(Context c, List<Course> courseList) {
        this.c = c;
        this.courseList = courseList;
    }

//    Intent myIntent = new Intent(this, Course1_OOP_java.class);
//    startActivity(myIntent);

    @NonNull
    @Override
    public courseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(c);
        View view = inflater.inflate(R.layout.card_layout, null);
        return new courseViewHolder(view);
    }

    VideoView videoview;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull courseViewHolder holder, final int position) {
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(c, "Recycle Click" + position, Toast.LENGTH_SHORT).show();
            }
        });


        if (position == 0) {

        }


        Course course = courseList.get(position);

        holder.textViewTitle.setText(course.getTitle());
        holder.imageView.setImageDrawable(c.getResources().getDrawable(course.getImage(), null));
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }


    class courseViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewTitle;

        public courseViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView_course1);
            textViewTitle = itemView.findViewById(R.id.textViewTitle1);
        }
    }
}