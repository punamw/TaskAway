package com.example.taskaway;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by SJIsmail.
 * The following below creates a layout class for the Bids class, working progress... Will be similar to Jobs class
 */

public class MyBids extends Fragment{
    View rootView;

    public MyBids(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.mybids_layout, container, false);


        return rootView;
    }
}
