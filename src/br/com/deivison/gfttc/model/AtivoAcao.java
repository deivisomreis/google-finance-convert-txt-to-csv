package br.com.deivison.gfttc.model;

import java.io.Serializable;

public class AtivoAcao implements Serializable{
 
	private static final long serialVersionUID = 1L;
	
	public AtivoAcao() { }
	
	

	public AtivoAcao(String empresa, String acao, String vlrFechamentoDia, String variacaoDiaPercentual) {
		this.empresa = empresa;
		this.acao = acao;
		this.vlrFechamentoDia = vlrFechamentoDia;
		this.variacaoDiaPercentual = variacaoDiaPercentual;
	}



	private String empresa;
	private String acao;
	private String vlrFechamentoDia;
	private String variacaoDiaPercentual;
	
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
	
	@Override
	public String toString() {
		return "AtivoAcao [empresa=" + empresa + ", acao=" + acao
				+ ", vlrFechamentoDia=" + vlrFechamentoDia
				+ ", variacaoDiaPercentual=" + variacaoDiaPercentual + "]";
	}
	
	
}
