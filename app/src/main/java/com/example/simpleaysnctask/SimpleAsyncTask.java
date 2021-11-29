package com.example.simpleaysnctask;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Void, String> {
    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> mProgressBar;

    SimpleAsyncTask(TextView tv, ProgressBar pb) {
        mTextView = new WeakReference<>(tv);
        mProgressBar = new WeakReference<>(pb);
    }


    @Override
    protected String doInBackground(Void... voids) {

        Random r = new Random();
        int n = r.nextInt(11);
        int s = n * 200;
        mProgressBar.get().setMax(s);
        try {
            for(int i =1; i<=100;i++) {
                Thread.sleep((s / 100));
                onProgressUpdate((s/100)*i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Awake at last sleeping for " + s + " milliseconds!";
    }

    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }
    protected void onProgressUpdate(Integer... progress) {
        mProgressBar.get().setProgress(progress[0]);
    }
}

