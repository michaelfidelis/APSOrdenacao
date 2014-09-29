package br.com.aps.ordenacao.model.bean;


public class BubbleSort extends Ordenacao {

	private String[] vetor;
	private long tempoOrdenacao;
	
	public BubbleSort(String[] vetor) {
		Cronometro.inicia();
		this.vetor = ordena(vetor);
		Cronometro.para();
		
		tempoOrdenacao = Cronometro.tempoDecorrido();      
	}
	
	@Override
	public String[] getDadosOdenados() {
		return this.vetor;
	}

	@Override
	public long getTempoOrdenacao() {
		return tempoOrdenacao;
	}

	private String[] ordena(String[] vetor) {
		String backup = "";
		for (int i = 0; i < vetor.length; i++) {
			for (int x = 0; x < vetor.length - 1; x++) {

				/*
				 * BubbleSort: Ordenação em pares; Se vetor[1] > vetor[2],
				 * guardo o valor de vetor[1] em backup; vetor[1] recebe o valor
				 * de vetor[2] e, vetor[2] recebe o backup (que guarda o valor
				 * de vetor[1]);
				 */

				if (vetor[x].compareToIgnoreCase(vetor[x + 1]) > 0) {
					backup = vetor[x];
					vetor[x] = vetor[x + 1];
					vetor[x + 1] = backup;
				}
			}
		}
		
		return vetor;

	}

	@Override
	public String toString() {
		return String.format("BubbleSort: %5dms", tempoOrdenacao);
	}


	

}
