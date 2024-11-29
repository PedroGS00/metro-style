package com.metrostyle.models;

public class Carrinho {
	int id_item_carrinho;
	int id_carrinho;
	int id_cliente;
	int id_produto;
	int quantidade;
	String nomeProduto;
	double preco_unitario;
	double subtotal;
	
	
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_clientes) {
		this.id_cliente = id_clientes;
	}
	public int getId_item_carrinho() {
		return id_item_carrinho;
	}
	public void setId_item_carrinho(int id_item_carrinho) {
		this.id_item_carrinho = id_item_carrinho;
	}
	public int getId_carrinho() {
		return id_carrinho;
	}
	public void setId_carrinho(int id_carrinho) {
		this.id_carrinho = id_carrinho;
	}
	public int getId_produto() {
		return id_produto;
	}
	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getNomeProduto() {
	    return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
	    this.nomeProduto = nomeProduto;
	}
	public double getPreco_unitario() {
		return preco_unitario;
	}
	public void setPreco_unitario(double preco_unitario) {
		this.preco_unitario = preco_unitario;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
}
