package com.esales.PA.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;

public class Validation_customerInfo {

private WebDriver driver = Main.getDriver();  
	
	public void validate(String fName , String lName, String address , String email, String date , Logger log){
		WebElement continueBtn = driver.findElement(By.className("PrimaryButton"));
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String currDate = dateFormat.format(new Date(System.currentTimeMillis())); //To check error for Current or Previous Date
		Calendar cal = Calendar.getInstance();
		String nextDate = ""; //To check error for more than 60 days
//		try {
//			cal.setTime(dateFormat.parse(currDate));
//			cal.add(Calendar.MONTH, 3);
//			nextDate = dateFormat.format(cal.getTime());
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
		continueBtn.sendKeys(Keys.RETURN);
		continueBtn.sendKeys(Keys.RETURN);
		List<WebElement> list = driver.findElements(By.cssSelector("#totalform .error"));
		for(WebElement elem : list){
			String forAttribute = elem.getAttribute("for");
			if(forAttribute != null){
				switch (forAttribute) {
				case "firstName":
					if(!Main.returnExpectedCondtion(elem, "Please provide a first name")){
					  log.error("Validation message: \"Please provide a first name\" on customer informatioin page was missing.");
					}
					driver.findElement(By.id("firstName")).sendKeys("2");
					((JavascriptExecutor)driver).executeScript("arguments[0].click();", continueBtn);
					if(!Main.returnExpectedCondtion(elem, "Please enter a name that does not include numbers or characters")){
						 log.error("Validation message: \"Please enter a name that does not include numbers or characters\" on customer informatioin page was missing.");
					}
					driver.findElement(By.id("firstName")).clear();
					break;
				case "lastName":
					if(!Main.returnExpectedCondtion(elem, "Please provide a last name")){
						log.error("Validation message: \"Please provide a last name\" on customer informatioin page was missing.");
					}
					driver.findElement(By.id("lastName")).sendKeys("2");
					((JavascriptExecutor)driver).executeScript("arguments[0].click();", continueBtn);
					if(!Main.returnExpectedCondtion(elem, "Please enter a name that does not include numbers or characters")){
						log.error("Validation message: \"Please enter a name that does not include numbers or characters\" on customer informatioin page was missing.");
					}
					driver.findElement(By.id("lastName")).clear();
					break;
				case "address1":
					if(!Main.returnExpectedCondtion(elem, "Please provide a street address")){
						 log.error("Validation message: \"Please provide a street address\" on customer informatioin page was missing.");
					}
					break;
				case "pedDateUI":
					if(!Main.returnExpectedCondtion(elem, "This field is required.")){
						log.error("Validation message: \"This field is required\" on customer informatioin page was missing.");
					}
					driver.findElement(By.id("pedDateUI")).sendKeys(currDate);
					continueBtn.sendKeys(Keys.RETURN);
					if(!Main.returnExpectedCondtion(elem, "Please enter a date in the future")){
						log.error("Validation message: \"Please enter a data in the future\" on customer informatioin page was missing.");
					}
					driver.findElement(By.id("pedDateUI")).clear();
					driver.findElement(By.id("pedDateUI")).sendKeys(nextDate);
					continueBtn.sendKeys(Keys.RETURN);
					break;
				}	
			}
		}
		driver.findElement(By.id("firstName")).sendKeys(fName);
		driver.findElement(By.id("lastName")).sendKeys(lName);
		driver.findElement(By.id("address1")).sendKeys(address);
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("pedDateUI")).sendKeys(date);
		continueBtn.sendKeys(Keys.RETURN);
	}
}
