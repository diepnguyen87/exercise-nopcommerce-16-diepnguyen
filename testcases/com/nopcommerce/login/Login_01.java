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
		driverManager = BrowserDriverFactory.getBrowserDriver(browserName);
		driver = driverManager.getDriver();
		startObject = PageFactoryManager.getStartPageObject(driver);
	}
	
	@Test
	public void TC_01_loginWithEmptyData() {
		startObject.navigateToPage_HeaderLink(driver, "login");
		loginObject = PageFactoryManager.getLoginPageObject(driver);
		loginObject.clickLoginButton();
		actualErrorMsg = loginObject.getEmailErrorMsg();
		verifyEquals(actualErrorMsg, "Please enter your email");
	}

	@Test
	public void TC_02_loginWithInvalidEmail() {
		loginObject.inputEmail("123@123.123");
		loginObject.clickLoginButton();
		actualErrorMsg = loginObject.getEmailErrorMsg();
		verifyEquals(actualErrorMsg, "Wrong email");
	}
	
	@Test
	public void TC_03_loginWithEmailNotExisted() {
		loginObject.inputEmail("ngo.le@gmail.com");
		loginObject.clickLoginButton();
		actualErrorMsg = loginObject.getSummaryErrorMsg();
		verifyEquals(actualErrorMsg, "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}
	
	@Test
	public void TC_04_loginWithEmptyPassword() {
		loginObject.inputEmail(GlobalConstants.EMAIL);
		loginObject.clickLoginButton();
		actualErrorMsg = loginObject.getSummaryErrorMsg();
		verifyEquals(actualErrorMsg, "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void TC_05_loginWithIncorrectPassword() {
		loginObject.inputEmail(GlobalConstants.EMAIL);
		loginObject.inputPassword("123457");
		loginObject.clickLoginButton();
		actualErrorMsg = loginObject.getSummaryErrorMsg();
		verifyEquals(actualErrorMsg, "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void TC_06_loginWithValidInformations() {
		loginObject.inputPassword(GlobalConstants.PASSWORD);
		loginObject.clickLoginButton();
		verifyTrue(loginObject.isMyAccountDisplayed());
	}
	
	@AfterClass
	public void afterClass() {
	}

}
