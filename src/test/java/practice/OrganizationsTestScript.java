package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

public class OrganizationsTestScript {
	
	public static void main(String[] args) throws IOException {
		
		Random r = new Random();
		int random = r.nextInt(1000);
		
		FileInputStream fisp = new FileInputStream(".\\src\\test\\resources\\config.properties");
		Properties pOBJ = new Properties();
		pOBJ.load(fisp);
		
		String URL = pOBJ.getProperty("url");
		String BROWSER1 = pOBJ.getProperty("browser1");
	    String BROWSER2 = pOBJ.getProperty("browser2");
	    String USERNAME = pOBJ.getProperty("username");
	    String PASSWORD = pOBJ.getProperty("password");
	    
	    FileInputStream fisx = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
	    Workbook wb = WorkbookFactory.create(fisx);
	    String ORGNAME = wb.getSheet("Organizations").getRow(1).getCell(2).getStringCellValue()+random;
	    System.out.println(ORGNAME);
	    
	    WebDriver driver = null;
	    if(BROWSER1.equalsIgnoreCase("chrome")) {
	    	driver = new ChromeDriver();
	    }else if(BROWSER2.equalsIgnoreCase("edge")) {
	    	driver = new EdgeDriver();
	    }else {
	    	System.out.println("invalid browser name");
	    }
	    driver.get(URL);
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    
	    driver.findElement(By.name("user_name")).sendKeys(USERNAME);
	    driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
	    driver.findElement(By.id("submitButton")).click();
	    driver.findElement(By.linkText("Organizations")).click();
	    driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
	    driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
	    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	    String OrgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	    
	    if(OrgHeader.equalsIgnoreCase(ORGNAME)) {
	    	System.out.println(OrgHeader+ "---PASS---" );
	    }else {
	    	System.out.println(OrgHeader+ "---FAIL---");
	    }
	    WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	    Actions act = new Actions(driver);
	    act.moveToElement(ele).perform();
	    driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
	    System.out.println("Organization created Successfully");
	    driver.close();
	    
	    
	    
	    		
	    
	    
	    
	    	
	    
	    
	}

}
