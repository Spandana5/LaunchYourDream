package com.example.spand.launchyourdream;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private Button LogIn;

    private ProgressDialog progressBar;

    private FirebaseAuth mAuth;

    private Button SignUp;

    private EditText UserName,UserEmail,UserPassword, UserCPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        progressBar = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();

        UserName = (EditText)findViewById(R.id.usernamef);
        UserEmail = (EditText)findViewById(R.id.emailf);
        UserPassword = (EditText)findViewById(R.id.passwordf);
        UserCPassword = (EditText)findViewById(R.id.cpasswordf);
        SignUp = (Button)findViewById(R.id.btnSignUp);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewAccount();
            }
        });

        LogIn=findViewById(R.id.btnLogIn);
        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToActivityFour();
            }
        });
    }

    private void CreateNewAccount() {

        String name = UserName.getText().toString();
        String email = UserEmail.getText().toString();
        String password = UserPassword.getText().toString();
        String cpassword = UserCPassword.getText().toString();

        if(TextUtils.isEmpty(name)) {
            Toast.makeText(this,"Enter the name",Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(email)) {
            Toast.makeText(this,"Enter email",Toast.LENGTH_LONG).show();

        }
        else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this,"Enter password",Toast.LENGTH_LONG).show();
        }
        else if(!password.equals(cpassword)) {
            Toast.makeText(this,"Passwords does not match",Toast.LENGTH_LONG).show();
        }
        else {
            progressBar.setTitle("Signing Up");
            progressBar.setMessage("Please wait while the account is being created");
            progressBar.show();
            progressBar.setCanceledOnTouchOutside(true);
            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()) {

                                SendUserToActivity();
                                Toast.makeText(SignUpActivity.this,"Sign Up successful",Toast.LENGTH_LONG).show();
                                progressBar.dismiss();
                            }
                            else {
                                String message = task.getException().getMessage();
                                Toast.makeText(SignUpActivity.this,"Error occured"+ message,Toast.LENGTH_LONG).show();
                                progressBar.dismiss();
                            }

                        }
                    });
        }
    }

    private void SendUserToActivity() {
        Intent sigintent = new Intent(SignUpActivity.this,DrawerActivity.class);
        sigintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(sigintent);
        finish();
    }


    private void moveToActivityFour() {
        Intent ina = new Intent(SignUpActivity.this,LogInActivity.class);
        startActivity(ina);

    }
}

