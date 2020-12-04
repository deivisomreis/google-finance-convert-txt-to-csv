package br.com.deivison.gfttc.model.comparator;

import java.util.Comparator;

import br.com.deivison.gfttc.model.AtivoAcao;

public class AtivoAcaoComparator implements Comparator<AtivoAcao>{

	@Override
	public int compare(AtivoAcao a, AtivoAcao b) {
		return a.getAcao().compareToIgnoreCase(b.getAcao());
	}

}
