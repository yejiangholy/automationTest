package com.esales.PA.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Validation_zipCode {
	
	private WebDriver driver = Main.getDriver();  
	
	public void validate(String zip, Logger log){
		driver.findElement(By.className("PrimaryButton")).click();
		List<WebElement> list = driver.findElements(By.cssSelector("#totalform .error"));
		for(WebElement elem : list){
			String forAttribute = elem.getAttribute("for"); 
			if(forAttribute != null){
				switch (forAttribute) {
				case "zipcode":
					if(! Main.returnExpectedCondtion(elem, "Please enter a zip code with 5 digits.")){
						log.error("Validation message: \"Please enter a zip code with 5 digits.\" on zipCode page was missing.");
					}
				}
			}
		}
		WebElement zipcodeBox = driver.findElement(By.id("zipcode"));
		zipcodeBox.sendKeys(zip);
		driver.findElement(By.className("PrimaryButton")).click();
	}
}
