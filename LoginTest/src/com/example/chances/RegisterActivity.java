package com.example.chances;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;

public class RegisterActivity extends Activity {
	
	EditText username;
    EditText email;
    EditText password;
    TextView lblogin;
    Button registro;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		username = (EditText)this.findViewById(R.id.txtUsername);
        email = (EditText)this.findViewById(R.id.txtEmail);
        password = (EditText)this.findViewById(R.id.txtPassword);
        registro = (Button)this.findViewById(R.id.btnRegister);

        registro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String name = username.getText().toString();
                String InputEmai = email.getText().toString();
                String InputPassword = password.getText().toString();
            }

        });
        lblogin = (TextView)this.findViewById(R.id.lblLogin);
        lblogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itemIntent = new Intent(RegisterActivity.this,LoginHActivity.class);
                RegisterActivity.this.startActivity(itemIntent);
                finish();

            }

        });

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
