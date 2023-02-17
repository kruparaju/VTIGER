package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class OrganizationDropdown {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		Random r = new Random();
		int random = r.nextInt();
				

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
	    
	    // Code for dropdown
	    WebElement dropdown = driver.findElement(By.name("industry"));
	    Select sel = new Select(dropdown);
	    sel.selectByVisibleText("Chemicals");
	    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	    
	    String OrgValidation = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	    
	    if(OrgValidation.contains(ORGNAME)) {
	    	System.out.println(OrgValidation+ "----- PASS ------");
	    }else {
	    	System.out.println("----FAIL----");
	    }
	    
	    // Code for Actoions Class
	    WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	    Actions act = new Actions(driver);
	    act.moveToElement(ele).perform();
	    Thread.sleep(3000);
	    
	    // Code for JavaScriptExecutor
	    WebElement logout = driver.findElement(By.xpath("//a[text()='Sign Out']"));
	    JavascriptExecutor js = (JavascriptExecutor)driver;
	    js.executeScript("arguments[0].click();", logout);
	    
	    System.out.println("dropdown selected successfully");

	    
	}

}
