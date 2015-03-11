package com.example.chances;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

public class ListaFragment extends Fragment {
	ListView List;
	ArrayList<ListaEntrada> datos = new ArrayList<ListaEntrada>();
	ArrayList<Chances> chances = new ArrayList<Chances>();
	private static String url = "http://ing-sis.jairoesc.com/chance?auth-token=";
	String token;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.activity_list_chance, container,
				false);
		
		/*getActivity().getActionBar().setDisplayShowHomeEnabled(false);
		getActivity().getActionBar().setDisplayShowTitleEnabled(false);*/
		
		SharedPreferences Token  = getActivity().getSharedPreferences("token",Context.MODE_PRIVATE);
		 token = Token.getString("auth_token", "NoToken");
		 
		 ChancesGetTask task = new ChancesGetTask();
		 task.execute(url+token);

		 List = (ListView)view.findViewById(R.id.listView_listado);

		
		 		

		List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> pariente, View view,
					int posicion, long id) {
				Chances Seleccion = (Chances) pariente
						.getItemAtPosition(posicion);

				CharSequence text = "Seleccionado: "+ Seleccion.getDeparture()
					+ " "	+ Seleccion.getDestino();
				Toast toast = Toast.makeText(getActivity(), text,
						Toast.LENGTH_LONG);
				toast.show();
				
				Intent myIntent = new Intent (getActivity(),AplyToChanceActivity.class);
				myIntent.putExtra("salida",Seleccion.getDestino().toString());
				myIntent.putExtra("destino",Seleccion.getDeparture());
				myIntent.putExtra("hora", Seleccion.getDate());
				myIntent.putExtra("cupos", Seleccion.getCapacity());
				myIntent.putExtra("ruta",Seleccion.getRoute());
				myIntent.putExtra("coment", Seleccion.getComents());
				myIntent.putExtra("chanceid", Seleccion.getId());
				myIntent.putExtra("precio", Seleccion.getFee());
				
				startActivity(myIntent);
				getActivity().overridePendingTransition(R.anim.acelerate,R.anim.desacelerate);
				
			}
		});
		return view;
	}
	
	private class ChancesGetTask extends AsyncTask<String, Void, String>
	{

		@Override
		protected String doInBackground(String... params) {
			String res = null;
			String response =null;
			
			try {
				
				response = CustomHttpClient.executeHttpGet(params[0]);
				res = response.toString();
				//Log.e("devuelto por servidor", res.toString());
				res = res.replaceAll("\\s+", "");
				
			} catch (Exception e) {
				Log.e("error", e.toString());
			}
			
			return res;
		}

		@Override
		protected void onPostExecute(String result) {
			
			FillListView(result);
		}
	
		
	}
	
	public void FillListView(String response)
	{
		JSONObject json = null;
		try {
			Gson gson = new Gson();
			json = new JSONObject(response);
			//Log.e("ChancesCreados", json.toString());
			JSONArray arrayJson = json.getJSONArray("chances");
			for(int i=0; i<arrayJson.length();i++)
			{
				
				int image = R.drawable.ic_launcher;
				String texTop,textBelow;
				String chanceinfo = arrayJson.getJSONObject(i).toString();
				//Log.e("datos chances creados",chanceinfo);
				texTop = arrayJson.getJSONObject(i).getString("destination");
				textBelow = arrayJson.getJSONObject(i).getString("departure");
				
				datos.add(new ListaEntrada(image, texTop, textBelow));
				
				Chances chance = gson.fromJson(chanceinfo, Chances.class);
				chances.add(chance);
				
			}
			List = (ListView) getView().findViewById(R.id.listView_listado);
			List.setAdapter(new com.example.chances.ListAdapter(getActivity(),
					R.layout.entrada, chances) {

				@Override
				public void onEntrada(Object entrada, View view) {
					TextView Text_Top_entrada = (TextView) view
							.findViewById(R.id.textView_superior);
					Text_Top_entrada.setText(((Chances) entrada)
							.getDestino());

					TextView Text_Below = (TextView) view
							.findViewById(R.id.textView_inferior);
					Text_Below.setText(((Chances) entrada).getDeparture());

					/*ImageView Imagen = (ImageView) view
							.findViewById(R.id.imageView_image);
					Imagen.setImageResource(((ListaEntrada) entrada).get_IdImagen());*/

				}
			});
		} catch (Exception e) {
			// TODO: handle exception
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
		
		switch (item.getItemId()) {
		case R.id.add_item:
			Intent myintent= new Intent(getActivity(),ActivityRegisterChance.class);
			startActivity(myintent); 
			getActivity().overridePendingTransition(R.anim.acelerate,R.anim.desacelerate);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	
}
