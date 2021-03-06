

package com.example.taskaway;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Displays tasks of other users. Current user can bid on these tasks.
 * Sources used:
 *      https://developer.android.com/reference/android/support/v7/widget/RecyclerView.html
 *      https://www.youtube.com/watch?v=oBhgyiBVd3k <- More examples/explanation
 *
 * @author Katherine Mae Patenio
 *
 * @see MainActivity
 */

public class MyAssigned extends Fragment {
    View rootView;
    private RecyclerView myrecyclerview;
    //private ArrayList<Task> lstTask;
    private static TaskList lstTask;
    private String user_name;
    private String user_id;

    private ImageView unavailableIcon;

    /**
     * Constructor of MyBids.
     */
    public MyAssigned() {
    }

    /**
     * Creates RecyclerView by setting layout and adapter.
     * @param inflater - instance of LayoutInflater
     * @param container - instance of ViewGroup
     * @param savedInstanceState - saved state
     * @return inflater
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.myassigned_layout, container, false);

        myrecyclerview = (RecyclerView) rootView.findViewById(R.id.myassigned_recyclerview);

        MyAssignedListViewAdapter recycleAdapter = new  MyAssignedListViewAdapter(getContext(), lstTask, user_name, user_id);

        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recycleAdapter);

        return rootView;

    }

    /**
     * Displays all tasks of other uses. Also receives username and user id from MainActivity.
     * @param savedInstanceState - saved state
     *
     *
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // GET USERNAME AND ID FROM LOGIN - TO BE PASSED TO SOME ACTIVITIES
        if (getArguments() != null){
            Log.i("My Assigned","getArguments NOT null!");
            //Bundle bundle = new Bundle();
            user_name = getArguments().getString("username");
            Log.i("My Assigned",getArguments().getString("username")+"");
            user_id = getArguments().getString("userid");
            Log.i("My Assigned", getArguments().getString("userid")+"");
        }
        else{
            Log.i("My Assigned","getArguments is null!");
        }
        updateList();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();

        updateList();
    }

    /**
     * Updates the tasklist on the server
     * @author Adrian Schuldhaus
     */

    public void updateList() {
        if (MainActivity.isOnline()){
            User user = ServerWrapper.getUserFromId(user_id);
            Log.i("MyAssigned", "user_id:" + user_id);
            if (user != null){
                lstTask = user.getAssignedTasks();
            }else{
                lstTask = new TaskList();
                Toast.makeText(getContext(), "Something went wrong fetching assigned jobs from server", Toast.LENGTH_SHORT).show();
            }
        }else{
            lstTask = new TaskList();

            View view = getView();
            if (view != null){
                unavailableIcon = view.findViewById(R.id.unavailableIcon);
                unavailableIcon.setVisibility(View.VISIBLE);
            }
        }
    }
}