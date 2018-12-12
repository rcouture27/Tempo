package com.example.ryan.temporaryname;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
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
    private ArrayList<String> mVideos = new ArrayList<>();
    VideoView videoview;


    public CourseAdapter(Context c, List<Course> courseList, ArrayList<String> videos) {
        this.c = c;
        this.courseList = courseList;
        this.mVideos = videos;
    }


    @NonNull
    @Override
    public courseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(c);
        View view = inflater.inflate(R.layout.card_layout, null);
        return new courseViewHolder(view);
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull courseViewHolder holder, final int position) {
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(c, "Recycle Click" + position, Toast.LENGTH_SHORT).show();

                if (position == 0) {
                    Intent intent = new Intent(c, Course1_OOP_java.class);
                    c.startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(c, Course2_Android_tips.class);
                    c.startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(c, Course3_HTML.class);
                    c.startActivity(intent);
                } else if (position == 3) {
                    Intent intent = new Intent(c, Course4_Javascript.class);
                    c.startActivity(intent);
                } else if (position == 4) {
                    Intent intent = new Intent(c, Course5_JavaCSharp.class);
                    c.startActivity(intent);
                } else if (position == 5) {
                    Intent intent = new Intent(c, Course6_languages.class);
                    c.startActivity(intent);
                } else if (position == 6) {
                    Intent intent = new Intent(c, Course7_fundamentals.class);
                    c.startActivity(intent);
                } else if (position == 7) {
                    Intent intent = new Intent(c, Course8_glusterfs.class);
                    c.startActivity(intent);
                } else if (position == 8) {
                    Intent intent = new Intent(c, Course9_Async.class);
                    c.startActivity(intent);
                } else if (position == 9) {
                    Intent intent = new Intent(c, Course10_Python.class);
                    c.startActivity(intent);
                }
            }
        });





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