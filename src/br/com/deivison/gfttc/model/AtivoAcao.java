package br.com.deivison.gfttc.model;

import java.io.Serializable;
import java.util.Date;

import br.com.deivison.gfttc.model.enums.Cotador;

public class AtivoAcao implements Serializable{
 
	private static final long serialVersionUID = 1L;
	
	public AtivoAcao() { }
	
	

	public AtivoAcao(String empresa, String acao, String vlrFechamentoDia, String variacaoDiaPercentual, Cotador cotador) {
		this.empresa = empresa;
		this.acao = acao;
		this.vlrFechamentoDia = vlrFechamentoDia;
		this.variacaoDiaPercentual = variacaoDiaPercentual;
		this.cotador = cotador;
	}

	private String empresa;
	private String acao;
	private String vlrFechamentoDia;
	private String variacaoDiaPercentual;
	private Cotador cotador;
	private Date dataCotacao;
	
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public String getVlrFechamentoDia() {
		return vlrFechamentoDia;
	}
	public void setVlrFechamentoDia(String vlrFechamentoDia) {
		this.vlrFechamentoDia = vlrFechamentoDia;
	}
	public String getVariacaoDiaPercentual() {
		return variacaoDiaPercentual;
	}
	public void setVariacaoDiaPercentual(String variacaoDiaPercentual) {
		this.variacaoDiaPercentual = variacaoDiaPercentual;
	}
	public String getUrlCotador(){
		return cotador.getUrlCotador() + this.acao;
	}
	
	@Override
	public String toString() {
		return "AtivoAcao [empresa=" + empresa + ", acao=" + acao
				+ ", vlrFechamentoDia=" + vlrFechamentoDia
				+ ", variacaoDiaPercentual=" + variacaoDiaPercentual + "]";
	}
}
