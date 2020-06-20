package com.nopcommerce.register;

import org.testng.annotations.Test;

import browsers.BrowserDriverFactory;
import browsers.DriverManager;
import commons.AbstractTest;
import commons.GlobalConstants;
import pageObjects.HomePageObject;
import pageObjects.PageFactoryManager;
import pageObjects.RegisterPageObject;
import pageObjects.StartPageObject;
import pageUI.HomePageUI;
import pageUI.RegisterPageUI;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Register_01{

	private WebDriver driver;
	private DriverManager driverManager;
	private StartPageObject startObject;
	private HomePageObject homeObject;
	private RegisterPageObject registerObject;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driverManager = BrowserDriverFactory.getBrowserDriver(browserName);
		driver = driverManager.getDriver();
		startObject = PageFactoryManager.getStartPageObject(driver);
	}

	@Test
	public void TC_01_registerWithEmptyData() {
		registerObject = startObject.clickRegisterLink();
		registerObject.clickRegisterButton();

		Assert.assertTrue(registerObject.isErrorMessageDisplayed(RegisterPageUI.FIRSTNAME_ERROR_MESSAGE, "First name is required."));
		Assert.assertTrue(registerObject.isErrorMessageDisplayed(RegisterPageUI.LASTNAME_ERROR_MESSAGE, "Last name is required."));
		Assert.assertTrue(registerObject.isErrorMessageDisplayed(RegisterPageUI.EMAIL_ERROR_MESSAGE, "Email is required."));
		Assert.assertTrue(registerObject.isErrorMessageDisplayed(RegisterPageUI.PASSWORD_ERROR_MESSAGE, "Password is required."));
		Assert.assertTrue(registerObject.isErrorMessageDisplayed(RegisterPageUI.CONFIRMPASSWORD_ERROR_MESSAGE, "Password is required."));
	}

	@Test
	public void TC_02_registerWithInvalidEmail() {
		registerObject.inputFirstName(GlobalConstants.fistName);
		registerObject.inputLastName(GlobalConstants.lastName);
		registerObject.inputEmail("123@123.123");
		registerObject.inputPasword("123456");
		registerObject.inputConfirmPassword("123456");
		
		registerObject.clickRegisterButton();
		Assert.assertTrue(registerObject.isErrorMessageDisplayed(RegisterPageUI.WRONG_EMAIL_MESSAGE, "Wrong email"));
	}
	
	@Test
	public void TC_03_registerWithExistedEmail() {
		registerObject.inputEmail("van.tran@gmail.com");
		registerObject.inputPasword("123456");
		registerObject.inputConfirmPassword("123456");
		
		registerObject.clickRegisterButton();
		Assert.assertTrue(registerObject.isErrorMessageDisplayed(RegisterPageUI.SUMMARY_ERROR_MESSAGE, "The specified email already exists"));	
	}
	
	@Test
	public void TC_04_registerWithPasswordLessThan6Chars() {
		registerObject.inputEmail("diep.nguyen" + registerObject.getRandomNumber() + "@gmail.com");
		registerObject.inputPasword("12345");
		registerObject.pressControlTab();
		Assert.assertTrue(registerObject.isErrorMessageDisplayed(RegisterPageUI.PASSWORD_ERROR_MESSAGE, "Password must meet the following rules:\nmust have at least 6 characters"));	
	}
	
	@Test
	public void TC_05_registerWithConfirmPasswordDoNotMatch() {
		registerObject.inputPasword("123456");
		registerObject.inputConfirmPassword("1");
		registerObject.pressControlTab();
		Assert.assertTrue(registerObject.isErrorMessageDisplayed(RegisterPageUI.CONFIRMPASSWORD_ERROR_MESSAGE, "The password and confirmation password do not match."));	
	}
	
	@Test
	public void TC_06_registerWithValidInformations() {
		registerObject.inputConfirmPassword("123456");
		registerObject.clickRegisterButton();
		Assert.assertTrue(registerObject.isErrorMessageDisplayed(HomePageUI.REGISTER_RESULT, "Your registration completed"));	
		
		homeObject = PageFactoryManager.getHomePageObject(driver);
		homeObject.clickContinueButton();
	}
	
	@AfterClass
	public void afterClass() {
	}

}
