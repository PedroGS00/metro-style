package com.metrostyle.models;

public class Produto {
    private int id_produto;
    private String nome;
    private String desc;
    private double preco;
    private int estoque;

    public int getId() {
        return id_produto;
    }
    public void setId(int id_produto) {
        this.id_produto = id_produto;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }
    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
}

