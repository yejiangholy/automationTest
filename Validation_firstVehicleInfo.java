package com.esales.PA.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Validation_firstVehicleInfo {
private WebDriver driver = Main.getDriver();  
	
	public void validate(int num , Logger log){
		try{
			driver.findElement(By.cssSelector("#btnSumbitVehicle .PrimaryButton")).click();
		}catch(Exception e){
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#btnSumbitVehicle .PrimaryButton")));
		}
		List<WebElement> list = driver.findElements(By.cssSelector("#totalform .error"));
		for(WebElement elem : list){
			String forAttribute = elem.getAttribute("for");
			if(forAttribute != null){
				switch (forAttribute) {
				case "primaryUse":
					if(!Main.returnExpectedCondtion(elem, "Please select an answer")){
						log.error("Validation message: \"Please select an answer\" on vehicle information page was missing");
					}
					break;
				case "purchaseMethod":
					if(!Main.returnExpectedCondtion(elem, "Please select an answer")){
						log.error("Validation message: \"Please select an answer\" on vehicle information page was missing");
					}
					break;
				case "vhc.vehicleDetailsInfo.originalOwner":
					if(!Main.returnExpectedCondtion(elem, "Please select an answer")){
						log.error("Validation message: \"Please select an answer\" on vehicle information page was missing");
					}
					break;
				case "primaryDriverID":
					if(!Main.returnExpectedCondtion(elem, "This field is required.")){
						log.error("Validation message: \"This field is required\" on vehicle information page was missing");
					}
					break;
				case "vhc.vehicleDetailsInfo.hasAntitheftDevice":
					if(!Main.returnExpectedCondtion(elem, "This field is required.")){
						log.error("Validation message: \"This field is required\" on vehicle information page was missing");
					}
					((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("yes_antitheftdevice")));
					((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.cssSelector("#btnSumbitVehicle .PrimaryButton")));
					//Get elements again because this error is loaded after other errors
//					List<WebElement> antiTheftList = driver.findElements(By.cssSelector("#totalform .error"));
//					for(WebElement elem1 : antiTheftList){
//						String forAttr = elem1.getAttribute("for");
//						if(forAttr != null && forAttr.equalsIgnoreCase("atdValidation")){
//							if(!Main.returnExpectedCondtion(elem1, "You have indicated you have an anti-theft device in your car. Pick up to 6 options above or indicate you do not have an anti-theft device.")){
//								log.error("Validation message: \"You have indicated you have an anti-theft device in your car. Pick up to 6 options above or indicate you do not have an anti-theft device.\" on vehicle information page was missing");
//							}
//							((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("AT1")));
//							((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("AT2")));
//							((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("AT3")));
//							((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("AT4")));
//							((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("AT5")));
//							((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("AT6")));
//							((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("AT7")));
//						    if(!Main.returnExpectedCondtion(elem1, "You may pick up to 6 anti-theft devices. You need to delete one before you can add another.")){
//						    	log.error("Validation message: \"You may pick up to 6 anti-theft devices. You need to delete one before you can add another.\" on vehicle information page was missing");
//						    }
//							((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("AT7")));
//						}
//					}
					break;
				}	
			}
		}
	for(int i=0 ; i<num ; i++){
			driver.findElement(By.id("primaryUse")).sendKeys("Personal");
			driver.findElement(By.id("purchaseMethod")).sendKeys("Paid for");
			 driver.findElement(By.id("yes_owner")).click();
			 try{driver.findElement(By.id("no_tnc_usage")).click();}catch(Exception e){}
			driver.findElement(By.id("no_antitheftdevice")).click();
			WebElement submit = driver.findElement(By.cssSelector("#btnSumbitVehicle a"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", submit);
		}
	 }
}
