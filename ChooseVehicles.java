package com.esales.PA.test;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChooseVehicles {
	
	private WebDriver driver =  Main.getDriver();
	public void chooseVehicels(int num, Logger log){
	try{
	new WebDriverWait(driver, 10).until(ExpectedConditions.titleContains("Your Vehicles"));
	}catch(Exception e){
		
	}
	
	try{
	for(int i=0 ; i<num ; i++){
		driver.findElement(By.id("vehicle-"+i)).click();
	}
	}catch(Exception e){
		log.error("Prefill test failed to offer vehicles information");
	}
	driver.findElement(By.cssSelector("#btnSumbitVehicleList .PrimaryButton")).click();
	}
}
