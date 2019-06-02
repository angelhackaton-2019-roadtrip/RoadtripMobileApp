package com.tama.roadtrip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends Activity {
    private static final String TAG = "DetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView nameText = findViewById(R.id.name_text);
        TextView plateNumberText = findViewById(R.id.plat_number_text);
        TextView vehicleText = findViewById(R.id.vehicle_text);
        TextView bioText = findViewById(R.id.bio_text);
        Button selectButton = findViewById(R.id.select_button);

        final Intent intent = getIntent();

        nameText.setText(intent.getExtras().getString("name"));
        plateNumberText.setText(intent.getExtras().getString("plateNumber"));
        vehicleText.setText(intent.getExtras().getString("vehicle"));
        bioText.setText(intent.getExtras().getString("bio"));

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NetworkTask.CallBack callBack = new NetworkTask.CallBack() {
                    @Override
                    public void callBack(String result) {
                        finish();
                    }
                };

                NetworkTask networkTask = new NetworkTask("POST",
                        "https://roadtrip.esinx.net/api/rides/" + intent.getExtras().getString("rideID") +"/join",
                        "authkey = 4feb6300873c39b547089ebd22b9af2fe1f3f758986128ca63cae9a3a68b2a93",
                        callBack);

                networkTask.execute();
            }
        });
    }
}
