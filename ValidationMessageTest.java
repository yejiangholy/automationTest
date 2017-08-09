package com.esales.PA.test;

import java.util.*;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.esales.data.dynamic.PA_test_data;

public class ValidationMessageTest {
	
	private static WebDriver driver = null;
	private Map<String, String> data = null;
    public void test(int rowNum, Logger log){
    	try{data = PA_test_data.readExcel(rowNum);
    	}catch(Exception e){e.printStackTrace();}
    	driver = Main.getDriver();
		driver.get("http://es2plymouthrockqa.prcins.net/esales/entry/start ");

		try{
		log.info("Start validation message test");
		new Validation_zipCode().validate(data.get("zip"), log);
		new Validation_customerInfo().validate(data.get("fName_0"),data.get("lName_0"), data.get("address"), data.get("email"),data.get("policy_startData"),log);
		new Validation_driverInfo().validate(data.get("dob_0"),log);
		new Validation_chooseVehicle().validate(PA_test_data.vehicleNumber , log);
		new Validation_firstVehicleInfo().validate(PA_test_data.vehicleNumber, log);
		new Validation_discountsInfo().validate(log);
		new Validation_details().validate(log);
		new Validation_quotePreview().validate();
		new Validation_finalDriverDetails().validate(data.get("LicenseState_0"),data.get("LicenseNum_0"),data.get("complete_date"),log);
		new Validation_finalVehicleDetails().validate(log);
		new Validation_finalPolicyDetails().validate(log);
		new Validation_checkOut().validate(data.get("fName_0")+" "+data.get("lName_0"), data.get("creditCard_Num"), data.get("cvv"),log);
		new Validation_policyDocuments().validate(data.get("fName_0"), data.get("lName_0"),log);
		log.info("Validation test completed");
		}catch(Exception ex){
			log.error("Exception occured when doing validation message tests");
			ex.printStackTrace();
			try{Thread.sleep(100000);}catch(Exception e){}
		}
    }
}
