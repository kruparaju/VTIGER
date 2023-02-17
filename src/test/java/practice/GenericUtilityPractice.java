package practice;

import java.io.IOException;

import vtiger.GeneticUtilities.ExcelFileUtility;
import vtiger.GeneticUtilities.PropertyFileUtility;

public class GenericUtilityPractice {

	public static void main(String[] args) throws IOException {


				
		
		PropertyFileUtility pUtil = new PropertyFileUtility();
		String URL = pUtil.readDataFromPropertyFile("url");
//		String USERNAME = pUtil.readDataFromPropertyFile("username");
//		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		System.out.println(URL);
		
		ExcelFileUtility eUtil = new ExcelFileUtility();
	    String value = eUtil.readDataFromExcel("Organizations", 1, 2);
	    System.out.println(eUtil.getRowCount("Organizations"));
		System.out.println(value);
		eUtil.writeDataFromExcel("Organizations", 1, 10, "love da");
		
		
		
		
	}

}
