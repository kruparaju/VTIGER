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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class OrganizationWindows {

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
	    String LASTNAME =wb.getSheet("Contacts").getRow(1).getCell(2).getStringCellValue();
	    System.out.println(LASTNAME);
	    
	    FileInputStream fise = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb1 = WorkbookFactory.create(fise);
		String ORGNAME = wb1.getSheet("Organizations").getRow(1).getCell(2).getStringCellValue() + random;
		System.out.println(ORGNAME);
		
		WebDriver driver = null;

		if (BROWSER1.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER2.equalsIgnoreCase("edge")) {
			driver = new ChromeDriver();
		} else {
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
		    
		    driver.findElement(By.linkText("Organizations")).click();
			driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
			driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
			
			WebElement dropdown = driver.findElement(By.name("industry"));
		    Select sel = new Select(dropdown);
		    sel.selectByVisibleText("Energy");
		    
		    WebElement typedrpdown = driver.findElement(By.name("accounttype"));
		    Select sel1 = new Select(typedrpdown);
		    sel1.selectByVisibleText("Customer");
		    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		    
		    String OrgValidation = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		    
		    if(OrgValidation.contains(ORGNAME)) {
		    	System.out.println(OrgValidation+ "----- PASS ------");
		    }else {
		    	System.out.println("----FAIL----");
		    }
		    
		    WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		    Actions act = new Actions(driver);
		    act.moveToElement(ele).perform();
		    
		    WebElement logout = driver.findElement(By.xpath("//a[text()='Sign Out']"));
		    JavascriptExecutor js = (JavascriptExecutor)driver;
		    js.executeScript("arguments[0].click();", logout);
		    
		    System.out.println("dropdowns selected successfully");


	}

}
