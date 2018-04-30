package br.com.starwars.planeta.vo;

import java.util.Collection;

public class PlanetaVO {
	
	public String nome;
	public String clima;
	public String terreno;
	public Collection<String> filmes;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getClima() {
		return clima;
	}
	public void setClima(String clima) {
		this.clima = clima;
	}
	public String getTerreno() {
		return terreno;
	}
	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}
	public Collection<String> getFilmes() {
		return filmes;
	}
	public void setFilmes(Collection<String> filmes) {
		this.filmes = filmes;
	}
	
}
