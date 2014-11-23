package com.example.chances;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

public class RegisterVehicle extends Fragment {

	ListView List;
	private static String url ="http://ing-sis.jairoesc.com/vehicle?auth-token=";
	String token;
	JSONObject jsonObject;
	
	String response;
	
	 GridView gridView;
	 ArrayList<Item> gridArray = new ArrayList<Item>();
	 CustomGridViewAdapter customGridAdapter;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		SharedPreferences Token  = getActivity().getSharedPreferences("token",Context.MODE_PRIVATE);
		 token = Token.getString("auth_token", "NoToken");
		
		
		View view = inflater.inflate(R.layout.activity_register_vehicle, container, false);
		
		VehicleGetTask task = new VehicleGetTask();
		Log.e("url",url+token);
		task.execute(url+token);
		
		Bitmap homeIcon = BitmapFactory.decodeResource(this.getResources(),R.drawable.ic_launcher );
		  
		gridArray.add(new Item(homeIcon,"Home"));
		gridArray.add(new Item(homeIcon,"H"));
		gridArray.add(new Item(homeIcon,"He"));
		gridArray.add(new Item(homeIcon,"Ho"));
		
		/*ArrayList<ListaEntrada> datos = new ArrayList<ListaEntrada>();
		datos.add(new ListaEntrada(R.drawable.ic_launcher, "Lorem ipsum",
				"Lorem ipsum,Lorem ipsum,Lorem ipsum,Lorem ipsum"));
		
		List = (ListView)view.findViewById(R.id.listViewVehicle);
		List.setAdapter(new com.example.chances.ListAdapter(getActivity(),
				R.layout.entrada, datos) {
			
			@Override
			public void onEntrada(Object entrada, View view) {
				
				TextView Text_Top_entrada = (TextView) view
						.findViewById(R.id.textView_superior);
				Text_Top_entrada.setText(((ListaEntrada) entrada)
						.get_TextoEncima());

				TextView Text_Below = (TextView) view
						.findViewById(R.id.textView_inferior);
				Text_Below.setText(((ListaEntrada) entrada).get_TextoDebajo());

				ImageView Imagen = (ImageView) view
						.findViewById(R.id.imageView_image);
				Imagen.setImageResource(((ListaEntrada) entrada).get_IdImagen());
				
				
			}
		});
		
		List.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
			}
			
			
		});*/
		
		gridView = (GridView) view.findViewById(R.id.gridView);
		 customGridAdapter = new CustomGridViewAdapter(view.getContext(), R.layout.row_grid, gridArray);
		 gridView.setAdapter(customGridAdapter);
		return view;
		
	}
	
	private class VehicleGetTask extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
			
			String res=null;
			try {
				
				response = CustomHttpClient.executeHttpGet(params[0]);
				res = response.toString();
				Log.e("devuelto por servidor", res.toString());
				res = res.replaceAll("\\s+", "");
				
			} catch (Exception e) {
				
				Log.e("error",res);
				
			}
			
			return res;
		}

		@Override
		protected void onPostExecute(String result) {
			
				try {
				
				
				jsonObject = new JSONObject(result);
				Log.e("JSONConvertido", jsonObject.toString());
											

			} catch (JSONException e) {
				Log.e("ERROR", "JSONERROR");

			}
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
		inflater.inflate(R.menu.menu_vehicle, menu);
		

	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.add_item:
			Intent myintent= new Intent(getActivity(),ActivityRegisterVehicle.class);
			startActivity(myintent); 
			getActivity().overridePendingTransition(R.anim.acelerate,R.anim.desacelerate);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
		
	}


}
