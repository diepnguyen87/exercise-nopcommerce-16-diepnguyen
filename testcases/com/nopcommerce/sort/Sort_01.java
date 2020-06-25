package com.nopcommerce.sort;

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
import pageObjects.SubCategoriesPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Sort_01 extends AbstractTest {

	private WebDriver driver;
	private DriverManager driverManager;
	private StartPageObject startObject;
	private SubCategoriesPageObject subCategoriesObject;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driverManager = BrowserDriverFactory.getBrowserDriver(browserName);
		driver = driverManager.getDriver();
		startObject = PageFactoryManager.getStartPageObject(driver);
		startObject.selectSubMenu(driver, "Notebooks", "Computers");
		subCategoriesObject = PageFactoryManager.getSubCategoriesPageObject(driver);
	}

	@Test
	public void TC_01_sortAscendingByName() {
		// data
		String sortBy = "Name: A to Z";

		// action
		subCategoriesObject.selectSortBy(sortBy);

		// verify
		verifyTrue(subCategoriesObject.isSortByAttributeName(driver, "name", "asc"));
	}

	@Test
	public void TC_02_sortDescendingByName() {
		// data
		String sortBy = "Name: Z to A";

		// action
		subCategoriesObject.selectSortBy(sortBy);

		// verify
		verifyTrue(subCategoriesObject.isSortByAttributeName(driver, "name", "desc"));
	}

	@Test
	public void TC_03_sortLowToHighByPrice() {
		// data
		String sortBy = "Price: Low to High";

		// action
		subCategoriesObject.selectSortBy(sortBy);

		// verify
		verifyTrue(subCategoriesObject.isSortByAttributeName(driver, "price", "asc"));
	}

	@Test
	public void TC_04_sortHighToLowByPrice() {
		// data
		String sortBy = "Price: High to Low";

		// action
		subCategoriesObject.selectSortBy(sortBy);

		// verify
		verifyTrue(subCategoriesObject.isSortByAttributeName(driver, "price", "desc"));
	}

	@Test
	public void TC_05_display3ProductsPerPage() {
		// data
		int numOfProductsPerPage = 3;

		// action
		subCategoriesObject.selectDisplayPerPage(numOfProductsPerPage);

		// verify paging icons on page 1
		verifyTrue(subCategoriesObject.isNumOfProductsPerPageCorrected(numOfProductsPerPage));
		verifyTrue(subCategoriesObject.isPagingDisplayed());
		verifyTrue(subCategoriesObject.isNextPageDisplayed());
		verifyFalse(subCategoriesObject.isPreviousPageDisplayed());
				
		//move to page 2
		subCategoriesObject.moveToPage(2);
		verifyTrue(subCategoriesObject.isPreviousPageDisplayed());
		verifyFalse(subCategoriesObject.isNextPageDisplayed());	
	}

	@Test
	public void TC_06_display6ProductsPerPage() {
		// data
		int numOfProductsPerPage = 6;

		// action
		subCategoriesObject.selectDisplayPerPage(numOfProductsPerPage);

		// verify
		verifyTrue(subCategoriesObject.isNumOfProductsPerPageCorrected(numOfProductsPerPage));
		verifyFalse(subCategoriesObject.isPagingDisplayed());
	}

	@Test
	public void TC_07_display9ProductsPerPage() {
		// data
		int numOfProductsPerPage = 9;

		// action
		subCategoriesObject.selectDisplayPerPage(numOfProductsPerPage);

		// verify
		verifyTrue(subCategoriesObject.isNumOfProductsPerPageCorrected(numOfProductsPerPage));
		verifyFalse(subCategoriesObject.isPagingDisplayed());
	}
	
	@AfterClass
	public void afterClass() {
	}

}
