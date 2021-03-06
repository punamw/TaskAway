/*
 * Copyright (c) 2018 Team X, CMPUT301. University of Alberta - All rights reserved.
 * You may use distribute and modify this code under terms and conditions of Code of Student Behavior at
 * University of Alberta
 * You can find a copy of this license in this project. Otherwise please contact contact@abc.ca
 * /
 */

package com.example.taskaway;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Contains the gridview that displays the images of associated with every kind of task
 *
 * @author Created by Sameerah Wajahat
 * Created on 2018-04-08.
 *
 * @see ViewOwnTask
 * @see ViewBidTask
 * @see ViewTask
 */

public class PhotosViewOwnTask extends AppCompatActivity {

    ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
    //ArrayList<byte[]> barray = new ArrayList<byte[]>();
    private String userID;
    private String user_name;
    private ImageButton back;
    private ImageButton mark;
    private TextView title;
    private Task task;

    /**
     * Gets the pictures associated with the task and displays them in a gridView
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos_view_own_task);

        back = (ImageButton)findViewById(R.id.toolbar_back_btn);
        back.setOnClickListener(new View.OnClickListener() {

            /**
             * When the user presses the back button, go back to viewing the task.
             * Go back to appropriate view depending on which one called this activity
             *
             * @param v - instance of View
             */
            @Override
            public void onClick(View v) {

                Intent intentGet = getIntent();
                user_name = intentGet.getStringExtra("userName");
                userID = intentGet.getStringExtra("userid");
                task = (Task) intentGet.getSerializableExtra("task");

                /* CHECK WHAT TYPE OF VIEWTASK CALLED THIS ACTIVITY */
                // VIEWOWNTASK
                if (intentGet.getStringExtra("viewTaskType").equals("ViewOwnTask")){
                    Intent intent = new Intent(PhotosViewOwnTask.this, ViewOwnTask.class);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("userName", user_name);
                    intent.putExtra("userid", userID);
                    intent.putExtra("task",task);
                    startActivity(intent);
                }
                // VIEWTASK/ASSIGNED
                else if (intentGet.getStringExtra("viewTaskType").equals("ViewTask")){
                    Intent intent = new Intent(PhotosViewOwnTask.this, ViewTask.class);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("userName", user_name);
                    intent.putExtra("userid", userID);
                    intent.putExtra("task",task);
                    startActivity(intent);
                }
                // VIEWBIDTASK
                else if (intentGet.getStringExtra("viewTaskType").equals("ViewBidTask")){
                    Intent intent = new Intent(PhotosViewOwnTask.this, ViewBidTask.class);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("userName", user_name);
                    intent.putExtra("userid", userID);
                    intent.putExtra("task",task);
                    startActivity(intent);
                }
            }
        });

        /* SAVE TOOLBAR BUTTON - REMOVE FOR THIS ACTIVITY */
        mark = (ImageButton)findViewById(R.id.toolbar_save_btn);
        mark.setVisibility(View.GONE);

        /* SET TITLE OF TOOLBAR */
        title = (TextView)findViewById(R.id.toolbar_title);
        title.setText("Task Photos");

        Intent intent = getIntent();
        user_name = intent.getStringExtra("userName");
        userID = intent.getStringExtra("userid");
        task = (Task) intent.getSerializableExtra("task");

        //if (intent.getStringArrayListExtra("images") != null) {
        int size = intent.getIntExtra("byteArraySize", 0);
        Log.i("RECEIVE SIZE", "size: "+ size);
        if (intent.getByteArrayExtra("barray0") != null) {
            for (int i = 0; i < size; i++) {
                byte b[] = intent.getByteArrayExtra("barray" + i);
                Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
                bitmaps.add(bitmap);
                Log.i("RECIEVED", "one bitmap" + bitmap);
            }
        }
        final GridView gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(new PicturesImageAdapter(PhotosViewOwnTask.this, bitmaps));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                final View view = LayoutInflater.from(PhotosViewOwnTask.this).inflate(R.layout.imageinflater, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(PhotosViewOwnTask.this);
                builder.setView(view);
                PicturesImageAdapter singlePicAdapter = new PicturesImageAdapter(PhotosViewOwnTask.this, bitmaps);
                final ImageView singlePic = view.findViewById(R.id.singlePicInflate);
                singlePic.setImageBitmap(bitmaps.get(position));
                //singlePic.setImageResource(new PicturesImageAdapter());
                //Sets up cancel option for user
                builder.setNegativeButton("Cancel", null);
                builder.setCancelable(false);

                AlertDialog alert = builder.create();
                alert.show();





            }
        });
    }
}
