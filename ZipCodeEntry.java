package com.esales.PA.test;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import java.util.*;

public class ZipCodeEntry {
	
	private WebDriver driver = Main.getDriver();
	
	public void enterZip(String zip){
		driver.findElement(By.className("PrimaryButton")).click();
		List<WebElement> list = driver.findElements(By.cssSelector("#totalform.error"));
		for(WebElement ele : list){
			String forAttribute = ele.getAttribute("for");
			if(forAttribute != null && forAttribute.contains("zipcode")){
				Main.returnExpectedCondtion(ele, "Please enter a zip code with 5 digits.");
			}
		}// checking error message end 
		WebElement zipcodeBox = driver.findElement(By.id("zipcode"));
		zipcodeBox.sendKeys(zip);
		driver.findElement(By.className("PrimaryButton")).click();
	}
}
