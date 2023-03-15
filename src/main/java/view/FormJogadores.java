package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.batmanvascaino.entidades.Jogador;
import com.batmanvascaino.entidades.Times;

import infra.DAO;
import view.listener.SairActionListener;

public class FormJogadores implements ActionListener{
	
	private JFrame frameFormJ;
	private final int LARGURA_TELA = 500;
	private final int ALTURA_TELA = 500;
	private JPanel panel;
	
	private JLabel tittle;
	private JLabel nome;
	private JTextField nomeTF;
	private JLabel idade;
	private JTextField idadeTF;
	private JLabel valor;
	private JTextField valorTF;
	private JLabel nacionalidade;
	private JTextField nacionalidadeTF;
	private JLabel posicao;
	private JTextField posicaoTF;
	private JLabel time;
	private JTextField timeTF;
	private JPanel panelRodape;
	private JButton btnSair;
	private JButton btnVoltar;
	private JButton btnEnviar;
	
	public FormJogadores() {
		frameFormJ = new JFrame("Cadastrar Jogadores");
		frameFormJ.setSize(new Dimension(LARGURA_TELA, ALTURA_TELA));
		frameFormJ.setLocationRelativeTo(null);
		frameFormJ.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initFormJogadores();
		
		frameFormJ.setVisible(true);
	}
	
	
	public void initFormJogadores() {
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 0;
		c.weighty = 1;
		
		
		JPanel panelT = new JPanel();
		tittle = new JLabel("Adicionar Jogador");
		tittle.setFont(new Font("Arial", Font.BOLD, 30));
		
		nome = new JLabel("Nome");
		nomeTF = new JTextField();
		configurarLabelField(nome, nomeTF, c, 0,0);
		
		idade = new JLabel("Idade");
		idadeTF = new JTextField();
		configurarLabelField(idade, idadeTF, c, 0, 1);
		
		valor = new JLabel("Valor");
		valorTF = new JTextField();
		configurarLabelField(valor, valorTF, c, 0, 2);
		
		nacionalidade = new JLabel("Nacionalidade");
		nacionalidadeTF = new JTextField();
		configurarLabelField(nacionalidade, nacionalidadeTF, c, 0, 3);
		
		posicao = new JLabel("Posição");
		posicaoTF = new JTextField();
		configurarLabelField(posicao, posicaoTF, c, 0, 4);
		
		time = new JLabel("Time");
		timeTF = new JTextField();
		configurarLabelField(time, timeTF, c, 0, 5);
		
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy =6;
		btnEnviar = new JButton("Adicionar Jogador");
		btnEnviar.addActionListener(this);
		panel.add(btnEnviar, c);
		
		
		panelRodape = new JPanel();
		panelRodape.setLayout(new BorderLayout());
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new SairActionListener());
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frameFormJ.dispose();
				new TelaInicial();
			}
			
		});
		
		
		panelRodape.add(BorderLayout.EAST, btnSair);
		panelRodape.add(BorderLayout.WEST, btnVoltar);
		
		panelT.add(tittle);
		
		frameFormJ.getContentPane().add(BorderLayout.NORTH, panelT);
		frameFormJ.getContentPane().add(BorderLayout.WEST, panel);
		frameFormJ.getContentPane().add(BorderLayout.SOUTH, panelRodape);
	}
	
	
	public void configurarLabelField(JLabel label, JTextField textField, GridBagConstraints c, int i, int j) {
		label.setFont(new Font("Arial", Font.PLAIN, 20));
		label.setPreferredSize(new Dimension(200,30));
		label.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 0));
		textField.setFont(new Font("Arial", Font.PLAIN, 15));
		textField.setPreferredSize(new Dimension(200, 30));
		
		c.gridx = i;
		c.gridy = j;
		panel.add(label, c);
		int iT = i+1;
		c.gridx = iT;
		panel.add(textField, c);
	}


	public void actionPerformed(ActionEvent e) {
		DAO<Jogador> daoJogador = new DAO<Jogador>(Jogador.class);
		
		
		String nome = nomeTF.getText();
		Integer idade = Integer.parseInt(idadeTF.getText());
		String valor = valorTF.getText();
		String nacionalidade = nacionalidadeTF.getText();
		String posicao = posicaoTF.getText();
		Times time = daoJogador.obterTimePeloNome(timeTF.getText());
		
		nomeTF.setText("");
		idadeTF.setText("");
		valorTF.setText("");
		nacionalidadeTF.setText("");
		posicaoTF.setText("");
		timeTF.setText("");
		
		Jogador jogador = new Jogador(nome, idade, valor, nacionalidade, posicao, time);
		
		daoJogador.incluirAtomico(jogador);
		
	}
	
	
}
