package com.esales.PA.test;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.log4j.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {
	private static WebDriver driver = null;
    private static WebDriverWait wait = null;
    private static Logger log = LogManager.getLogger(Main.class.getName());
    
    public static WebDriver getDriver(){
    	return driver;
    }
    
    public static WebDriverWait getDriverWait(){
    	return wait;
    }
    
    public static Boolean returnExpectedCondtion(WebElement ele, String text){
		return(wait.until(ExpectedConditions.textToBePresentInElement(ele, text)));
	}
    
    public static void main(String[] args) {
    	
   // delete previous log file first
    try{	Files.delete(Paths.get("./logs/log.out"));
    	}catch(Exception e){ e.printStackTrace();}
      
      PropertyConfigurator.configure("log.properties");
  	  int rowNum_PreFill = 3;
  	  int rowNum_Validation = 4;
  	  int row_discount_0 = 10;
  	  // complete get user input row number 
  	  
  	  System.setProperty("webdriver.chrome.driver","chromedriver");
	  ChromeOptions options = new ChromeOptions();
	  options.setBinary("chromeBinary/chrome.exe");
	  options.addArguments("--start-maximized");
	  options.addArguments("--headless");
      driver = new ChromeDriver(options);
	  wait = new WebDriverWait(driver,5);
	  // complete system set up 
	  
	  new AutoFillTest().test(rowNum_PreFill, log);
	  new ValidationMessageTest().test(rowNum_Validation, log);
	  
	  DiscountsTest discountTest = new DiscountsTest();
	  discountTest.test(row_discount_0,log);
	  // complete test 

		 log.info("All Test completed"); 
		 driver.close();
   }
}
