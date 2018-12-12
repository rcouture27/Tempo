package com.example.ryan.temporaryname;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Login_DatabaseHelper myDB;
    TextInputEditText inputEmail;
    TextInputEditText inputPass;
    Button btn_login;
    Button btn_sign_up;
    String registered_pass;
    String registered_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {
            myDB = new Login_DatabaseHelper(this);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //replaces the default 'Back' button action
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            KeyEvent.changeAction(event, 0);
        }
        return true;
    }

    public void sign_up() {
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input_email = inputEmail.getText().toString();
                String input_pass = inputPass.getText().toString();

                if (input_pass.isEmpty() || input_email.isEmpty()) {
                    if (input_email.isEmpty() && input_pass.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Email and password is required", Toast.LENGTH_LONG).show();
                    } else if (input_email.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Email is required", Toast.LENGTH_LONG).show();
                    } else if (input_pass.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Password is required", Toast.LENGTH_LONG).show();
                    }
                    return;
                } else {
                    getAccount();
                }

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
                    Intent myIntent = new Intent(getApplicationContext(), activity_dashboard.class);
                    //myIntent.putExtra("key", value); //Optional parameters //to pass data to the next activity
                    startActivity(myIntent);
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

} // end class main activity

