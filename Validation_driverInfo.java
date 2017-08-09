package com.esales.PA.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Validation_driverInfo {
private WebDriver driver = Main.getDriver();  
	
	public void validate(String dob, Logger log){
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String currDate = dateFormat.format(new Date(System.currentTimeMillis()));
		new WebDriverWait(driver, 5).until(ExpectedConditions.titleContains("| Drivers"));
		WebElement continueBtn = driver.findElement(By.cssSelector("#btnSubmitDriver .PrimaryButton"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", continueBtn);
		List<WebElement> list = driver.findElements(By.cssSelector("#totalform .error"));
		for(WebElement elem : list){
			String forAttribute = elem.getAttribute("for");
			if(forAttribute != null){
				switch (forAttribute) {
				case "dobDate":
					if(!Main.returnExpectedCondtion(elem, "Please provide a valid birthday, MM/DD/YYYY")){
						log.error("Validation message: \"Please provide a valid birthday, MM/DD/YYYY\" on driver information page was missing");
					}
					driver.findElement(By.id("dobDate")).sendKeys(currDate);
					((JavascriptExecutor)driver).executeScript("arguments[0].click();", continueBtn);
					if(!Main.returnExpectedCondtion(elem, "Only enter people who are 15 or older")){
						log.error("Validation message: \"Only enter people who are 15 or older\" on driver information page was missing");
					}
					driver.findElement(By.id("dobDate")).clear();
					break;
				case "driverInfo.gender":
					if(!Main.returnExpectedCondtion(elem, "Please select a gender")){
						log.error("Validation message: \"Please select a gender\" on driver information page was missing");
					}
					break;
				case "maritalStatus":
					if(!Main.returnExpectedCondtion(elem, "Please select a marital status")){
						log.error("Validation message: \"Please select a marital status\" on driver information page was missing");
					}
					break;
				case "licenseStatus":
					if(!Main.returnExpectedCondtion(elem, "Please select an answer")){
						log.error("Validation message: \"Please select an answer\" on driver information page was missing");
					}
					break;
				case "ageFirstLicensed":
					if(!Main.returnExpectedCondtion(elem, "Please enter a 2 digit number")){
						log.error("Validation message: \"Please enter a 2 digit number\" on driver information page was missing");
					}
					driver.findElement(By.id("ageFirstLicensed")).sendKeys("14");
					((JavascriptExecutor)driver).executeScript("arguments[0].click();", continueBtn);
					if(!Main.returnExpectedCondtion(elem, "This age must be equal to or lower than your current age and must be 15 or higher.")){
						log.error("Validation message: \"This age must be equal to or lower than your current age and must be 15 or higher.\" on driver information page was missing");
					}
					driver.findElement(By.id("ageFirstLicensed")).clear();
					break;
				case "driverInfo.hasViolation":
					if(!Main.returnExpectedCondtion(elem, "Have you had any accidents or violations?")){
						log.error("Validation message: \"Have you had any accidents or violations?\" on driver information page was missing");
					}
					((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.id("yes_violation")));
					new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("acc_month-0"))));
					break;	
				}	
			}
		}
		new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.id("dobDate")));//This field takes time to load into the DOM sometimes
		 try{
		        driver.findElement(By.id("dobDate")).sendKeys(dob);
		        }catch(Exception e){
		        	Select mouth = new Select(driver.findElement(By.id("dobMonth")));
		        	mouth.selectByIndex(5);
		        	Select day = new Select(driver.findElement(By.id("dobDay")));
		        	day.selectByIndex(5);
		        	Select year = new Select(driver.findElement(By.id("dobYear")));
		        	year.selectByValue("1979");
		        }
		driver.findElement(By.id("M")).click();
		driver.findElement(By.id("maritalStatus")).sendKeys("Single");
		driver.findElement(By.id("licenseStatus")).sendKeys("Valid");
		driver.findElement(By.id("ageFirstLicensed")).sendKeys("18");
		
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.id("no_violation")));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", continueBtn);
		if(driver.findElements(By.cssSelector("#dialog-addAll-as-drivers-ok .PrimaryButton")).size() > 0)driver.findElement(By.cssSelector("#dialog-addAll-as-drivers-ok .PrimaryButton")).click();
	}
}
