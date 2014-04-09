package com.mhealth.testserverconnection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.mhealth.testserverconnection.R;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Initalize controls of different layout items
		final TextView fileView   = (TextView) findViewById(R.id.editText_file);
		final TextView statusView = (TextView) findViewById(R.id.textView_status);
		Button   saveButton     = (Button)   findViewById(R.id.button_save);
		Button updateButton     = (Button)   findViewById(R.id.button_update);
		
		// Define the name of the file
		final String fileName = getString(R.string.output_filename);
					
		// Event listener for the save button
		saveButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				// Test writing and saving a file
				String fileContent = fileView.getText().toString();
				
				try {
				    FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
				    fos.write(fileContent.getBytes());
				    fos.close();
				    statusView.setText("Created the file.");
				} catch (Exception e) {
				    e.printStackTrace();
				}
				
			}
		});
		
		// Event listener for the update button 
		updateButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Test reading and logging a file
				BufferedReader reader;
				try {
					reader = new BufferedReader(new InputStreamReader(openFileInput(fileName)));
				    StringBuilder sb = new StringBuilder();
				    String line = null;
					while ((line = reader.readLine()) != null) {
						  sb.append(line).append("\n");
					}
				    reader.close();
				    fileView.setText(sb.toString());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
				
				//DataStreamToServer serverConnection = new DataStreamToServer();
				//serverConnection.execute();
			}
		});
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/* Perform network connection in an AsyncTask */
	public class DataStreamToServer extends AsyncTask<String, Integer, HttpResponse> {

		@Override
		protected HttpResponse doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			/*	
			// Get user input
			
			
			EditText blank_acceleration_x = (EditText) findViewById(R.id.editText_acceleration_x);
			EditText blank_acceleration_y = (EditText) findViewById(R.id.editText_acceleration_y);
			EditText blank_acceleration_z = (EditText) findViewById(R.id.editText_acceleration_z);
			String acceleration_x = blank_acceleration_x.getText().toString();
			String acceleration_y = blank_acceleration_y.getText().toString();
			String acceleration_z = blank_acceleration_z.getText().toString();
			
			// Call the PHP script on the server 
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost          httpPost   = new HttpPost("http://mhealth1.cse.nd.edu/tests/AndroidInsertData.php");
		
			// Post the input data 
			try {
				ArrayList<NameValuePair> person = new ArrayList<NameValuePair>();
				person.add(new BasicNameValuePair("AccelerationX", acceleration_x));
				person.add(new BasicNameValuePair("AccelerationY", acceleration_y));
				person.add(new BasicNameValuePair("AccelerationZ", acceleration_z));
				
				httpPost.setEntity(new UrlEncodedFormEntity(person));
				HttpResponse httpResponse = httpClient.execute(httpPost);
				return httpResponse;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			*/
			return null;
		}
		
	}

}
