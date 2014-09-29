package br.com.aps.ordenacao.model.bean;

public class QuickSort extends Ordenacao{
	
	private String[] vetor = null;
	private long tempoOrdenacao;
	
	public QuickSort(String[] vetor) {
		Cronometro.inicia();
		this.vetor = ordena(vetor, 0, vetor.length-1);
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

	private int meioDoVetor(String[] vetor, int inicioVet, int fimVet) {
		String backup = "";

		backup = vetor[inicioVet];

		for (int i = inicioVet + 1; i <= fimVet; i++) {
			if (backup.compareToIgnoreCase(vetor[i]) > 0) {
				vetor[inicioVet] = vetor[i];
				vetor[i] = vetor[inicioVet + 1];
				inicioVet++;
			}
		}
		vetor[inicioVet] = backup;
		return inicioVet;
	}

	private String[] ordena(String[] vetor, int inicioVet, int fimVet) {
		int meio;
		if (inicioVet < fimVet) {
			meio = meioDoVetor(vetor, inicioVet, fimVet);
			ordena(vetor, inicioVet, meio);
			ordena(vetor, meio + 1, fimVet);
		}
		return vetor;
	}

	@Override
	public String toString() {
		return String.format("QuickSort: %5dms", tempoOrdenacao);
	}

	
}
