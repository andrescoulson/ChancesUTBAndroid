package com.example.chances;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.Button;
import android.widget.TextView;

public class AplyToChanceActivity extends Activity {

	private TextView destino, salida, precio, comentarios, hora, ruta, cupos;
	private static String url = "http://ing-sis.jairoesc.com/usersofchance";
	String token;
	String chance_id;
	Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aply_to_chance);

		SharedPreferences Token = getSharedPreferences("token",
				Context.MODE_PRIVATE);
		token = Token.getString("auth_token", "NoToken");

		destino = (TextView) findViewById(R.id.textDestino);
		salida = (TextView) findViewById(R.id.textSalida);
		precio = (TextView) findViewById(R.id.textPrecio);
		comentarios = (TextView) findViewById(R.id.textComment);
		hora = (TextView) findViewById(R.id.textHora);
		ruta = (TextView) findViewById(R.id.textRuta);
		cupos = (TextView) findViewById(R.id.textCupo);
		btn = (Button)findViewById(R.id.btnAply);
		try {

			Intent myintent = getIntent();
			destino.setText(myintent.getStringExtra("destino").toString());
			salida.setText(myintent.getStringExtra("salida").toString());
			precio.setText(myintent.getStringExtra("precio").toString());
			comentarios.setText(myintent.getStringExtra("coment").toString());
			hora.setText(myintent.getStringExtra("hora").toString());
			ruta.setText(myintent.getStringExtra("ruta"));
			cupos.setText(myintent.getStringExtra("cupos"));
			chance_id = myintent.getStringExtra("chanceid");
			Log.e("idchance",chance_id);
			
			

		} catch (Exception e) {
			Log.e("CHEE", e.toString());
		}
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				 new AplytoChance().execute(new String [] {chance_id,token});
				
			}
		});

	}
	
	public class AplytoChance extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
			ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
			
			postParameters.add(new BasicNameValuePair("chances_id", params[0]));
			postParameters.add(new BasicNameValuePair("auth-token", params[1]));
			 
			
			String res = null;
			String response = null;
			
			try {
				
				response = CustomHttpClient.executeHttpPost(url, postParameters);
				res = response.toString();
				Log.e("devuelto por servidor", res.toString());
				//res = res.replaceAll("\\s+", "");
				
			} catch (Exception e) {
				
				Log.e("Error aplicando a chance", e.toString());
				
			}
			
			return res;
		}

		@Override
		protected void onPostExecute(String result) {
			 
			AlertDialog alert =  new AlertDialog.Builder(AplyToChanceActivity.this).create();
			alert.setTitle(result);
			alert.setMessage("Aceptar");
			alert.setButton(-1, "OK",new DialogInterface.OnClickListener()
					{

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							
							Intent itemIntent = new Intent(getBaseContext(),
									MainFragmentActivity.class);
							startActivity(itemIntent);
						}
						
					});
			alert.show();
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		
	}
	}


