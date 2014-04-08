package com.example.filedownloader;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class TaskFragment extends Fragment {

	private static final String TAG = TaskFragment.class.getSimpleName();

	public static interface TaskCallbacks {
		void onPreExecute();

		void onProgressUpdate(int percent);

		void onCancelled();

		void onPostExecute();
	}

	private TaskCallbacks mCallbacks;
	private DownloadFileAsync mTask;
	private FileOutputStream output;

	// ProgressBar progressBar;

	@Override
	public void onAttach(Activity activity) {
		Log.d(TAG, "onAttach");
		super.onAttach(activity);
		mCallbacks = (TaskCallbacks) activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");

		// Retain this fragment across configuration changes.
		setRetainInstance(true);
		String url = "http://oi39.tinypic.com/21oydxs.jpg";

		Context fileContext = getActivity().getApplicationContext();
		// Create and execute the background task.
		mTask = new DownloadFileAsync(fileContext);
		mTask.execute(url);
	}

	@Override
	public void onDetach() {
		Log.d(TAG, "onDetach");
		super.onDetach();
		mCallbacks = null;
	}

	class DownloadFileAsync extends AsyncTask<String, String, String> {
		Context fileContext;

		public DownloadFileAsync(Context fileContext) {
			this.fileContext = fileContext;

		}

		@Override
		protected void onPreExecute() {
			if (mCallbacks != null) {
				mCallbacks.onPreExecute();
			}

		}

		@Override
		protected String doInBackground(String... aurl) {
			int count;

			try {

				URL url = new URL(aurl[0]);
				URLConnection conexion = url.openConnection();
				conexion.connect();

				int lenghtOfFile = conexion.getContentLength();

				InputStream input = new BufferedInputStream(url.openStream());

				output = fileContext.openFileOutput("d.jpg",
						Context.MODE_PRIVATE);

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
			if (mCallbacks != null) {
				mCallbacks.onProgressUpdate(Integer.parseInt(progress[0]));
			}
		}

		@Override
		protected void onCancelled() {
			if (mCallbacks != null) {
				mCallbacks.onCancelled();
			}
		}

		@Override
		protected void onPostExecute(String unused) {
			if (mCallbacks != null) {
				mCallbacks.onPostExecute();
			}

		}
	}
}
