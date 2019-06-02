package com.tama.roadtrip;

import android.os.AsyncTask;

public class NetworkTask extends AsyncTask<Void,Void,String> {
    private String mMethod;
    private String mUrl;
    private String mSubString;
    private CallBack mCallBack;

    public interface CallBack {
        void callBack(String result);
    }

    public NetworkTask(String method, String url, String subString, CallBack callBack) {
        this.mMethod = method;
        this.mUrl = url;
        this.mSubString = subString;
        this.mCallBack = callBack;
    }

    @Override
    protected String doInBackground(Void... voids) {
        HttpsUrlConnection urlConnection = new HttpsUrlConnection();
        return urlConnection.request(mMethod, mUrl, mSubString);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        mCallBack.callBack(result);
    }
}
