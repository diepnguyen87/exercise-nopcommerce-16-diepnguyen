package com.nopcommerce.myaccount;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
import pageObjects.CustomerAddressesPageObject;
import pageObjects.CustomerChangePasswordPageObject;
import pageObjects.CustomerInfosPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MyProductReviewPageObject;
import pageObjects.PageFactoryManager;
import pageObjects.ProductPageObject;
import pageObjects.ProductReviewPageObject;
import pageObjects.RegisterPageObject;
import pageObjects.StartPageObject;

public class MyAccount_01 extends AbstractTest {

	private WebDriver driver;
	private DriverManager driverManager;
	private StartPageObject startObject;
	private LoginPageObject loginObject;
	private RegisterPageObject registerObject;
	private HomePageObject homeObject;
	private CustomerInfosPageObject customerInfoObject;
	private CustomerAddressesPageObject customerAddressObject;
	private CustomerChangePasswordPageObject changePasswordObject;
	private ProductPageObject productObject;
	private ProductReviewPageObject productReviewObject;
	private MyProductReviewPageObject myProductReviewObject;
	private String email;
	private String newEmail;
	private String password = GlobalConstants.PASSWORD;
	private String newPassword = "654321";
//	private String newGender = "female";
	private String newGender = "F";
	private String newFirstName = "Automation";
	private String newLastName = "FC";
	private String[] newOB = { "January", "1", "1999" };
	private String newCompanyName = "Automation FC";
	private String actualValue;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driverManager = BrowserDriverFactory.getBrowserDriver(browserName);
		driver = driverManager.getDriver();
		startObject = PageFactoryManager.getStartPageObject(driver);
		startObject.navigateToPage_HeaderLink(driver, "login");
		loginObject = PageFactoryManager.getLoginPageObject(driver);
		loginObject.inputToDynamicTextbox(driver, "Email", GlobalConstants.EMAIL);
		loginObject.inputToDynamicTextbox(driver, "Password", GlobalConstants.PASSWORD);
		loginObject.clickToDynamicButton(driver, "Log in");
		verifyTrue(loginObject.isMyAccountDisplayed());

