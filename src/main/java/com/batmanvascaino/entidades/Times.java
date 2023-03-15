package com.batmanvascaino.entidades;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "times")
public class Times implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String campeonato;
	
	@Column(nullable = false)
	private String nacionalidade;
	
	public Times() {
		
	}

	public Times(String nome, String campeonato, String nacionalidade) {
		super();
		this.nome = nome;
		this.campeonato = campeonato;
		this.nacionalidade = nacionalidade;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(String campeonato) {
		this.campeonato = campeonato;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
	
	
}
