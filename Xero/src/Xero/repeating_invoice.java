/*
 * Code to automate following test cases on Repeating Invoices:-
Test case 1: Xero site getting loaded and transferred to Existing user Login page 
Test case 2: User id and password being populated and submit button is clicked
Test case3:  Dashboard page getting displayed after successful login
Test case4: Traverse through Accounts Menu and Sales Sub menu.
Test Case5: Click on Repeating invoice link
Test case6: Click on existing invoice to modify the invoice. 
Test case7: check whether repeat field for weeks or month accepts negative number 
Test case8: check whether repeating frequency any other value other than weeks / months
Test case9: check whether user can choose repeating frequency any other value other than weeks / months
Test case 10: check whether invoice date can be chosen past dated or present date or future dated.
Test case 11: check whether due date can be left blank
Test case 12: Check whether the system accepts end date entered is before invoice date
Test case 13: check whether system accepts blank value for Invoice to field. Check whether system accepts list of vendors.
Test case 14: Check whether system accepts black item value and item description
Test case 15: Check whether system accepts negative quantity
Test case 16: Check whether system accepts negative unit price
Test case 17: Check tax rate effects in total price
Test case 18: Check whether “Approve for sending” pop up a new window which send the invoice details to email ids specified.
Test case 19: Click on save button and check whether system guides user appropriately. 
Invoice summary page:
Test case 20:  Check whether draft invoices are listed in Invoice summary page
Test case 21: Check whether status of the selected invoices turn “Approved” when Approve button is clicked.
Test case 22: Check whether “Approve for sending” pop up a new window which send the invoice details to email is specified in new pop up window when Approve for sending button is clicked.
Test Case 23: Check whether Delete  button deletes  selected invoices.

 * 
 */


