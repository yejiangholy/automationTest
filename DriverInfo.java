package com.esales.PA.test;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

public class DriverInfo {
	
	private WebDriver driver = Main.getDriver();
	
	public void enterDriverInfo(String dob, String gender , String marital_status, String age_first_licesed , String have_violation ,String[] violations){
		new WebDriverWait(driver,10).until(ExpectedConditions.titleContains("Drivers"));//wait until we up to this driver info page
        WebElement continueBtn = driver.findElement(By.cssSelector("#btnSubmitDriver .PrimaryButton"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.id("dobDate")));
        try{
        driver.findElement(By.id("dobDate")).sendKeys(dob);
        }catch(Exception e){
        	Select mouth = new Select(driver.findElement(By.id("dobMonth")));
        	mouth.selectByIndex(5);
        	Select day = new Select(driver.findElement(By.id("dobDay")));
        	day.selectByIndex(5);
        	Select year = new Select(driver.findElement(By.id("dobYear")));
        	year.selectByValue("1979");
        }
        
        if("MALE".equalsIgnoreCase(gender)){
        	driver.findElement(By.id("M")).click();
        }else{
        	driver.findElement(By.id("F")).click();
        }
 
        Select marital_choose = new Select(driver.findElement(By.id("maritalStatus")));
        if("Single".equalsIgnoreCase(marital_status)){
        	marital_choose.selectByValue("S");
        }else{
        	marital_choose.selectByIndex(2);;
        }
        Actions action = new Actions(driver);
         Select license = new Select(driver.findElement(By.id("licenseStatus")));
         license.selectByValue("V");
     
        driver.findElement(By.id("ageFirstLicensed")).sendKeys(age_first_licesed);
        if(have_violation.equalsIgnoreCase("NO")){
        	try{
           driver.findElement(By.id("no_violation")).click();
        	}catch(Exception e){
        		WebElement no_violation = driver.findElement(By.id("no_violation"));
        		action.moveToElement(no_violation).click().perform();
        		((JavascriptExecutor)driver).executeScript("arguments[0].click();", no_violation);
        	}
        }else{
        	driver.findElement(By.id("yes_violation")).click();
        	enterViolationInfo(violations);
        }
        action.moveToElement(continueBtn).click().perform();
        
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", continueBtn);
        
        if(driver.findElements(By.cssSelector("#dialog-addAll-as-drivers-ok .PrimaryButton")).size() > 0){
			WebElement button = driver.findElement(By.cssSelector("#dialog-addAll-as-drivers-ok .PrimaryButton"));
			action.moveToElement(button).click().perform();
	 }
	}
	
	private void enterViolationInfo(String[] violations){
	            for(int i=0 ; i<violations.length;i++){
	            	
				if(i>0)driver.findElement(By.id("add_accident")).click();
				
				driver.findElement(By.id("acc_type_"+ i +"_chosen")).click();
				Select select_type = new Select(driver.findElement(By.id("acc_type-"+i)));
				select_type.selectByIndex(i+1);
	           
				Select select_descrip = new Select(driver.findElement(By.id("acc_descr-"+i)));
				select_descrip.selectByIndex(1);
			    
				driver.findElement(By.id("acc_month-"+i)).sendKeys("02");
				driver.findElement(By.id("acc_year-"+i)).sendKeys("2015");
	            }
	}
}
