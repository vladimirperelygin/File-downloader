package com.example.filedownloader;

import android.os.AsyncTask;

abstract class DownloadFileAsync extends AsyncTask<String, String, String> {
	/*
	 * private FileOutputStream output; // for internal storage ProgressBar
	 * progressBar; Context fileContext;
	 * 
	 * @Override final protected String doInBackground(String... aurl) { int
	 * count;
	 * 
	 * try {
	 * 
	 * URL url = new URL(aurl[0]); URLConnection conexion =
	 * url.openConnection(); conexion.connect();
	 * 
	 * int lenghtOfFile = conexion.getContentLength(); Log.d("ANDRO_ASYNC",
	 * "Lenght of file: " + lenghtOfFile);
	 * 
	 * InputStream input = new BufferedInputStream(url.openStream());
	 * 
	 * output = fileContext.openFileOutput("d.jpg", Context.MODE_PRIVATE);
	 * 
	 * byte data[] = new byte[1024];
	 * 
	 * long total = 0;
	 * 
	 * while ((count = input.read(data)) != -1) { total += count;
	 * publishProgress("" + (int) ((total * 100) / lenghtOfFile));
	 * output.write(data, 0, count); }
	 * 
	 * output.flush(); output.close(); input.close(); } catch (Exception e) { }
	 * return null; }
	 * 
	 * @Override protected void onPreExecute() { super.onPreExecute(); }
	 * 
	 * protected void onProgressUpdate(String... progress) {
	 * Log.d("ANDRO_ASYNC", progress[0]);
	 * progressBar.setProgress(Integer.parseInt(progress[0])); }
	 * 
	 * @Override protected void onPostExecute(String unused) {
	 * 
	 * int count1; progressBar.setProgress(100); try { byte data[] = new
	 * byte[1024];
	 * 
	 * FileInputStream fs = fileContext.openFileInput("d.jpg"); FileOutputStream
	 * fs2 = new FileOutputStream( "/sdcard/DCIM/Camera/f.jpg");
	 * 
	 * while ((count1 = fs.read(data)) != -1) {
	 * 
	 * fs2.write(data, 0, count1); } fs.close(); fs2.close(); fs2.flush();
	 * 
	 * } catch (IOException e) {
	 * 
	 * }
	 * 
	 * Intent intent = new Intent();
	 * intent.setAction(android.content.Intent.ACTION_VIEW); File file = new
	 * File("/sdcard/DCIM/Camera/f.jpg");
	 * intent.setDataAndType(Uri.fromFile(file), "image/*"); //
	 * startActivity(intent);
	 * 
	 * }
	 */
}
