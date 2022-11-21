package com.gmail.base.pages;

import java.io.File;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;



public class BaseGmailAutomationPage {
	
	protected WebDriver driver =null;
	public static String TEST_FILE_PATH=null;
	
	public static final Logger logger=Logger.getLogger(BaseGmailAutomationPage.class.getName());
	
	public BaseGmailAutomationPage(WebDriver driver) {
		
		this.driver=driver;
		
		if(TEST_FILE_PATH==null) {
			
			TEST_FILE_PATH = getTestFilePath();
					
		}
		
		PageFactory.initElements(driver, this);
		
	}
	
	public String getTestFilePath() {
		
		String path="src/test/resources";
		
		File file=new File(path);
			
		return file.getAbsolutePath();
	}
	
	public void clickOnWebElement(WebElement webelement) {
		logger.info("Starting of clickOnWebElement method");

		JavascriptExecutor jsExec = (JavascriptExecutor) driver;
		jsExec.executeScript("arguments[0].click();", webelement);

		logger.info("Ending of clickOnWebElement method");
	}

	



}
