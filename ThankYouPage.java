package com.esales.PA.test;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ThankYouPage {
	
	WebDriver driver = Main.getDriver();
	
	public void verify(Logger log){
		new WebDriverWait(driver, 20).until(ExpectedConditions.titleContains("Thank You"));
		try{driver.findElement(By.id("btnDialogRegisterNow")).click();}catch(Exception e){};
		WebElement policyNum = driver.findElement(By.xpath("//*[@id=\"stylized\"]/div[1]/div[1]/h3[1]"));
		if(policyNum.isDisplayed()){
		log.info("Success in prefill test : We get a "+policyNum.getText());
		}else{
			log.error("Error in prefill test: We do not get a valid policy number in the end.");
		}
	}
}
