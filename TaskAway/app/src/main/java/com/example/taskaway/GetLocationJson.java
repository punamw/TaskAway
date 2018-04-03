package com.example.taskaway;

import android.content.Intent;
import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

/**
 * This class enables us to get a json object representation of our location
 *
 * Source: https://stackoverflow.com/questions/4567216/geocoder-getfromlocationname-returns-only-null/4950178#4950178
 * Accessed on: April 3, 2018
 * @author Diane Boytang
 */

public class GetLocationJson extends AsyncTask <String, Void, String>{



    @Override
    protected String doInBackground(String... strings) {


        String result = "";
        URL url;
        HttpURLConnection urlConnection;
        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream is = urlConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is);

            int data = inputStreamReader.read();
            while(data != -1){
                char curr = (char) data;
                result += curr;
                data = inputStreamReader.read();
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
       // GoogleMap locationMap = new GoogleMap();
        final int request_code = 1;



        if(result != null) {
            try {
                JSONObject locationObject = new JSONObject(result);
                JSONObject locationGeo = locationObject.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
                Intent i = new Intent();




            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }


}
