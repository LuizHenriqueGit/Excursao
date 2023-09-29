package Projeto1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Excursao {
	
	// Atributos
	
	private int codexc; // código da excursão
	private double precoex; // preco da excursão
	private int max; // máximo de reservas
	private ArrayList<String> listadereservas = new ArrayList<String>(); // lista de reservas da excursão
	
	// Construtores
	
	public Excursao(int codigo, double preco, int maximo) throws Exception {
		if (codigo <=0 || preco <= 0 || maximo <= 0)
			throw new IllegalArgumentException("Proibido valores menores que 0.");
			
			codexc = codigo;
			precoex = preco;
			max = maximo;
	}

	public Excursao(int codigo) throws Exception{
		if (codigo <= 0)
			throw new IllegalArgumentException("Proibido valores menores que 0.");
		codexc = codigo;
	}
	
	// Métodos
	
	public void criarReserva(String cpf, String nome) throws Exception {
		
		if (max == listadereservas.size())
			throw new Exception("Número máximo de vagas atingido, não é possível criar novas reservas.");
		
		if (cpf.equals("") || nome.equals(""))
			throw new Exception("CPF e nome não podem ser vazios.");
		
		String[] reserva;
		
		if (listadereservas.size() > 0) {
			for (int i = 0; i < listadereservas.size(); i++) {
				reserva = listadereservas.get(i).split("/");
				if (reserva[1].equals(nome)) 
					throw new IllegalStateException("Nome já cadastrado na excursão");
			} 		
		}

		listadereservas.add(cpf + "/" +nome);
	}
	
	public void cancelarReserva(String cpf, String nome) throws Exception {
		
		boolean remove = listadereservas.removeIf(reserva -> reserva.equals(cpf + "/" + nome));
		if (!remove)
			throw new Exception("Reserva não encontrada.");
	}
	
	public void cancelarReserva(String cpf) throws Exception {
		
		boolean remove = listadereservas.removeIf(reserva -> reserva.split("/")[0].equals(cpf));
		if (!remove)
			throw new Exception("CPF não encontrado.");
	}
	
	public ArrayList<String> listarReservasPorCpf(String digitos) {

		ArrayList<String> resposta = new ArrayList<String>();
	
		if (digitos.length() > 0)
			for (int i = 0; i < listadereservas.size(); i++) {
				
				if (listadereservas.get(i).contains(digitos))
					resposta.add(listadereservas.get(i));
			}
		else 
			return listadereservas;
		
		return resposta;
	}
	
	public ArrayList<String> listarReservasPorNome(String texto) {
		
		ArrayList<String> resposta = new ArrayList<String>();
		
		if (texto.length() > 0)
			for (int i = 0; i < listadereservas.size(); i++) {
				
				if (listadereservas.get(i).contains(texto))
					resposta.add(listadereservas.get(i));	
			}
		else 
			return listadereservas;
		
		return resposta;
	}
	
	public double calcularValorTotal() {
		return listadereservas.size() * precoex;
	}
	
	public void salvarExcursao() {
		
		try {
		Path path = Path.of("./"+getCodexc()+".txt");
		
		if (Files.notExists(path)) {
			Files.createFile(path);
		}
		
		String preco = this.getPrecoex() + "\n";
		String maximo = this.getMax() + "\n";
		String reservas = this.getListadereservas() + "\n";

		Files.writeString(path, preco, StandardOpenOption.APPEND);
		Files.writeString(path, maximo, StandardOpenOption.APPEND);
		Files.writeString(path, reservas, StandardOpenOption.APPEND);

		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "Código da excursão: " + codexc +
				"\nValor da excursão:  " + precoex +
				"\nMáximo de reservas: " + max + 
				"\nTotal de reservas: " + listadereservas.size();
	}

	// Getters e Setters
	
	public int getCodexc() {
		return codexc;
	}

	public void setCodexc(int codexc) {
		if (codexc <= 0)
			throw new IllegalArgumentException("Proibido valores menores que 0.");
		this.codexc = codexc;
	}

	public double getPrecoex() {
		return precoex;
	}

	public void setPrecoex(double precoex) {
		if (precoex <= 0)
			throw new IllegalArgumentException("Proibido valores menores que 0.");
		this.precoex = precoex;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		if (max <= 0)
			throw new IllegalArgumentException("Proibido valores menores que 0.");
		this.max = max;
	}

	public ArrayList<String> getListadereservas() {
		return listadereservas;
	}
	
}