package com.example.taskaway;


import android.content.Context;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Adapter used by all the Activity classes that use grid views
 * @authors Created by KatherineMae, Edited by Sameerah Wajahat
 * @see UploadPic
 * @see UploadPicEdit
 * @see PhotosViewOwnTask
 */

public class PicturesImageAdapter extends BaseAdapter {
    private Context mContext;
    ArrayList<Bitmap> arrayU;

    // Constructor
    public PicturesImageAdapter(Context c, ArrayList<Bitmap> a) {
        mContext = c;
        arrayU = a;
    }

    public int getCount() {
        return arrayU.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(5, 5, 5, 5);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageBitmap(arrayU.get(position));
        return imageView;
    }

    /**
     * The inflaters for images (upon clicking the grid item) uses this to
     *
     * @param position
     * @param convertView
     * @param parent
     * @authors Punam Woosaree and Sameerah Wajahat
     * @return imageView
     */

    public View getSingleView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(500, 500));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(3, 3, 3, 3);
        } else {
            imageView = (ImageView) convertView;
        }

        return imageView;
    }

}

