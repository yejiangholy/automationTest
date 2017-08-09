package com.esales.PA.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Validation_finalPolicyDetails {
private WebDriver driver = Main.getDriver();

public void validate(Logger log){ 
	new WebDriverWait(driver, 5).until(ExpectedConditions.titleContains("| Final Policy Details"));
	WebElement continueBtn = driver.findElement(By.id("btnContinueFinalPolicy"));
	((JavascriptExecutor)driver).executeScript("arguments[0].click();",continueBtn);
	List<WebElement> list = driver.findElements(By.cssSelector("#totalform .error"));
	for(WebElement elem : list){
		String forAttribute = elem.getAttribute("for");
		if(forAttribute != null){
			switch (forAttribute) {
			case "policyDetailsInfo.isMailAddressDifferent":
				if(!Main.returnExpectedCondtion(elem, "Please select an answer.")){
				  log.error("Validation message: \"Please select an answer.\" on final policy details was missing");
				}
				break;
			case "policyDetailsInfo.isVehicleRegisteredToOtherApplicant":
				if(!Main.returnExpectedCondtion(elem, "Please select an answer.")){
					 log.error("Validation message: \"Please select an answer.\" on final policy details was missing");
				}
				break;
			case "policyDetailsInfo.hasAnyVehicleBeenCustomized":
				if(!Main.returnExpectedCondtion(elem, "Please select an answer.")){;
				 log.error("Validation message: \"Please select an answer.\" on final policy details was missing");
				}
				break;
			case "num_of_accident_in_loaned_vehicle":
				if(! Main.returnExpectedCondtion(elem, "Please select an answer.")){
					 log.error("Validation message: \"Please select an answer.\" on final policy details was missing");
				}
				break;
			case "policyDetailsInfo.isPolicyOwnerDriverHPPal":
				if(!Main.returnExpectedCondtion(elem, "This field is required.")){
					 log.error("Validation message: \"Please select an answer.\" on final policy details was missing");
				}
				break;	
			case "policyDetailsInfo.hasAnyoneBeenConvictedInsuranceFraud":
				if(!Main.returnExpectedCondtion(elem, "Please select an answer.")){
					 log.error("Validation message: \"Please select an answer.\" on final policy details was missing");
				}
				break;	
			}	
		}
	}
	((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("no_mail_address_different")));
	((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("no_vehicle_registered_to_other_than_applicant")));
	((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("no_has_any_vehicle_been_customized")));
	driver.findElement(By.id("num_of_accident_in_loaned_vehicle")).sendKeys("0");
	try{
	driver.findElement(By.id("no_policy_owner_driver_hp_pal")).click();
	}catch(Exception e){}
	try{
	driver.findElement(By.id("no_insurance_fraud_or_material_misrepresentation")).click();
	}catch(Exception e){}
	try{
	driver.findElement(By.id("no_policy_cancelled")).click();
	}catch(Exception e){}
	try{
	((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("no_insurance_fraud_or_material_misrepresentation")));
	}catch(Exception e){}
	((JavascriptExecutor)driver).executeScript("arguments[0].click();",continueBtn);
  }
}
