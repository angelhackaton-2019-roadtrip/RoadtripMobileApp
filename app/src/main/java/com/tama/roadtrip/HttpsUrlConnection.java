package com.tama.roadtrip;

import android.location.LocationManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpsUrlConnection {
    private final static String TAG = "HttpUrlConnection";

    private URL mUrl;
    private HttpURLConnection mUrlConn = null;

    public String request(String method, String url, String subString){
        try{
            if(method.equals("GET")){
                mUrl = new URL(url + "?" + subString);
                mUrlConn = (HttpURLConnection) mUrl.openConnection();

                Log.d(TAG,"1");
            } else {
                mUrl = new URL(url);
                mUrlConn = (HttpURLConnection) mUrl.openConnection();

                // urlConn 설정하기(POST)
                mUrlConn.setDoOutput(true);

                //내보내기
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(mUrlConn.getOutputStream()));
                writer.write(subString);
                writer.flush();
                writer.close();

                Log.d(TAG,"2");
            }

            Log.d(TAG,"3");

            //가져오기
            BufferedReader reader = new BufferedReader(new InputStreamReader(mUrlConn.getInputStream()));

            String line;
            String sumString = "";

            while ((line = reader.readLine()) != null)
                sumString += line;

            Log.d(TAG,"4");

            Log.d(TAG, sumString);

            return sumString;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (mUrlConn != null)
                mUrlConn.disconnect();
        }

        return null;
    }
}
