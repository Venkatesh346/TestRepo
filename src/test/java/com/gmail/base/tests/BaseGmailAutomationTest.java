package com.gmail.base.tests;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.gmail.base.pages.GmailLoginPage;


import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseGmailAutomationTest {
	
protected WebDriver driver=null;
	
	protected GmailLoginPage loginPage=null;
	protected String loginURL = null;
	
	protected static Map<String ,String> chromeDriverMap = new HashMap<String ,String>();
	
	private static final Logger logger=Logger.getLogger(BaseGmailAutomationTest.class.getName());
	
	protected static Properties testDataProp=null;
	protected static Properties expectedAssertionsProp=null;
	
	@BeforeSuite
	@Parameters({ "siteURL" })
	public void initTestData() {

		
		
		/*
		 * if (siteURL != null) {
		 * 
		 * this.loginURL = siteURL;
		 * 
		 * }
		 */
		 
		 

		if (testDataProp == null) {
			FileReader testDataReader = null;
			FileReader assertionsReader = null;
			try {
				testDataReader = new FileReader("src/main/resources/testdata.properties");
				assertionsReader = new FileReader("src/main/resources/expectedassertions.properties");

				testDataProp = new Properties();
				testDataProp.load(testDataReader);

				expectedAssertionsProp = new Properties();
				expectedAssertionsProp.load(assertionsReader);

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					testDataReader.close();
					assertionsReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * This method is used for get driver
	 * 
	 * @param webDriver
	 * @return
	 */

	protected synchronized WebDriver getWebDriver(String browser) {
		logger.info("Starting of method getWebDriver");

		String osPath = System.getProperty("os.name");
		WebDriver webDriver = null;

		if (osPath.contains("Linux")) {

			if (browser.equalsIgnoreCase("Firefox")) {

				WebDriverManager.firefoxdriver().setup();

				FirefoxOptions options = new FirefoxOptions();
				options.setHeadless(true);
				options.addArguments("--no-sandbox");

				webDriver = new FirefoxDriver(options);

			} else if (browser.equalsIgnoreCase("Chrome")) {

				WebDriverManager.chromedriver().setup();

				ChromeOptions options = new ChromeOptions();

				options.setHeadless(true);
				options.addArguments("--headless"); // Bypass OS security model, MUST BE THE VERY FIRST OPTION
				options.addArguments("enable-automation");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-extensions");
				options.addArguments("--dns-prefetch-disable");
				options.addArguments("--disable-gpu");
				options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

				options.addArguments("--window-size=1920,1080");
				options.setPageLoadStrategy(PageLoadStrategy.EAGER);// del
				options.addArguments("--disable-browser-side-navigation"); // del
				options.addArguments("--disable-dev-shm-usage"); // del
				options.addArguments("--disable-gpu");
				options.addArguments("--no-sandbox");
				options.addArguments("auto-open-devtools-for-tabs", "true");

				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_settings.popups", 0);
				options.setExperimentalOption("prefs", prefs);

				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setBrowserName("CHROME");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				capabilities.setCapability(CapabilityType.SUPPORTS_NETWORK_CONNECTION, true);
				capabilities.setCapability("applicationCacheEnabled", "true");

				webDriver = new ChromeDriver(options);
			}

		} else if (osPath.contains("Mac OS X")) {

			if (browser.equalsIgnoreCase("Chrome")) {
				WebDriverManager.chromedriver().setup();

				ChromeOptions options = new ChromeOptions();

				options.addArguments("enable-automation");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-extensions");
				options.addArguments("--dns-prefetch-disable");
				options.addArguments("--disable-gpu");
				options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

				webDriver = new ChromeDriver(options);

			} else if (browser.equalsIgnoreCase("Firefox")) {
				WebDriverManager.firefoxdriver().setup();
				webDriver = new FirefoxDriver();

			}

		} else {

			if (browser.equalsIgnoreCase("Chrome")) {

				WebDriverManager.chromedriver().setup();

				ChromeOptions options = new ChromeOptions();

				options.addArguments("enable-automation");
				//options.addArguments("--headless");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-extensions");
				options.addArguments("--dns-prefetch-disable");
				options.addArguments("--disable-gpu");
				options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

				//options.setHeadless(isHeadless());
				options.addArguments("--no-sandbox");

				webDriver = new ChromeDriver(options);



			} else if (browser.equalsIgnoreCase("Firefox")) {

				WebDriverManager.firefoxdriver().setup();
				webDriver = new FirefoxDriver();

			} else if (browser.equalsIgnoreCase("Chromium")) {

				WebDriverManager.chromiumdriver().setup();
				webDriver = new EdgeDriver();

			} else if (browser.equalsIgnoreCase("IEDriverServer")) {

				WebDriverManager.iedriver().setup();
				webDriver = new InternetExplorerDriver();
			}
		}
		
		webDriver.manage().window().maximize();
		webDriver.manage().deleteAllCookies();
		webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		logger.info("**************** Driver Successfully Created *************** " + webDriver.getTitle());
		logger.info("End of method getWebDriver");

		return webDriver;
	}

	protected void goToSite(String siteURL, WebDriver driver) throws Exception {

		driver.get(siteURL);

	}


}
