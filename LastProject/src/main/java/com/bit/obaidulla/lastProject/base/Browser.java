package com.bit.obaidulla.lastProject.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Browser {
	
	WebDriver dr;
	@Test
	public void openBrowser() {
		/*
		System.setProperty("webdriver.chrome.driver", "/Users/mdobaidulla/desktop/chromedriver");
		dr=new ChromeDriver();
		*/
		System.setProperty("webdriver.gecko.driver", "/src/test/resources/driver/chromedriver");
		dr=new FirefoxDriver();
		dr.get("https://www.facebook.com");
		
		
		
	}
}
