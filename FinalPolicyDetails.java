package com.esales.PA.test;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class FinalPolicyDetails {
	
	private WebDriver driver = Main.getDriver();
	
	public void select(){
		
		new WebDriverWait(driver, 5).until(ExpectedConditions.titleContains("Final Policy Details"));
		WebElement continueButton = driver.findElement(By.cssSelector("#btnContinueFinalPolicy .PrimaryButton"));
		driver.findElement(By.id("no_mail_address_different")).click();
		driver.findElement(By.id("no_vehicle_registered_to_other_than_applicant")).click();
		driver.findElement(By.id("no_has_any_vehicle_been_customized")).click();
		
		WebElement element = driver.findElement(By.id("num_of_accident_in_loaned_vehicle"));
		Select select = new Select(element);
		select.selectByValue("2");
		
		driver.findElement(By.id("no_insurance_fraud_or_material_misrepresentation")).click();
		// if user choose yes, then the step 1 in the next page will not show up, which is a error should be fixed 
		try{
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("no_policy_cancelled")));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("no_insurance_fraud_or_material_misrepresentation")));
		}catch(Exception e){}
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",continueButton);
	}
}
