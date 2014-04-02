package com.example.filedownloader;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.content.ContextWrapper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
	public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
	private Button startBtn;
	private FileOutputStream output; // for internal storage
	ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		startBtn = (Button) findViewById(R.id.startBtn);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		startBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startDownload();
			}
		});

	}

	private void startDownload() {
		String url = "http://www.walls-world.ru/wallpapers/3d/wallpapers_19924_1600x1200.jpg";
		new DownloadFileAsync().execute(url);

	}

	class DownloadFileAsync extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}

		@Override
		protected String doInBackground(String... aurl) {
			int count;

			try {

				URL url = new URL(aurl[0]);
				URLConnection conexion = url.openConnection();
				conexion.connect();

				int lenghtOfFile = conexion.getContentLength();
				Log.d("ANDRO_ASYNC", "Lenght of file: " + lenghtOfFile);
				ContextWrapper cw = new ContextWrapper(getApplicationContext());// for
																				// internal
																				// storage
				InputStream input = new BufferedInputStream(url.openStream());

				// output = openFileOutput("ks.jpg", Context.MODE_PRIVATE);
				// //for internal storage
				OutputStream output = new FileOutputStream("/sdcard/111q.jpg");
				byte data[] = new byte[1024];

				long total = 0;

				while ((count = input.read(data)) != -1) {
					total += count;
					publishProgress("" + (int) ((total * 100) / lenghtOfFile));
					output.write(data, 0, count);
				}

				output.flush();
				output.close();
				input.close();
			} catch (Exception e) {
			}
			return null;

		}

		protected void onProgressUpdate(String... progress) {
			Log.d("ANDRO_ASYNC", progress[0]);
			progressBar.setProgress(Integer.parseInt(progress[0]));
		}

		@Override
		protected void onPostExecute(String unused) {

			progressBar.setProgress(100);
		}
	}
}