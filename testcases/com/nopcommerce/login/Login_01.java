package com.nopcommerce.login;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browsers.BrowserDriverFactory;
import browsers.DriverManager;
import commons.AbstractTest;
import commons.GlobalConstants;
import pageObjects.LoginPageObject;
import pageObjects.PageFactoryManager;
import pageObjects.StartPageObject;

public class Login_01 extends AbstractTest {

	private WebDriver driver;
	private DriverManager driverManager;
	private StartPageObject startObject;
	private LoginPageObject loginObject;
	private String actualErrorMsg;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		log.info("Pre-condition - STEP 01: get browser driver");
		driverManager = BrowserDriverFactory.getBrowserDriver(browserName);
		
		log.info("Pre-condition - STEP 02: open browser with specific config");
		driver = driverManager.getDriver();
		
		log.info("Pre-condition - STEP 03: open start page");
		startObject = PageFactoryManager.getStartPageObject(driver);
	}
	
	@Test
	public void TC_01_loginWithEmptyData() {
		startObject.navigateToPage_HeaderLink(driver, "login");
		loginObject = PageFactoryManager.getLoginPageObject(driver);
		loginObject.clickToDynamicButton(driver, "Log in");
		actualErrorMsg = loginObject.getEmailErrorMsg();
		verifyEquals(actualErrorMsg, "Please enter your email");
	}

	@Test
	public void TC_02_loginWithInvalidEmail() {
		loginObject.inputToDynamicTextbox(driver, "Email", "123@123.123");
		loginObject.clickToDynamicButton(driver, "Log in");
		actualErrorMsg = loginObject.getEmailErrorMsg();
		verifyEquals(actualErrorMsg, "Wrong email");
	}
	
	@Test
	public void TC_03_loginWithEmailNotExisted() {
		loginObject.inputToDynamicTextbox(driver, "Email", "ngo.le@gmail.com");
		loginObject.clickToDynamicButton(driver, "Log in");
		actualErrorMsg = loginObject.getSummaryErrorMsg();
		verifyEquals(actualErrorMsg, "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}
	
	@Test
	public void TC_04_loginWithEmptyPassword() {
		loginObject.inputToDynamicTextbox(driver, "Email", GlobalConstants.EMAIL);
		loginObject.clickToDynamicButton(driver, "Log in");
		actualErrorMsg = loginObject.getSummaryErrorMsg();
		verifyEquals(actualErrorMsg, "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void TC_05_loginWithIncorrectPassword() {
		loginObject.inputToDynamicTextbox(driver, "Email", GlobalConstants.EMAIL);
		loginObject.inputToDynamicTextbox(driver, "Password", "123457");
		loginObject.clickToDynamicButton(driver, "Log in");
		actualErrorMsg = loginObject.getSummaryErrorMsg();
		verifyEquals(actualErrorMsg, "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void TC_06_loginWithValidInformations() {
		loginObject.inputToDynamicTextbox(driver, "Password", GlobalConstants.PASSWORD);
		loginObject.clickToDynamicButton(driver, "Log in");
		verifyTrue(loginObject.isMyAccountDisplayed());
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		log.info("Post-condition - close browser");
		closeBrowserAndDriver(driver);
	}

}
