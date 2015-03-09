package com.example.chances;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class AplyToChanceActivity extends Activity {
	
	private TextView destino,salida,precio,comentarios,hora,ruta,cupos;
	private static String url = "http://ing-sis.jairoesc.com/chance?auth-token=";
	String token;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aply_to_chance);
		
		SharedPreferences Token  = getSharedPreferences("token",Context.MODE_PRIVATE);
		 token = Token.getString("auth_token", "NoToken");
		
		destino=(TextView)findViewById(R.id.textDestino);
		salida =(TextView)findViewById(R.id.textSalida);
		precio=(TextView)findViewById(R.id.textPrecio);
		comentarios=(TextView)findViewById(R.id.textComment);
		hora=(TextView)findViewById(R.id.textHora);
		ruta=(TextView)findViewById(R.id.textRuta);
		cupos=(TextView)findViewById(R.id.textCupo);
		
		Intent myintent = getIntent();
		destino.setText( myintent.getStringExtra("destino"));
		salida.setText(myintent.getStringExtra("salida"));
		precio.setText(myintent.getStringExtra("precio"));
		comentarios.setText(myintent.getStringExtra("coment"));
		String route = myintent.getStringExtra("ruta");
		Log.e("ruta",route);
		if(Integer.parseInt(route)==1)
			ruta.setText("Avenida");
		else if(Integer.parseInt(route)==2)
			ruta.setText("Mamonal");
		else if(Integer.parseInt(route)==3)
			ruta.setText("Bosque");
		else
			ruta.setText("Otra");
		
		
		
		
	}

	
}
