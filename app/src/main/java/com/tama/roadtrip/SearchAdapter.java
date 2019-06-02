package com.tama.roadtrip;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final String TAG = "SearchAdapter";

    private ArrayList<SearchItem> mSearchItems = new ArrayList<>();

    private static class SearchHolder extends RecyclerView.ViewHolder{
        View mView;

        TextView mClockTextView;
        TextView mPriceTextView;


        public SearchHolder(View view) {
            super(view);

            mView = view;

            mClockTextView = view.findViewById(R.id.clock_text);
            mPriceTextView = view.findViewById(R.id.price_text);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int postion) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_search, viewGroup, false);
        return new SearchHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        final SearchHolder searchHolder = (SearchHolder) viewHolder;

        searchHolder.mClockTextView.setText(mSearchItems.get(position).getClock());
        searchHolder.mPriceTextView.setText(mSearchItems.get(position).getPrice());

        //누르면 세부 정보 창으로 넘어가기
        searchHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                NetworkTask.CallBack callBack = new NetworkTask.CallBack() {
                    @Override
                    public void callBack(String result) {
                        Intent intent = new Intent(searchHolder.mView.getContext(), DetailActivity.class);

                        try {
                            Log.d(TAG, result);

                            JSONObject jsonObject = new JSONObject(result);
                            JSONObject driver = jsonObject.getJSONObject("driver");

                            String name = jsonObject.get("name") + "";

                            Log.d(TAG, "name: " + name);
                            String plateNumber = driver.get("platenumber") + "";
                            String vehicle = driver.get("vehicle") + "";
                            String bio = jsonObject.get("bio") + "";


                            intent.putExtra("name", name);
                            intent.putExtra("plateNumber", plateNumber);
                            intent.putExtra("vehicle", vehicle);
                            intent.putExtra("bio", bio);
                            intent.putExtra("rideID", mSearchItems.get(position).getmRideID());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        v.getContext().startActivity(intent);
                    }
                };

                NetworkTask networkTask = new NetworkTask("GET",
                        "https://roadtrip.esinx.net/api/accounts/" + mSearchItems.get(position).getUserID(),
                        "",
                        callBack);

                networkTask.execute();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSearchItems.size();
    }

    void add(SearchItem searchItem){
        mSearchItems.add(searchItem);
    }
}
