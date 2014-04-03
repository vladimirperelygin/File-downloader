package com.example.filedownloader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
	public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
	private Button startBtn;
	private FileOutputStream output; // for internal storage
	ProgressBar progressBar;
	ImageView myPicture;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myPicture = (ImageView) findViewById(R.id.imageView1);
		startBtn = (Button) findViewById(R.id.startBtn);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		startBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startDownload();
			}
		});

	}

	private void startDownload() {
		String url = "http://cs619818.vk.me/v619818814/1980/A7dire9jMdM.jpg";
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

				output = openFileOutput("d.jpg", Context.MODE_PRIVATE);

				// //for internal storage
				// OutputStream output = new FileOutputStream(
				// "/sdcard/DCIM/Camera/d.jpg");
				// FileInputStream fs = openFileInput("111q.jpg");
				// OutputStream output1 = new
				// FileOutputStream("/sdcard/DCIM/Camera/c.jpg");

				// OutputStream output1;
				// output1= new FileOutputStream(output.write(oneByte));
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
			int count1;
			progressBar.setProgress(100);
			try {
				byte data[] = new byte[1024];

				long total = 0;
				FileInputStream fs = openFileInput("d.jpg");
				FileOutputStream fs2 = new FileOutputStream(
						"/sdcard/DCIM/Camera/f.jpg");

				while ((count1 = fs.read(data)) != -1) {

					fs2.write(data, 0, count1);
				}
				fs.close();
				fs2.close();
				fs2.flush();

			} catch (IOException e) {

			}

			Intent intent = new Intent();
			intent.setAction(android.content.Intent.ACTION_VIEW);
			File file = new File("/sdcard/DCIM/Camera/f.jpg");
			intent.setDataAndType(Uri.fromFile(file), "image/*");
			startActivity(intent);

		}
	}
}