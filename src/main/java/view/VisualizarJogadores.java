package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

public class VisualizarJogadores {
	private JFrame frameVisT;
	private final int LARGURA_TELA = 500;
	private final int ALTURA_TELA = 500;
	private GridBagConstraints c = new GridBagConstraints();
	
	private JPanel panel;
	private JButton btnProx;
	private JButton btnAnt;
	private JButton btnUltimo;
	private JButton btnPrimeiro;
	
	private JLabel labelNome;
	private JLabel respNome;
	private JLabel labelIdade;
	private JLabel respIdade;
	private JLabel labelValor;
	private JLabel respValor;
	private JLabel labelNacionalidade;
	private JLabel respNacionalidade;
	private JLabel labelPosicao;
	private JLabel respPosicao;
	private JLabel labelTime;
	private JLabel respTime;
	
	private JButton btnExcluir;
	private JButton btnAlterar;
	
	private JTextField altNome;
	private JTextField altIdade;
	private JTextField altValor;
	private JTextField altNacionalidade;
	private JTextField altPosicao;
	private JTextField altTime;
	private JButton alterarJogador;
	
	private JPanel panelRodape;
	private JButton btnSair;
	private JButton btnVoltar;
	
	
	DAO<Jogador> daoJogadores = new DAO<Jogador>(Jogador.class);
	
	private int aux = 0;
	
