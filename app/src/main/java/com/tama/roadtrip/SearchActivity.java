package com.tama.roadtrip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class SearchActivity extends Activity {
    private final static String TAG = "SearchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent = getIntent();

        String departure = intent.getExtras().getString("departure");
        String arrival = intent.getExtras().getString("arrival");
        String time = intent.getExtras().getString("time");

        NetworkTask.CallBack callBack = new NetworkTask.CallBack() {
            @Override
            public void callBack(String result) {
                Log.d(TAG, result);

                RecyclerView recyclerView = findViewById(R.id.search_recycler);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
                recyclerView.setLayoutManager(linearLayoutManager);

                SearchAdapter searchAdapter = new SearchAdapter();

                try {
                    JSONArray jsonArray = new JSONArray(result);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

//                        SimpleDateFormat format = new SimpleDateFormat("MM-dd");
//                        Date date = format.parse(jsonObject.getString("date"));
//                        Log.d(TAG, date.toString());

                        searchAdapter.add(new SearchItem("" + jsonObject.get("date"),
                                "" + jsonObject.get("cost"),
                                "" + jsonObject.get("userID"),
                                "" + jsonObject.get("rideID")));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                recyclerView.setAdapter(searchAdapter);
            }
        };

        NetworkTask networkTask = new NetworkTask("GET",
                "https://roadtrip.esinx.net/api/ride",
                "departure=" + departure + "&arrival=" + arrival,
                callBack);

        networkTask.execute();
    }
}
