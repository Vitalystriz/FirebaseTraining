package com.example.firebasetraining;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.integrity.p;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPost extends AppCompatActivity implements View.OnClickListener {
    EditText etTitle,etBody;
    Button btnSubmit;
    FirebaseAuth mAuth;
    DatabaseReference PostRef;
    Post p;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        etTitle = findViewById(R.id.etTitle);
        etBody = findViewById(R.id.etBody);
        btnSubmit = findViewById(R.id.btnSave);
        btnSubmit.setOnClickListener(this);
        mAuth= FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View v) {

        // Create a new Post instance
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String title = etTitle.getText().toString(); // Replace with the actual title
        String body = etBody.getText().toString();   // Replace with the actual body
        int likes = 0;               // Initialize likes to 0
        String key = null;           // Firebase will generate the key

        Post p = new Post();
        p.uid = uid;
        p.body = body;
        p.title = title;
        p.key = key;
        p.likes = likes;



        // Get a reference to the Firebase database
        DatabaseReference postsRef = FirebaseDatabase.getInstance().getReference("posts");

        // Push the new post to Firebase, which generates a unique key
        DatabaseReference newPostRef = postsRef.push();

        // Set the value of the new post using the Post object
        newPostRef.setValue(p).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Post saved successfully
                    // You can add any additional logic here
                } else {
                    // Handle the error
                }
            }
        });
        finish();




    }
}