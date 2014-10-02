package com.example.chances;


import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginHActivity extends Activity {
	
	EditText txtUsername;
	EditText txtPass;
	Button btnLogin;
	TextView txtRegister;
	TextView txtError;
	String response = null;
	JSONObject jsonObject;
	boolean conexion;
	private static String url = "http://ing-sis.jairoesc.com/sessions";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_h);
		
		

		txtUsername = (EditText) this.findViewById(R.id.txtUsername);
		txtPass = (EditText) this.findViewById(R.id.txtPass);
		btnLogin = (Button) this.findViewById(R.id.send);
		txtError = (TextView) this.findViewById(R.id.lblMensaje);
		
		conexion = Conexion.verificaConexion(this);
		

		btnLogin.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				if(!conexion)
				{
					Toast.makeText(getBaseContext(),
				            "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
				            .show();
				}else{
				
								
				String uname = txtUsername.getText().toString();
				String pwd = txtPass.getText().toString();
				
				validateUserTask task = new validateUserTask();				
				task.execute(new String[] { uname, pwd });
				
				}

			}
		});
		
		txtRegister = (TextView) this.findViewById(R.id.txtRegister);
		txtRegister.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if(!conexion)
				{
					Toast.makeText(getBaseContext(),
				            "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
				            .show();
				}else{
				Intent itemIntent = new Intent(LoginHActivity.this,
						RegisterActivity.class);
				LoginHActivity.this.startActivity(itemIntent);
				finish();
				}
			}
		});
	}

	private class validateUserTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... params) {
			
		
			ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
			
			postParameters.add(new BasicNameValuePair("email", params[0]));
			postParameters.add(new BasicNameValuePair("password", params[1]));
			
			String res = null;
			
			try {
				
				response = CustomHttpClient.executeHttpPost(url, postParameters);
				res = response.toString();
				Log.e("devuelto por servidor", res.toString());
				res = res.replaceAll("\\s+", "");
				
			} catch (Exception e) {
				
				txtError.setText(e.toString());
				
			}
			
			return res;
			
		}// close doInBackground

		@Override
		protected void onPostExecute(String result) {

			try {
				
				jsonObject = new JSONObject(result);
				Log.e("JSONConvertido", jsonObject.toString());
				
				if (jsonObject.getString("auth_token") != null) {
					
					Intent MyIntent = new Intent(LoginHActivity.this,MainFragmentActivity.class);
					startActivity(MyIntent);
					finish();
					
				}

			} catch (JSONException e) {
				Log.e("ERROR", "JSONERROR");
				txtError.setText("Sorry!! Incorrect Username or Password");

			}

		} 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_h, menu);
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
