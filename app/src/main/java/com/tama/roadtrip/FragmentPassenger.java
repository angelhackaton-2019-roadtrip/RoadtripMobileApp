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
import android.widget.TextView;

public class FragmentPassenger extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_passenger,null);

        final TextView departureText = view.findViewById(R.id.departure_text);
        final TextView arrivalText = view.findViewById(R.id.arrival_text);
        final TextView timeText = view.findViewById(R.id.time_text);
        Button button = view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SearchActivity.class);

                intent.putExtra("departure", departureText.getText());
                intent.putExtra("arrival", arrivalText.getText());
                intent.putExtra("time", timeText.getText());

                startActivity(intent);
            }
        });

        return view;
    }
}
