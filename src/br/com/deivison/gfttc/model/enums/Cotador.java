package br.com.deivison.gfttc.model.enums;

public enum Cotador {

	STATUS_INVEST("https://statusinvest.com.br/acoes/");
	
	private String urlCotador;

	private Cotador(String urlCotador){
		this.urlCotador = urlCotador;
	}
	
	
	public String getUrlCotador(){
		return this.urlCotador;
	}
}
