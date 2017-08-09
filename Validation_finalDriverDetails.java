package com.esales.PA.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;

import com.esales.data.dynamic.PA_test_data;

public class Validation_finalDriverDetails {
private WebDriver driver = Main.getDriver();  
	
	public void validate(String licenseState ,String LicenseNum, String improvement_complete_date, Logger log){
		try{Thread.sleep(2000);}catch(Exception e){};
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.className("PrimaryButton")));
		List<WebElement> list = driver.findElements(By.cssSelector("#totalform.error"));
		for(WebElement elem : list){
			String forAttribute = elem.getAttribute("for");
			if(forAttribute != null){
				switch (forAttribute) {
				case "license_number-0":
					if(!Main.returnExpectedCondtion(elem, "Please provide a valid license number.")){
						log.error("Validation message: \"Please provide a valid license number.\" on final driver detail page was missing");
					}
					break;
				case "driver_improvement_course_completion_date-0":
					if(!Main.returnExpectedCondtion(elem, "Please provide a valid date.")){
						log.error("Validation message: \"Please provide a valid date.\" on final driver detail page was missing");
					}
					break;
				case "occupation-0":
					if(!Main.returnExpectedCondtion(elem, "Please select an answer.")){
						log.error("Validation message: \"Please select an answer.\" on final driver detail page was missing");
					}
					break;
				case "occupation-1":
					if(!Main.returnExpectedCondtion(elem, "Please select an answer.")){
						log.error("Validation message: \"Please select an answer.\" on final driver detail page was missing");
					}
					break;
				case "occupation-2":
					if(!Main.returnExpectedCondtion(elem, "Please select an answer.")){
						log.error("Validation message: \"Please select an answer.\" on final driver detail page was missing");
					}
					break;
				}	
			}
		}
		try{Thread.sleep(2000);}catch(Exception e){};
		Select LicenseState = new Select(driver.findElement(By.id("license_state-0")));
		LicenseState.selectByValue(licenseState);
		
		driver.findElement(By.id("license_number-0")).sendKeys(LicenseNum);
		
		for(int i=0 ;i<PA_test_data.userNumber ; i++){
			try{
		WebElement element = driver.findElement(By.id("occupation-"+i));
		Select select = new Select(element);
		select.selectByValue("ACCT");
			}catch(Exception e){}
		}
		
		if(improvement_complete_date != null && improvement_complete_date.length() > 0){
			driver.findElement(By.id("driver_improvement_course_completion_date-0")).sendKeys(improvement_complete_date);
		}
		driver.findElement(By.cssSelector("#btnContinueFinalDriver .PrimaryButton")).sendKeys(Keys.RETURN);
	}
}
