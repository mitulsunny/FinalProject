package com.bit.obaidulla.lastProject.common;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;


public class Base {
	Properties prop;
	 public WebDriver dr=null;
	 String browser, browserT;
	 protected Logger log=LoggerHelper.getLogger(Base.class);
	 
	@BeforeTest
	public void beforeTest() throws IOException {
		String n=ResourcesHelper.getResourcesPath("/src/test/resources/properties/browsers.properties");
		//System.out.println(n);
		prop = Utility.readPropertyFile(n);
		 browser = prop.getProperty("default");
		 browserT=prop.getProperty("browserType");
	}
	@BeforeMethod
	public WebDriver openBrowser() throws IOException {
		
		if (browserT.equals("local")) {
			chooseLocalBrowser(browser);
			
		}else if(browserT.equals("remote")){
			chooseRemoteBrowser(browser);
		} else {
			chooseLocalBrowser("chrome");
		}
	return dr;
	}
	
	public WebDriver getDriver() throws IOException {
		if(dr==null) {
			openBrowser();
		}
		return dr;
	}
	public void chooseLocalBrowser(String browser) {
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver","/Users/mdobaidulla/Desktop/chromedriver");
			dr=new ChromeDriver();
		} else if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver","/Users/mdobaidulla/Desktop/geckodriver");
			dr=new FirefoxDriver();
		} else if (browser.equals("ie")) {
				//IE browser code goes here
		}
	}
	
	public void chooseRemoteBrowser(String browser) throws MalformedURLException {
		if (browser.equals("chrome")) {
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setPlatform(Platform.MAC);
	
			dr = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		} else if (browser.equals("firefox")) {
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setPlatform(Platform.MAC);
			dr = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		} else if (browser.equals("ie")) {
				//IE browser code goes here
		}
	}
@AfterMethod	
public void afterMethod(ITestResult r) throws InterruptedException, IOException {
	System.out.println("this");
	if(r.getStatus()==ITestResult.FAILURE) {
		log.info("Test case failed! \nTake Screen shot of testCase: "+r.getName());
		String path=ResourcesHelper.getResourcesPath("/screenshot/"+r.getName()+".png");
		Utility.takeScreenShot(dr, path);
		
	}
	Thread.sleep(3000);
	dr.quit();
}


}
