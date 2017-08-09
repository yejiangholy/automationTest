package com.esales.PA.test;

import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.esales.data.dynamic.PA_test_data;

public class driver2Page {
	
	private static WebDriver driver = Main.getDriver();
	
	public  void enter(int number,Map<String,String> data ){
		for(int i=0 ; i<number ; i++){
			int index = i+1;
			try{
			 Thread.sleep(3000);
			}catch(Exception ex){}
			try{
			driver.findElement(By.id("firstName")).sendKeys(data.get("fName_"+index));
			driver.findElement(By.id("lastName")).sendKeys(data.get("lName_"+index));
			driver.findElement(By.id("dobDate")).sendKeys(data.get("dob_"+index));
			}catch(Exception e){
				
				
				List<WebElement> list = driver.findElements(By.id("firstName"));
				if(list.size() == 0){
					driver.navigate().refresh();
					driver.get(driver.getCurrentUrl());
				}else{
					driver.findElement(By.id("firstName")).sendKeys(data.get("fName_"+index));
					driver.findElement(By.id("lastName")).sendKeys(data.get("lName_"+index));
					 try{
					        driver.findElement(By.id("dobDate")).sendKeys("dob_"+index);
					        }catch(Exception ee){
					        	Select mouth = new Select(driver.findElement(By.id("dobMonth")));
					        	mouth.selectByIndex(5);
					        	Select day = new Select(driver.findElement(By.id("dobDay")));
					        	day.selectByIndex(5);
					        	Select year = new Select(driver.findElement(By.id("dobYear")));
					        	year.selectByValue("1979");
					}
				}
			}
			
		 if("MALE".equalsIgnoreCase(data.get("gender_"+index))){
	        	driver.findElement(By.id("M")).click();
	        }else{
	        	driver.findElement(By.id("F")).click();
	        }
		 
		 Select marial = new Select(driver.findElement(By.id("maritalStatus")));
		 marial.selectByValue("M");
		 
		 Select relationship = new Select(driver.findElement(By.id("relationship")));
		 relationship.selectByIndex(2);
		 
		 ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement((By.name("driverInfo.liveWithYou"))));
		 
		 ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement((By.name("driverInfo.driverStatus"))));
		 
		
		 Select company = new Select(driver.findElement(By.id("currentInsCompany")));
		 company.selectByIndex(2);
		 
		 driver.findElement(By.id("ageFirstLicensed")).sendKeys(data.get("age_first_Licensed_"+index));
	     WebElement continueBtn = driver.findElement(By.cssSelector("#btnSubmitDriver .PrimaryButton"));
	     if(i == number - 1){
	    	 ((JavascriptExecutor)driver).executeScript("arguments[0].click();", continueBtn);
			}else{  
	         driver.findElement(By.id("btnAddDriver")).click();
	      }
		}
	}
}
