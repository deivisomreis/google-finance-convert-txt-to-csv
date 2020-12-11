package br.com.deivison.gfttc.utils;

public class AtivoAcaoUtils {
	
	public static Double convertValorDia(String strVlrDia){
		String replace = strVlrDia.replace(" BRL", "").replace(",", ".");
		
		return new Double(replace);
	}

}
