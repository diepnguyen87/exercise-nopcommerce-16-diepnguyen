package com.nopcommerce.commons;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.AbstractTest;
import commons.GlobalConstants;
import pageObjects.HomePageObject;
import pageObjects.PageFactoryManager;
import pageObjects.RegisterPageObject;
import pageObjects.StartPageObject;
import pageUI.HomePageUI;

public class Commons_Register extends AbstractTest {

	private WebDriver driver;
	private StartPageObject startObject;
	private RegisterPageObject registerObject;
	private String email;
	private String password = "123456";

	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browserName) {
//		log.info("Pre-condition - Open browser");
		driver = getBrowserDriver(browserName, GlobalConstants.NOPCOMMERCE_URL);

//		log.info("Pre-condition - STEP 01: open start page");
		startObject = PageFactoryManager.getStartPageObject(driver);

//		log.info("Pre-condition - STEP 02: click 'Register' link");
		startObject.navigateToPage_HeaderLink(driver, "register");

//		log.info("Pre-condition - STEP 03: open register page");
		registerObject = PageFactoryManager.getRegisterPageObject(driver);

		// data
//		log.info("Pre-condition - STEP 04: create a random email");
		email = GlobalConstants.FIRSTNAME + "." + GlobalConstants.LASTNAME + getRandomNumber() + "@gmail.com";

//		log.info("Pre-condition - STEP 05: input to first name textbox");
		registerObject.inputToDynamicTextbox(driver, "FirstName", GlobalConstants.FIRSTNAME);
		
//		log.info("Pre-condition - STEP 06: input to last name textbox");
		registerObject.inputToDynamicTextbox(driver, "LastName", GlobalConstants.LASTNAME);
		
//		log.info("Pre-condition - STEP 07: input to email textbox");
		registerObject.inputToDynamicTextbox(driver, "Email", email);

//		log.info("Pre-condition - STEP 08: input to password textbox");
		registerObject.inputToDynamicTextbox(driver, "Password", password);
		
//		log.info("Pre-condition - STEP 09: input to confirm password textbox");
		registerObject.inputToDynamicTextbox(driver, "ConfirmPassword", password);

//		log.info("Pre-condition - STEP 10: click to 'Register' button");
		registerObject.clickToDynamicButton(driver, "Register");

		// verify
//		log.info("Pre-condition - STEP 11: verify registration is successful");
		verifyTrue(registerObject.isErrorMessageDisplayed(HomePageUI.REGISTER_RESULT, "Your registration completed"));

		// save data into global constant
//		log.info("Pre-condition - STEP 12: save email into global contants variable");
		GlobalConstants.EMAIL = email;

//		log.info("Pre-condition - STEP 13: input password into global contants variable");
		GlobalConstants.PASSWORD = password;
//		homeObject = PageFactoryManager.getHomePageObject(driver);
//		homeObject.clickContinueButton();
	}

	@AfterTest(alwaysRun = true)
	public void afterTest() {
//		closeBrowserAndDriver(driver);
	}

}
