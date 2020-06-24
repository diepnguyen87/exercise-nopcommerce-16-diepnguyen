package com.nopcommerce.search;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browsers.BrowserDriverFactory;
import browsers.DriverManager;
import commons.AbstractTest;
import commons.GlobalConstants;
import pageObjects.HomePageObject;
import pageObjects.PageFactoryManager;
import pageObjects.RegisterPageObject;
import pageObjects.SearchPageObject;
import pageObjects.StartPageObject;

public class Search_01 extends AbstractTest {

	private WebDriver driver;
	private DriverManager driverManager;
	private StartPageObject startObject;
	private HomePageObject homeObject;
	private RegisterPageObject registerObject;
	private SearchPageObject searchObject;

	private String email, actualErrorMsg, keyword, category, manufacturer;
	private int priceFrome, priceTo;
	private List<WebElement> elements;
	private String noProductsErrorMessage = "No products were found that matched your criteria.";

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driverManager = BrowserDriverFactory.getBrowserDriver(browserName);
		driver = driverManager.getDriver();
		startObject = PageFactoryManager.getStartPageObject(driver);
		startObject.navigateToPage_HeaderLink(driver, "register");
		registerObject = PageFactoryManager.getRegisterPageObject(driver);

		// register + login success
		email = createRandomEmail(GlobalConstants.FIRSTNAME, GlobalConstants.LASTNAME);
		registerObject.register(GlobalConstants.FIRSTNAME, GlobalConstants.LASTNAME, email, GlobalConstants.PASSWORD);
		GlobalConstants.EMAIL = email;
		homeObject = PageFactoryManager.getHomePageObject(driver);
	}

	@Test
	public void TC_01_searchWithEmptyData() {
		// Navigate to search page
		homeObject.navigateToPage_FooterLink(driver, "Search");
		searchObject = PageFactoryManager.getSearchPageObject(driver);

		//
		searchObject.clickSearchButton();
		actualErrorMsg = searchObject.getWarningErrorMessage();
		verifyEquals(actualErrorMsg, "Search term minimum length is 3 characters");
	}

	@Test
	public void TC_02_searchWithDataNotExist() {
		keyword = "Macbook Pro 2050";
		searchObject.inputSearchKeyword(keyword);
		searchObject.clickSearchButton();
		actualErrorMsg = searchObject.getNoResultErrorMessage();
		verifyEquals(actualErrorMsg, noProductsErrorMessage);
	}

	@Test
	public void TC_03_searchWithRelativeProductName() {
		keyword = "Lenovo";
		searchObject.inputSearchKeyword(keyword);
		searchObject.clickSearchButton();
		elements = searchObject.getProductItems();

		// verify
		verifyEquals(elements.size(), 2);
		verifyEquals(elements.get(0).getText(), "Lenovo IdeaCentre 600 All-in-One PC");
		verifyEquals(elements.get(1).getText(), "Lenovo Thinkpad X1 Carbon Laptop");
	}

	@Test
	public void TC_04_searchWithAbsoluteProductName() {
		keyword = "Thinkpad X1 Carbon";
		searchObject.inputSearchKeyword(keyword);
		searchObject.clickSearchButton();
		elements = searchObject.getProductItems();

		// verify
		verifyEquals(elements.size(), 1);
		verifyEquals(elements.get(0).getText(), "Lenovo Thinkpad X1 Carbon Laptop");
	}

	@Test
	public void TC_05_advancedSearchWithParentCategories() {
		keyword = "Apple Macbook Pro";
		category = "Computers";
		searchObject.inputSearchKeyword(keyword);
		searchObject.selectAdvancedSeach();
		searchObject.selectCategory(category);
		searchObject.clickSearchButton();

		// Verify
		actualErrorMsg = searchObject.getNoResultErrorMessage();
		verifyEquals(actualErrorMsg, noProductsErrorMessage);
	}

	@Test
	public void TC_06_advancedSearchWithSubCategories() {
		keyword = "Apple Macbook Pro";
		category = "Computers";
		searchObject.inputSearchKeyword(keyword);
		searchObject.selectAdvancedSeach();
		searchObject.selectCategory(category);
		searchObject.selectSearchSubCategories();
		searchObject.clickSearchButton();
		elements = searchObject.getProductItems();

		// Verify
		verifyEquals(elements.size(), 1);
		verifyEquals(elements.get(0).getText(), "Apple MacBook Pro 13-inch");
	}

	@Test
	public void TC_07_advancedSearchWithIncorrectManufacturer() {
		// data
		keyword = "Apple Macbook Pro";
		category = "Computers";
		manufacturer = "HP";

		searchObject.inputSearchKeyword(keyword);
		searchObject.selectAdvancedSeach();
		searchObject.selectCategory(category);
		searchObject.selectSearchSubCategories();
		searchObject.selectManufacturer(manufacturer);
		searchObject.clickSearchButton();

		// verify
		actualErrorMsg = searchObject.getNoResultErrorMessage();
		verifyEquals(actualErrorMsg, noProductsErrorMessage);
	}

	@Test
	public void TC_08_advancedSearchWithCorrectManufacturer() {
		// data
		keyword = "Apple Macbook Pro";
		category = "Computers";
		manufacturer = "Apple";

		searchObject.inputSearchKeyword(keyword);
		searchObject.selectAdvancedSeach();
		searchObject.selectCategory(category);
		searchObject.selectSearchSubCategories();
		searchObject.selectManufacturer(manufacturer);
		searchObject.clickSearchButton();

		elements = searchObject.getProductItems();

		// Verify
		verifyEquals(elements.size(), 1);
		verifyEquals(elements.get(0).getText(), "Apple MacBook Pro 13-inch");
	}

	@Test
	public void TC_09_advancedSearchInPriceRange() {
		// data
		keyword = "Apple Macbook Pro";
		category = "Computers";
		manufacturer = "Apple";
		priceFrome = 1000;
		priceTo = 2000;

		searchObject.inputSearchKeyword(keyword);
		searchObject.selectAdvancedSeach();
		searchObject.selectCategory(category);
		searchObject.selectSearchSubCategories();
		searchObject.selectManufacturer(manufacturer);
		searchObject.inputPrice(priceFrome, priceTo);
		searchObject.clickSearchButton();

		elements = searchObject.getProductItems();

		// Verify
		verifyEquals(elements.size(), 1);
		verifyEquals(elements.get(0).getText(), "Apple MacBook Pro 13-inch");
	}

	@Test
	public void TC_10_advanedSearchWithPriceRangeLowerThanProductPrice() {
		// data
		keyword = "Apple Macbook Pro";
		category = "Computers";
		manufacturer = "Apple";
		priceFrome = 1000;
		priceTo = 1700;

		searchObject.inputSearchKeyword(keyword);
		searchObject.selectAdvancedSeach();
		searchObject.selectCategory(category);
		searchObject.selectSearchSubCategories();
		searchObject.selectManufacturer(manufacturer);
		searchObject.inputPrice(priceFrome, priceTo);
		searchObject.clickSearchButton();

		// Verify
		actualErrorMsg = searchObject.getNoResultErrorMessage();
		verifyEquals(actualErrorMsg, noProductsErrorMessage);
	}

	@Test
	public void TC_11_advancedSearchWithPriceRangeGreaterThanProductPrice() {
		// data
		keyword = "Apple Macbook Pro";
		category = "Computers";
		manufacturer = "Apple";
		priceFrome = 1900;
		priceTo = 5000;

		searchObject.inputSearchKeyword(keyword);
		searchObject.selectAdvancedSeach();
		searchObject.selectCategory(category);
		searchObject.selectSearchSubCategories();
		searchObject.selectManufacturer(manufacturer);
		searchObject.inputPrice(priceFrome, priceTo);
		searchObject.clickSearchButton();

		// Verify
		actualErrorMsg = searchObject.getNoResultErrorMessage();
		verifyEquals(actualErrorMsg, noProductsErrorMessage);
	}

	@AfterClass
	public void afterClass() {
	}

}
