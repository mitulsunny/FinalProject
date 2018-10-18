package com.bit.obaidulla.lastProject.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestClass {
public static void main(String[] args) {
	System.setProperty("webdriver.gecko.driver", "/Users/mdobaidulla/Desktop/geckodriver");
	WebDriver dr=new FirefoxDriver();
	dr.get("https://www.facebook.com");
}
}
