package com.esales.PA.test;

import org.openqa.selenium.*;

public class CustomerInfo {
	
	private WebDriver driver =  Main.getDriver();
	
	public void enterCustomerInfo(String fName, String lName, String address, String email,String date){
		WebElement continueButton = driver.findElement(By.className("PrimaryButton"));
		driver.findElement(By.id("firstName")).sendKeys(fName);
		driver.findElement(By.id("lastName")).sendKeys(lName);
		driver.findElement(By.id("address1")).sendKeys(address);
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("pedDateUI")).sendKeys(date);
		continueButton.sendKeys(Keys.RETURN);
	}
}
