package com.esales.PA.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Validation_policyDocuments {
private WebDriver driver = Main.getDriver();

public void validate(String fName , String lName, Logger log){
	try{
		Thread.sleep(2000);
		driver.findElement(By.id("btnDebitConfirmNext")).click();
	}catch(Exception e){}
	
	try{Thread.sleep(10000);}catch(Exception ee){
		// wait the server to response.... 
	}
	
	if(driver.findElements(By.id("btnContinueWithPurchase")).size() > 0 && driver.findElement(By.id("btnContinueWithPurchase")).isDisplayed()){
		try{Thread.sleep(2000);}catch(Exception ee){}
		driver.findElement(By.id("btnContinueWithPurchase")).click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.titleContains("Checkout"));
		WebElement continueBtn = driver.findElement(By.cssSelector("#btnContinueCheckout .PrimaryButton"));
		driver.findElement(By.id("security_code")).sendKeys("123");
		continueBtn.click();
		try{
			Thread.sleep(2000);
			driver.findElement(By.id("btnDebitConfirmNext")).click();
		}catch(Exception ex){}
	}
	
	new WebDriverWait(driver, 5).until(ExpectedConditions.titleContains("| Policy Documents"));
	((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.cssSelector("#btnContinueDocs .PrimaryButton")));
	List<WebElement> list = driver.findElements(By.cssSelector("#totalform .row.errorMessage"));
	for(WebElement elem : list){
		if(!Main.returnExpectedCondtion(elem, "Oops! Please check all the boxes and type in your name to complete your purchase.")){
			 log.error("Validation message: \"Oops! Please check all the boxes and type in your name to complete your purchase.\" on policy documents page was missing.");
		}
	}
	driver.findElement(By.id("policy_document_0")).click();
	driver.findElement(By.id("policy_document_1")).click();
	driver.findElement(By.id("policy_document_2")).click();
	driver.findElement(By.id("policy_document_3")).click();
	driver.findElement(By.id("policy_document_4")).click();
	driver.findElement(By.id("policy_document_5")).click();
	driver.findElement(By.id("policy_document_6")).click();
	driver.findElement(By.id("policy_document_7")).click();
	driver.findElement(By.id("eSignature_first_name")).sendKeys(fName);
	driver.findElement(By.id("eSignature_last_name")).sendKeys(lName);
	driver.findElement(By.cssSelector("#btnContinueDocs .PrimaryButton")).click();
  }
}
