package com.example.asynctaskmyattempt;

import android.support.v7.app.ActionBarActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	EditText etSleeptime;
	Button btnSleep;
	TextView tvSleptTime;
	ProgressBar pbWait;
	RelativeLayout rlAsyncTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		etSleeptime = (EditText) findViewById(R.id.etSleepTime);
		btnSleep = (Button) findViewById(R.id.btnSleep);
		tvSleptTime = (TextView) findViewById(R.id.tvSleptTime);
		pbWait = (ProgressBar) findViewById(R.id.pbWait);
		rlAsyncTask = (RelativeLayout) findViewById(R.id.rlAsyncTask);
		btnSleep.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String sleepTime = etSleeptime.getText().toString();
				AsyncTaskRunner runner = new AsyncTaskRunner();
				runner.execute(sleepTime);

			}
		});
	}

	class AsyncTaskRunner extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pbWait.setVisibility(View.VISIBLE);
			rlAsyncTask.setVisibility(View.GONE);
		}

		@Override
		protected String doInBackground(String... params) {

			String response = null;
			// TODO Auto-generated method stub
			int sleepTime = Integer.parseInt(params[0]);
			try {
				Thread.sleep(sleepTime);
				response = "Slept for " + sleepTime + " milliseconds";
			} catch (Exception e) {
				// TODO: handle exception
			}

			return response;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pbWait.setVisibility(View.GONE);
			rlAsyncTask.setVisibility(View.VISIBLE);
			tvSleptTime.setText(result);
		}

	}

}
