package co.com.blogspot.wilmarcp.calculator;

public class StringCalculator {

	private static final String default_separator = ",";
	
	public static int add(String valores) {
		int suma = 0;
		if(valores.isEmpty()){
			suma = 0;
			return suma;
		}
		
		suma = sumarElementos(obtenerValoresASumar(valores));
	
		return suma;
	}

	private static int sumarElementos(int[] valores) {
		int suma = 0;
		for (int valor : valores) {
			suma += valor;
		}
		return suma;
	}
	
	private static int[] obtenerValoresASumar(String cadenaNumerosASumar){
		int[] valores;
		
		String separador = extraerSeparadores(cadenaNumerosASumar);
		separador = separador.isEmpty() ? default_separator : separador;
		String valoresASumar = extraerValoresASumar(cadenaNumerosASumar, separador);
		
		valores = obtenerValoresNumericosASumar(separador, valoresASumar);
		
		return valores;
	}

	private static int[] obtenerValoresNumericosASumar(String separador, String valoresASumar) {
		String [] valoresIndividuales = valoresASumar.split("\\" + separador);
		int[] retorno = new int[valoresIndividuales.length];
		
		for (int i = 0; i < valoresIndividuales.length; i++) {
			retorno[i] = Integer.parseInt(valoresIndividuales[i]) ;
		}
		
		return retorno;
	}

	private static String extraerValoresASumar(String cadenaNumerosASumar, String separador) {
		String valoresASumar = "";
		if(cadenaNumerosASumar.startsWith("//")){
			valoresASumar = cadenaNumerosASumar.substring(cadenaNumerosASumar.indexOf("\n")+1);
		}else{
			valoresASumar = cadenaNumerosASumar;
		}
		
		valoresASumar = valoresASumar.replace("\n", ",");
				
		return valoresASumar;
	}

	private static String extraerSeparadores(String cadenaNumerosASumar) {
		String separador = "";
		if(cadenaNumerosASumar.startsWith("//")){
			separador = cadenaNumerosASumar.substring(cadenaNumerosASumar.indexOf("//") + 2, cadenaNumerosASumar.indexOf("\n"));
		}
		return separador;
	}
	

}
