package com.example.ryan.temporaryname;

import android.database.Cursor;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
        DatabaseHelper myDB;
        TextInputEditText inputEmail;
        TextInputEditText inputPass;
        Button btn_login;
        Button btn_sign_up;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            try {
                myDB = new DatabaseHelper(this);
            } catch (Exception e) {
                System.out.print("Exception occurred");
            }
            inputEmail = findViewById(R.id.editTextEmail);
            inputPass = findViewById(R.id.editTextPass);
            btn_sign_up = findViewById(R.id.btn_sign_up);
            btn_login = findViewById(R.id.btn_login);
            sign_up();
            login();

        }

        public void sign_up() {
            btn_sign_up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean inserted = myDB.insertData(inputEmail.getText().toString(), inputPass.getText().toString());

                    if (inserted) {
                        Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }

        public void login() {
            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String input_email = inputEmail.getText().toString();
                    String input_pass = inputPass.getText().toString();

                    Cursor res = myDB.getData();
                    if (res.getCount() == 0) {
                        // show message
                        return;
                    }

                    StringBuffer buffer = new StringBuffer();
                    //StringBuffer account_email = buffer;
                    StringBuffer account_pass = buffer;

                    res.moveToNext();
                    //account_email = buffer.append(res.getString(1));

                    //res.moveToNext();
                    account_pass = buffer.append(res.getString(2));


                    //Toast.makeText(MainActivity.this, "PreLogin", Toast.LENGTH_LONG).show();
                    if (input_pass == account_pass) {
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                    }


                }
            });
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

} // end class main activity

