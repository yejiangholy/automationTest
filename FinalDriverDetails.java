package com.esales.PA.test;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import com.esales.data.dynamic.PA_test_data;

public class FinalDriverDetails {
	
	private WebDriver driver = Main.getDriver();
	
	public void enter(String licenseState ,String LicenseNum, String improvement_complete_date){
		new WebDriverWait(driver, 5).until(ExpectedConditions.titleContains("Final Drivers Details"));
		Select LicenseState = new Select(driver.findElement(By.id("license_state-0")));
		//System.out.println("Here is the state : "+ licenseState);
		LicenseState.selectByValue(licenseState);
		driver.findElement(By.id("license_number-0")).sendKeys(LicenseNum);
		for(int i=0 ;i<PA_test_data.userNumber ; i++){
		WebElement element = driver.findElement(By.id("occupation-"+i));
		Select select = new Select(element);
		select.selectByValue("ACCT");
		}
		if(improvement_complete_date != null && improvement_complete_date.length() > 0){
			driver.findElement(By.id("driver_improvement_course_completion_date-0")).sendKeys(improvement_complete_date);
			try{Thread.sleep(3000);}catch(Exception e){}
		}
		driver.findElement(By.cssSelector("#btnContinueFinalDriver .PrimaryButton")).sendKeys(Keys.RETURN);
	}
}
