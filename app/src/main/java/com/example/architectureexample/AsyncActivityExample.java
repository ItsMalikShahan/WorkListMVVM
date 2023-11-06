package com.example.architectureexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class AsyncActivityExample extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_example);
        progressBar = findViewById(R.id.pb_progressBar);

    }

    public void startAsyncTask(View v) {
        ExampleAsyncTask asyncTask = new ExampleAsyncTask(this);
        asyncTask.execute(10);
    }

    private static class ExampleAsyncTask extends AsyncTask<Integer, Integer, String> {

        private WeakReference<AsyncActivityExample> activityExampleWeakReference;

        ExampleAsyncTask(AsyncActivityExample activity) {
            activityExampleWeakReference = new WeakReference<AsyncActivityExample>(activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            AsyncActivityExample activity = activityExampleWeakReference.get();
            if (activity == null){
                return;
            }
            activity.progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            for (int i = 0; i < integers[0]; i++) {
                publishProgress((i * 100) / integers[0]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Finished!";

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            AsyncActivityExample activity = activityExampleWeakReference.get();
            if (activity == null){
                return;
            }
            activity.progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            AsyncActivityExample activity = activityExampleWeakReference.get();
            if (activity == null){
                return;
            }
            Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
            activity.progressBar.setProgress(0);
            activity.progressBar.setVisibility(View.INVISIBLE);
        }


    }
}