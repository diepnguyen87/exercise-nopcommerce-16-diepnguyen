package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageFactoryManager {

	public static HomePageObject getHomePageObject(WebDriver driver) {
		return new HomePageObject(driver);
	}
	
	public static LoginPageObject getLoginPageObject(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static RegisterPageObject getRegisterPageObject(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	
	public static StartPageObject getStartPageObject(WebDriver driver) {
		return new StartPageObject(driver);
	}

	public static MyAccountPageObject getMyAccountPageObject(WebDriver driver) {
		return new MyAccountPageObject(driver);
	}
	
	public static CustomerInfosPageObject getCustomerInfosPageObject(WebDriver driver) {
		return new CustomerInfosPageObject(driver);
	}

	public static CustomerAddressesPageObject getCustomerAddressesPageObject(WebDriver driver) {
		return new CustomerAddressesPageObject(driver);
	}
	
	public static CustomerChangePasswordPageObject getCustomerChangePasswordPageObject(WebDriver driver) {
		return new CustomerChangePasswordPageObject(driver);
	}
	
	public static ProductPageObject getProductPageObject(WebDriver driver) {
		return new ProductPageObject(driver);
	}
	
	public static ProductReviewPageObject getProductReviewPageObject(WebDriver driver) {
		return new ProductReviewPageObject(driver);
	}
	
	public static MyProductReviewPageObject getMyProductReviewPageObject(WebDriver driver) {
		return new MyProductReviewPageObject(driver);
	}
	
	public static SearchPageObject getSearchPageObject(WebDriver driver) {
		return new SearchPageObject(driver);
	}

	public static SubCategoriesPageObject getSubCategoriesPageObject(WebDriver driver) {
		return new SubCategoriesPageObject(driver);
	}

	public static WishListPageObject getWishListPageObject(WebDriver driver) {
		return new WishListPageObject(driver);
	}

	
}
