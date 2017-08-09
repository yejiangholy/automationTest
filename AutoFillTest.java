package com.esales.PA.test;

import java.io.IOException;
import java.util.*;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.annotations.Test;

import com.esales.data.dynamic.PA_test_data;


public class AutoFillTest {
	private static WebDriver driver = null;
    
	public void test(int rowNum, Logger log){
		
		driver = Main.getDriver();
		driver.get("http://es2plymouthrockqa.prcins.net/esales/entry/start");
			HashMap<String,String> data = null;
		try{
			data = PA_test_data.readExcel(rowNum);
		} catch (IOException e){
		     log.fatal("Exceptoin occured when trying to read the data in the excel sheet row number: "+rowNum);
		}
		//messages.add("Expectation: "+data.get("expectation"));
		log.info("Expectation: "+data.get("expectation"));
		try{
			log.info("Start autofilling test");
		new ZipCodeEntry().enterZip(data.get("zip"));
		new CustomerInfo().enterCustomerInfo(data.get("fName_0"), data.get("lName_0"), data.get("address"), data.get("email"),data.get("policy_startData"));
		new DriverInfo().enterDriverInfo(data.get("dob_0"), data.get("gender_0"), data.get("marital"), data.get("age_first_Licensed_0"), data.get("has_accident_Past5"), data.get("type").split(";"));
			new driver2Page().enter(PA_test_data.userNumber-1, data);
		new ChooseVehicles().chooseVehicels(PA_test_data.vehicleNumber,log);
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
		//try{Thread.sleep(300000);}catch(Exception e){}
		new QuotePreview().printQuotePrice();
		new FinalDriverDetails().enter(data.get("LicenseState_0"),data.get("LicenseNum_0"),data.get("complete_date"));
		new FinalVehicleDetails().select();
		new FinalPolicyDetails().select();
		new CheckOut().checkOut(data.get("fName")+" "+data.get("lName"), data.get("creditCard_Num"), data.get("cvv"));
	    new PolicyDocuments().acknowledge(data.get("fName_0"),data.get("lName_0"),data.get("creditCard_Num"), data.get("cvv"));
	    log.info("Autofilling test completed");
	   // new ThankYouPage().verify(log);
		}catch(Exception ee){
			ee.printStackTrace();
			System.out.println("Exception occured when performing auto fill test");
			try{Thread.sleep(3000000);}catch(Exception e){}
			log.fatal("Exception occured when perfroming prefill test: "+ee.toString());
		}
	}
}
