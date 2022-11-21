package com.gmail.base.tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.gmail.base.pages.GmailLoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import static com.gmail.util.Constants.*;

public class GmailLoginTest extends BaseGmailAutomationTest {
	
	private static final Logger logger = Logger.getLogger(GmailLoginTest.class.getName());

	@BeforeClass
	@Parameters({ "siteURL", "browser" })
	public void initGmailLogin(String siteURL, String browser) throws Exception {

		this.driver = this.getWebDriver(browser);

		this.goToSite(siteURL, this.driver);

		this.loginPage = new GmailLoginPage(this.driver);

	}
	
	
	@Test(priority = 1, description = "verifying Login")
	@Description("verifying Login")
	@Severity(SeverityLevel.BLOCKER)
	@Story("verifying Login")
	public void verifyLogin() {

		logger.info("Starting of verifyLogin method");

		try {

			// Assert.assertEquals(this.signUpPage.getTxtHome(),
			// expectedAssertionsProp.getProperty("home"));

			//Thread.sleep(3000);
			String textSignIn = this.loginPage.getLblSignInTxt();
			Assert.assertEquals(textSignIn, expectedAssertionsProp.getProperty("sign.in"));
			
			this.loginPage.setTxtBoxEmail(testDataProp.getProperty(EMAIL));;
			this.loginPage.clickOnBtnNext();
			
			String textForgotPasswort = this.loginPage.getLblForgotPassword();
			Assert.assertEquals(textForgotPasswort, expectedAssertionsProp.getProperty("forgot.password"));
			
			this.loginPage.setTxtBoxPassword(testDataProp.getProperty(PASSWORD));
			this.loginPage.clickOnBtnNext();
			
			

		} catch (Exception e) {

			Assert.fail("Exception occured while verifying Login" + e.getMessage());
			logger.error("Error occured while testing Login  " + e);
		}

		logger.info("Ending of verifyLogin method");
	}
	
	

}
