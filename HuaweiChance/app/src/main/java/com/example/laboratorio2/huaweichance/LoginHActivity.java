package com.example.laboratorio2.huaweichance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class LoginHActivity extends Activity {
    EditText txtUsername;
    EditText txtPass;
    Button btnLogin;
    TextView txtRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_h);

        txtUsername = (EditText)this.findViewById(R.id.txtUsername);
        txtPass = (EditText)this.findViewById(R.id.txtPass);
        btnLogin = (Button)this.findViewById(R.id.send);

        btnLogin.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                Intent listview = new Intent(LoginHActivity.this,ListChanceActivity.class);
                LoginHActivity.this.startActivity(listview);
                finish();
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
