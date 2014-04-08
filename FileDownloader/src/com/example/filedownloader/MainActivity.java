package com.example.filedownloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends Activity implements
		TaskFragment.TaskCallbacks {
	public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
	private Button startBtn;
	ProgressBar progressBar;
	private TaskFragment mTaskFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		startBtn = (Button) findViewById(R.id.startBtn);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		startBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				FragmentManager fm = getFragmentManager();
				mTaskFragment = (TaskFragment) fm.findFragmentByTag("task");

				if (mTaskFragment == null) {
					mTaskFragment = new TaskFragment();
					fm.beginTransaction().add(mTaskFragment, "task").commit();
				}
			}
		});

	}

	@Override
	public void onPreExecute() {
	}

	@Override
	public void onProgressUpdate(int percent) {

		progressBar.setProgress(percent);
	}

	@Override
	public void onCancelled() {
	}

	@Override
	public void onPostExecute() {
		int count1;
		progressBar.setProgress(100);
		try {
			byte data[] = new byte[1024];

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