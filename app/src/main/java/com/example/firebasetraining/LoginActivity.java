package com.example.firebasetraining;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etEmail, etPassword;
    Button submit;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        submit = (Button) findViewById(R.id.btnSubmit);
        submit.setOnClickListener(this);
        mAuth= FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        String email = etEmail.getText().toString();
        String password = etEmail.getText().toString();
        mAuth.signInWithEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Successfully registered", Toast.LENGTH_LONG).show();
                    finish();

                } else {
                    Toast.makeText(LoginActivity.this, "Registration Error", Toast.LENGTH_LONG).show();

                }


            }

        });
    }
}