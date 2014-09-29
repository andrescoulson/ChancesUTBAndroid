package com.example.chances;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.view.View;
import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginHActivity extends Activity {
	 EditText txtUsername;
	    EditText txtPass;
	    Button btnLogin;
	    TextView txtRegister;
	    JSONArray user = null;
	    TextView  txtError;
	    private static String url = "http://ing-sis.jairoesc.com/sessions";
	    private static final String TAG_TOKEN = "auth_token";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_h);
		
		txtUsername = (EditText)this.findViewById(R.id.txtUsername);
        txtPass = (EditText)this.findViewById(R.id.txtPass);
        btnLogin = (Button)this.findViewById(R.id.send);
        txtError = (TextView)this.findViewById(R.id.lblMensaje);
        
        btnLogin.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
            	
            	new JSONParse().execute(new String[]{txtUsername.getText().toString(), txtPass.getText().toString()});
            	
            	/*Intent listview = new Intent(LoginHActivity.this,MainFragmentActivity.class);
                LoginHActivity.this.startActivity(listview);
                finish();*/
            }
        });
        txtRegister = (TextView)this.findViewById(R.id.txtRegister);
        txtRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itemIntent = new Intent (LoginHActivity.this,RegisterActivity.class);
                LoginHActivity.this.startActivity(itemIntent);
                finish();

            }
        });
	}
	
	private class JSONParse extends AsyncTask<String, Void, JSONObject>{

		@Override
		protected JSONObject doInBackground(String... params) {
			
			ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
            postParameters.add(new BasicNameValuePair("email", params[0] ));
            postParameters.add(new BasicNameValuePair("password", params[1] ));
			JSONParser jParser = new JSONParser();
	        // Getting JSON from URL
	        JSONObject json = jParser.executeHttpPost(url,postParameters);
	        return json;
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			try {
				user = result.getJSONArray(TAG_TOKEN);
			} catch (JSONException e) {
				 txtError.setText("Error no Pudo Acceder");
			} 
			Intent itemIntent = new Intent (LoginHActivity.this,RegisterActivity.class);
            LoginHActivity.this.startActivity(itemIntent);	
		
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
