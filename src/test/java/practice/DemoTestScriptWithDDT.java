package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DemoTestScriptWithDDT {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		FileInputStream fisp = new FileInputStream(".\\src\\test\\resources\\config.properties");
		Properties pOBJ = new Properties();
		pOBJ.load(fisp);
		
	    String URL=	pOBJ.getProperty("url");
	    String BROWSER = pOBJ.getProperty("browser");
	   // String BROWSER2 = pOBJ.getProperty("browser2");
	   // String BROWSER3 = pOBJ.getProperty("browser2")
	    String USERNAME = pOBJ.getProperty("username");
	    String PASSWORD = pOBJ.getProperty("password");
	    
	    FileInputStream fisx = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
	    Workbook wb = WorkbookFactory.create(fisx);
	    String LASTNAME =wb.getSheet("Contacts").getRow(1).getCell(2).getStringCellValue();
	    
	    WebDriver driver = null;
	    
	    if(BROWSER.equalsIgnoreCase("chrome")) {
	    	
	    	WebDriverManager.chromedriver().setup();
	    	driver = new ChromeDriver();
	    }else if(BROWSER.equalsIgnoreCase("firefox")) {
	    	
	    	WebDriverManager.firefoxdriver().setup();
	    	driver = new FirefoxDriver();
	    }else if(BROWSER.equalsIgnoreCase("edge")){
	    	WebDriverManager.edgedriver().setup();
	    	driver= new EdgeDriver();
	    }else{
	    	System.out.println("invalid browser name");
	    }
	    
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    driver.get(URL);
	    
	    driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USERNAME);
	    driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(PASSWORD);
	    driver.findElement(By.xpath("//input[@id='submitButton']")).click();
	    
	    driver.findElement(By.linkText("Contacts")).click();
	    driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
	    driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
	    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	    
	    String ContactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	    
	    if(ContactHeader.contains(LASTNAME)) {
	    	System.out.println(ContactHeader+ "----- PASS ------");
	    }else {
	    	System.out.println("----FAIL----");
	    }
	    
	    WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	    
	    Actions act = new Actions(driver);
	    act.moveToElement(ele).perform();
	    
	    driver.findElement(By.linkText("Sign Out")).click();
	    System.out.println("Sign Out Successfull");
	    
	    
	    
	    
	    
	    
	    	
	    
		

	}

}
