package br.com.aps.ordenacao.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import br.com.aps.ordenacao.controller.Control;
import br.com.aps.ordenacao.model.TableModel;
import br.com.aps.ordenacao.model.dao.OrdenacaoDAO;

public class TabelaPrincipal extends JFrame {

	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}

		TabelaPrincipal frame = new TabelaPrincipal();
		frame.setVisible(true);

	}

	private JPanel panelTop;
	private JPanel panelCental;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JPanel panelDown;
	private JLabel lblDigite;
	private JFileChooser fileChooser;
	private JMenuBar menuBar;
	private JMenu mnDados;
	private JMenuItem mnIportarDoArquivo;
	private JLabel lblItens;
	private JLabel lblContItens;
	private JTable table;
	private TableModel tableModel;
	private JMenuItem mnLimpar;
	private JMenu mnOrdenacao;
	private JMenuItem mntmOrdenaTabela;
	private JMenuItem mnComparaMetodos;
	private Control control;
	private OrdenacaoDialog ordenacaoDialog;
	private JMenu mnAjuda;
	private JMenuItem mntmManual;
	public TabelaPrincipal() {
		setTitle("Ordenação de Dados");
		setSize(450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);
		
		tableModel = new TableModel();
		fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		panelTop = new JPanel();

		panelTop.setLayout(new BorderLayout(0, 0));

		panelCental = new JPanel();
		panelCental.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		panelCental.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(tableModel);
		scrollPane.setViewportView(table);

		textField = new JTextField();
		textField.setToolTipText("Digite algo e pressione [ENTER]");
		textField.setColumns(10);
		panelTop.add(textField);
		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!textField.getText().equals("")) {
					tableModel.addItem(textField.getText());
					textField.setText("");
					lblContItens.setText("" + getTotalItens());
					textField.requestFocus();
				} else {
					JOptionPane.showMessageDialog(scrollPane,
							"Este campo não pode ser vazio!", "Alerta",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		lblDigite = new JLabel("Digite alguma palavra: ");
		panelTop.add(lblDigite, BorderLayout.WEST);

		panelDown = new JPanel();
		panelDown.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		lblItens = new JLabel("Itens: ");
		panelDown.add(lblItens);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnDados = new JMenu("Dados");
		menuBar.add(mnDados);

		mnIportarDoArquivo = new JMenuItem("Do arquivo...");
		mnIportarDoArquivo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_MASK));
		mnDados.add(mnIportarDoArquivo);

		mnLimpar = new JMenuItem("Limpar");
		mnLimpar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		mnLimpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int escolha = JOptionPane
						.showConfirmDialog(
								scrollPane,
								"Tem certeza que deseja remover\n todos os itens da tabela?",
								"Cuidado", JOptionPane.YES_NO_OPTION,
								JOptionPane.WARNING_MESSAGE);
				if (escolha == JOptionPane.YES_OPTION) {
					tableModel.limpar();
					lblContItens.setText("0");
				}

			}
		});
		mnDados.add(mnLimpar);

		mnOrdenacao = new JMenu("Ordenação");
		menuBar.add(mnOrdenacao);

		mntmOrdenaTabela = new JMenuItem("Ordena Tabela");
		mntmOrdenaTabela.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		mntmOrdenaTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel.ordenar();

			}
		});
		mnOrdenacao.add(mntmOrdenaTabela);

		mnComparaMetodos = new JMenuItem("Compara Métodos");
		mnComparaMetodos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		mnComparaMetodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control = new Control(tableModel.getArray());
			ordenacaoDialog = new OrdenacaoDialog(control.getDadosOrdenados());
			ordenacaoDialog.setVisible(true);
			}
		});

	
		mnOrdenacao.add(mnComparaMetodos);
		
		mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);
		
		mntmManual = new JMenuItem("Dicas");
		mntmManual.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mntmManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(textField,"<html>"
					+ "<strong>Dicas</strong>"
					+ "<hr/>"
					+ "<b>Selecionar do arquivo</b><br/>"
					+ "Seleciona os itens de um arquivo com informações (Strings)<br/>"
					+ "separados por <b>, (Vírgula) </b>"
					+ ""
					+ "<br/><br/>"
					+ "<b>Digitar dado</b><br/>"
					+ "Digite um dado na caixa de texto principal para inseri-lo<br/> na lista"
					+ ""
					+ "<br/><br/>"
					+ "<b>Comparar ordenações</b><br/>"
					+ "Exibe todos os dados ordenados alfabeticamente, bem como <br/> "
					+ "o tempo de cada ordenação, juntamente com a ordenação de melhor "
					+ "desempenho."
					+ ""
					+ "</html>", "Manual", JOptionPane.PLAIN_MESSAGE);
			
			
			
			
			}
		});
		mnAjuda.add(mntmManual);

		mnIportarDoArquivo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addItensDoArquivo(selecionaArquivo());
			}
		});

		getContentPane().add(panelTop, BorderLayout.NORTH);
		getContentPane().add(panelCental, BorderLayout.CENTER);
		getContentPane().add(panelDown, BorderLayout.SOUTH);

		lblContItens = new JLabel("0");
		panelDown.add(lblContItens);

	}

	private void addItensDoArquivo(File arquivo) {
		int contador = 0;
		String[] dados;
		if (arquivo != null) {
			dados = OrdenacaoDAO.getDadosDoArquivo(arquivo);
			for (String s : dados) {
				tableModel.addItem(s.trim());
				contador++;
			}
			JOptionPane.showMessageDialog(scrollPane, contador
					+ " elemento(s) inserido(s)!", "Alerta",
					JOptionPane.PLAIN_MESSAGE);
			lblContItens.setText("" + getTotalItens());

		}
	}

	private int getTotalItens() {
		return tableModel.getRowCount();

	}

	// Uma vez definido que os dados virão de um arquivo, não se
	private File selecionaArquivo() {
		File file = null;
		int escolha = fileChooser.showOpenDialog(this);

		if (escolha != JFileChooser.CANCEL_OPTION) {
			file = fileChooser.getSelectedFile();
			System.out.println(file);
		} else {
			System.out.println("Arquivo não selecionado!");
		}
		return file;
	}
}
