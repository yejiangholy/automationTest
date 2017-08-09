package com.esales.PA.test;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PolicyDocuments {
	
	WebDriver driver = Main.getDriver();
	
	public void acknowledge(String fName, String lName, String Num, String cvv){
		try{Thread.sleep(2000);}catch(Exception e){}
			driver.findElement(By.id("btnDebitConfirmNext")).click();
		try{Thread.sleep(6000);}catch(Exception ee){}
		if(driver.findElements(By.id("btnContinueWithPurchase")).size() > 0 && driver.findElement(By.id("btnContinueWithPurchase")).isDisplayed()){
			
			try{Thread.sleep(3000);}catch(Exception ee){}
			driver.findElement(By.id("btnContinueWithPurchase")).click();
			
			new WebDriverWait(driver, 5).until(ExpectedConditions.titleContains("Checkout"));
			WebElement continueBtn = driver.findElement(By.cssSelector("#btnContinueCheckout .PrimaryButton"));
			driver.findElement(By.id("payment_plan-0")).click();
			try{((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("payment_method_card")));}catch(Exception e){}
			driver.findElement(By.id("card_number")).sendKeys(Num);
			driver.findElement(By.id("security_code")).sendKeys(cvv);
			WebElement expiration_month = driver.findElement(By.id("expiration_month"));
			((JavascriptExecutor)driver).executeScript("arguments[0].style.display='block'", expiration_month);
			driver.findElement(By.id("expiration_month")).sendKeys("02");
			WebElement expiration_year = driver.findElement(By.id("expiration_year"));
			((JavascriptExecutor)driver).executeScript("arguments[0].style.display='block'", expiration_year);
			driver.findElement(By.id("expiration_year")).sendKeys("2020");
			((JavascriptExecutor)driver).executeScript("arguments[0].click();",continueBtn);
			try{Thread.sleep(2000);}catch(Exception e){}
			driver.findElement(By.id("btnDebitConfirmNext")).click();
		}
		
		new WebDriverWait(driver, 15).until(ExpectedConditions.titleContains("Policy Documents"));
		driver.findElement(By.id("policy_document_0")).click();
		driver.findElement(By.id("policy_document_1")).click();
		driver.findElement(By.id("policy_document_2")).click();
		driver.findElement(By.id("policy_document_3")).click();
		driver.findElement(By.id("policy_document_4")).click();
		driver.findElement(By.id("policy_document_5")).click();
		driver.findElement(By.id("policy_document_6")).click();
		try{driver.findElement(By.id("policy_document_7")).click();}catch(Exception e){}
		driver.findElement(By.id("eSignature_first_name")).sendKeys(fName);
		driver.findElement(By.id("eSignature_last_name")).sendKeys(lName);
		driver.findElement(By.cssSelector("#btnContinueDocs .PrimaryButton")).click();
	}
}
