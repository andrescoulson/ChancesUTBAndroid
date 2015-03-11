package com.chances.extras;

public class ListaEntrada {
	 private int IdImagen;
	    private String TextoEncima;
	    private String TextoDebajo;

	    public ListaEntrada(int IdImage, String TextoEncima,String TextoDebajo)
	    {
	        this.IdImagen = IdImage;
	        this.TextoEncima = TextoEncima;
	        this.TextoDebajo = TextoDebajo;
	    }
	    public String get_TextoEncima() {
	        return TextoEncima;
	    }
	    public String get_TextoDebajo() {
	        return TextoDebajo;
	    }
	    public int get_IdImagen() {
	        return IdImagen;
	    }

}
