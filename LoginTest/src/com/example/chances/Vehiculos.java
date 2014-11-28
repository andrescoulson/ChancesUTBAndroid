package com.example.chances;

public class Vehiculos {
	
	int id;
	String nombre;
	String model;
	
	public Vehiculos(int id, String nombre,String model)
	{
		this.id =id;
		this.nombre=nombre;
		this.model=model;
	}

	@Override
	public String toString() {
		
		return nombre;
	}
	public int getId()
	{
		return id;
	
	}
	
	public String getNombre()
	{
		return nombre;
	}
	
	public String getModel()
	{
		return model;
	}
	

}
