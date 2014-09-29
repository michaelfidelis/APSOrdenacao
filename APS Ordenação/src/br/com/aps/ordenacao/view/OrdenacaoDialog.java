package br.com.aps.ordenacao.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import br.com.aps.ordenacao.controller.Control;

public class OrdenacaoDialog extends JDialog {

	
	private static final long serialVersionUID = 1L;
	
	public OrdenacaoDialog(String dados) {
		setTitle("Dados Ordenados");
		setMinimumSize(new Dimension(450, 300));
		setLocationRelativeTo(null);
		setModal(true);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setContentType("text/html");

		editorPane.setText(dados);
		scrollPane.setViewportView(editorPane);
		
		JPanel bottomPane = new JPanel();
		bottomPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		getContentPane().add(bottomPane, BorderLayout.SOUTH);
		
		JLabel lblTempo = new JLabel();
		lblTempo.setText(Control.getTemposOrdenacao());
		bottomPane.add(lblTempo);
		
		
		

	}
	
	
	
	
}
