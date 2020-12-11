package br.com.deivison.gfttc.batch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import br.com.deivison.gfttc.model.AtivoAcao;
import br.com.deivison.gfttc.model.enums.Cotador;
import br.com.deivison.gfttc.repository.AtivoAcaoRepository;

public class AtivoAcaoReader {

	private static String fileName = "/tmp/finance.txt";
	
	
	public static boolean readerFile(String file, File fileResult) throws IOException{
		File arquivo = initFile(file, fileResult);
		
		if(arquivo != null){
			BufferedReader buffRead = new BufferedReader(new FileReader(arquivo));
			AtivoAcaoRepository repository = new AtivoAcaoRepository();
			
			int contProp = 1;
			String empresa = "";
			String acao = "";
			String vlrDia = "";
			String variacaoDia = "";
			
			while (buffRead.ready()) {
				
				if(contProp == 1)
					empresa = buffRead.readLine();
				if(contProp == 2)
					acao  = buffRead.readLine();
				if(contProp == 3)
					vlrDia  = buffRead.readLine();
				if(contProp == 4){
					variacaoDia  = buffRead.readLine();
					String variacaoReplace = variacaoDia.contains("−") ?
							"-" + variacaoDia.substring(variacaoDia.indexOf("(") + 1, variacaoDia.indexOf("%")) :
								variacaoDia.substring(variacaoDia.indexOf("(") + 1, variacaoDia.indexOf("%"));
					repository.addAcao(new AtivoAcao(empresa, acao, vlrDia, variacaoReplace, Cotador.STATUS_INVEST));
				}
				
				contProp = contProp == 4 ? 1 : contProp + 1;
			}
			buffRead.close();
			return true;
		}
		
		else
			return false;
	}
	
	private static File initFile(String file, File fileResult){
		File arquivo = fileResult != null ? fileResult : new File(file);
		
		if(arquivo.exists())
			return arquivo;
		else{
			System.out.println("Arquivo não existe! Favor criar arquivo: " + arquivo.getAbsolutePath());
			JOptionPane.showMessageDialog(null, "Arquivo não existe! Favor criar arquivo: " + arquivo.getAbsolutePath(), "Google Finance", 0);
			return null;
		}
	}
}
