package com.tama.roadtrip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        RecyclerView recyclerView = findViewById(R.id.list_recycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        ListAdapter listAdapter = new ListAdapter(this);
        listAdapter.add(new ListItem("서울"));
        listAdapter.add(new ListItem("광주"));
        listAdapter.add(new ListItem("부산"));
        listAdapter.add(new ListItem("대전"));
        listAdapter.add(new ListItem("경기"));

        recyclerView.setAdapter(listAdapter);
    }
}
