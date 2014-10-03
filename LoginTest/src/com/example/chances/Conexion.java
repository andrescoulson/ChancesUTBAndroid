package com.example.chances;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Conexion {
	
	public static boolean verificaConexion(Context ctx) {
	    boolean bConectado = false;
	    ConnectivityManager connec = (ConnectivityManager) ctx
	            .getSystemService(Context.CONNECTIVITY_SERVICE);
	    // No s�lo wifi, tambi�n GPRS
	    NetworkInfo[] redes = connec.getAllNetworkInfo();
	    // este bucle deber�a no ser tan �apa
	    for (int i = 0; i < 2; i++) {
	        // �Tenemos conexi�n? ponemos a true
	        if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
	            bConectado = true;
	        }
	    }
	    return bConectado;
	}

}
