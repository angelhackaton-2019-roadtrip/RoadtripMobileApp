package com.tama.roadtrip;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class FragmentDriver extends Fragment {
    private final static String TAG = "FragmentDriver";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_driver,null);

        final TextView departureText = view.findViewById(R.id.departure_text);
        final TextView arrivalText = view.findViewById(R.id.arrival_text);
        final TextView timeText = view.findViewById(R.id.time_text);
        final EditText editText = view.findViewById(R.id.price_text);
        Button button = view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                NetworkTask.CallBack callBack = new NetworkTask.CallBack() {
                    @Override
                    public void callBack(String result) {
                        Log.d(TAG,result);
                        Toast.makeText(v.getContext(),"등록이 완료되었습니다.",Toast.LENGTH_SHORT).show();
                    }
                };

                NetworkTask networkTask = new NetworkTask("POST",
                        "https://roadtrip.esinx.net/api/ride",
                        "authkey=4feb6300873c39b547089ebd22b9af2fe1f3f758986128ca63cae9a3a68b2a93"
                                + "&departure=" + departureText.getText()
                                + "&arrival=" + arrivalText.getText()
                                + "&date=" + getISO8601StringForDate(new Date())
                                + "&cost=" + editText.getText()
                                + "&tags=" + "조용한,남성",
                        callBack);

                networkTask.execute();
            }
        });


        return view;
    }

    private String getISO8601StringForDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.KOREA);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(date);
    }


}