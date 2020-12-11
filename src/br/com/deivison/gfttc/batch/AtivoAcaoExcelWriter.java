package br.com.deivison.gfttc.batch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import br.com.deivison.gfttc.model.AtivoAcao;
import br.com.deivison.gfttc.model.comparator.AtivoAcaoComparator;
import br.com.deivison.gfttc.repository.AtivoAcaoRepository;
import br.com.deivison.gfttc.utils.AtivoAcaoUtils;

public class AtivoAcaoExcelWriter {

	private static final String fileName = "/tmp/finance.csv";
	private static String pathDir = "/home/deivisonreis/google-finance/";
	private static SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy_HHmmss");
	private static Workbook wb = null;
	
	public static String createWorkbook(){
		AtivoAcaoRepository repository = new AtivoAcaoRepository();
		List<AtivoAcao> listAtivos = repository.list();
		Collections.sort(listAtivos, new AtivoAcaoComparator());
		String urlFile =  null;
		
		if(listAtivos.size() > 0){
			Date dataCotacao = new Date();
			wb = new HSSFWorkbook();
			Sheet sheet1 = wb.createSheet("Cotação");
			createHeader(sheet1);
			
			int contRow = 0;
			for(AtivoAcao atv : listAtivos){
				if(!atv.getAcao().equalsIgnoreCase("ibov")){
					Row row = sheet1.createRow(++contRow);
					createBody(row, atv, dataCotacao);
				}
			}
			
			try{
				urlFile = pathDir + sdf.format(new Date()) + ".xls";
				OutputStream os = new FileOutputStream(urlFile);
				wb.write(os);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return urlFile;
	}
	
	private static void createHeader(Sheet sheet){
		Row linha1 = sheet.createRow(0);
		
		Cell col1 = linha1.createCell(0);
		Cell col2 = linha1.createCell(1);
		Cell col3 = linha1.createCell(2);
		Cell col4 = linha1.createCell(3);
		Cell col5 = linha1.createCell(4);
		Cell col6 = linha1.createCell(5);
		
		
		col1.setCellValue("Empresa");
		col2.setCellValue("Data");
		col3.setCellValue("Ação");
		col4.setCellValue("Valor");
		col5.setCellValue("Variação");
		col6.setCellValue("Cotação");
		
		formatHeader(Arrays.asList(col1, col2, col3, col4, col5, col6));
	}
	
	private static void createBody(Row row, AtivoAcao atv, Date dataCotacao){
		row.createCell(0).setCellValue(atv.getEmpresa());
		
		Cell col2 = row.createCell(1);
		col2.setCellValue(dataCotacao);
		formatDate(col2);
		
		row.createCell(2).setCellValue(atv.getAcao());
		
		Cell col4 = row.createCell(3); 
		col4.setCellValue(AtivoAcaoUtils.convertValorDia(atv.getVlrFechamentoDia()));
		formatCurrencyValue(col4);
		
		Cell col5 = row.createCell(4); 
		col5.setCellValue(Double.parseDouble(atv.getVariacaoDiaPercentual().replace(",", ".")) / 100);
		formatPercentage(col5);
		
		Hyperlink link = wb.getCreationHelper().createHyperlink(HyperlinkType.URL);
		link.setAddress(atv.getUrlCotador());
		
		Cell col6 = row.createCell(5);
		col6.setCellValue("Status Invest :: " + atv.getAcao());
		col6.setHyperlink(link);
	}
	
	private static void formatPercentage(Cell cell){
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setDataFormat(wb.createDataFormat().getFormat("0.0000%"));
		cell.setCellStyle(cellStyle);
	}
	
	private static void formatCurrencyValue(Cell cell){
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setDataFormat(wb.createDataFormat().getFormat("_(R$* #,##0.00_);_(R$* (#,##0.00);_(R$* \"-\"??_);_(@_)"));
		cell.setCellStyle(cellStyle);
	}
	
	private static void formatHeader(List<Cell> cells){
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER);

		Font font = wb.createFont();
		font.setBold(true);
		cellStyle.setFont(font);
		
		for(Cell cell : cells)
			cell.setCellStyle(cellStyle);
	}
	
	private static void formatDate(Cell cell){
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setDataFormat(wb.createDataFormat().getFormat("dd/mm/yyyy"));
		cell.setCellStyle(cellStyle);
	}
}
