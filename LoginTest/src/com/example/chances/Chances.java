package com.example.chances;

public class Chances {
	
	String id,fee,date,hour,destination,departure,capacity,comments,route,vehicles_id,created_at,updated_at;
	int image;
	public Chances(String id,String date,String hour,String destination,String departure,String capacity, String comments,String route, String vehicles_id,String created_at,String update_at)
	{
		this.id =id;
		this.date=date;
		this.hour=hour;
		this.destination=destination;
		this.capacity = capacity;
		this.departure=departure;
		this.comments=comments;
		this.route = route;
		this.vehicles_id=vehicles_id;
		this.created_at =created_at;
		this.updated_at =update_at;
	}
	
	public String getId()
	{
		return id;
	}
	public String getDate()
	{
		return date;
	}
	
	public String getDestino() {
		
		return destination;
	}
	
	public String getCapacity() {
		return capacity;
		
	}
	
	public String getDeparture() {
		return departure;
	}
	
	public String getComents() {
		return comments;
	}
	
	public String getRoute() {
		
		if(route.equals("1"))
			return "Avenida";
		
		if(route.equals("2"))
			return "Mamonal";
		if(route.equals("3"))
			return "Bosque";
		
		return "Otros";
	}
	
	public String  getVehicleId() {
		
		return vehicles_id;
	}

}
