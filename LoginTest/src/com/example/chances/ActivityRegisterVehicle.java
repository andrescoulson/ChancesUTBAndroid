package com.example.chances;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class ActivityRegisterVehicle extends Activity {
	
	private Spinner spiner;
	EditText plate;
	EditText color;
	EditText brand;
	EditText model;
	EditText capacity;
	Button btnR;
	String tipo;
	String response;
	JSONObject jsonObject;
	String token;
	private static String url = "http://ing-sis.jairoesc.com/vehicle";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registre_vehicle);
		
		List<String> list = new ArrayList<String>();
		list.add("Carro/Camioneta");
		list.add("Moto");
		list.add("Otro");

		plate = (EditText) this.findViewById(R.id.txtPlate);
		color = (EditText) this.findViewById(R.id.txtColor);
		brand = (EditText) this.findViewById(R.id.txtBrand);
		model = (EditText) this.findViewById(R.id.txtModel);
		capacity = (EditText) this.findViewById(R.id.txtCapacity);
		spiner = (Spinner) this.findViewById(R.id.txtType);
		btnR = (Button) this.findViewById(R.id.btnRegisterV);
		
		
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
				getBaseContext(),
				android.R.layout.simple_spinner_dropdown_item, list);
		adaptador
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spiner.setAdapter(adaptador);

		spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				String iten = parent.getItemAtPosition(position).toString();
				
				Log.e("iten",iten);
				if(iten == "Carro/Camioneta" )
					tipo = "1";
				else
				if(iten == "Moto" )
					tipo = "2";
				else
				if(iten == "Moto" )
					tipo = "3";
				
					
					
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		SharedPreferences Token = getSharedPreferences("token",MODE_PRIVATE);
		 token = Token.getString("auth_token", "NoToken");
		btnR.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (ValidarRegistro.ValidarVehicle(plate.getText().toString(),
						Integer.parseInt(capacity.getText().toString())) && token != "NoToken") {
					String placa = plate.getText().toString();
					String Color = color.getText().toString();
					String marca = brand.getText().toString();
					String modelo = model.getText().toString();
					String capacidad = capacity.getText().toString();
					String type = tipo;	
					
				
					
					Log.e("token",token);
					Log.e("tipo",tipo);

					validateRegisterTask task = new validateRegisterTask();
					task.execute(new String[] { placa, Color, marca, modelo, capacidad,
							type,token });

				} else {

					Toast.makeText(getBaseContext(),
							"Comprueba tu Placa o Capacidad.... ",
							Toast.LENGTH_SHORT).show();
				}

			}
		});
	}
	
	private class validateRegisterTask extends AsyncTask<String, Void, String>{
		
		ProgressDialog progressDialog = new ProgressDialog(ActivityRegisterVehicle.this);
       

		@Override
		protected String doInBackground(String... params) {
			
			ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
			
			postParameters.add(new BasicNameValuePair("plate", params[0]));
			postParameters.add(new BasicNameValuePair("color", params[1]));
			postParameters.add(new BasicNameValuePair("brand", params[2]));
			postParameters.add(new BasicNameValuePair("model", params[3]));
			postParameters.add(new BasicNameValuePair("capacity", params[4]));
			postParameters.add(new BasicNameValuePair("type", params[5]));
			postParameters.add(new BasicNameValuePair("auth-token", params[6]));
			
			String res = null;
			
			try {
				
				response = CustomHttpClient.executeHttpPost(url, postParameters);
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
			super.onPostExecute(result);
			
			progressDialog.dismiss();
			
			AlertDialog alertDialog = new AlertDialog.Builder(ActivityRegisterVehicle.this).create();
			
			try {
				
				jsonObject = new JSONObject(result);
				Log.e("JSONConvertidoVehicle", jsonObject.toString());
				
				if(jsonObject != null)
				{
					alertDialog.setTitle("Registro Exitoso");
					alertDialog.setMessage("Aceptar");
					alertDialog.setButton(-1, "OK",new DialogInterface.OnClickListener()
					{

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							
							Intent myintent = new Intent(ActivityRegisterVehicle.this , MainFragmentActivity.class);
							startActivity(myintent);
							//arg0.cancel();
						}
						
					});
					
					alertDialog.show();
					
				}
				
				
			} catch (Exception e) {
				Log.e("ERRORVehicle", e.toString());
			}
			
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
}
