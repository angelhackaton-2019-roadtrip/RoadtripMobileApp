package com.tama.roadtrip;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FragmentPassenger extends Fragment {

    private TextView mArrivalText;
    private TextView mDepartureText;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                mDepartureText.setText(data.getExtras().getString("location"));
                break;
            case 2:
                mArrivalText.setText(data.getExtras().getString("location"));
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_passenger,null);

        LinearLayout departureLayout = view.findViewById(R.id.passenger_departure);
        LinearLayout arrivalLayout = view.findViewById(R.id.passenger_arrival);

        mDepartureText = view.findViewById(R.id.departure_text);
        mArrivalText = view.findViewById(R.id.arrival_text);
        Button button = view.findViewById(R.id.button);

        departureLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        arrivalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListActivity.class);
                startActivityForResult(intent, 2);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SearchActivity.class);

                intent.putExtra("departure", mDepartureText.getText());
                intent.putExtra("arrival", mArrivalText.getText());

                startActivity(intent);
            }
        });

        return view;
    }
}
