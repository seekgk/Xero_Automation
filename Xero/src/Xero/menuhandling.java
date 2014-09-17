package Xero;

//import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class menuhandling {
	WebDriver driver = new FirefoxDriver();
	
	public void signup() throws InterruptedException
	{
		
		// URL can be fetched from config file or from database; hard coded as of now
		driver.get("https://www.xero.com/signup/");
				
		//New User Signing Up: Hard Coding values as of now, these values can be fetched from  database or from config file
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[1]/form/div[2]/div[1]/label/input")).sendKeys("Gopalakrishnan");
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[1]/form/div[2]/div[2]/label/input")).sendKeys("Sridhar");
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[1]/form/div[3]/label/input")).sendKeys("testxerodemo@gmail.com");
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[1]/form/div[4]/label/input")).sendKeys("9486112355");
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[1]/form/div[6]/label/input")).click();
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[1]/form/button")).click();
		Thread.sleep(3000);
		//String Su_page = driver.findElement(By.xpath("html/body/div[1]/div/div/div/h2")).getText();
		String ss_url = driver.getCurrentUrl();
		// hard coded Compare string shall be from database or from file
		if (/*Su_page.contentEquals("Check your inbox") && */ss_url.contains("success") )
		{
			System.out.println("Sign Up successful");	
		}
		
		else
		{
			System.out.println("Sign Up Failed");	
		}
		
		
	}
	

	
	
	//This is when user has already activated his account , we are checking for existing user	
	public void existinguser() throws InterruptedException
	{
		//Signing in as Existing user: Hard Coding values as of now, these values can be fetched from  database or from file
		driver.get("https://www.xero.com/signup/");	
		driver.findElement(By.linkText("Existing user? Login to Xero")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys("testxerodemo@gmail.com");
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("xero123456");
		driver.findElement(By.xpath("//*[@id='submitButton']")).click();
		Thread.sleep(2000);
		
		if (driver.getTitle().contains("Dashboard"))
		{
			// access parent menu and sub menu to access Sales tab
			WebElement p_menu=driver.findElement(By.xpath("//*[@id='Accounts']"));
			Actions act=new Actions(driver);
			act.click(p_menu).perform();
			WebElement c_menu=driver.findElement(By.xpath("//*[@id='xero-nav']/div[2]/div[2]/div/ul/li[2]/ul/li[2]/a"));
			act.click(c_menu).perform();
			Thread.sleep(3000);
			
			// find repeating tab in the page and click on the tab
			driver.findElement(By.xpath("//*[@id='ext-gen1018']/div[4]/div/div/h2/div/a[2]")).click();
			Thread.sleep(3000);		
			System.out.println("In Repeating tab ");
		}
		else
		{
			System.out.println("Login failed");
			//the failure message can be caputured or screenshot of the page can be stored at some location
			driver.quit();
		}
		
		
	}
	
	public static void main(String[] args) throws Exception {
	menuhandling mh = new menuhandling();
	mh.signup();
	mh.existinguser();

	}

}
