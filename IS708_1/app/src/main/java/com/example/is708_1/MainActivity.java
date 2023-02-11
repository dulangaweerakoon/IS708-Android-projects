package com.example.is708_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button loginBtn;
    private EditText username;
    private EditText pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.editTextTextPersonName);
        pass = findViewById(R.id.editTextTextPassword2);
        loginBtn = findViewById(R.id.button4);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userval = username.getText().toString();
                String passval = pass.getText().toString();

                Toast.makeText(getApplicationContext(),userval,Toast.LENGTH_LONG);

                Intent newIntent = new Intent(getApplicationContext(),HomeActivity.class);
                newIntent.putExtra("username",userval);

                startActivity(newIntent);
            }
        });
    }
}