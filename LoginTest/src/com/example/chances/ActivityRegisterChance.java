package com.example.chances;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

public class ActivityRegisterChance extends Activity {
	
	Spinner spiner;
	EditText fee,date,hour,destination,departure,capacity,comments,route;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_register_chance);
		
		fee = (EditText)this.findViewById(R.id.txtFee);
		date  =(EditText)this.findViewById(R.id.txtDate);
		hour = (EditText)this.findViewById(R.id.txtHour);
		destination = (EditText)this.findViewById(R.id.txtDestination);
		departure = (EditText)this.findViewById(R.id.txtDeparture);
		capacity = (EditText)this.findViewById(R.id.txtCapacity);
		comments = (EditText)this.findViewById(R.id.txtComments);
		route = (EditText)this.findViewById(R.id.txtRoute);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_register_chance, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
