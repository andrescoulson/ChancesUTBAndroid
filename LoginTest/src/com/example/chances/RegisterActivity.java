package com.example.chances;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.content.DialogInterface;
import android.content.Intent;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.app.AlertDialog;

public class RegisterActivity extends Activity {

	EditText username;
	EditText lastname;
	EditText email;
	EditText remail;
	EditText password;
	EditText rpassword;
	TextView lblogin;
	Button registro;
	String response = null;
	JSONObject jsonObject;
	private static String url = "http://ing-sis.jairoesc.com/signup";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		

		username = (EditText) this.findViewById(R.id.txtUsername);
		email = (EditText) this.findViewById(R.id.txtEmail);
		password = (EditText) this.findViewById(R.id.txtPassword);
		registro = (Button) this.findViewById(R.id.btnRegister);
		lastname = (EditText) this.findViewById(R.id.txtLastName);
		rpassword = (EditText) this.findViewById(R.id.txtRPassword);
		remail = (EditText) this.findViewById(R.id.txtREmail);

		registro.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				if (ValidarRegistro.ValidarEmail(email.getText().toString(),
						remail.getText().toString())
						&& ValidarRegistro.ValidarPass(password.getText()
								.toString(), rpassword.getText().toString())) {
					
					String name = username.getText().toString();
					String Lastname = lastname.getText().toString();
					String InputEmai = email.getText().toString();
					String InputPassword = password.getText().toString();
					String RInputEmail = remail.getText().toString();
					

					validateRegisterTask task = new validateRegisterTask();
					task.execute(new String[] { name, Lastname, InputEmai, RInputEmail, InputPassword  });

				} else {
					Toast.makeText(getBaseContext(),
							"Comprueba tu Email o Password.... ",
							Toast.LENGTH_SHORT).show();
				}

			}

		});
		lblogin = (TextView) this.findViewById(R.id.lblLogin);
		lblogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent itemIntent = new Intent(RegisterActivity.this,
						LoginHActivity.class);
				startActivity(itemIntent);
				finish();

			}

		});

	}

	// tarea para registrar usuario nuevo
	private class validateRegisterTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {

			ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();

			postParameters.add(new BasicNameValuePair("name", params[0]));
			postParameters.add(new BasicNameValuePair("lastname", params[1]));
			postParameters.add(new BasicNameValuePair("email", params[2]));
			postParameters.add(new BasicNameValuePair("confirmemail", params[3]));
			postParameters.add(new BasicNameValuePair("password", params[4]));

			String res = null;

			try {

				response = CustomHttpClient
						.executeHttpPost(url, postParameters);
				res = response.toString();
				Log.e("devuelto por servidor", res.toString());
				res = res.replaceAll("\\s+", "");

			} catch (Exception e) {

				Toast.makeText(getBaseContext(), "ERROR .... ",
						Toast.LENGTH_SHORT).show();

			}

			return res;
		}

		@Override
		protected void onPostExecute(String result) {		
			
			AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this).create();
			
			
			try {

				jsonObject = new JSONObject(result);
				Log.e("JSONConvertido", jsonObject.toString());
				
				if(jsonObject != null){
					
					
					alertDialog.setTitle("Registro Exitoso");
					alertDialog.setMessage("Aceptar");
					alertDialog.setButton(-1, "OK",new DialogInterface.OnClickListener()
					{

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							
							Intent itemIntent = new Intent(RegisterActivity.this,
									LoginHActivity.class);
							startActivity(itemIntent);
						}
						
					});
					
					alertDialog.show();
				
				
				}					       				

			} catch (JSONException e) {
				Log.e("ERROR", result);
				Toast.makeText(getBaseContext(),
						"ERROR no c de q.... ",
						Toast.LENGTH_SHORT).show();
				}
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
	        progressDialog.setCancelable(true);
	        progressDialog.setMessage("Loading...");
	        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	        progressDialog.setProgress(0);
	        progressDialog.setMax(5);
	        progressDialog.show();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
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
