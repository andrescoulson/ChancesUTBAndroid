package com.chances.extras;

import com.example.chances.ListaFragment;
import com.example.chances.ProfileFragment;
import com.example.chances.RegisterVehicle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter{

	public TabPagerAdapter(FragmentManager fm) {
		super(fm);
		
	}

	@Override
	public Fragment getItem(int index) {
		
		switch (index) {
		case 0:
			return new ListaFragment();
		case 1:
			return new RegisterVehicle();
		case 2:
			return new ProfileFragment();
		default:
			break;
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}
	
	

}
