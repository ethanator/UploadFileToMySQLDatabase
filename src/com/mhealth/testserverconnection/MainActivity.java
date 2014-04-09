package com.mhealth.testserverconnection;

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
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/* Event listener for the update button */
		Button updateButton = (Button) findViewById(R.id.button_update);
		updateButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				DataStreamToServer serverConnection = new DataStreamToServer();
				serverConnection.execute();
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
			
			/* Get user input */
			EditText blank_acceleration_x = (EditText) findViewById(R.id.editText_acceleration_x);
			EditText blank_acceleration_y = (EditText) findViewById(R.id.editText_acceleration_y);
			EditText blank_acceleration_z = (EditText) findViewById(R.id.editText_acceleration_z);
			String acceleration_x = blank_acceleration_x.getText().toString();
			String acceleration_y = blank_acceleration_y.getText().toString();
			String acceleration_z = blank_acceleration_z.getText().toString();
			
			/* Call the PHP script on the server */
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost          httpPost   = new HttpPost("http://mhealth1.cse.nd.edu/tests/AndroidInsertData.php");
		
			/* Post the input data */
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
			
			return null;
		}
		
	}

}
