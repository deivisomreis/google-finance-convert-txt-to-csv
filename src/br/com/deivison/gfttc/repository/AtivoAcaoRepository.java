package br.com.deivison.gfttc.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.deivison.gfttc.model.AtivoAcao;

public class AtivoAcaoRepository {
	
	private static List<AtivoAcao> ativos;
	
	public AtivoAcaoRepository() {
		if(ativos == null)
			ativos = new ArrayList<AtivoAcao>();
	}
	
	public void addAcao(AtivoAcao atv){
		ativos.add(atv);
		System.out.println(atv.toString()  + " adicionado ao repositorio");
	}
	
	public void clearRepository(){
		ativos.clear();
		System.out.println("repositório vazio");
	}
	
	public List<AtivoAcao> list(){
		return ativos;
	}

}
