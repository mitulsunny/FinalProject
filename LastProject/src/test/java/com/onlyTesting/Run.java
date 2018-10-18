package com.onlyTesting;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.bit.obaidulla.lastProject.common.Base;
import com.bit.obaidulla.lastProject.common.LoggerHelper;
import com.bit.obaidulla.lastProject.common.Utility;
public class Run extends Base{

@Test(priority=1)
public void verifyTitle() throws IOException {
	dr.get("https://www.facebook.com");
	log.info("verifying Title");
	String expectedTitle="Facebook - Log In or Sign Up";
	Utility.myVerifyTitle(dr, expectedTitle);
	log.info("verified");
}
@Test(priority=2)
public void verifyLogin() {
	dr.get("https://www.facebook.com"); 
	log.info("verifying login ");
	Utility.myClear(dr.findElement(By.id("email")));
	Utility.mySendKey(dr.findElement(By.id("email")), "mitul.li@yahoo.com");
	Utility.myClear(dr.findElement(By.id("pass")));
	Utility.mySendKey(dr.findElement(By.id("pass")), "01723Mitul");
	Utility.myClick(dr.findElement(By.id("u_0_2")));
	Utility.myVerification(dr.findElement(By.xpath("@a[]")),"Not Found");
	log.info("verified ");
	
}
}
