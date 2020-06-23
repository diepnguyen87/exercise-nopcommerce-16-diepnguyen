package com.nopcommerce.myaccount;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browsers.BrowserDriverFactory;
import browsers.DriverManager;
import commons.AbstractTest;
import commons.GlobalConstants;
import pageObjects.HomePageObject;
import pageObjects.MyAccountPageObject;
import pageObjects.PageFactoryManager;
import pageObjects.RegisterPageObject;
import pageObjects.StartPageObject;

public class MyAccount_01 extends AbstractTest{

	private WebDriver driver;
	private DriverManager driverManager;
	private StartPageObject startObject;
	private RegisterPageObject registerObject;
	private HomePageObject homeObject;
	private MyAccountPageObject accountObject;
	private String email;
	private String updatedGender = "female";
	private String updatedFirstName="Automation";
	private String updatedLastName="FC";
	private String[] updateDOB = {"January", "1", "1999"};
	private String updatedCompanyName = "Automation FC";
	private String updatedEmail;
	private String actualErrorMsg;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driverManager = BrowserDriverFactory.getBrowserDriver(browserName);
		driver = driverManager.getDriver();
		startObject = PageFactoryManager.getStartPageObject(driver);
		registerObject = startObject.clickRegisterLink();

		// register + login success
		email = createRandomEmail(GlobalConstants.fistName, GlobalConstants.lastName);
		registerObject.inputFirstName(GlobalConstants.fistName);
		registerObject.inputLastName(GlobalConstants.lastName);
		registerObject.inputEmail(email);
		registerObject.inputPasword("123456");
		registerObject.inputConfirmPassword("123456");
		registerObject.clickRegisterButton();
		homeObject = PageFactoryManager.getHomePageObject(driver);

		// homepage
		accountObject = homeObject.clickMyAccountLink();
		accountObject.selectGender(updatedGender);
		accountObject.updateFirstName(updatedFirstName);
		accountObject.updateLastName(updatedLastName);
		accountObject.selectDateOfBirth(updateDOB[0], updateDOB[1], updateDOB[2]);
		updatedEmail = createRandomEmail("automationfc", "vn");
		accountObject.updateEmail(updatedEmail);
		accountObject.updateCompanyName(updatedCompanyName);
		
		
		accountObject = accountObject.clickSaveButton();
//		actualErrorMsg = accountObject.getValueOfField("female");
//		Assert.assertEquals(actualErrorMsg, updatedGender);
//		
		actualErrorMsg = accountObject.getValueOfField("first name");
		Assert.assertEquals(actualErrorMsg, updatedFirstName);
		
		actualErrorMsg = accountObject.getValueOfField("last name");
		Assert.assertEquals(actualErrorMsg, updatedLastName);
		
		actualErrorMsg = accountObject.getValueOfField("birth day");
		Assert.assertEquals(actualErrorMsg, updateDOB[1]);
		
		actualErrorMsg = accountObject.getValueOfField("birth month");
		Assert.assertEquals(actualErrorMsg, updateDOB[0]);
		
		actualErrorMsg = accountObject.getValueOfField("birth year");
		Assert.assertEquals(actualErrorMsg, updateDOB[2]);
		
		actualErrorMsg = accountObject.getValueOfField("email");
		Assert.assertEquals(actualErrorMsg, updatedEmail);
		
		actualErrorMsg = accountObject.getValueOfField("company");
		Assert.assertEquals(actualErrorMsg, updatedCompanyName);
		
	}

	@Test
	public void TC_01_updateCustomerInfos() {
		
	}

	@AfterClass
	public void afterClass() {
	}

}
