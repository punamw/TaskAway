package com.example.taskaway;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Displays tasks user has made/requested.
 * Sources used:
 *      https://developer.android.com/reference/android/support/v7/widget/RecyclerView.html
 *      https://www.youtube.com/watch?v=oBhgyiBVd3k <- More examples/explanation
 *
 * @author Jonathan Ismail
 *
 * @see MainActivity
 * @see TaskListViewAdapter
 * @see SaveFileController
 * @see AddTaskActivity
 */
public class MyJobs extends Fragment {

    View rootView;
    private RecyclerView myrecyclerview;
    //private static ArrayList<Task> lstTask; /* CHANGE LATER */
    private static TaskList lstTask;
    private ArrayList<Bid> blist; /* DELETE LATER - temporarily make an arraylist of bids for TESTING (see MARCH 17 2018 note)*/
    ImageButton imgButton;
    private String user_name;
    private String user_id;

    /**
     * Contructor of MyJobs
     */
    public MyJobs() {
    }
    /**
     * instantiate the ViewPagerAdapter class with the lstTask arraylist
     * OnCreateView adds the "Add Button" to the first Fragment tab.
     *
     */

    /**
     * Creates RecyclerView by setting layout and adapter. Also adds "Add Button" for adding a task.
     * @param inflater - instance of LayoutInflater
     * @param container - instance of ViewGroup
     * @param savedInstanceState - saved state
     * @return inflater
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.myjobs_layout, container, false);



        /**
         * Determines behaviour of Add button. Go to AddActivity. Also pass username and user id to
         * AddActivity.
         *
         * @see AddActivity
         */


        myrecyclerview = (RecyclerView) rootView.findViewById(R.id.jobs_recyclerview);

        TaskListViewAdapter recycleAdapter = new TaskListViewAdapter(getContext(), lstTask, user_name, user_id);

        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recycleAdapter);


        return rootView;

    }


    /**
     * Displays all tasks of current user. Also receives username and user id from MainActivity.
     * @param savedInstanceState - saved state
     *
     * @see SaveFileController
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // GET USERNAME AND ID FROM LOGIN - TO BE PASSED TO SOME ACTIVITIES
        if (getArguments() != null){
            Log.i("My Jobs","getArguments NOT null!");
            //Bundle bundle = new Bundle();
            user_name = getArguments().getString("username");
            Log.i("My Jobs",getArguments().getString("username")+"");
            user_id = getArguments().getString("userid");
            Log.i("My Jobs", getArguments().getString("userid")+"");
        }

        updateList();
    }

    /**
     * Update the list view in real time
     *
     */
    @Override
    public void onResume() {
        super.onResume();
        updateList();
    }

    /**
     * Updates the current list view // TODO: make this an interface instead?
     *
     */
    public void updateList() {
        Log.i("MYJOBS","current user is: "+user_name);
        final Context context = getContext();
        SaveFileController saveFileController = new SaveFileController();
        int userIndex = saveFileController.getUserIndex(context, user_name);
        Log.i("My Jobs","userindex is "+userIndex);
        lstTask = saveFileController.getUserRequiredTasks(context, userIndex);
    }

}
