package com.example.firebasetraining;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PostAdadpter extends ArrayAdapter<Post> {
    Context context;
    List<Post> objects;

    public PostAdadpter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Post> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        @SuppressLint("ViewHolder") View view  = layoutInflater.inflate(R.layout.custom_layout,parent, false);
        TextView likes = (TextView) view.findViewById(R.id.tvLikes);
        TextView title = (TextView) view.findViewById(R.id.tvTitle);
        Post p = objects.get(position);
        likes.setText("9");
        title.setText(p.body);
        return super.getView(position, convertView,parent);
    }
}
