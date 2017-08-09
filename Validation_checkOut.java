package com.esales.PA.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Validation_checkOut {
	private WebDriver driver = Main.getDriver();
	
	public void validate(String name, String Num, String cvv , Logger log){
		try{Thread.sleep(2000);}catch(Exception e){}
		WebElement continueBtn = driver.findElement(By.cssSelector("#btnContinueCheckout .PrimaryButton")); 
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", continueBtn);
		List<WebElement> list = driver.findElements(By.cssSelector("#totalform .error"));
		for(WebElement elem : list){
			String forAttribute = elem.getAttribute("for");
			if(forAttribute != null){
				switch (forAttribute) {
				case "paymentPlanKey":
					if(!Main.returnExpectedCondtion(elem, "Please select an payment plan and payment amount.")){
						 log.error("validation message: \"Please select an payment plan and payment amount.\" on checkOut page missing." );
					}
					break;
				case "paymentMethod":
					if(!Main.returnExpectedCondtion(elem, "Please select a payment method.")){
						 log.error("validation message: \"Please select a payment method.\" on checkOut page missing.");
					}
					break;
				case "futureInstallmentAutomaticWithdrawl":
				    if(! Main.returnExpectedCondtion(elem, "Please select an answer.")){
				    	 log.error("validation message: \"Please select an answer.\" on checkOut page missing.");
				    }
					break;
				}	
			}
		}
		try{Thread.sleep(2000);}catch(Exception e){}
		driver.findElement(By.id("payment_plan-0")).click();
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("payment_method_card")));
		}catch(Exception e){
			try{
				((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("payment_method_card")));
			}catch(Exception ex){
			}
		}
		driver.findElement(By.id("card_number")).sendKeys(Num);
		driver.findElement(By.id("security_code")).sendKeys(cvv);
		WebElement expiration_month = driver.findElement(By.id("expiration_month"));
		((JavascriptExecutor)driver).executeScript("arguments[0].style.display='block'", expiration_month);
		driver.findElement(By.id("expiration_month")).sendKeys("02");
		WebElement expiration_year = driver.findElement(By.id("expiration_year"));
		((JavascriptExecutor)driver).executeScript("arguments[0].style.display='block'", expiration_year);
		driver.findElement(By.id("expiration_year")).sendKeys("2020");
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", continueBtn);
	}
}
