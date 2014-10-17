package com.example.chances;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisterChance extends Activity{
	
	private Spinner spinerVehi;
	EditText txtdeparture;
	EditText txtdestination;
	EditText txtcapacitiy;
	EditText txtroute;
	EditText txtComent;
	Button btnSend;
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.activity_register_chances);
		
		txtdeparture = (EditText)this.findViewById(R.id.txtDeparture);
		txtdestination = (EditText)this.findViewById(R.id.txtDestination);
		txtcapacitiy = (EditText)this.findViewById(R.id.txtcapacity);
		txtroute = (EditText)this.findViewById(R.id.txtRoute);
		txtComent = (EditText)this.findViewById(R.id.txtComments);
		spinerVehi = (Spinner)this.findViewById(R.id.spin_vehicle);
		btnSend = (Button)this.findViewById(R.id.btn_submit);
		
		
		
		
		
		
		
	
		
	}
	

}
