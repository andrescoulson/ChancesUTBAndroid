package com.example.chances;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;








import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterVehicle extends Fragment {


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		View view = inflater.inflate(R.layout.activity_register_vehicle, container, false);
		
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
