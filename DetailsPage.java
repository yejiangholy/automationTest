package com.esales.PA.test;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DetailsPage {
	
	private WebDriver driver = Main.getDriver();
	
	public void enterHomeOwner_Yes(){
	    new WebDriverWait(driver, 5).until(ExpectedConditions.titleContains("Details")); 
		driver.findElement(By.id("yes_hoi")).click();
		
		Select type = new Select(driver.findElement(By.id("residentType")));
		type.selectByIndex(1);
		
		Select company = new Select(driver.findElement(By.id("homeInsuranceCompany")));
		company.selectByIndex(2);
	}
	
	public void enterHomeOwner_No(){
		 new WebDriverWait(driver, 5).until(ExpectedConditions.titleContains("Details"));
		 driver.findElement(By.id("no_hoi")).click();
	}
	
	public void enterCarInsurance_Yes(){
		driver.findElement(By.id("yes_auto_ins"));
		
		driver.findElement(By.id("currentCarrier")).sendKeys("21st Century");
		
		driver.findElement(By.id("insuranceLasts")).sendKeys("6 months or more, but less than 3 years");
		
		driver.findElement(By.id("liabilityLimits")).sendKeys("50,000/100,000 or 100,000 single limit");
		
		WebElement submit = driver.findElement(By.cssSelector("#btnSumbitDetails .PrimaryButton"));
		  ((JavascriptExecutor)driver).executeScript("arguments[0].click();", submit);
	}
	
	public void enterCarInsurance_No(){
		driver.findElement(By.id("no_auto_ins")).click();
		
		driver.findElement(By.id("daysWithoutInsurance")).sendKeys("1 to 30 days");
		
		driver.findElement(By.id("insuranceHistory")).sendKeys("Newly licensed");
		
		driver.findElement(By.cssSelector("#btnSumbitDetails .PrimaryButton")).click();
		
	}
}
