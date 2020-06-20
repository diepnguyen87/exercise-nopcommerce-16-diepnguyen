package com.nopcommerce.login;

import org.testng.annotations.Test;

import browsers.BrowserDriverFactory;
import browsers.DriverManager;
import pageObjects.LoginPageObject;
import pageObjects.PageFactoryManager;
import pageObjects.StartPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.server.handler.GetElementText;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Login_01 {

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
		loginObject = startObject.clickLoginLink();
		loginObject.clickLoginButton();
		actualErrorMsg = loginObject.getEmailErrorMsg();
		Assert.assertEquals(actualErrorMsg, "Please enter your email");
	}

	@Test
	public void TC_02_loginWithInvalidEmail() {
		loginObject.inputEmail("123@123.123");
		loginObject.clickLoginButton();
		actualErrorMsg = loginObject.getEmailErrorMsg();
		Assert.assertEquals(actualErrorMsg, "Wrong email");
	}
	
	@Test
	public void TC_03_loginWithEmailNotExisted() {
		loginObject.inputEmail("ngo.le@gmail.com");
		loginObject.clickLoginButton();
		actualErrorMsg = loginObject.getSummaryErrorMsg();
		Assert.assertEquals(actualErrorMsg, "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}
	
	@Test
	public void TC_04_loginWithEmptyPassword() {
		loginObject.inputEmail("diep.test200@gmail.com");
		loginObject.clickLoginButton();
		actualErrorMsg = loginObject.getSummaryErrorMsg();
		Assert.assertEquals(actualErrorMsg, "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void TC_05_loginWithIncorrectPassword() {
		loginObject.inputEmail("diep.test200@gmail.com");
		loginObject.inputPassword("123457");
		loginObject.clickLoginButton();
		actualErrorMsg = loginObject.getSummaryErrorMsg();
		Assert.assertEquals(actualErrorMsg, "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void TC_06_loginWithValidInformations() {
		loginObject.inputPassword("123456");
		loginObject.clickLoginButton();
		Assert.assertTrue(loginObject.isMyAccountDisplayed());
	}
	
	@AfterClass
	public void afterClass() {
	}

}
