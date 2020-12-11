package br.com.deivison.gfttc.app;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import br.com.deivison.gfttc.batch.AtivoAcaoExcelWriter;
import br.com.deivison.gfttc.batch.AtivoAcaoReader;
import br.com.deivison.gfttc.batch.AtivoAcaoWriter;
import br.com.deivison.gfttc.model.AtivoAcao;
import br.com.deivison.gfttc.repository.AtivoAcaoRepository;

public class App {

	public static void main(String[] args) throws IOException {
		String inputFileName = "";
		String pathOutput = "";
		String outPutFileName = "";
		String dataInline = "";
		
//		dataInline = JOptionPane.showInputDialog(null, "Informe os dados:", "Google Finance", 1); 
//		File fileResult= AtivoAcaoWriter.fileWriterPaste(dataInline);
		
		while(inputFileName.isEmpty() && dataInline.isEmpty()){
			inputFileName = JOptionPane.showInputDialog(null, "Informe o caminho do arquivo:", "Google Finance", 1);
		}
		
		boolean readerFileStatus = AtivoAcaoReader.readerFile(inputFileName, null);
		
		if(readerFileStatus){
//			while(pathOutput.isEmpty()){
//				pathOutput = JOptionPane.showInputDialog(null, "Informe o diretório de saída:", "Google Finance", 1);
//			}
			
//			while(outPutFileName.isEmpty()){
//				outPutFileName = JOptionPane.showInputDialog(null, "Informe o nome do arquivo de saída:", "Google Finance", 1);
//			}
			
//			AtivoAcaoWriter.fileWriter(pathOutput + "/" + outPutFileName);
//			JOptionPane.showMessageDialog(null, "Processo finalizado com sucesso. Arquivo gerado: " + pathOutput + "/" + outPutFileName, "Google Finance", 3);
			
			String resultUrlFile = AtivoAcaoExcelWriter.createWorkbook();
			JOptionPane.showMessageDialog(null, "Processo finalizado com sucesso. Arquivo gerado: " + resultUrlFile, "Google Finance", 3);
			
			Runtime.getRuntime().exec("libreoffice " + resultUrlFile);
			System.exit(0);
		}
	}
}
