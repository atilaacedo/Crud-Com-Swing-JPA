package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.batmanvascaino.entidades.Times;

import infra.DAO;
import view.listener.SairActionListener;


public class VisualizarTimes {
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
	private JLabel labelCampeonato;
	private JLabel respCampeonato;
	private JLabel labelNacionalidade;
	private JLabel respNacionalidade;
	
	private JButton btnExcluir;
	private JButton btnAlterar;
	
	private JTextField altNome;
	private JTextField altCampeonato;
	private JTextField altNacionalidade;
	private JButton alterarTime;
	
	private JPanel panelRodape;
	private JButton btnSair;
	private JButton btnVoltar;
	
	
	DAO<Times> daoTimes = new DAO<Times>(Times.class);
	
	private int aux = 0;
	
	public VisualizarTimes() {
		frameVisT = new JFrame("Visualizar Times");
		frameVisT.setSize(new Dimension(LARGURA_TELA, ALTURA_TELA));
		frameVisT.setLocationRelativeTo(null);
		frameVisT.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initVisualizarTimes();
		frameVisT.setVisible(true);
	}
	
	
	public void initVisualizarTimes() {
		
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
		
		labelCampeonato = new JLabel("Campeonato:");
		respCampeonato = new JLabel();
		configurarLabels(labelCampeonato, respCampeonato, c, 0, 1);
		
		labelNacionalidade = new JLabel("Nacionalidade:");
		respNacionalidade = new JLabel("");
		configurarLabels(labelNacionalidade, respNacionalidade, c, 0, 2);
		

		
		passarElementos(respNome, respCampeonato, respNacionalidade, aux);
		
		btnPrimeiro.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				aux = 0;
				passarElementos(respNome, respCampeonato, respNacionalidade, aux);
				panel.repaint();
			}
		
		});
		
		btnAnt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				aux--;
				if(aux < 0) aux = 0;
				passarElementos(respNome, respCampeonato, respNacionalidade, aux);
				panel.repaint();
			}
		
		});
		
		
		btnProx.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				aux++;
				if(aux > daoTimes.obterTodos().size()-1) aux = daoTimes.obterTodos().size()-1;
				passarElementos(respNome, respCampeonato, respNacionalidade, aux);
				panel.repaint();
			}
		
		});
		
		btnUltimo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				aux = daoTimes.obterTodos().size()-1;
				passarElementos(respNome, respCampeonato, respNacionalidade, aux);
				panel.repaint();
			}
		
		});
		
		btnExcluir = new JButton("Excluir Time");
		btnExcluir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Times timeAExcluir = daoTimes.obterTodos().get(aux);
				daoTimes.removerEntidade(timeAExcluir);
				if(daoTimes.obterTodos().size() > 0) {
					aux = 0;
					passarElementos(respNome, respCampeonato, respNacionalidade, aux);
				}else {
					respNome.setText("");
					respCampeonato.setText("");
					respNacionalidade.setText("");
				}
			}
			
		});
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy =3;
		panel.add(btnExcluir,c);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				alterarTimes();
				
			}
			
		});
		
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy =3;
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
	
	public void passarElementos(JLabel respNome, JLabel respCampeonato, JLabel respNacionalidade, int i) {
		
		List<Times> times = daoTimes.obterTodos();
		Times timeSelecionado = times.get(i);
		
		respNome.setText(timeSelecionado.getNome());
		respCampeonato.setText(timeSelecionado.getCampeonato());
		respNacionalidade.setText(timeSelecionado.getNacionalidade());
		
		
	}
	
	public void alterarTimes() {
		altNome = new JTextField(respNome.getText());
		altCampeonato = new JTextField(respCampeonato.getText());
		altNacionalidade = new JTextField(respNacionalidade.getText());
		
		altNome.setFont(new Font("Arial", Font.PLAIN, 15));
		altNome.setPreferredSize(new Dimension(200, 30));
		altCampeonato.setFont(new Font("Arial", Font.PLAIN, 15));
		altCampeonato.setPreferredSize(new Dimension(200, 30));
		altNacionalidade.setFont(new Font("Arial", Font.PLAIN, 15));
		altNacionalidade.setPreferredSize(new Dimension(200, 30));
		
		
		respNome.setVisible(false);
		respCampeonato.setVisible(false);
		respNacionalidade.setVisible(false);
		
		alterarTime = new JButton("Alterar Time");
		
		c.gridx = 1;
		c.gridy = 0;
		panel.add(altNome,c);
		
		c.gridy = 1;
		panel.add(altCampeonato, c);
		
		c.gridy = 2;
		panel.add(altNacionalidade, c);
		
		c.gridy = 3;
		panel.add(alterarTime,c);
		
		btnAlterar.setVisible(false);
		
		panel.repaint();
		
		
		altNome.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				altNome.setText("");
			}
			
		});
		
		altCampeonato.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				altCampeonato.setText("");
			}
			
		});
		
		altNacionalidade.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				altNacionalidade.setText("");
			}
			
		});
		
		alterarTime.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Times timeAlterado = daoTimes.obterTodos().get(aux);	
				
				daoTimes.alterarTime(timeAlterado, altNome.getText(), altCampeonato.getText(), altNacionalidade.getText());
				
				altNome.setVisible(false);
				altCampeonato.setVisible(false);
				altNacionalidade.setVisible(false);
				
				respNome.setVisible(true);
				respCampeonato.setVisible(true);
				respNacionalidade.setVisible(true);
				
				alterarTime.setVisible(false);
				btnAlterar.setVisible(true);
				
				panel.repaint();
				passarElementos(respNome, respCampeonato, respNacionalidade, aux);
				
				
				
			}
			
		});
		
	}
	
}
