package com.example.ryan.temporaryname;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                Toast.makeText(getApplicationContext(), "Hello 1", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_item_profile:
                Toast.makeText(getApplicationContext(), "Hello 2", Toast.LENGTH_LONG).show();
                return true;

            case R.id.menu_item_settings:
                Toast.makeText(getApplicationContext(), "Hello 3", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    } // end switch

    class getUserLogin extends AsyncTask<String, Void, String> {

        String status = null;
        Activity context;
        ListView listView;

        public getUserLogin(Activity context, ListView listView) {
            this.context = context;
            this.listView = listView;
        }

        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... connUrl) {
            HttpURLConnection conn = null;
            BufferedReader reader;

            try {
                final URL url = new URL(connUrl[0]);
                conn = (HttpURLConnection) url.openConnection();
                conn.addRequestProperty("Content-Type", "application/json; charset=utf-8");
                conn.setRequestMethod("GET");

                int result = conn.getResponseCode();

                if (result == 200) {
                    InputStream in = new BufferedInputStream(conn.getInputStream());
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    while ((line = reader.readLine()) != null) {
                        status = line;
                    }
                } // end if
            } catch (Exception e) {
                System.out.print("Unknown error");
            }
            return status;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {
                try {

                    ArrayList<String> stringArrayList = new ArrayList<String>();
                    JSONArray jsonArray = new JSONArray(result);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String User = object.getString("User");

                        HashMap<String, String> itemList = new HashMap<String, String>();
                        itemList.put("User", User);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(MainActivity.this, "Could not get any data.", Toast.LENGTH_LONG).show();
            }
        }
    } // end inner class getUserLogin

} // end class main activity

