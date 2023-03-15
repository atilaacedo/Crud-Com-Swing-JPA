package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import infra.DAO;
import view.listener.SairActionListener;

public class TelaInicial {
	
	public JFrame frameInicial;	
	private JButton btnAdicionarTime, btnAdicionarJogador, btnMostrarTimes, btnMostrarJogadores, btnSair;
	private final int LARGURA_TELA = 500;
	private final int ALTURA_TELA = 500;
	
	public TelaInicial() {
		inicioProg();
	}
	
	
	public void inicioProg() {
		frameInicial = new JFrame("Cadastro times e jogadores");

		frameInicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		btnAdicionarTime = new JButton("Adicionar Time");
		btnAdicionarJogador = new JButton("Adicionar Jogador");
		btnMostrarTimes = new JButton("Mostrar Times");
		btnMostrarJogadores = new JButton("Mostrar Jogadores");
		btnSair = new JButton("Sair");
		
		btnAdicionarTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameInicial.dispose();
				new FormTimes();
			}
		});
		
		btnAdicionarJogador.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frameInicial.dispose();
				new FormJogadores();
				
			}
			
		});
		
		btnMostrarTimes.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new VisualizarTimes();
				frameInicial.dispose();
			}
			
		});
		
		btnMostrarJogadores.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new VisualizarJogadores();
				frameInicial.dispose();
				
			}
			
		});
		
		btnSair.addActionListener(new SairActionListener());
		
		JPanel painelMain = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = GridBagConstraints.REMAINDER;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		
		JPanel painelBotao = new JPanel();
		
		painelMain.add(btnAdicionarTime, gbc);
		painelMain.add(btnAdicionarJogador, gbc);
		painelMain.add(btnMostrarTimes, gbc);
		painelMain.add(btnMostrarJogadores, gbc);
		
		painelBotao.setLayout(new BorderLayout());
		
		painelBotao.add(BorderLayout.EAST , btnSair);
		frameInicial.getContentPane().setBackground(Color.black);
		frameInicial.getContentPane().add(BorderLayout.CENTER, painelMain);
		frameInicial.getContentPane().add(BorderLayout.SOUTH, painelBotao);
		
		frameInicial.setSize(new Dimension(LARGURA_TELA, ALTURA_TELA));
		frameInicial.setLocationRelativeTo(null);
		frameInicial.setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		new DAO<Object>();
		new TelaInicial();
	}
	
}
