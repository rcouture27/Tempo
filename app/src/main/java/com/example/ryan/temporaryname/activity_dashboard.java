package com.example.ryan.temporaryname;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class activity_dashboard extends AppCompatActivity {

    RecyclerView recyclerView;
    CourseAdapter adapter;
    ImageView courseImage;
    CardView cardView;

    ArrayList<String> mVideos;
    List<Course> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        courseList = new ArrayList<>();
        mVideos = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        displayCourses();

        courseImage = findViewById(R.id.imageView_course1);

        mVideos.add("This is a name");

        //creating recycler view adapter
        CourseAdapter adapter = new CourseAdapter(this, courseList, mVideos);

        //setting adapter to recycler view
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //replaces the default 'Back' button action
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            KeyEvent.changeAction(event, 0);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_home:
                Intent myIntent = new Intent(getApplicationContext(), activity_dashboard.class);
                startActivity(myIntent);
                return true;
            case R.id.menu_item_logout:
                Intent logout = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(logout);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    } // end switch

    public void displayCourses() {

        //adding some items to the list
        courseList.add(new Course(1, "OOP with Java", R.drawable.card1xxxhdpi));

        courseList.add(new Course(1,"Android studio tips", R.drawable.card2xxxhdpi));

        courseList.add(new Course(1,"HTML", R.drawable.card3xxxhdpi));

        courseList.add(new Course(1, "Javascript functions", R.drawable.card4xxxhdpi));

        courseList.add(new Course(1, "Java VS C#", R.drawable.card5xxxhdpi));

        courseList.add(new Course(1, "Best languages", R.drawable.card6xxxhdpi));

        courseList.add(new Course(1, "fundamentals w/ Java", R.drawable.card7xxxhdpi));

        courseList.add(new Course(1, "glusterfs volumes", R.drawable.card8xxxhdpi));

        courseList.add(new Course(1, "Async programming C#", R.drawable.card9xxxhdpi));

        courseList.add(new Course(1, "Intro to Python", R.drawable.card10xxxhdpi));
    }

} // end class
