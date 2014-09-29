package br.com.aps.ordenacao.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.aps.ordenacao.model.bean.QuickSort;

public class TableModel extends AbstractTableModel {
	private static final int COL_ITEM = 0;

	private List<String> itens;

	public TableModel() {
		this.itens = new ArrayList<String>();
	}

	public TableModel(List<String> valores) {
		this.itens = new ArrayList<String>(valores);
	}

	public void addItem(String item) {
		itens.add(item);
		int ultimoItem = getRowCount() - 1;
		fireTableRowsInserted(ultimoItem, ultimoItem);
	}

	private void addList(List<String> itens) {
		this.itens.addAll(itens);
	}

	@Override
	public Class getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public String getColumnName(int columnIndex) {
		if (columnIndex == COL_ITEM)
			return "Item";
		else
			return "";
	}

	public List<String> getList() {
		return itens;
	}

	public String[] getArray() {
		String[] vetor = new String[itens.size()];
		return itens.toArray(vetor);

	}

	@Override
	public int getRowCount() {
		return itens.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		String item = itens.get(row);
		if (column == COL_ITEM)
			return item;
		return "Inválido!";
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public void limpar() {
		itens.clear();
		fireTableDataChanged();
	}

	public void ordenar() {
		String[] vetor = getArray();
		new QuickSort(vetor);
		this.itens = Arrays.asList(vetor);
		fireTableDataChanged();

	}
}
