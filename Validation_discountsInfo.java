package com.esales.PA.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Validation_discountsInfo {
private WebDriver driver = Main.getDriver();  
	
	public void validate(Logger log){
		try{Thread.sleep(2000);}catch(Exception e){}
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.cssSelector("#btnSubmitDiscounts .PrimaryButton")));
		List<WebElement> list = driver.findElements(By.cssSelector("#totalform .error"));
		for(WebElement elem : list){
			String forAttribute = elem.getAttribute("for");
			if(forAttribute != null){
				switch (forAttribute) {
				case "policy.discountsInfo.pdEdocument.value":
					if(!Main.returnExpectedCondtion(elem, "Please select an answer.")){
						log.error("Validation message: \"Please select an answer\" on discount information page was missing");
					}
					break;
				case "policy.discountsInfo.pdCommercialAuto.value":
					if(!Main.returnExpectedCondtion(elem, "Please select an answer.")){
						log.error("Validation message: \"Please select an answer\" on discount information page was missing");
					}
					break;
				case "policy.discountsInfo.pdTeacher.value":
					if(!Main.returnExpectedCondtion(elem, "Please select an answer.")){
						log.error("Validation message: \"Please select an answer\" on discount information page was missing");
					}
					break;
				case "MotorClub":
					if(!Main.returnExpectedCondtion(elem, "Please select an answer.")){
						log.error("Validation message: \"Please select an answer\" on discount information page was missing");
					}
					break;
				case "AffinityGroup":
					if(!Main.returnExpectedCondtion(elem, "Please select an answer.")){
						log.error("Validation message: \"Please select an answer\" on discount information page was missing");
					}
					break;
				}	
			}
		}
		driver.findElement(By.id("pdEdocument_Y")).click();
		driver.findElement(By.id("pdCommercialAuto_N")).click();
		Select motorClub = new Select(driver.findElement(By.id("MotorClub")));
		motorClub.selectByIndex(3);
		
		Select affinityGroup = new Select(driver.findElement(By.id("AffinityGroup")));
		affinityGroup.selectByIndex(3);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.cssSelector("#btnSubmitDiscounts .PrimaryButton")));
	}
}
