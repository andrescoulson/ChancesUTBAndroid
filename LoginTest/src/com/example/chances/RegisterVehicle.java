package com.example.chances;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;





import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterVehicle extends Fragment {

	private Spinner spiner;
	EditText plate;
	EditText color;
	EditText brand;
	EditText model;
	EditText capacity;
	Button btnR;
	int tipo;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		View view = inflater.inflate(R.layout.activity_register_vehicle, container, false);
		List<String> list = new ArrayList<String>();
		        list.add("Carro/Camioneta");
		        list.add("Moto");
		        list.add("Otro");
		
		
		
		plate = (EditText)view.findViewById(R.id.txtPlate);
		color = (EditText)view.findViewById(R.id.txtColor);
		brand = (EditText)view.findViewById(R.id.txtBrand);
		model = (EditText)view.findViewById(R.id.txtModel);
		capacity = (EditText)view.findViewById(R.id.txtCapacity);
		spiner = (Spinner)view.findViewById(R.id.txtType);
		btnR = (Button)view.findViewById(R.id.btnRegisterV);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, list);
		adaptador.setDropDownViewResource(
		        android.R.layout.simple_spinner_dropdown_item);
		
		spiner.setAdapter(adaptador);
		
	   spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(
			   ) {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub
					
				}
	});
		
		btnR.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(ValidarRegistro.ValidarVehicle(plate.getText().toString(),Integer.parseInt(capacity.getText().toString())))
				{
					String placa = plate.getText().toString();
					String Color = color.getText().toString();
					String marca = brand.getText().toString();
					String capacidad = (capacity.getText().toString());
					String type = Integer.toString(tipo);					
					
					validateRegisterTask task = new validateRegisterTask();
					task.execute(new String[] {placa,Color,marca,capacidad,type });
					
				}else {
					
					Toast.makeText(getActivity(),
							"Comprueba tu Placa o Capacidad.... ",
							Toast.LENGTH_SHORT).show();
				}
				
				
				
			}
		});
		
		return view;
		
	}
	
	private class validateRegisterTask extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
			ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
			postParameters.add(new BasicNameValuePair("plate", params[0]));
			postParameters.add(new BasicNameValuePair("color", params[1]));
			postParameters.add(new BasicNameValuePair("brand", params[2]));
			postParameters.add(new BasicNameValuePair("model", params[3]));
			postParameters.add(new BasicNameValuePair("capacity", params[4]));
			postParameters.add(new BasicNameValuePair("type", params[5]));
			


			
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	} 
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.activity_itemdetail, menu);
		

	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}


}
