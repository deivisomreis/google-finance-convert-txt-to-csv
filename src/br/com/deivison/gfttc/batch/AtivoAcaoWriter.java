package br.com.deivison.gfttc.batch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import br.com.deivison.gfttc.model.AtivoAcao;
import br.com.deivison.gfttc.model.comparator.AtivoAcaoComparator;
import br.com.deivison.gfttc.repository.AtivoAcaoRepository;

public class AtivoAcaoWriter {

	private static final String fileName = "/tmp/finance.csv";
	
	public static void fileWriter(String fileName) throws IOException{
		AtivoAcaoRepository repository = new AtivoAcaoRepository();
		List<AtivoAcao> listAtivos = repository.list();
		Collections.sort(listAtivos, new AtivoAcaoComparator());
		
		if(listAtivos.size() > 0){
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(new File(fileName)));
			
			buffWrite.write("Empresa, Ação, Valor, Variação\n");
			for(AtivoAcao atv : listAtivos){
				buffWrite.write(atv.getEmpresa() +"," + atv.getAcao() + ",\"" + atv.getVlrFechamentoDia().replace(" BRL", "") + "\",\"" + atv.getVariacaoDiaPercentual() + "\"\n");
				buffWrite.flush();
			}
			buffWrite.close();
		}
	}
	
	public static File fileWriterPaste(String data) throws IOException{
		File file = null;
		if(data != null && !data.isEmpty()){
			file = new File("/tmp/tmp.txt");
			
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(new File("/tmp/tmp.txt")));
			buffWrite.write(data);
			buffWrite.close();
		}
		
		return file;
	}
	
}
