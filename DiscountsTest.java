package com.esales.PA.test;

import java.io.IOException;
import java.util.*;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.esales.data.dynamic.PA_test_data;

public class DiscountsTest {
	private WebDriver driver = Main.getDriver();
	
	public void test(int rowNum , Logger log){
		Map<String, String> data = null;
		try{
			data = PA_test_data.readExcel(rowNum);
		} catch (IOException e){
			log.fatal("Exceptoin occured when trying to read the data in the excel sheet row number: "+rowNum);
		}
		   log.info("Start a discount match test with expected discounts: ");
		   log.info("Expectation: "+data.get("expectation"));
		 driver.get("http://es2plymouthrockqa.prcins.net/esales/entry/start");
		 
		 try{
			 new ZipCodeEntry().enterZip(data.get("zip"));
				new CustomerInfo().enterCustomerInfo(data.get("fName_0"), data.get("lName_0"), data.get("address"), data.get("email"),data.get("policy_startData"));
				new DriverInfo().enterDriverInfo(data.get("dob_0"), data.get("gender_0"), data.get("marital"), data.get("age_first_Licensed_0"), data.get("has_accident_Past5"), data.get("type").split(";"));
					new driver2Page().enter(PA_test_data.userNumber-1, data);
				new ChooseVehicles().chooseVehicels(PA_test_data.vehicleNumber, log);
				for(int i=0 ;i<PA_test_data.vehicleNumber ; i++){
				new VehicleInfo().enter(data.get("usage"), data.get("purchase_method"), data.get("anti_theft"));
				}
				new DiscountsInfo().enter(data.get("eDelivery"), data.get("commercial_auto"), data.get("affinity_group"), data.get("motor_club"),data.get("driverImprovement"));
				DetailsPage details = new DetailsPage();
				
				if("YES".equalsIgnoreCase(data.get("homeOwner_insurance"))){
					details.enterHomeOwner_Yes();
				}else{
					details.enterHomeOwner_No();
				}
				if("YES".equalsIgnoreCase(data.get("current_have_insurance"))){
					details.enterCarInsurance_Yes();
				}else{
					details.enterCarInsurance_No();
				}
				
				List<String>  actual_discounts = new QuotePreview().getDiscounts();
				String[]  expect_discounts = data.get("expectation").split(";");
				
				if(match(actual_discounts, expect_discounts,log)){
					log.info("Discount test success ! we get all the expected discount displayed");
				}else{
					log.error("Actual discounts is not match with the discounts we expected");
				}
		 }catch(Exception ee){
			 log.error("Exception occured when perfroming discounts test: "+ee.toString());
			ee.printStackTrace();
		 } 
	}
	private boolean match(List<String> actual , String[] expect, Logger log){
		boolean result = true;
		for(int i=0 ; i<expect.length ; i++){
			String cur = expect[i].toLowerCase().trim();
			if(actual.contains(cur)){
				actual.remove(cur);
			}else{
				log.error("We expect dicount: "+cur +" does not showed up on screen.");
				result = false; 
			}
		}
		return result; 
	}
}
