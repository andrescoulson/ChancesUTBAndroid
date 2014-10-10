package com.example.chances;

import java.util.regex.Pattern;


public class ValidarRegistro {
	
	public static boolean ValidarEmail(String email , String Validate_email ) {
        String expresionRegular = email;
        String cadenaAEvaluar = Validate_email;
        return ( Pattern.matches(expresionRegular,cadenaAEvaluar));
        
    }
	
	public static boolean ValidarPass(String pass , String Validate_pass ) {
        String expresionRegular = pass;
        String cadenaAEvaluar = Validate_pass;
        return (expresionRegular.equals(cadenaAEvaluar));
        
    }
	
	// [A-Za-z][A-Za-z][A-Za-z][0-9][0-9]([A-Za-z]|[0-9])
	public static boolean ValidarVehicle(String Validate_plate, int Validate_capacitity)
	{
		String expreRPlaca = "[A-Za-z][A-Za-z][A-Za-z][0-9][0-9]([A-Za-z]|[0-9])";
		int capacity = 9;
		return (Pattern.matches(expreRPlaca, Validate_plate) && (capacity<= Validate_capacitity));
	}

}