package Xero;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class repeating_invoice {
	WebDriver driver = new FirefoxDriver();
	int login_ret;
	String test_type;
	
	
	public void existinguser() throws InterruptedException
	{
		
		login_ret = login();
		
		if (login_ret == 1)
		{
			// access parent menu and sub menu to access Sales tab
			// check positive test cases - traverse to repeating invoice and select a existing invoice
			test_type = "Positive";
			traverse_r_invoice_page(test_type);
			
			click_save_as_draft();
			
			traverse_r_invoice_page(test_type);
			click_approve();
			
			traverse_r_invoice_page(test_type);
			click_approve_for_sending();
			
			
			// check Negative test cases - call below function to set negative test data
			test_type = "Negative";
			traverse_r_invoice_page(test_type);
			
			click_save_as_draft();
			
			
			traverse_r_invoice_page(test_type);
			click_approve();
			
			
			traverse_r_invoice_page(test_type);
			click_approve_for_sending();
			
			/*
			traverse_invoice_summary_page();
			
			
			check_approve_invoices();
			
			check_approve_for_sending_invoices();
			
			check_delete_invoices();*/
			
		}
		
			
	}
	


	public int login() throws InterruptedException
	{
		//This is when user has already activated his account , we are checking for existing user
		//Signing in as Existing user: Hard Coding values as of now, these values can be fetched from  database or from file
				int i = 0;
				driver.get("https://www.xero.com/signup/");	
				driver.findElement(By.linkText("Existing user? Login to Xero")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//*[@id='email']")).sendKeys("testxerodemo@gmail.com");
				driver.findElement(By.xpath("//*[@id='password']")).sendKeys("xero123456");
				driver.findElement(By.xpath("//*[@id='submitButton']")).click();
				Thread.sleep(2000);
				
				if (driver.getTitle().contains("Dashboard"))
				{
					System.out.println("Login successful");
					i = 1;
				}
				else
				{
					System.out.println("Login failed");
					//the failure message can be caputured or screenshot of the page can be stored at some location
					driver.close();
				}
				return i;
				

	}
	
	private void traverse_r_invoice_page(String test_type) throws InterruptedException
	{
		WebElement p_menu=driver.findElement(By.xpath("//*[@id='Accounts']"));
		Actions act=new Actions(driver);
		act.click(p_menu).perform();
		WebElement c_menu=driver.findElement(By.xpath("//*[@id='xero-nav']/div[2]/div[2]/div/ul/li[2]/ul/li[2]/a"));
		act.click(c_menu).perform();
		Thread.sleep(3000);
		
		// click on existing invoice to change 
		driver.findElement(By.xpath("//*[@id='ext-gen1018']/div[4]/div/div/h2/div/a[2]")).click();
		Thread.sleep(3000);		
		driver.findElement(By.linkText("Vendor1")).click();
					
		String Ri_title = driver.getTitle();
		// hard coded Compare string shall be from database or from file
		if (Ri_title.contains("Edit Repeating Invoice") || (driver.getCurrentUrl()).contains("SearchRepeating"))
		{
			//if successful - Create Repeating Invoices 
			System.out.println("RI");
			
		}
		else
		{
			//these error messages can be 
			System.out.println("Repeating Invoice did not load");
		}
		
	}
	
	public void fill1() throws InterruptedException
	{
    	//sending hard coded values as of now, ideally these informations will be fetched from database or from file for execution
		driver.findElement(By.id("PeriodUnit")).clear();
		driver.findElement(By.id("PeriodUnit")).sendKeys("1");
		driver.findElement(By.id("TimeUnit_value")).clear();
		driver.findElement(By.id("TimeUnit_value")).sendKeys("Week(s)");
		driver.findElement(By.id("StartDate")).clear();
		driver.findElement(By.id("StartDate")).sendKeys("Sep 26, 2014");
		driver.findElement(By.id("DueDateDay")).clear();
		driver.findElement(By.id("DueDateDay")).sendKeys("2");
		driver.findElement(By.id("EndDate_container")).sendKeys("Sep 28, 2014");
		
		driver.findElement(By.xpath("//*[@id='PaidToName_fc83c375fcbf4c9ca2641eb001bb0eca_value']")).clear();
		driver.findElement(By.xpath("//*[@id='PaidToName_fc83c375fcbf4c9ca2641eb001bb0eca_value']")).sendKeys("Vendor1");
		driver.findElement(By.xpath("//*[@id='Reference_fc83c375fcbf4c9ca2641eb001bb0eca']")).clear();
		driver.findElement(By.xpath("//*[@id='Reference_fc83c375fcbf4c9ca2641eb001bb0eca']")).sendKeys("Vendor1");
		driver.findElement(By.id("ext-gen8")).clear();
		driver.findElement(By.id("ext-gen8")).sendKeys("Tax Exclusive");
		
				
		// code to add new inventory item if new item comes up
		// handle new inventory item pop up window

		// code to add new tax rate 
		// handle new new tax rate pop up window		
		
	}

	public void fill2() throws InterruptedException
	{
		//sending hard coded values as of now, ideally these informations will be fetched from database or from file for execution
		// code to fill in all negative input test data 
				driver.findElement(By.id("PeriodUnit")).clear();
				driver.findElement(By.id("PeriodUnit")).sendKeys("0");
				driver.findElement(By.id("TimeUnit_value")).clear();
				driver.findElement(By.id("TimeUnit_value")).sendKeys("Weekk(s)");
				driver.findElement(By.id("StartDate")).clear();
				driver.findElement(By.id("StartDate")).sendKeys("Sep 26, 2014");
				driver.findElement(By.id("DueDateDay")).clear();
				driver.findElement(By.id("EndDate_container")).sendKeys("Set 28, 2014");
				
				driver.findElement(By.xpath("//*[@id='PaidToName_fc83c375fcbf4c9ca2641eb001bb0eca_value']")).clear();
				driver.findElement(By.xpath("//*[@id='PaidToName_fc83c375fcbf4c9ca2641eb001bb0eca_value']")).sendKeys("@34Vendor2");
				driver.findElement(By.xpath("//*[@id='Reference_fc83c375fcbf4c9ca2641eb001bb0eca']")).clear();
				driver.findElement(By.xpath("//*[@id='Reference_fc83c375fcbf4c9ca2641eb001bb0eca']")).sendKeys("Vendor2");
				driver.findElement(By.id("ext-gen8")).clear();
				driver.findElement(By.id("ext-gen8")).sendKeys("Tax xclusive");
				
				// code to add new inventory item if new item comes up
				// handle new inventory item pop up window

				// code to add new tax rate 
				// handle new new tax rate pop up window
	}
	
	

	private void click_approve_for_sending() throws InterruptedException {
		Thread.sleep(500);
		if (test_type.equals("Positive"))
		{
			fill1();
		}
		if (test_type.equals("Negative"))
		{
			fill2();
		}
		driver.findElement(By.id("saveAsAutoApprovedAndEmail")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("MessageTo664b475d-8687-44be-810d-bec194005e1b")).clear();
		driver.findElement(By.id("MessageTo664b475d-8687-44be-810d-bec194005e1b")).sendKeys("testxerodemo@gmail.com");
		driver.findElement(By.cssSelector("tr td:nth-child(1) em[id*=ext-gen]")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[onclick*=\"Invoice.save\"]")).click();
		Thread.sleep(2000);
		if((driver.getCurrentUrl()).contains("SearchRepeating"))
		{
			System.out.println("Approve and Save successful");
		}
		else
		{
		  System.out.println("*********Error*********");
		  System.out.println(driver.findElement(By.xpath("//*[@id='notify01']/div")).getText());	
		}		
	}



	private void click_approve() throws InterruptedException {
		Thread.sleep(500);
		if (test_type.equals("Positive"))
		{
			fill1();
		}
		if (test_type.equals("Negative"))
		{
			fill2();
		}

		driver.findElement(By.id("saveAsAutoApproved")).click();
		driver.findElement(By.cssSelector("button[onclick*=\"Invoice.save\"]")).click();
		Thread.sleep(2000);
		if((driver.getCurrentUrl()).contains("SearchRepeating"))
		{
			System.out.println("approve successful");
		}
		else
		{
			System.out.println("*********Error*********");
			System.out.println(driver.findElement(By.xpath("//*[@id='notify01']/div")).getText());
		}
		// check for Errors	- check all field validations are captured
	}



	private void click_save_as_draft() throws InterruptedException {
		Thread.sleep(500);
		if (test_type.equals("Positive"))
		{
			fill1();
		}
		if (test_type.equals("Negative"))
		{
			fill2();
		}

		driver.findElement(By.id("saveAsDraft")).click();
		driver.findElement(By.cssSelector("button[onclick*=\"Invoice.save\"]")).click();
		Thread.sleep(2000);
		if((driver.getCurrentUrl()).contains("SearchRepeating"))
		{
			System.out.println("Save As Draft successful");
		}
		else
		{
			System.out.println("*********Error*********");
			System.out.println(driver.findElement(By.xpath("//*[@id='notify01']/div")).getText());
		}
		
	}

	private void traverse_invoice_summary_page() {
		// code to traverse till repeating invoice summary page
		
	}
	
	private void check_delete_invoices() {
		// code to check whether invoices are selected
		// if not check for pop up message
		//if selected - 
		// code to handle confirmation pop up
		// code to check whether selected invoices gets deleted from invoice summary page
		
	}



	private void check_approve_for_sending_invoices() {
		// code to check whether invoices are selected
		// if not check for pop up message
		//if selected - 		
		// code to check whether pop up window appears foe sending invoices to eail specified by user
		// code to check Email contents 
		
	}



	private void check_approve_invoices() {
		// code to check whether invoices are selected
		// if not check for pop up message
		//if selected - 
		// code to check whether selected invoices gets approved status when approve button is clicked
		
	}
	
	public static void main(String[] args) throws Exception {
		repeating_invoice ri = new repeating_invoice();
	ri.existinguser();

	}

}
