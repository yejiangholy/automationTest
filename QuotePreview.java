package com.esales.PA.test;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QuotePreview {
	
	private WebDriver driver = Main.getDriver();
	
	public void printQuotePrice(){
		new WebDriverWait(driver, 120).until(ExpectedConditions.titleContains("Quote"));
		driver.findElement(By.id("tab_0")).click();
		System.out.println("Most Popular Total - " + driver.findElement(By.id("pay_full_0")).getText());
		driver.findElement(By.id("tab_1")).click();
		System.out.println("Less Coverage Total - " + driver.findElement(By.id("pay_full_1")).getText());
		driver.findElement(By.id("tab_2")).click();
		System.out.println("More Coverage Total - " + driver.findElement(By.id("pay_full_2")).getText());
		driver.findElement(By.cssSelector("#quotegroup .PrimaryButton")).click();
	}
	
	public List<String> getDiscounts(){
		new WebDriverWait(driver, 120).until(ExpectedConditions.titleContains("Quote"));
		driver.findElement(By.id("all_discounts")).click();
		try{Thread.sleep(2000);}catch(Exception e){}
		List<String> discounts = new ArrayList<>();
		List<WebElement> discount_divs = driver.findElements(By.xpath("//*[@id=\"included_discounts_payload\"]/div"));
		System.out.println("We find: "+ discount_divs.size()+" discounts displayed");
		for(WebElement divs : discount_divs){
			discounts.add(divs.getText().toLowerCase().trim());
		}
		return discounts;
	}
}
