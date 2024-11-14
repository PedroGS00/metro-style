package model;

import java.io.Serializable;
public class Cliente implements Serializable{
	
private String nome;
private String sobrenome;
private String cidade;
private String sexo;
private String []idiomas;

public Cliente() {}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getSobrenome() {
	return sobrenome;
}

public void setSobrenome(String sobrenome) {
	this.sobrenome = sobrenome;
}

public String getCidade() {
	return cidade;
}

public void setCidade(String cidade) {
	this.cidade = cidade;
}

public String getSexo() {
	return sexo;
}

public void setSexo(String sexo) {
	this.sexo = sexo;
}

public String []getIdiomas() {
	return idiomas;
}

public void setIdiomas(String[] idiomas) {
	this. idiomas = idiomas;
	}

}