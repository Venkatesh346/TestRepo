package com.gmail.base.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class GmailLoginPage extends BaseGmailAutomationPage{
	
	@FindBy(xpath = "//span[text()='Sign in']")
	private WebElement lblSignInTxt;
	
	@FindBy(xpath = "//input [@id='identifierId']")
	private WebElement txtBoxEmail;
	
	@FindBy(xpath = "//span[text()='Next']")
	private WebElement btnNext;
	
	@FindBy(xpath = "//span[text()='Forgot password?']")
	private WebElement lblForgotPassword;
	
	
	@FindBy(xpath = "//input [@type='password']")
	private WebElement txtBoxPassword;
	
	@FindBy(xpath = "//span[text()='Next']")
	private WebElement btnNextPassword;
	
	
	private static final Logger logger = Logger.getLogger(GmailLoginPage.class.getName());

	public GmailLoginPage(WebDriver driver) {
		super(driver);

		logger.info("Starting of GmailLoginPage constructor");
		PageFactory.initElements(driver, this);

		logger.info("Ending of GmailLoginPage constructor");

	}
	
	
	public String getLblSignInTxt() {

		logger.info("Starting of getLblSignInTxt method");

		logger.info("Starting of getLblSignInTxt method");

		return lblSignInTxt.getText();
	}
	
	
	public void setTxtBoxEmail(String emailOrPhoneNumber) {
		logger.info("Starting of setTextBoxEmail method");

		this.clickOnWebElement(txtBoxEmail);
		this.txtBoxEmail.clear();
		this.txtBoxEmail.sendKeys(emailOrPhoneNumber);
		
		logger.info("Ending of setTextBoxEmail method");
	}
	
	public void clickOnBtnNext() {

		logger.info("Starting of clickOnBtnNext method");
		// this.btnLogin.click();
		this.clickOnWebElement(btnNext);
		
		logger.info("Ending of clickOnBtnNext method");
	}
	
	public String getLblForgotPassword() {

		logger.info("Starting of getLblForgotPassword method");

		logger.info("Starting of getLblForgotPassword method");

		return lblForgotPassword.getText();
	}
	
	public void setTxtBoxPassword(String password) {
		
		logger.info("Starting of setTxtBoxPassword method");

		this.clickOnWebElement(txtBoxPassword);
		this.txtBoxPassword.clear();
		this.txtBoxPassword.sendKeys(password);
		
		logger.info("Ending of setTxtBoxPassword method");
	}
	
	
	
	
	
	
	

}