	public VisualizarJogadores() {
		frameVisT = new JFrame("Visualizar Jogadores");
		frameVisT.setSize(new Dimension(LARGURA_TELA, ALTURA_TELA));
		frameVisT.setLocationRelativeTo(null);
		frameVisT.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initVisualizarJogadores();
		frameVisT.setVisible(true);
	}
	
	
	public void initVisualizarJogadores() {
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		
		c.weightx = 0;
		c.weighty = 1;
		
		JPanel panelBotao = new JPanel();
		btnProx = new JButton(">>");
		btnAnt = new JButton("<<");
		btnUltimo = new JButton("Ultimo");
		btnPrimeiro = new JButton("Primeiro");
		
		panelBotao.add(btnPrimeiro);
		panelBotao.add(btnAnt);
		panelBotao.add(btnProx);
		panelBotao.add(btnUltimo);
		
		labelNome = new JLabel("Nome:");
		respNome = new JLabel();
		configurarLabels(labelNome, respNome, c, 0, 0);
		
		labelIdade = new JLabel("Idade:");
		respIdade = new JLabel();
		configurarLabels(labelIdade, respIdade, c, 0, 1);
		
		labelValor = new JLabel("Valor:");
		respValor = new JLabel();
		configurarLabels(labelValor, respValor, c, 0, 2);
		
		labelNacionalidade =new JLabel("Nacionalidade:");
		respNacionalidade = new JLabel();
		configurarLabels(labelNacionalidade, respNacionalidade, c, 0, 3);
		
		labelPosicao = new JLabel("Posição:");
		respPosicao = new JLabel();
		configurarLabels(labelPosicao, respPosicao, c, 0, 4);
		
		labelTime = new JLabel("Time:");
		respTime = new JLabel();
		configurarLabels(labelTime, respTime, c, 0, 5);
		

		
		passarElementos(respNome, respIdade, respValor, respNacionalidade, respPosicao, respTime, aux);
		
		btnPrimeiro.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				aux = 0;
				passarElementos(respNome, respIdade, respValor, respNacionalidade, respPosicao, respTime, aux);
				panel.repaint();
			}
		
		});
		
		btnAnt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				aux--;
				if(aux < 0) aux = 0;
				passarElementos(respNome, respIdade, respValor, respNacionalidade, respPosicao, respTime, aux);
				panel.repaint();
			}
		
		});
		
		
		btnProx.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				aux++;
				if(aux > daoJogadores.obterTodos().size()-1) aux = daoJogadores.obterTodos().size()-1;
				passarElementos(respNome, respIdade, respValor, respNacionalidade, respPosicao, respTime, aux);
				panel.repaint();
			}
		
		});
		
		btnUltimo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				aux = daoJogadores.obterTodos().size()-1;
				passarElementos(respNome, respIdade, respValor, respNacionalidade, respPosicao, respTime, aux);
				panel.repaint();
			}
		
		});
		
		btnExcluir = new JButton("Excluir Jogador");
		btnExcluir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Jogador jogadorAExcluir = daoJogadores.obterTodos().get(aux);
				daoJogadores.removerEntidade(jogadorAExcluir);
				if(daoJogadores.obterTodos().size() > 0) {
					aux = 0;
					passarElementos(respNome, respIdade, respValor, respNacionalidade, respPosicao, respTime, aux);
				}else {
					respNome.setText("");
					respIdade.setText("");
					respValor.setText("");
					respNacionalidade.setText("");
					respPosicao.setText("");
					respTime.setText("");
				}
			}
			
		});
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy =7;
		panel.add(btnExcluir,c);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				alterarJogadores();
				
			}
			
		});
		
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy =7;
		panel.add(btnAlterar,c);
		
		panelRodape = new JPanel();
		panelRodape.setLayout(new BorderLayout());
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new SairActionListener());
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frameVisT.dispose();
				new TelaInicial();
			}
			
		});
		
		
		panelRodape.add(BorderLayout.EAST, btnSair);
		panelRodape.add(BorderLayout.WEST, btnVoltar);
		
		
		frameVisT.getContentPane().add(BorderLayout.NORTH, panelBotao);
		frameVisT.getContentPane().add(BorderLayout.WEST, panel);
		frameVisT.getContentPane().add(BorderLayout.SOUTH, panelRodape);
	}
	
	public void configurarLabels(JLabel label, JLabel labelResp, GridBagConstraints c, int i, int j) {
		label.setFont(new Font("Arial", Font.PLAIN, 20));
		
		label.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 0));
		labelResp.setFont(new Font("Arial", Font.PLAIN, 20));
		
		labelResp.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 0));
		
		c.gridx = i;
		c.gridy = j;
		panel.add(label, c);
		int iT = i+1;
		c.gridx = iT;
		panel.add(labelResp, c);
	}
	
	public void passarElementos(JLabel respNome, JLabel respIdade, JLabel respValor, JLabel respNacionalidadem, JLabel respPosicao, JLabel respTime, int i) {
		
		List<Jogador> jogadores = daoJogadores.obterTodos();
		Jogador jogadorSelecionado = jogadores.get(i);
		
		respNome.setText(jogadorSelecionado.getNome());
		respIdade.setText(Integer.toString(jogadorSelecionado.getIdade()));
		respValor.setText(jogadorSelecionado.getValor());
		respNacionalidade.setText(jogadorSelecionado.getNacionalidade());
		respPosicao.setText(jogadorSelecionado.getPosicao());
		respTime.setText(jogadorSelecionado.getTime().getNome());
		
		
	}
	
	public void configurarTextField(JTextField textField) {
		textField.setFont(new Font("Arial", Font.PLAIN, 15));
		textField.setPreferredSize(new Dimension(200, 30));
	}
	
	public void alterarJogadores() {
		altNome = new JTextField(respNome.getText());
		altIdade = new JTextField(respIdade.getText());
		altValor = new JTextField(respValor.getText());
		altNacionalidade = new JTextField(respNacionalidade.getText());
		altPosicao = new JTextField(respPosicao.getText());
		altTime = new JTextField(respTime.getText());
		
		
		configurarTextField(altNome);
		configurarTextField(altIdade);
		configurarTextField(altValor);
		configurarTextField(altNacionalidade);
		configurarTextField(altPosicao);
		configurarTextField(altTime);
		
		
		respNome.setVisible(false);
		respIdade.setVisible(false);
		respValor.setVisible(false);
		respNacionalidade.setVisible(false);
		respPosicao.setVisible(false);
		respTime.setVisible(false);
		
		
		alterarJogador = new JButton("Alterar Jogador");
		
		c.gridx = 1;
		c.gridy = 0;
		panel.add(altNome,c);
		
		c.gridy = 1;
		panel.add(altIdade, c);
		
		c.gridy = 2;
		panel.add(altValor, c);
		
		c.gridy=3;
		panel.add(altNacionalidade, c);
		
		c.gridy=4;
		panel.add(altPosicao, c);
		
		c.gridy=5;
		panel.add(altTime, c);
		
		c.gridy = 6;
		panel.add(alterarJogador,c);
		
		btnAlterar.setVisible(false);
		
		panel.repaint();
		
		
		altNome.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				altNome.setText("");
			}
			
		});
		
		altIdade.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				altIdade.setText("");
			}
			
		});
		
		altValor.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				altValor.setText("");
			}
			
		});
		
		altNacionalidade.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				altNacionalidade.setText("");
			}
			
		});
		
		altPosicao.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				altPosicao.setText("");
			}
			
		});
		
		altTime.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				altTime.setText("");
			}
			
		});
		
		alterarJogador.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Jogador jogadorAlterado = daoJogadores.obterTodos().get(aux);	
				Times timeDoJogador = jogadorAlterado.getTime();
				timeDoJogador.setNome(altTime.getText());
				
				daoJogadores.alterarJogador(jogadorAlterado, altNome.getText(), altIdade.getText(), altValor.getText(), altNacionalidade.getText(), altPosicao.getText(),timeDoJogador);
				
				altNome.setVisible(false);
				altIdade.setVisible(false);
				altValor.setVisible(false);
				altNacionalidade.setVisible(false);
				altPosicao.setVisible(false);
				altTime.setVisible(false);
				
				respNome.setVisible(true);
				respIdade.setVisible(true);
				respValor.setVisible(true);
				respNacionalidade.setVisible(true);
				respPosicao.setVisible(true);
				respTime.setVisible(true);
				
				
				alterarJogador.setVisible(false);
				btnAlterar.setVisible(true);
				
				panel.repaint();
				passarElementos(respNome, respIdade, respValor, respNacionalidade, respPosicao, respTime, aux);
				
				
				
			}
			
		});
		
	}
	
}
