package com.example.firebasetraining;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    FirebaseAuth mAuth;
    Button btnRegister, btnLogin,btnLogout,btnAddPost,btnAllPost;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogout = findViewById(R.id.btnLogout);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);
        btnAddPost = findViewById(R.id.btnAddPost);
        btnAllPost = findViewById(R.id.btnAllPost);
        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        btnLogout.setOnClickListener(this);
        btnAddPost.setOnClickListener(this);
        btnAllPost.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume ", Toast.LENGTH_LONG).show();
        this.showRightButtons();

    }
    public void showRightButtons(){
        if(mAuth.getCurrentUser()!=null){
            btnLogin.setVisibility(View.INVISIBLE);
            btnRegister.setVisibility(View.INVISIBLE);
            btnLogout.setVisibility(View.VISIBLE);
        }
        else{
            btnLogin.setVisibility(View.VISIBLE);
            btnRegister.setVisibility(View.VISIBLE);
            btnLogout.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        if(view ==btnAddPost){
            Intent intent = new Intent(this, AddPost.class);
            startActivity(intent);
        }
        if(view ==btnAllPost){
            Intent intent = new Intent(this, AllPosts.class);
            startActivity(intent);
        }
        if(view==btnRegister){
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);

        }
        else if(btnLogout==view){
            mAuth.signOut();
            this.showRightButtons();
        }
        else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        }


    }

}