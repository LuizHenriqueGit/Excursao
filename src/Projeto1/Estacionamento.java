package Projeto1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
 
/**
 * TSI - POO - Prof. Fausto Ayres
 * 
 * Implementação da classe Estacionamento usando ArrayList
 * 
 */

public class Estacionamento {
	private ArrayList<String> placas = new ArrayList<>();

	public Estacionamento(int n) throws Exception {
		if (n <= 0)
			throw new Exception("estacionamento deve ter num. de vagas > 0");

		for (int i = 0; i < n; i++) {
			placas.add("vazia");
		}

	}

	public void entrar(String placa, int vaga) throws Exception {
		// ocupar a vaga com a placa
		// a vaga tem que estar vazia
		if (vaga > placas.size() || vaga < 1)
			throw new Exception("entrar - vaga inexistente " + vaga);

		if (!placas.get(vaga - 1).equals("vazia"))
			throw new Exception("entrar - vaga esta ocupada " + vaga);

		placas.set(vaga - 1, placa);

		// ----------------------------------------------------------------------------------------------------
		// gravar dados (data,vaga,placa) no arquivo historico.csv
		// ----------------------------------------------------------------------------------------------------
		File f = new File(new File(".\\historico.csv").getCanonicalPath()); // pasta do projeto
		FileWriter arq = new FileWriter(f, true); // abrir arquivo para adição de dados
		String dataFormatada = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm:ss"));
		arq.write(dataFormatada + ";" + vaga + ";" + placa + " - Entrada \n");
		arq.close();
		// ----------------------------------------------------------------------------------------------------
	}

	public void sair(int vaga) throws Exception {
		// desocupar a vaga
		// a vaga tem que estar ocupada
		if (vaga > placas.size() || vaga < 1)
			throw new Exception("sair - vaga inexistente " + vaga);

		if (placas.get(vaga - 1).equals("vazia")) {
			throw new Exception("sair - vaga nao esta ocupada " + vaga);
		}
		String placasalva = placas.get(vaga - 1);
		placas.set(vaga - 1, "vazia");

		// ----------------------------------------------------------------------------------------------------
		// gravar dados (data,vaga,placa) no arquivo historico.csv
		// ----------------------------------------------------------------------------------------------------
		File f = new File(new File(".\\historico.csv").getCanonicalPath()); // pasta do projeto
		FileWriter arq = new FileWriter(f, true); // abrir arquivo para adição de dados
		String dataFormatada = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm:ss"));
		arq.write(dataFormatada + ";" + vaga + ";" + placasalva + " - Saida \n");
		arq.close();
		// ----------------------------------------------------------------------------------------------------

	}

	public int consultarPlaca(String placa) {
		// retorna a vaga da placa, ou -1 caso nao exista
		for (int i = 0; i < placas.size(); i++) {
			if (placas.get(i).equals(placa))
				return i + 1;
		}
		return -1;
	}

	public void transferir(int vaga1, int vaga2) throws Exception {
		// move a placa da vaga1 para a vaga2
		// verificar vaga1 e vaga2
		if (vaga1 > placas.size() || vaga1 < 1)
			throw new Exception("transferir - vaga inexistente " + vaga1);
		if (vaga2 > placas.size() || vaga2 < 1)
			throw new Exception("transferir - vaga inexistente " + vaga2);
		if (vaga1 == vaga2)
			throw new Exception("transferir - Vaga origem e destino devem ser distintas");
		if (placas.get(vaga1 - 1).equals("vazia"))
			throw new Exception("transferir - A vaga origem esta livre!");
		if (!placas.get(vaga2 - 1).equals("vazia")) {
			throw new Exception("transferir - A vaga destino nao esta livre!");
		}

		String temporario = placas.get(vaga2 - 1);
		placas.set(vaga2 - 1, placas.get(vaga1 - 1));
		placas.set(vaga1 - 1, temporario);
	}

	public ArrayList<String> listarGeral() {
		return placas;
	}

	public ArrayList<Integer> listarLivres() {
		// retorna uma lista com as posicoes vazias
		ArrayList<Integer> livres = new ArrayList<>();

		for (int i = 0; i < placas.size(); i++) {
			if (placas.get(i).equals("vazia"))
				livres.add(i + 1); // vaga é indice+1

		}
		return livres;
	}

	public void carregar() throws Exception {
		// ler cada linha do arquivo texto "placas.csv", contendo as placas das vagas
		// ocupadas
		// exemplo de arquivo com 3 linhas (a primeira contem o cabecalho):
		// vaga;placa
		// 1;AAA1234
		// 5;BBB1234

		try {
			File f = new File(new File(".\\placas.csv").getCanonicalPath()); // pasta do projeto
			// System.out.println(f.getAbsolutePath()); //ver caminho do S.O.
			Scanner arquivo = new Scanner(f);
			String linha, placa;
			int vaga;
			String[] partes;
			String cabecalho = arquivo.nextLine(); // primeira linha de cabecalho é descartada
			while (arquivo.hasNextLine()) {
				linha = arquivo.nextLine(); // leitura de uma linha
				partes = linha.split(";"); // separa os campos da linha
				vaga = Integer.parseInt(partes[0]); // campo da vaga
				placa = partes[1]; // campo da placa
				placas.set(vaga - 1, placa); // coloca a placa na lista indexada pela vaga-1
			}
		} catch (FileNotFoundException e) {
			// criar arquivo vazio se o arquivo nao existir
			File f = new File(new File(".\\placas.csv").getCanonicalPath()); // pasta do projeto
			FileWriter arquivo2 = new FileWriter(f, false); // append=false apaga todo conteudo
			arquivo2.close();
		}
	}

	public void gravar() throws Exception {
		// (re)gravar no arquivo placas.csv, vaga e placa ocupada no momento
		try {
			File f = new File(new File(".\\placas.csv").getCanonicalPath()); // pasta do projeto
			FileWriter arquivo = new FileWriter(f, false); // append=false apaga o conteudo anterior
			arquivo.write("vaga;placa\n"); // grava primeira linha de cabecalho
			int vaga;
			String placa;
			for (int i = 0; i < placas.size(); i++)
				if (!placas.get(i).equals("vazia")) { // somente vagas ocupadas
					vaga = i + 1;
					placa = placas.get(i);
					arquivo.write(vaga + ";" + placa + "\n");
				}
			arquivo.close();
		} catch (IOException e) {
			throw new Exception("problema na gravacao do arquivo de saida");
		}
	}
}
