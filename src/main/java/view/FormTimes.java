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

import com.batmanvascaino.entidades.Times;

import infra.DAO;
import view.listener.SairActionListener;

public class FormTimes implements ActionListener{
	
	JFrame frameFormT;
	private final int LARGURA_TELA = 500;
	private final int ALTURA_TELA = 500;
	
	private JPanel panel;
	private JLabel tittle;
	private JLabel name;
	private JTextField nameTF;
	private JLabel campeonato;
	private JTextField campeonatoTF;
	private JLabel nacionalidade;
	private JTextField nacionalidadeTF;
	private JButton btnSair;
	private JButton btnEnviar;
	private JButton btnVoltar;
	private JLabel enviado;
	
	DAO<Times> daoTime = new DAO<Times>(Times.class);
	
	public FormTimes() {
		frameFormT = new JFrame("Cadastrar Times");
		frameFormT.setSize(new Dimension(LARGURA_TELA, ALTURA_TELA));
		frameFormT.setLocationRelativeTo(null);
		frameFormT.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initFormTimes();
		
		frameFormT.setVisible(true);
	}
	
	
	public void initFormTimes() {
		JPanel panelT = new JPanel();
		JPanel panelSair = new JPanel();
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 0;
		c.weighty = 1;
			
		panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		
		tittle = new JLabel("Cadastrar Time");
		tittle.setFont(new Font("Arial", Font.BOLD, 30));
		
		name = new JLabel("Nome ");
		nameTF = new JTextField();
		
		configurarLabelField(name, nameTF,c, 0, 0);
		
		campeonato = new JLabel("Campeonato ");
		campeonatoTF = new JTextField();
		configurarLabelField(campeonato, campeonatoTF,c,0,1);
		
		nacionalidade = new JLabel("País");
		nacionalidadeTF = new JTextField();
		configurarLabelField(nacionalidade, nacionalidadeTF, c , 0, 2);
		
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy =3;
		btnEnviar = new JButton("Adicionar Time");
		btnEnviar.addActionListener(this);
		panel.add(btnEnviar, c);
	
	
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new SairActionListener());
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frameFormT.dispose();
				new TelaInicial();
			}
			
		});
		

		enviado = new JLabel("Time Adicionado!");
		enviado.setFont(new Font("Arial", Font.PLAIN, 14));
		enviado.setVisible(false);;
		
		
		panelSair.setLayout(new BorderLayout());
		panelSair.add(BorderLayout.EAST, btnSair);
		panelSair.add(BorderLayout.WEST, btnVoltar);
		panelSair.add(BorderLayout.CENTER, enviado);
		
		panelT.add(BorderLayout.CENTER, tittle);
		
		frameFormT.getContentPane().add(BorderLayout.NORTH, panelT);
		frameFormT.getContentPane().add(BorderLayout.WEST, panel);
		frameFormT.getContentPane().add(BorderLayout.SOUTH,panelSair);
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
		String nome = nameTF.getText();
		String campeonato = campeonatoTF.getText();
		String pais = nacionalidadeTF.getText();
		
		nameTF.setText("");
		campeonatoTF.setText("");
		nacionalidadeTF.setText("");
		
		Times time = new Times(nome, campeonato, pais);
		
		daoTime.incluirAtomico(time);
		enviado.setVisible(true);
		
	}
}
