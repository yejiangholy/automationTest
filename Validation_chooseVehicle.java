package com.esales.PA.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Validation_chooseVehicle {
private WebDriver driver = Main.getDriver();  
	
	public void validate(int num , Logger log){
		new WebDriverWait(driver, 5).until(ExpectedConditions.titleContains("| Your Vehicles"));
		driver.findElement(By.cssSelector("#btnSumbitVehicleList .PrimaryButton")).click();
		List<WebElement> list = driver.findElements(By.cssSelector("#totalform .error"));
		for(WebElement elem : list){
			String forAttribute = elem.getAttribute("for");
			if(forAttribute != null){
				switch (forAttribute) {
				case "vhcValidation":
					if(!Main.returnExpectedCondtion(elem, "Please select at least one vehicle")){
						log.error("Validation message: \"Please select at least one vehicle\" on choose vehicle page was missing.");
					}
					break;	
				}	
			}
		}
		try{
			for(int i=0 ; i<num ; i++){
		driver.findElement(By.id("vehicle-"+i)).click();
			}
		}catch(Exception e){
			Select year = new Select(driver.findElement(By.id("year")));
			year.selectByValue("2013");
			
			try{Thread.sleep(1000);}catch(Exception ex){}
			
			Select make = new Select(driver.findElement(By.id("make")));
			make.selectByValue("BMW");
			
			try{Thread.sleep(1000);}catch(Exception ex){}
			
			Select model = new Select(driver.findElement(By.id("model")));
			model.selectByValue("335 I");
			
			try{Thread.sleep(1000);}catch(Exception ex){}
			
			Select body = new Select(driver.findElement(By.id("body")));
			body.selectByIndex(1);;
		}
		driver.findElement(By.cssSelector("#btnSumbitVehicleList .PrimaryButton")).click();
	}
}
