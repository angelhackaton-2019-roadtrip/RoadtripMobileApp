package com.tama.roadtrip;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final String TAG = "ListAdapter";

    private ArrayList<ListItem> mListItems = new ArrayList<>();
    private ListActivity mListActivity;

    public ListAdapter(ListActivity mListActivity) {
        this.mListActivity = mListActivity;
    }

    private static class ListHolder extends RecyclerView.ViewHolder{
        View mView;
        TextView mLocationTextView;

        public ListHolder(View view) {
            super(view);

            mView = view;
            mLocationTextView = view.findViewById(R.id.location_text);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int postion) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_list, viewGroup, false);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        final ListHolder listHolder = (ListHolder) viewHolder;

        listHolder.mLocationTextView.setText(mListItems.get(position).mLocation);

        //누르면 세부 정보 창으로 넘어가기
        listHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                intent.putExtra("location",mListItems.get(position).mLocation);
                mListActivity.setResult(Activity.RESULT_OK,intent);
                mListActivity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }

    void add(ListItem listItem){
        mListItems.add(listItem);
    }
}
