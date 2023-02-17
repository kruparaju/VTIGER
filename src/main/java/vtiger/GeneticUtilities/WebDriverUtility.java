package vtiger.GeneticUtilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
	 /**
	  * this class consists of generic methods related to webdriver actions
	  */
	
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	public void minimizingWindow(WebDriver driver) 
	{
		driver.manage().window().minimize();
	}
	public void waitForPage(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	public void visibilityOfElement(WebDriver driver,WebElement ele) 
	{
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(0));
	    wait.until(ExpectedConditions.visibilityOf(ele));
	}
	public void elementToBeClickable(WebDriver driver,WebElement ele )
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(ele));				
	}
	public void select_By_VisibleText(WebDriver driver,WebElement ele,String text)
	{
		Select sel = new Select(ele);
		sel.selectByVisibleText(text);
	}
	public void select_By_Index(WebDriver driver,WebElement ele,int num )
	{
		Select sel = new Select(ele);
		sel.selectByIndex(num);
	}
	public void clickOnElemnt(WebDriver driver,WebElement ele) 
	{
		Actions act = new Actions(driver);
		act.click().perform();
	}
	public void move_To_Element(WebDriver driver, WebElement ele )
	{
		Actions act = new Actions(driver);
		act.moveToElement(ele).perform();
	}
	public void right_Click(WebDriver driver,WebElement ele)
	{
		Actions act = new Actions(driver);
		act.contextClick(ele).perform();
	}
	public void double_Click(WebDriver driver,WebElement ele) 
	{
		Actions act = new Actions(driver);
		act.doubleClick(ele).perform();
	}
        public void double_Click2(WebDriver driver,WebElement ele) 
	{
		Actions act = new Actions(driver);
		act.doubleClick(ele).perform();
	}
	
	

}
