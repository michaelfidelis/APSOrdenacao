package br.com.aps.ordenacao.model.bean;

public class InsertSort extends Ordenacao {
	
	private String[] vetor;
	private long tempoOrdenacao;
	
	
	public InsertSort(String[] vetor) {
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
		for (int i = 0; i < vetor.length; i++) {
			int x = i - 1;
			String eleito = vetor[i];
			while ((x >= 0) && eleito.compareToIgnoreCase(vetor[x]) < 0) {
				vetor[x + 1] = vetor[x];
				x--;
			}
			vetor[x + 1] = eleito;
		}
		return vetor;
	}

	@Override
	public String toString() {
		return String.format("InsertSort: %5dms", tempoOrdenacao);
	}
}
