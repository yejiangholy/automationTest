package com.esales.PA.test;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.esales.data.dynamic.PA_test_data;

public class FinalVehicleDetails {
	
	private WebDriver driver = Main.getDriver();
	
	public void select(){
		new WebDriverWait(driver, 10).until(ExpectedConditions.titleContains("Final Vehicle"));
		for(int i=0; i<PA_test_data.vehicleNumber; i++){
		if(i == 0 && driver.findElements(By.id("comprehensive-0")).size() > 0){
			try{
			driver.findElement(By.id("no_comprehensive_coverage-0")).click();
			}catch(ElementNotVisibleException e){}
		}
		try{
		driver.findElement(By.id("no_vehicle_garaged-"+i)).click();
		driver.findElement(By.id("no_has_snow_plow-"+i)).click();
		}catch(Exception ee){
			((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("no_vehicle_garaged-"+i)));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("no_has_snow_plow-"+i)));
		}
		if(driver.findElements(By.id("loan_lease_info-"+i)).size() > 0){
			Select loan_lease = new Select(driver.findElement(By.id("loan_lease_info-"+i)));
			loan_lease.selectByIndex(2);
			try{
				Thread.sleep(1000);
			}catch(Exception e){
				e.printStackTrace();
			}
			driver.findElement(By.id("btnUseThisAddress")).click();
		}
	}
		WebElement continueBtn = driver.findElement(By.cssSelector("#btnContinueFinalVehicle .PrimaryButton"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",continueBtn);
		
	}
}
