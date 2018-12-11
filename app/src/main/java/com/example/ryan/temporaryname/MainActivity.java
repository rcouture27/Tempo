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
    String registered_pass;
    String registered_email;

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
            getAccount();

            boolean inserted = myDB.insertData(inputEmail.getText().toString(), inputPass.getText().toString(), registered_email, registered_pass);

            if (inserted == true) {
                Toast.makeText(MainActivity.this, "Account Created", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "Registration failed: Email is already in use", Toast.LENGTH_LONG).show();
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

            Cursor res_email = myDB.getEmail(input_email);
            res_email.moveToNext();
            if (res_email.getCount() == 0) {
                Toast.makeText(MainActivity.this, "Login failed: Email or Password is incorrect", Toast.LENGTH_LONG).show();
                return;
            }

            Cursor res_pass = myDB.getPass(input_pass);
            res_pass.moveToNext();
            if (res_pass.getCount() == 0) {
                Toast.makeText(MainActivity.this, "Login failed: Email or Password is incorrect", Toast.LENGTH_LONG).show();
                return;
            }

            StringBuffer email_buffer = new StringBuffer();
            StringBuffer pass_buffer = new StringBuffer();

            email_buffer.append(res_email.getString(1));
            String registered_email = email_buffer.toString();


            pass_buffer.append(res_pass.getString(2));
            String registered_pass = pass_buffer.toString();

            if (input_email.equals(registered_email) && input_pass.equals(registered_pass)) {
                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
            }
            }
        });
    }

    public void getAccount() {
        String input_email = inputEmail.getText().toString();
        String input_pass = inputPass.getText().toString();

        Cursor res_email = myDB.getEmail(input_email);
        res_email.moveToNext();
        if (res_email.getCount() == 0) {
            return;
        }

        Cursor res_pass = myDB.getPass(input_pass);
        res_pass.moveToNext();
        if (res_pass.getCount() == 0) {
            return;
        }

        StringBuffer email_buffer = new StringBuffer();
        StringBuffer pass_buffer = new StringBuffer();

        email_buffer.append(res_email.getString(1));
        registered_email = email_buffer.toString();

        pass_buffer.append(res_pass.getString(2));
        registered_pass = pass_buffer.toString();
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

