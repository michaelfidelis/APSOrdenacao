package br.com.aps.ordenacao.model.bean;

public abstract class Cronometro {
	
	 private static long inicio;  
	 private static long fim;  
	 private static long diferenca;
	 
	public static void inicia() {
		fim = 0;
		diferenca = 0;
		inicio = System.currentTimeMillis();
	}
	
	public static void para(){
		fim = System.currentTimeMillis();
		diferenca = (fim - inicio);
	}
	
	public static long tempoDecorrido(){
		return diferenca;
	}
}
