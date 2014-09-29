package br.com.aps.ordenacao.model.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class OrdenacaoDAO {

	// Le o arquivo e separa pela string passada como parametro
	public static String[] getDadosDoArquivo(File file) {
		String conteudo = "";
		String linha;

		// Com o try-with-resources eu não preciso "fechar" os readers.
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {

			linha = br.readLine();
			while (linha != null) {
				conteudo += linha;
				linha = br.readLine();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return conteudo.split(",");
	}

}
