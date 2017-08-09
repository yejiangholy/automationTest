package com.esales.PA.test;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckOut {

	private WebDriver driver = Main.getDriver();
	
	public void checkOut(String name, String Num, String cvv ){
		new WebDriverWait(driver, 5).until(ExpectedConditions.titleContains("Checkout"));
		WebElement continueBtn = driver.findElement(By.cssSelector("#btnContinueCheckout .PrimaryButton"));
		driver.findElement(By.id("payment_plan-0")).click();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("payment_method_card")));
		driver.findElement(By.id("card_number")).sendKeys(Num);
		driver.findElement(By.id("security_code")).sendKeys(cvv);
		WebElement expiration_month = driver.findElement(By.id("expiration_month"));
		((JavascriptExecutor)driver).executeScript("arguments[0].style.display='block'", expiration_month);
		driver.findElement(By.id("expiration_month")).sendKeys("02");
		WebElement expiration_year = driver.findElement(By.id("expiration_year"));
		((JavascriptExecutor)driver).executeScript("arguments[0].style.display='block'", expiration_year);
		driver.findElement(By.id("expiration_year")).sendKeys("2020");
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",continueBtn);
	}
}
