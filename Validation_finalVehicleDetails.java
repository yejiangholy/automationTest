package com.esales.PA.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;

import com.esales.data.dynamic.PA_test_data;


public class Validation_finalVehicleDetails {
  private WebDriver driver = Main.getDriver();
  
  public void validate(Logger log){
	  new WebDriverWait(driver, 10).until(ExpectedConditions.titleContains("| Final Vehicle Details"));
		WebElement continueBtn = driver.findElement(By.cssSelector("#btnContinueFinalVehicle .PrimaryButton"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",continueBtn);
		List<WebElement> list = driver.findElements(By.cssSelector("#totalform .error"));
		for(WebElement elem : list){
			String forAttribute = elem.getAttribute("for");
			if(forAttribute != null){
				switch (forAttribute) {
				case "vehicles[0].isVehicleGaraged":
					if(!Main.returnExpectedCondtion(elem, "Please select an answer.")){
						log.error("Validation message: \"Please select an answer.\" on final vehicle details page was missing");
					}
					break;
				case "vehicles[0].hasSnowPlow":
					if(!Main.returnExpectedCondtion(elem, "Please select an answer.")){
						log.error("Validation message: \"Please select an answer.\" on final vehicle details page was missing");
					}
					break;
				}	
			}
		}
		// end error validation
		for(int i= 0 ; i<PA_test_data.vehicleNumber ; i++){
			if(driver.findElements(By.id("comprehensive-"+i)).size() > 0){
				try{
				driver.findElement(By.id("no_comprehensive_coverage-"+i)).click();
				}catch(ElementNotVisibleException e){}
			}
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.id("no_vehicle_garaged-"+i)));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.id("no_has_snow_plow-"+i)));
		}
			
			if(driver.findElements(By.id("loan_lease_info-0")).size() > 0){
				Select loan_lease = new Select(driver.findElement(By.id("loan_lease_info-0")));
				loan_lease.selectByIndex(2);
				try{Thread.sleep(1000);}catch(Exception e){}
				driver.findElement(By.id("btnUseThisAddress")).click();
			}
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", continueBtn);
  }
}
