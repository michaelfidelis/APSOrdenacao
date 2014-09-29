package br.com.aps.ordenacao.model.bean;

import java.util.Collections;
import java.util.List;

public abstract class Ordenacao implements Comparable<Ordenacao> {
	
	public abstract long getTempoOrdenacao();
	public abstract String[] getDadosOdenados();
	
	public int compareTo(Ordenacao o) {
		if (this.getTempoOrdenacao() < o.getTempoOrdenacao()) {
			return -1;
		}

		if (this.getTempoOrdenacao() > o.getTempoOrdenacao()) {
			return 1;
		}

		return 0;
	}
	
	public static Ordenacao getMaisRapido(List<Ordenacao> ordenacoes) {
		Collections.sort(ordenacoes);
		return ordenacoes.get(0);
	}
	
	
}
