package com.example.chances;

import android.content.Intent;
import android.os.Bundle;
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

public class ListaFragment extends Fragment {
	ListView List;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.activity_list_chance, container,
				false);
		ArrayList<ListaEntrada> datos = new ArrayList<ListaEntrada>();

		datos.add(new ListaEntrada(R.drawable.ic_launcher, "Lorem ipsum",
				"Lorem ipsum,Lorem ipsum,Lorem ipsum,Lorem ipsum"));
		datos.add(new ListaEntrada(R.drawable.ic_launcher, "Lorem ipsum2",
				"Lorem ipsumLorem ipsumLorem ipsumLorem ipsum"));
		datos.add(new ListaEntrada(R.drawable.ic_launcher, "Lorem ipsum",
				"Lorem ipsumLorem ipsumLorem ipsum"));
		datos.add(new ListaEntrada(R.drawable.ic_launcher, "Lorem ipsum",
				"Lorem ipsumLorem ipsumLorem ipsum"));
		datos.add(new ListaEntrada(R.drawable.ic_launcher, "Lorem ipsum",
				"Lorem ipsumLorem ipsumLorem ipsum"));
		datos.add(new ListaEntrada(R.drawable.ic_launcher, "Lorem ipsum",
				"Lorem ipsumLorem ipsumLorem ipsum"));
		datos.add(new ListaEntrada(R.drawable.ic_launcher, "Lorem ipsum",
				"Lorem ipsumLorem ipsumLorem ipsum"));

		List = (ListView) view.findViewById(R.id.listView_listado);
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
			public void onItemClick(AdapterView<?> pariente, View view,
					int posicion, long id) {
				ListaEntrada Seleccion = (ListaEntrada) pariente
						.getItemAtPosition(posicion);

				CharSequence text = "Seleccionado: "
						+ Seleccion.get_TextoDebajo();
				Toast toast = Toast.makeText(getActivity(), text,
						Toast.LENGTH_LONG);
				toast.show();

			}
		});
		return view;
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
			Intent myintent= new Intent(getActivity(),ActivityRegisterVehicle.class);
			startActivity(myintent); 
			getActivity().overridePendingTransition(R.anim.acelerate,R.anim.desacelerate);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	
}
