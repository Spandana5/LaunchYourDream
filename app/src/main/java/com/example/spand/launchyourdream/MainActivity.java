package com.example.spand.launchyourdream;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private Button btnSignUp;
    private Button btnLoginIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignUp=findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToActivityTwo();
            }
        });

        btnLoginIn=findViewById(R.id.btnLogIn);
        btnLoginIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToActivityThree();
            }
        });
    }
    private void moveToActivityTwo() {
        Intent in = new Intent(MainActivity.this,SignUpActivity.class);
        startActivity(in);

    }

    private void moveToActivityThree() {
        Intent inn = new Intent(MainActivity.this,LogInActivity.class);
        startActivity(inn);

    }

}
