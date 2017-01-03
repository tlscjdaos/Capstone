package org.androidtown.asynctaskex;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int mValue;
    TextView textView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id);
    }

    class UploadTast extends AsyncTask<Integer, Integer, Integer>
    {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mValue = 0;
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setTitle("updateing");
            progressDialog.setMessage("업데이트 중 입니다");
            progressDialog.setCancelable(false);
            progressDialog.setProgress(0);
            progressDialog.setButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    cancel(true);
                }
            });
            progressDialog.show();
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            while (isCancelled() == false)
            {
                mValue++;
                if(mValue <= 100)
                {
                    publishProgress(mValue);
                }else
                    break;
                try {
                    Thread.sleep(70);
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            return mValue;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
            textView.setText("progress value" + values[0]);
        }
    }
}
