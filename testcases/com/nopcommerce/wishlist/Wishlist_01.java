package com.nopcommerce.wishlist;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browsers.BrowserDriverFactory;
import browsers.DriverManager;
import commons.AbstractTest;
import commons.GlobalConstants;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.PageFactoryManager;
import pageObjects.ProductPageObject;
import pageObjects.StartPageObject;
import pageObjects.WishListPageObject;

public class Wishlist_01 extends AbstractTest {

	private WebDriver driver;
	private DriverManager driverManager;
	private StartPageObject startObject;
	private HomePageObject homeObject;
	private LoginPageObject loginObject;
	private ProductPageObject productObject;
	private WishListPageObject wishListOjbect;

	private String email, actualMessage;
	private List<String> productURLs = new ArrayList<String>();

	private List<String> elementList;

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
	public void TC_01_addToWishlist() {
		productURLs.add("https://demo.nopcommerce.com/vintage-style-engagement-ring");
		productURLs.add("https://demo.nopcommerce.com/oversized-women-t-shirt");
		productURLs.add("https://demo.nopcommerce.com/universal-7-8-inch-tablet-cover");
		
		for (String url : productURLs) {
			homeObject.openURL(driver, url);
			productObject = PageFactoryManager.getProductPageObject(driver);

			productObject.addWishlist();
			actualMessage = productObject.getActualNotification();
			verifyEquals(actualMessage, "The product has been added to your wishlist");
		}
		productObject.sleepInSecond(10);
		productObject.navigateToPage_HeaderLink(driver, "wishlist");
		wishListOjbect = PageFactoryManager.getWishListPageObject(driver);
		// verify
		elementList = wishListOjbect.getHrefValuesOfAddedItems(productURLs.size());
		for (int i = 0; i < elementList.size(); ++i) {
			verifyTrue(productURLs.get(i).contains(elementList.get(i)));
		}

		//wishListOjbect.clickToShareLink();
		//actualMessage = wishListOjbect.getShareLabel();
		//verifyEquals(actualMessage, "Wishlist of " + GlobalConstants.FIRSTNAME + " " + GlobalConstants.LASTNAME);
	}

	@Test
	public void TC_02_addProductToCart() {
		List<String> newProduct = new ArrayList<String>();
		newProduct.add("https://demo.nopcommerce.com/nikon-d5500-dslr");
		newProduct.add("https://demo.nopcommerce.com/fahrenheit-451-by-ray-bradbury");
		for (String url : newProduct) {
			homeObject.openURL(driver, url);
			productObject = PageFactoryManager.getProductPageObject(driver);

			productObject.addWishlist();
			actualMessage = productObject.getActualNotification();
			verifyEquals(actualMessage, "The product has been added to your wishlist");
		}
		productURLs.addAll(newProduct);
		productObject.sleepInSecond(10);
	
		productObject.navigateToPage_HeaderLink(driver, "wishlist");
		wishListOjbect = PageFactoryManager.getWishListPageObject(driver);
		verifyEquals(productURLs.size(), 5);
		
		wishListOjbect.selectProducts(elementList.get(0), elementList.get(2));
		wishListOjbect.clickToDynamicButton(driver, "Add to cart");
		verifyEquals(productURLs.size() - 2, 3);
	}

	@AfterClass
	public void afterClass() {
//		closeBrowserAndDriver(driver);
	}

}
