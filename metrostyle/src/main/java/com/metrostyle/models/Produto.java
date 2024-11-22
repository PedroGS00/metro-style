package com.metrostyle.models;

public class Produto {
	private int id_produto;
	private String marca;
	private String desc;
	private double valor;
	
	public int getId() {
		return id_produto;
	}
	public void setId(int id) {
		this.id_produto = id;
	}
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
}
