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

}
