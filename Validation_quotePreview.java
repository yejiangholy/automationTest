package com.esales.PA.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Validation_quotePreview {
private WebDriver driver = Main.getDriver();  
	
	public void validate(){
		new WebDriverWait(driver, 120).until(ExpectedConditions.titleContains("| Quote"));
		driver.findElement(By.id("tab_1")).click();
		
		driver.findElement(By.id("tab_2")).click();
	
		driver.findElement(By.id("tab_0")).click();
		
		driver.findElement(By.cssSelector("#quotegroup .PrimaryButton")).click();
	}
}
