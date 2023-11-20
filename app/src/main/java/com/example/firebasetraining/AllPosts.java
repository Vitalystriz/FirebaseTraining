package com.example.firebasetraining;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllPosts extends AppCompatActivity {
    ArrayList<Post> posts;
    PostAdadpter postAdapter;

    ListView lv;
    FirebaseDatabase db;
    DatabaseReference postsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_posts);
        lv  = findViewById(R.id.lv);
        db = FirebaseDatabase.getInstance();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        postsRef = db.getReference("posts");
        retrieveData(false);


    }
    public void retrieveData(boolean filterOnMyPost) {
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
                postAdapter = new PostAdadpter(AllPosts.this,0,0, posts);
                lv.setAdapter(postAdapter);//get view start
                Toast.makeText(AllPosts.this, "oooooooooo", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AllPosts.this, "yui", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
