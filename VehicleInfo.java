package com.esales.PA.test;

import java.util.List;

import org.openqa.selenium.*;

public class VehicleInfo {
	
	private WebDriver driver = Main.getDriver();
	
	public void enter(String primaryUse , String purchaseMethod  , String anti_theft){
		driver.findElement(By.id("primaryUse")).sendKeys(primaryUse);
		driver.findElement(By.id("purchaseMethod")).sendKeys(purchaseMethod);
		List<WebElement> owner = driver.findElements(By.id("yes_owner"));
		for(WebElement elem : owner){
			if(elem.getAttribute("value").equalsIgnoreCase("Y"))
				elem.click();
		}
		if("NO".equalsIgnoreCase(anti_theft)){
		driver.findElement(By.id("no_antitheftdevice")).click();
		}else{
			driver.findElement(By.id("yes_antitheftdevice")).click();
			driver.findElement(By.id("AT2")).click();
			driver.findElement(By.id("AT1")).click();
			driver.findElement(By.id("AT3")).click();
		}
		WebElement submit = driver.findElement(By.cssSelector("#btnSumbitVehicle a"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", submit);
	}
}
