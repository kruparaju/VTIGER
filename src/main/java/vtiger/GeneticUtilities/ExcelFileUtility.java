package vtiger.GeneticUtilities;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {
	
	/**
	 * This method will read the data from excel File
	 * @param name
	 * @param rownum
	 * @param cellnum
	 * @return
	 * @throws IOException
	 */
	
	
	public String readDataFromExcel(String name, int rownum, int cellnum) throws EncryptedDocumentException, IOException {
		
		
		FileInputStream fis = new FileInputStream(IConstantUtility.ExcelFilePath);
	    Workbook wb = WorkbookFactory.create(fis);
		String stringCellValue = wb.getSheet(name).getRow(rownum).getCell(cellnum).getStringCellValue();
		wb.close();
		return stringCellValue;
	}
	public int getRowCount(String name) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream(IConstantUtility.ExcelFilePath);
		Workbook wb = WorkbookFactory.create(fis);
		int lastRowNum = wb.getSheet(name).getLastRowNum();
		wb.close();
		return lastRowNum;
	}
	public void writeDataFromExcel(String name, int rownum,int cellnum, String value) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream(IConstantUtility.ExcelFilePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(name);
		Row row = sh.getRow(rownum);
	    row.createCell(cellnum).setCellValue(value);
		//ce.setCellValue(value);
		
		FileOutputStream fos = new FileOutputStream(IConstantUtility.ExcelFilePath);
		wb.write(fos);
		System.out.println(value+"---is added");
		wb.close();
		
	}
	
	
	
 	
}
