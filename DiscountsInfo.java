package com.esales.PA.test;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class DiscountsInfo {
	
	private WebDriver driver = Main.getDriver();
	
	public void enter(String eDelivery , String commercial_auto, String affinityGroup , String motorClub, String driverImprovement){
		try{Thread.sleep(2000);}catch(Exception e){}
		if("yes".equalsIgnoreCase(eDelivery)){
			driver.findElement(By.id("pdEdocument_Y")).click();
		}else{
			driver.findElement(By.id("pdEdocument_N")).click();
		}
		driver.findElement(By.id("pdCommercialAuto_N")).click();
		driver.findElement(By.id("MotorClub")).sendKeys(motorClub);
		
		Select affinity_Group = new Select(driver.findElement(By.id("AffinityGroup")));
		affinity_Group.selectByIndex(3);
		driver.findElement(By.id("AffinityGroup")).sendKeys(affinityGroup);
		
		if("YES".equalsIgnoreCase(driverImprovement)){
			driver.findElement(By.id("id_drivers[0].discounts1625[DIP]")).click();
		}
		
		driver.findElement(By.cssSelector("#btnSubmitDiscounts .PrimaryButton")).click();
	}
}
