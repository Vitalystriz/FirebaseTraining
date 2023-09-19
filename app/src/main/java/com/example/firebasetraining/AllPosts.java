package com.example.firebasetraining;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllPosts extends AppCompatActivity {
    ArrayList<Post> posts;
    PostAdadpter postAdadpter;

    ListView lv;
    FirebaseDatabase db;
    DatabaseReference postsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_posts);
        lv  = findViewById(R.id.lv);
        db = FirebaseDatabase.getInstance();
        postRef

    }
    public void retrieveData() {
        // Initialize the ArrayList to store retrieved posts
        posts = new ArrayList<>();

        // Add a ValueEventListener to fetch data from Firebase
        postsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Clear the existing posts list
                posts.clear();

                // Loop through the dataSnapshot to get each post
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    // Deserialize the post object and add it to the ArrayList
                    Post post = postSnapshot.getValue(Post.class);
                    if (post != null) {
                        posts.add(post);
                    }
                }
                // Update the ListView with the retrieved posts
                postAdapter = new PostAdapter(AllPosts.this,0,0, posts);
                listView.setAdapter(postAdapter);//get view start
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle database errors if any
            }
        });
    }
}