		homeObject = PageFactoryManager.getHomePageObject(driver);
	}

	@Test
	public void TC_01_updateCustomerInfos() {
		// navigate to My Account
		homeObject.navigateToPage_HeaderLink(driver, "account");
		customerInfoObject = PageFactoryManager.getCustomerInfosPageObject(driver);

		// Update infos
		customerInfoObject.clickToDynamicRadioButton(driver, newGender);
		customerInfoObject.inputToDynamicTextbox(driver, "FirstName", newFirstName);
		customerInfoObject.inputToDynamicTextbox(driver, "LastName", newLastName);
		customerInfoObject.updateDateOfBirth(newOB[0], newOB[1], newOB[2]);
		newEmail = createRandomEmail("automationfc", "vn");
		customerInfoObject.inputToDynamicTextbox(driver, "Email", newEmail);
		customerInfoObject.inputToDynamicTextbox(driver, "Company", newCompanyName);

		// save new infos
		customerInfoObject.clickToDynamicButton(driver, "Save");
		GlobalConstants.EMAIL = newEmail;

		// Verify infos after update
		Assert.assertTrue(customerInfoObject.isRadioButtonSelected(driver, newGender));

		actualValue = customerInfoObject.getTextboxValue(driver, "FirstName");
		Assert.assertEquals(actualValue, newFirstName);

		actualValue = customerInfoObject.getTextboxValue(driver, "LastName");
		verifyEquals(actualValue, newLastName);

		actualValue = customerInfoObject.getDropdownTextSelected(driver, "Day");
		verifyEquals(actualValue, newOB[1]);

		actualValue = customerInfoObject.getDropdownTextSelected(driver, "Month");
		verifyEquals(actualValue, newOB[0]);

		actualValue = customerInfoObject.getDropdownTextSelected(driver, "Year");
		verifyEquals(actualValue, newOB[2]);

		actualValue = customerInfoObject.getTextboxValue(driver, "Email");
		verifyEquals(actualValue, newEmail);

		actualValue = customerInfoObject.getTextboxValue(driver, "Company");
		verifyEquals(actualValue, newCompanyName);
	}

	@Test
	public void TC_02_addAddress() {
		// data
		String country = "Viet Nam";
		String state = "Other";
		String city = "Da Nang";
		String address1 = "123/01 Le Lai";
		String address2 = "234/05 Hai Phong";
		String zipcode = "550000";
		String phoneNumber = "0123456789";
		String faxNumber = "0987654321";

		// navigate to Customer Addresses
		homeObject.navigateToPage_ContentList(driver, "/customer/addresses");
		customerAddressObject = PageFactoryManager.getCustomerAddressesPageObject(driver);

		// click add new
		customerAddressObject.clickToDynamicButton(driver, "Add new");

		// enter new address
		customerAddressObject.inputToDynamicTextbox(driver, "Address.FirstName", newFirstName);
		customerAddressObject.inputToDynamicTextbox(driver, "Address.LastName", newLastName);
		customerAddressObject.inputToDynamicTextbox(driver, "Address.Email", newEmail);
		customerAddressObject.inputToDynamicTextbox(driver, "Address.Company", newCompanyName);
		customerAddressObject.selectNewInfos("CountryId", country);
		customerAddressObject.selectNewInfos("StateProvinceId", state);
		customerAddressObject.inputToDynamicTextbox(driver, "Address.City", city);
		customerAddressObject.inputToDynamicTextbox(driver, "Address.Address1", address1);
		customerAddressObject.inputToDynamicTextbox(driver, "Address.Address2", address2);
		customerAddressObject.inputToDynamicTextbox(driver, "Address.ZipPostalCode", zipcode);
		customerAddressObject.inputToDynamicTextbox(driver, "Address.PhoneNumber", phoneNumber);
		customerAddressObject.inputToDynamicTextbox(driver, "Address.FaxNumber", faxNumber);
		
		// click save
		customerAddressObject.clickToDynamicButton(driver, "Save");

		// Verify address
		actualValue = customerAddressObject.getCustomerInfo("Automation FC", "name");
		verifyTrue(actualValue.contains(newFirstName + " " + newLastName));

		actualValue = customerAddressObject.getCustomerInfo("Automation FC", "email");
		verifyTrue(actualValue.contains(newEmail));

		actualValue = customerAddressObject.getCustomerInfo("Automation FC", "phone");
		verifyTrue(actualValue.contains(phoneNumber));

		actualValue = customerAddressObject.getCustomerInfo("Automation FC", "fax");
		verifyTrue(actualValue.contains(faxNumber));

		actualValue = customerAddressObject.getCustomerInfo("Automation FC", "company");
		verifyTrue(actualValue.contains(newCompanyName));

		actualValue = customerAddressObject.getCustomerInfo("Automation FC", "address1");
		verifyTrue(actualValue.contains(address1));

		actualValue = customerAddressObject.getCustomerInfo("Automation FC", "address2");
		verifyTrue(actualValue.contains(address2));

		actualValue = customerAddressObject.getCustomerInfo("Automation FC", "city-state-zip");
		verifyTrue(actualValue.contains(city + ", " + zipcode));

		actualValue = customerAddressObject.getCustomerInfo("Automation FC", "country");
		verifyTrue(actualValue.contains(country));
	}

	// @Test
	public void TC_03_changePassword() {
		// navigate to change password
		customerAddressObject.navigateToPage_ContentList(driver, "/customer/changepassword");
		changePasswordObject = PageFactoryManager.getCustomerChangePasswordPageObject(driver);

		// update password
		changePasswordObject.inputPassword("OldPassword", GlobalConstants.PASSWORD);
		changePasswordObject.inputPassword("NewPassword", newPassword);
		GlobalConstants.PASSWORD = newPassword;
		changePasswordObject.inputPassword("ConfirmNewPassword", newPassword);

		changePasswordObject.clickChangePasswordButton();

		// logout
		startObject = changePasswordObject.logout(driver);

		// verify login failed with old password
		startObject.navigateToPage_HeaderLink(driver, "login");
		loginObject = PageFactoryManager.getLoginPageObject(driver);
		loginObject.login(email, password);
		verifyEquals(loginObject.getSummaryErrorMsg(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");

		// verify login pass with new password
		startObject.navigateToPage_HeaderLink(driver, "login");
		loginObject = PageFactoryManager.getLoginPageObject(driver);
		loginObject.login(newEmail, newPassword);
		verifyTrue(loginObject.isMyAccountDisplayed());
		homeObject = PageFactoryManager.getHomePageObject(driver);
	}

	// @Test
	public void TC_04_myProductReviews() {
		// data
		String url = "https://demo.nopcommerce.com/adidas-consortium-campus-80s-running-shoes";
		String reviewTitle = "Đáng đồng tiền";
		String reviewText = "Độ bám tốt, phù hợp cho các hoạt động thể thao, trekking... Các bạn nên mua thử";
		String productName = "";

		// open random product and add review 1
		homeObject.openURL(driver, url);
		productObject = PageFactoryManager.getProductPageObject(driver);
		productName = productObject.getProductName();
		productReviewObject = productObject.addReview(reviewTitle, reviewText, "3");

		// open the product again and add review 2
		productReviewObject.openURL(driver, url);
		productObject = PageFactoryManager.getProductPageObject(driver);
		productName = productObject.getProductName();
		productReviewObject = productObject.addReview(reviewTitle, reviewText, "1");

		// navigate to my product reviews
		productReviewObject.navigateToPage_HeaderLink(driver, "account");
		customerInfoObject = PageFactoryManager.getCustomerInfosPageObject(driver);
		customerInfoObject.navigateToPage_ContentList(driver, "/customer/productreviews");
		myProductReviewObject = PageFactoryManager.getMyProductReviewPageObject(driver);

		// Verify
		actualValue = myProductReviewObject.getMyLastestReviewTitle();
		verifyEquals(actualValue, reviewTitle);

		actualValue = myProductReviewObject.getMyLastestReviewText();
		verifyEquals(actualValue, reviewText);

		actualValue = myProductReviewObject.getMyLastestReviewProductName();
		verifyEquals(actualValue, productName);

		String[] latestReviewDateTime = myProductReviewObject.getMyLastestReviewDateTime();

		SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy h:mm a");
		sdf.setTimeZone(TimeZone.getTimeZone("US/Central"));
		String[] currentDateTime = sdf.format(new Date()).split("\\s");

		verifyEquals(latestReviewDateTime[0], currentDateTime[0]);
		verifyTrue(myProductReviewObject.isCurrentTimeGreaterThanLastestReviewTime(latestReviewDateTime[1], currentDateTime[1]));
		verifyEquals(latestReviewDateTime[2], currentDateTime[2]);
	}

	@AfterClass
	public void afterClass() {
	}

}
