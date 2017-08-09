package com.esales.PA.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Validation_details {
private WebDriver driver = Main.getDriver();  
	
	public void validate(Logger log){
		new WebDriverWait(driver, 5).until(ExpectedConditions.titleContains("| Details"));
		WebElement continueBtn = driver.findElement(By.cssSelector("#btnSumbitDetails .PrimaryButton"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",continueBtn);
		List<WebElement> list = driver.findElements(By.cssSelector("#totalform .error"));
		for(WebElement elem : list){
			String id = null;
			 id = elem.getAttribute("id");
			if(id != null){
				switch (id) {
				case "label-yes_hoi":
					 if(!Main.returnExpectedCondtion(elem, "This field is required.")){
						 log.error("Validation message: \"This field is required\" on details page was missing.");
					 }
					break;
				case "label-currentCarrier":
					if(!Main.returnExpectedCondtion(driver.findElement(By.id("label-currentCarrier")), "Please, select current auto insurance provider")){
						log.error("Validation message: \"Please, select current auto insurance provider\" on details page was missing.");
					}
	
					break;	
				}	
			}
		}
		
		//((JavascriptExecutor) driver).executeScript("(function(){if($){$('#box_hoi .ezmark-checked').removeClass('ezmark-checked');$('input[name=\"detailsInfo.homeInsuranceHave\"][value=\"Y\"]').trigger('change').prop('checked',!0).parent('div').addClass('ezmark-checked');$('#residentType').val('HOME').trigger('chosen:updated');setTimeout(function(){$('#homeInsuranceCompany').val('21ST').trigger('chosen:updated')},1000);$('#box_auto_ins .ezmark-checked').removeClass('ezmark-checked');$('input[name=\"detailsInfo.continuousAutoInsurance\"][value=\"Y\"]').trigger('change').prop('checked',!0).parent('div').addClass('ezmark-checked');setTimeout(function(){$('#currentCarrier').val('21ST').trigger('chosen:updated')},1000);$('#insuranceLasts').val('6MOS3YRS').trigger('chosen:updated');$('#currentCarrier').val('LT250_500').trigger('chosen:updated');$('#email').val('SAMPLE@PA.COM')}})()");
		try{Thread.sleep(1000);}catch(Exception e){}
        driver.findElement(By.id("no_hoi")).click();
	
        driver.findElement(By.id("yes_auto_ins")).click();
        driver.findElement(By.id("no_auto_ins")).click();
        driver.findElement(By.id("yes_auto_ins")).click();
		driver.findElement(By.id("currentCarrier")).sendKeys("21st Century");
		driver.findElement(By.id("insuranceLasts")).sendKeys("6 months or more, but less than 3 years");
		driver.findElement(By.id("liabilityLimits")).sendKeys("50,000/100,000 or 100,000 single limit");
		WebElement submit = driver.findElement(By.cssSelector("#btnSumbitDetails .PrimaryButton"));
		  ((JavascriptExecutor)driver).executeScript("arguments[0].click();", submit);
	}
}
