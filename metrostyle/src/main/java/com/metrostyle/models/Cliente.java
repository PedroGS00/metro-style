package com.metrostyle.models;

public class Cliente {
	private int id_cliente;
	private String nome;
	private String user;
	private String senha;
	
	public int getId() {
		return id_cliente;
	}
	public void setId(int id) {
		this.id_cliente = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
