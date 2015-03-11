package com.example.chances;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.chances.extras.CustomHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ActivityRegisterChance extends Activity {

	Spinner spiner, route;
	EditText fee, date, hour, destination, departure, capacity, comments;
	private static String url = "http://ing-sis.jairoesc.com/chance";
	String response, tipo,idvehicle;
	String token;
	Button btnRChance,btnpicker;
	List<String> vehiculos = new ArrayList<String>();
	static final int DATE_PICKER_ID = 1111; 	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_register_chance);

		List<String> list = new ArrayList<String>();
		list.add("--Seleccionar Ruta--");
		list.add("Avenida");
		list.add("Mamonal");
		list.add("Bosque");
		list.add("Otros");
		idvehicle = "a";

		fee = (EditText) this.findViewById(R.id.txtFee);
		date = (EditText) this.findViewById(R.id.txtDate);
		hour = (EditText) this.findViewById(R.id.txtHour);
		destination = (EditText) this.findViewById(R.id.txtDestinatio);
		departure = (EditText) this.findViewById(R.id.txtDeparture);
		capacity = (EditText) this.findViewById(R.id.txtCapacity);
		comments = (EditText) this.findViewById(R.id.txtComents);
		spiner = (Spinner) this.findViewById(R.id.spinVehicle);
		route = (Spinner) this.findViewById(R.id.spinRoute);
		btnRChance = (Button) this.findViewById(R.id.btnRegisteChance);
		
		
		

		SharedPreferences Token = getSharedPreferences("token", MODE_PRIVATE);
		token = Token.getString("auth_token", "NoToken");
		
		VehicleGetTask task = new VehicleGetTask();
		task.execute("http://ing-sis.jairoesc.com/vehicle?auth-token=" + token);

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

				Log.e("iten", iten);
				if (iten == "Avenida")
					tipo = "1";
				else if (iten == "Mamonal")
					tipo = "2";
				else if (iten == "Bosque")
					tipo = "3";
				else if (iten == "Otros")
					tipo = "4";
				else
					tipo = "0";

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}

		});

		
		btnRChance.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				if (/*(Integer.parseInt(capacity.getText().toString())) > 0
						&& (Integer.parseInt(capacity.getText().toString())) < 5 && */!tipo.equals("0") && !idvehicle.equals("a")) {
					
					String costo = fee.getText().toString();
					String fecha = date.getText().toString();
					String hora = hour.getText().toString();
					String destino = destination.getText().toString();
					String salida = departure.getText().toString();
					String capacidad = capacity.getText().toString();
					String comentarios = comments.getText().toString();
					String ruta = tipo;
					String id = idvehicle;
					
					ChancepostTask task = new ChancepostTask();
					task.execute(new String[]{costo,fecha,hora,destino,salida,capacidad,comentarios,ruta,id,token});
				}
				else
				{
					Toast.makeText(getBaseContext(), "Error en Algunos datos ", Toast.LENGTH_SHORT).show();
				}

			}
		});

	}

	private class VehicleGetTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {

			String res = null;

			try {

				response = CustomHttpClient.executeHttpGet(params[0]);
				res = response.toString();
				Log.e("devuelto por servidor", res.toString());
				res = res.replaceAll("\\s+", "");

			} catch (Exception e) {

				Log.e("error", e.toString());

			}

			return res;
		}

		@Override
		protected void onPostExecute(String result) {

			FillSpinner(result);
		}

	}

	public void FillSpinner(String response) {
		JSONObject json = null;
		
		try {
			
			json = new JSONObject(response); 
			JSONArray arrayjson = json.getJSONArray("vehicle");
			String[] displayNombre = new String[arrayjson.length()];
			
			for(int i=0;i<arrayjson.length();i++)
			{
				displayNombre[i]= arrayjson.getJSONObject(i).getString("brand")+" "+arrayjson.getJSONObject(i).getString("model"); 
				vehiculos.add(arrayjson.getJSONObject(i).getString("id"));
			}
			
			ArrayAdapter<String> adapterSpin = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,displayNombre);
			adapterSpin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spiner.setAdapter(adapterSpin);
			OnItemSelectedListener spinerlistener = new myOnItemSelectedListener(this);
			spiner.setOnItemSelectedListener(spinerlistener);

		} catch (JSONException w) {
			Log.e("ErroW", w.toString());
		}
	}
	
	public class myOnItemSelectedListener implements OnItemSelectedListener
	{
		Context context;
		
		 public myOnItemSelectedListener(Context context) {
			 
			 this.context = context;
		}

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long row) {
						
				idvehicle = vehiculos.get(pos);
				Log.e("idVehicle",idvehicle);
				
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class ChancepostTask extends AsyncTask<String, Void, String>{
		ProgressDialog progressDialog = new ProgressDialog(ActivityRegisterChance.this);

		@Override
		protected String doInBackground(String... params) {
			
			String res=null;
			String respuesta= null;
			ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
			
			postParameters.add(new BasicNameValuePair("fee", params[0]));
			postParameters.add(new BasicNameValuePair("date", params[1]));
			postParameters.add(new BasicNameValuePair("hour", params[2]));
			postParameters.add(new BasicNameValuePair("destination", params[3]));
			postParameters.add(new BasicNameValuePair("departure", params[4]));
			postParameters.add(new BasicNameValuePair("capacity", params[5]));
			postParameters.add(new BasicNameValuePair("comments", params[6]));
			postParameters.add(new BasicNameValuePair("route", params[7]));
			postParameters.add(new BasicNameValuePair("vehicles_id", params[8]));
			postParameters.add(new BasicNameValuePair("auth-token", params[9]));
			
			
			try{
				
				respuesta = CustomHttpClient.executeHttpPost(url, postParameters);
				res = respuesta.toString();
				Log.e("Resp Chances servidor chance", res.toString());
				res = res.replaceAll("\\s+", "");
				
				
			}catch(Exception E){Log.e("Error enviando chance",E.toString());}
			
			return res;
			}
			

		@Override
		protected void onPostExecute(String result) {
			progressDialog.dismiss();
			AlertDialog alertDialog = new AlertDialog.Builder(ActivityRegisterChance.this).create();
			
			try {

				JSONObject j = new JSONObject(result);
				Log.e("message",j.getString("message").toString());
				
				if(j.getString("message").toString().equals("Chancecreated.")){
					
					
					alertDialog.setTitle("Registro Exitoso");
					alertDialog.setMessage("Aceptar");
					alertDialog.setButton(-1, "OK",new DialogInterface.OnClickListener()
					{

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							
							Intent itemIntent = new Intent(ActivityRegisterChance.this,MainFragmentActivity.class);
							startActivity(itemIntent);
						}
						
					});
					
					alertDialog.show();
				
				
				}
				else
				{
					alertDialog.setTitle("Registro Fallido");
					alertDialog.setMessage("Chance no fue Creado");
					alertDialog.setButton(-2, "OK",new DialogInterface.OnClickListener()
					{

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							
							arg0.cancel();
						}
						
					});
					
					alertDialog.show();
				}
			}catch(JSONException e){Log.e("Error",e.toString());}
		
			
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
	        progressDialog.setCancelable(true);
	        progressDialog.setMessage("Enviando...");
	        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	        progressDialog.setProgress(0);
	        progressDialog.setMax(20);
	        progressDialog.show();
		}
		
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
