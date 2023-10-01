package Projeto1;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


public class ExcursaoInterface {

	private JFrame frameExcursao;
	private Excursao excursao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExcursaoInterface window = new ExcursaoInterface();
					window.frameExcursao.setVisible(true);
		
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ExcursaoInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		//frame principal
		frameExcursao = new JFrame();
		frameExcursao.setResizable(false);
		frameExcursao.setTitle("Criador de Excursão");
		frameExcursao.setBounds(100, 100, 407, 465);
		frameExcursao.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameExcursao.getContentPane().setLayout(null);
		frameExcursao.setLocationRelativeTo(null);
		
		try {
			excursao = new Excursao(1);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}


		//area de texto
		JTextArea txtrAsReservasDa = new JTextArea();
		txtrAsReservasDa.setLineWrap(true);
		txtrAsReservasDa.setFont(new Font("Gadugi", Font.PLAIN, 13));
		txtrAsReservasDa.setText("Os dados da excursão aparecerão aqui.");
		txtrAsReservasDa.setBounds(170, 74, 200, 302);
		txtrAsReservasDa.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		txtrAsReservasDa.requestFocus();
		txtrAsReservasDa.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txtrAsReservasDa.getText().equals("Os dados da excursão aparecerão aqui.")) {
		        	txtrAsReservasDa.setText("");
		        	txtrAsReservasDa.setForeground(Color.BLACK);
		        }
		    }
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txtrAsReservasDa.getText().isEmpty()) {
		        	txtrAsReservasDa.setForeground(Color.BLACK);
		        	txtrAsReservasDa.setText("Os dados da excursão aparecerão aqui.");
		        }
		    }
		    });
		frameExcursao.getContentPane().add(txtrAsReservasDa);
		
		//titulo
		JLabel lblNewLabel = new JLabel("Projeto 1 - Excursão");
		lblNewLabel.setFont(new Font("Gadugi", Font.BOLD, 19));
		lblNewLabel.setBounds(102, 11, 180, 23);
		frameExcursao.getContentPane().add(lblNewLabel);
		
		//subtitulo
		JLabel lblNewLabel_3 = new JLabel("Grupo: Luiz Henrique e Lucas Jaud");
		lblNewLabel_3.setBounds(102, 34, 205, 14);
		lblNewLabel_3.setFont(new Font("Gadugi", Font.PLAIN, 11));
		frameExcursao.getContentPane().add(lblNewLabel_3);
		
		//subtitulo 1
		JLabel lblNewLabel_1 = new JLabel("Reservas");
		lblNewLabel_1.setFont(new Font("Gadugi", Font.BOLD, 13));
		lblNewLabel_1.setBounds(59, 192, 63, 14);
		frameExcursao.getContentPane().add(lblNewLabel_1);
		
		//subtitulo 2
		JLabel lblNewLabel_2 = new JLabel("Excursão");
		lblNewLabel_2.setFont(new Font("Gadugi", Font.BOLD, 13));
		lblNewLabel_2.setBounds(59, 77, 63, 14);
		frameExcursao.getContentPane().add(lblNewLabel_2);
		
		//versao
		JLabel label = new JLabel("V1.0");
		label.setFont(new Font("Gadugi", Font.BOLD, 12));
		label.setBounds(73, 396, 46, 14);
		frameExcursao.getContentPane().add(label);
		
		//botao: criar reserva
		JButton btnCriarRes = new JButton("Criar reserva");
		btnCriarRes.setFont(new Font("Gadugi", Font.PLAIN, 12));
		btnCriarRes.setEnabled(false);
		btnCriarRes.setBounds(29, 217, 120, 23);
		btnCriarRes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cpf = JOptionPane.showInputDialog("Digite o CPF: ");
				String nome = JOptionPane.showInputDialog("Digite o nome: ");
				try {
					excursao.criarReserva(cpf, nome);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());			
				}
			}
		});
		frameExcursao.getContentPane().add(btnCriarRes);
		
		//botao: cancelar reserva
		JButton btnCancelarRes = new JButton("Cancelar(Nome)");
		btnCancelarRes.setBounds(29, 285, 120, 23);
		btnCancelarRes.setEnabled(false);
		btnCancelarRes.setFont(new Font("Gadugi", Font.PLAIN, 12));
		btnCancelarRes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					excursao.cancelarReserva(
							JOptionPane.showInputDialog("Digite o CPF: "),
							JOptionPane.showInputDialog("Digite o nome: "));
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		frameExcursao.getContentPane().add(btnCancelarRes);
		
		//botao: cancelar cpf
		JButton btnCancelarRes_1 = new JButton("Cancelar(CPF)");
		btnCancelarRes_1.setFont(new Font("Gadugi", Font.PLAIN, 12));
		btnCancelarRes_1.setEnabled(false);
		btnCancelarRes_1.setBounds(29, 251, 120, 23);
		btnCancelarRes_1.setEnabled(false);
		btnCancelarRes_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					excursao.cancelarReserva(JOptionPane.showInputDialog("Digite o CPF: "));
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		frameExcursao.getContentPane().add(btnCancelarRes_1);
		
		//botao: listar por CPF
		JButton btnListarCPF = new JButton("Listar por CPF");
		btnListarCPF.setBounds(29, 319, 120, 23);
		btnListarCPF.setFont(new Font("Gadugi", Font.PLAIN, 12));
		btnListarCPF.setEnabled(false);
		btnListarCPF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String CPF = JOptionPane.showInputDialog("Digite o CPF: ");
				
				ArrayList<String> reservas = excursao.listarReservasPorCpf(CPF);
				
				String aux = "";
				
				for (String reserva : reservas) {
					aux += "CPF: " + reserva.split("/")[0] + "  Nome: "+reserva.split("/")[1]+ "\n";
				}
				
				txtrAsReservasDa.setText(aux);
			}
		});
		frameExcursao.getContentPane().add(btnListarCPF);

		//botao: valor total
		JButton btnValorTotal = new JButton("Valor total");
		btnValorTotal.setFont(new Font("Gadugi", Font.PLAIN, 12));
		btnValorTotal.setEnabled(false);
		btnValorTotal.setBounds(29, 136, 120, 23);
		btnValorTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtrAsReservasDa.setText("Valor total: R$: "+excursao.calcularValorTotal());
			}
		});
		frameExcursao.getContentPane().add(btnValorTotal);
		
		//botao: salvar
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Gadugi", Font.PLAIN, 12));
		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(170, 387, 89, 23);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excursao.salvarExcursao();
			}
		});
		frameExcursao.getContentPane().add(btnSalvar);
	
		//botao: listar por nome
		JButton btnListarNome = new JButton("Listar por nome");
		btnListarNome.setFont(new Font("Gadugi", Font.PLAIN, 12));
		btnListarNome.setBounds(29, 353, 120, 23);
		btnListarNome.setEnabled(false);
		btnListarNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = JOptionPane.showInputDialog("Digite o nome: ");
				
				ArrayList<String> reservas = excursao.listarReservasPorCpf(nome);
				
				String aux = "";
				
				for (String reserva : reservas) {
					aux += "CPF: " + reserva.split("/")[0] + "  Nome: "+reserva.split("/")[1]+ "\n";
				}
				
				txtrAsReservasDa.setText(aux);
					
			}
		});
		frameExcursao.getContentPane().add(btnListarNome);
		
		//botao: criar excursão
		JButton btnCriarExcursao = new JButton("Criar");
		btnCriarExcursao.setFont(new Font("Gadugi", Font.PLAIN, 12));
		btnCriarExcursao.setBounds(29, 102, 120, 23);
		btnCriarExcursao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código da excursão: "));
					double preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o preço da excursão: "));
					int max = Integer.parseInt(JOptionPane.showInputDialog("Digite o máximo de reservas da excursão: "));
			
					excursao = new Excursao(codigo,preco,max);
					JOptionPane.showMessageDialog(null, "Excursão " + codigo + " criada com sucesso.");
					btnListarNome.setEnabled(true);
					btnSalvar.setEnabled(true);
					btnValorTotal.setEnabled(true);
					btnListarCPF.setEnabled(true);
					btnCancelarRes.setEnabled(true);
					btnCriarRes.setEnabled(true);
					btnCancelarRes_1.setEnabled(true);
					
					excursao.salvarExcursao();
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Formato numerico invalido");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} 
			}
		});
		frameExcursao.getContentPane().add(btnCriarExcursao);
		
		//botao: carregar
		JButton btnCarregar = new JButton("Carregar");
		btnCarregar.setFont(new Font("Gadugi", Font.PLAIN, 12));
		btnCarregar.setBounds(281, 387, 89, 23);
		btnCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código da excursão: "));
					excursao.carregarExcursao(codigo);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				btnListarNome.setEnabled(true);
				btnSalvar.setEnabled(true);
				btnValorTotal.setEnabled(true);
				btnListarCPF.setEnabled(true);
				btnCancelarRes.setEnabled(true);
				btnCriarRes.setEnabled(true);
				btnCancelarRes_1.setEnabled(true);
			}
		});
		frameExcursao.getContentPane().add(btnCarregar);
	}
}