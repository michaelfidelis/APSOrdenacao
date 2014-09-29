package br.com.aps.ordenacao.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.aps.ordenacao.model.bean.BubbleSort;
import br.com.aps.ordenacao.model.bean.InsertSort;
import br.com.aps.ordenacao.model.bean.Ordenacao;
import br.com.aps.ordenacao.model.bean.QuickSort;

public class Control {

	private static BubbleSort bubbleSort;
	private static InsertSort insertSort;
	private static QuickSort quickSort;
	private static String[] dados;

	

	public Control(String[] vetor) {
		dados = vetor;
		bubbleSort = new BubbleSort(dados.clone());
		insertSort = new InsertSort(dados.clone());
		quickSort = new QuickSort(dados.clone());

		
	}

	public Ordenacao getMelhorOrdenacao() {
		List<Ordenacao> ordenacoes = new ArrayList<>();
		ordenacoes.add(bubbleSort);
		ordenacoes.add(insertSort);
		ordenacoes.add(quickSort);
		return Ordenacao.getMaisRapido(ordenacoes);
	}

	public static String getTemposOrdenacao() {
		return String.format("%-10s || %-10s || %-10s ", bubbleSort, insertSort , quickSort);
	}

	public String getDadosOrdenados() {
		StringBuilder sbuilder = new StringBuilder();
		
		String[] vetorBubble = bubbleSort.getDadosOdenados();
		String[] vetorInsert = insertSort.getDadosOdenados();
		String[] vetorQuick = quickSort.getDadosOdenados();
		sbuilder.append("<table style='width: 100%; border: 1px; border-collapse:collapse;'>");
		sbuilder.append(String.format("<tr style='font: bold'><td>%s</td><td>%s</td><td>%s</td></tr>", "Bubble", "Insert" , "Quick"));
		sbuilder.append("<tr><td colspan='3'><hr></td></tr>");
		for(int x = 0; x < dados.length; x++){
			sbuilder.append(String.format("<tr><td>%s</td><td>%s</td><td>%s</td></tr>", vetorBubble[x], vetorInsert[x] , vetorQuick[x]));
		}
		sbuilder.append("</table>");
		
		if(dados.length != 0) {
		sbuilder.append("<hr>");
		sbuilder.append("<b>Melhor Ordenação: </b> "+ getMelhorOrdenacao());
		}
		return sbuilder.toString();
	}
}
