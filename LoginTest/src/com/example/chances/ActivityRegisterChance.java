package com.example.chances;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ActivityRegisterChance extends Activity {
	
	Spinner spiner,route;
	EditText fee,date,hour,destination,departure,capacity,comments;
	private static String url = "http://ing-sis.jairoesc.com/chance";
	String response,tipo;
	JSONObject jsonObject,json;
	String token;
	Button btnRChance;
	List<Vehiculos> vehiculos = new ArrayList<Vehiculos>();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_register_chance);
		
		List<String> list = new ArrayList<String>();
		list.add("Avenida");
		list.add("Mamonal");
		list.add("Bosque");
		list.add("Otros");
		
		
		
		fee = (EditText)this.findViewById(R.id.txtFee);
		date  =(EditText)this.findViewById(R.id.txtDate);
		hour = (EditText)this.findViewById(R.id.txtHour);
		destination = (EditText)this.findViewById(R.id.txtDestination);
		departure = (EditText)this.findViewById(R.id.txtDeparture);
		capacity = (EditText)this.findViewById(R.id.txtCapacity);
		comments = (EditText)this.findViewById(R.id.txtComments);
		spiner = (Spinner)this.findViewById(R.id.spin_vehicle);
		route =(Spinner)this.findViewById(R.id.spinRoute);
		btnRChance = (Button)this.findViewById(R.id.btnRegisteChance);
		
		
		SharedPreferences Token = getSharedPreferences("token",MODE_PRIVATE);
		 token = Token.getString("auth_token", "NoToken");
		
		
		
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
				getBaseContext(),
				android.R.layout.simple_spinner_dropdown_item, list);
		adaptador
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		route.setAdapter(adaptador);
		route.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				String iten = parent.getItemAtPosition(position).toString();
				
				Log.e("iten",iten);
				if(iten == "Avenida" )
					tipo = "1";
				else
				if(iten == "Mamonal" )
					tipo = "2";
				else
				if(iten == "Bosque" )
					tipo = "3";
				else
					tipo = "4";
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		VehicleGetTask task = new VehicleGetTask();
		task.execute("http://ing-sis.jairoesc.com/vehicle?auth-token="+token);
		btnRChance.setOnClickListener(new OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
				if((Integer.parseInt(capacity.getText().toString())) > 0 || (Integer.parseInt(capacity.getText().toString())) < 5)
				{
					String costo = fee.getText().toString();
					String fecha = date.getText().toString();
					String hora = hour.getText().toString();
					String destino = destination.getText().toString();
					String salida = departure.getText().toString();
					String capacidad = capacity.getText().toString();
					String comentarios = comments.getText().toString();
					String ruta=tipo;
					
					
				}
				
			}
		});

		
	}

	private class VehicleGetTask extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
			
			String res=null;
			
			try {
				
				response = CustomHttpClient.executeHttpGet("");
				res = response.toString();
				Log.e("devuelto por servidor", res.toString());
				res = res.replaceAll("\\s+", "");
				
			} catch (Exception e) {
				
				Log.e("error",res);
				
			}
			
			return res;
		}

		@Override
		protected void onPostExecute(String result) {
			
				try {
				
				
				jsonObject = new JSONObject(result);
				Log.e("JSONConvertido", jsonObject.toString());
											

			} catch (JSONException e) {
				Log.e("ERROR", "JSONERROR");

			}
		}
		
		
	}
	
	public  void FillSpinner(List<Vehiculos> v, JSONObject j)
	{
		
		
		
	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
