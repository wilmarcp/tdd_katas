package co.com.blogspot.wilmarcp.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class StringCalculator {

	private static final String default_separator = ",";
	
	public static int add(String valores) throws NegativeNumberNotPermitException{
		int suma = 0;
		if(valores.isEmpty()){
			suma = 0;
			return suma;
		}
		
		suma = sumarElementos(obtenerValoresASumar(valores));
	
		return suma;
	}

	private static int sumarElementos(int[] valores) throws NegativeNumberNotPermitException {
		int suma = 0;
		for (int valor : valores) {
			if(valor < 0){
				throw new NegativeNumberNotPermitException("Numeros menores a cero no son permitidos");
			}else if(valor <= 1000){
				suma += valor;
			}
		}
		return suma;
	}
	
	private static int[] obtenerValoresASumar(String cadenaNumerosASumar){
		int[] valores;
		
		List<String> separadores = extraerSeparadores(cadenaNumerosASumar);
		String valoresASumar = extraerValoresASumar(cadenaNumerosASumar, separadores);
		valores = obtenerValoresNumericosASumar(separadores, valoresASumar);
		
		return valores;
	}

	private static int[] obtenerValoresNumericosASumar(List<String> separadores, String valoresASumar) {
		String regex = obtenerRegexDeSeparadores(separadores);
		String [] valoresIndividuales = Pattern.compile(regex).split(valoresASumar);
		int[] retorno = new int[valoresIndividuales.length];
		
		for (int i = 0; i < valoresIndividuales.length; i++) {
			retorno[i] = Integer.parseInt(valoresIndividuales[i]) ;
		}
		
		return retorno;
	}

	private static String obtenerRegexDeSeparadores(List<String> separadores) {
		String regex = "[";
		for (String separador : separadores) {
			regex = regex.concat(separador);
		}
		regex = regex.concat("]");
		return regex;
	}

	private static String extraerValoresASumar(String cadenaNumerosASumar, List<String> separadores) {
		String valoresASumar = "";
		if(cadenaNumerosASumar.startsWith("//")){
			valoresASumar = cadenaNumerosASumar.substring(cadenaNumerosASumar.indexOf("\n")+1);
		}else{
			valoresASumar = cadenaNumerosASumar;
		}
		
		valoresASumar = valoresASumar.replace("\n", separadores.get(0));
				
		return valoresASumar;
	}

	private static List<String> extraerSeparadores(String cadenaNumerosASumar) {
		List<String> separadores = new ArrayList<>();
		if(cadenaNumerosASumar.startsWith("//")){
			cadenaNumerosASumar = cadenaNumerosASumar.substring(cadenaNumerosASumar.indexOf("//") + 2, cadenaNumerosASumar.indexOf("\n")); 
			separadores = Arrays.asList( cadenaNumerosASumar.split("\\]\\["));
			separadores.get(0).replace("[", "");
			separadores.get(separadores.size()-1).replace("]", "");
		}else{
			separadores.add(default_separator);
		}
		return separadores;
	}
	

	
	public static void main(String[] args) {
		String a = "1,2-3";
		String array[] = Pattern.compile("[,-]").split(a);
		System.out.println(Arrays.asList(array));
		
		List<String> lista = new ArrayList<>();
		lista.add(",");
		lista.add("-");
		System.out.println(lista);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
