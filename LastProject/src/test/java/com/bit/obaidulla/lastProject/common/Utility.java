package com.bit.obaidulla.lastProject.common;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.google.common.hash.HashingInputStream;

public class Utility {
	public static void myVerification(WebElement e, String message) {
		if(e.isDisplayed()) {
			System.out.println(message);
		}else {
			System.out.println("Fail");
		}	
	}
	public static void myVerifyTitle(WebDriver dr, String expectedTitle) {
		String actualTitle=dr.getTitle();
		if(actualTitle.equals(expectedTitle)){
			System.out.println("Title Matched : "+ actualTitle);
		}else {
			System.out.println("Failed \nExpected : "+ expectedTitle + "\nActual : " + actualTitle);
		}
}
	public static String myGetText(WebElement e) {
		String myText=e.getText();
		return myText;
	}
	public static void myClear(WebElement e) {
		e.clear();
	}
	public static void myClick(WebElement e) {
		e.click();
	}
	public static void mySendKey(WebElement e,String sendKey) {
		e.sendKeys(sendKey);
	}
	public static Properties  readPropertyFile(String path) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader(path));
		Properties p = new Properties();
		p.load(f);
		return p;
	}
public static void takeScreenShot(WebDriver dr, String path) throws IOException {
	TakesScreenshot s=((TakesScreenshot)dr); 
	File sf=s.getScreenshotAs(OutputType.FILE);
	File df=new File(path);
	FileUtils.copyFile(sf, df);
}
public WebElement waitForElement(WebElement e ) {
	ExpectedConditions.visibilityOf(e);
	return e;
 }
public static void verifyLinkText(WebDriver dr, List<WebElement> elements, String[]ex) {
	String [] textArray=new String [elements.size()];
	int i=0;
	for(WebElement element: elements) {
		textArray[i]=element.getText();
		if(textArray[i].equals(ex[i])) {
			System.out.println("Matched");
		}else {
			System.out.println("Not matched");
		}
		i++;
	}
}
public static void hoverOver(WebDriver dr, WebElement e) {
	Actions a= new Actions(dr);
	a.moveToElement(e).perform();
}
public static Actions rightClick(WebDriver dr, WebElement e) {
	Actions a= new Actions(dr);
	a.contextClick(e).perform();
	return a;
}
public static void dragAndDrop(WebDriver dr, WebElement source, WebElement target) {
	Actions a= new Actions(dr);
	a.dragAndDrop(source, target).perform();
}
public static void dragAndDrop(WebDriver dr, WebElement source,int x, int y) {
	Actions a= new Actions(dr);
	a.dragAndDropBy(source, x, y).perform();
}
public static void multipleWindowHandle(WebDriver dr, WebElement e) {
	rightClick(dr,e).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
	String parent=dr.getWindowHandle();
	Set<String> childWindows=dr.getWindowHandles();
	//dr.switchTo().defaultContent();
	for(String win:childWindows) {
		if(!win.equals(parent)) {
			dr.switchTo().window(win);
		}
	}
}
public void iFrameHandler(WebDriver dr, String frameName) {
	dr.switchTo().frame(frameName);
}
public void multipleIFrameHandler(WebDriver dr,String attributeName, String attributeValue) {
	List<WebElement> elements=dr.findElements(By.tagName("iframe"));//<iframe></iframe>
	for(WebElement el: elements) {
		if(el.getAttribute(attributeName).equals(attributeValue)) {
			dr.switchTo().frame(attributeValue);			
		}
	}
}
public static void alertHandlerAccept(WebDriver dr) {
	Alert a= dr.switchTo().alert();
	a.accept();
}
public static void alertHandlerDismiss(WebDriver dr) {
	Alert a= dr.switchTo().alert();
	a.dismiss();
}
public static void alertHandlerSendKeys(WebDriver dr, String type) {
	Alert a= dr.switchTo().alert();
	a.sendKeys(type);
}
public static String getTextFromAlertHandler(WebDriver dr) {
	Alert a= dr.switchTo().alert();
	return a.getText();
}
public static void executeScript(WebDriver dr, int x, int y) {
	JavascriptExecutor js= (JavascriptExecutor)dr;
	js.executeScript("window.scrollTo("+x+","+y+")"); //BOM is browser, DOM is Document
	
}
//how to click on selenium using javaScript
public static void clickByJs(WebDriver dr,WebElement e) {
	JavascriptExecutor js= (JavascriptExecutor)dr;
	js.executeScript("arguments[0].click();",e);
	
}
//how to click on Hidden Element
public static void findHiddenElement(WebDriver dr, WebElement e) {
	JavascriptExecutor js= (JavascriptExecutor)dr;
	js.executeScript("arguements[0].setAttribute('style','visibility:visible;", e);
	}
public static void handleKeyBoard(WebDriver dr) {
	Keyboard kb= ((HasInputDevices) dr).getKeyboard();
	//kb.sendKeys(Keys.chord(Keys.CONTROL,Keys.ALT,"p"));//for windows
	kb.sendKeys(Keys.chord(Keys.COMMAND,Keys.SHIFT,"p"));//for mack
	//kb.pressKey(Keys.ENTER);
}
public static void handleDropDown(WebDriver dr) {
	Select month =new Select(dr.findElement(By.id("month")));
	
}
}

