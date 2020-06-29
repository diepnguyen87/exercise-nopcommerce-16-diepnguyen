package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.ProductPageUI;

public class ProductPageObject extends AbstractPage{

	private WebDriver driver;
	
	public ProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public ProductReviewPageObject clickAddYourReviewLink() {
		waitForElementClickable(driver, ProductPageUI.ADD_REVIEW_LINK);
		clickToElement(driver, ProductPageUI.ADD_REVIEW_LINK);
		return PageFactoryManager.getProductReviewPageObject(driver);
	}

	public String getProductName() {
		waitForElementVisible(driver, ProductPageUI.PRODUCT_NAME);
		return getElementText(driver, ProductPageUI.PRODUCT_NAME);
	}
	
	public ProductReviewPageObject addReview(String reviewTitle, String reviewText, String rating) {
		// add your review
		ProductReviewPageObject productReviewObject = clickAddYourReviewLink();

		// add review
		productReviewObject.inputReviewTitle(reviewTitle);
		productReviewObject.inputReviewText(reviewText);
		productReviewObject.selectRating(rating);
		productReviewObject.clickSubmitReview();
		return productReviewObject;
	}

	public void addWishlist() {
		waitForElementVisible(driver, ProductPageUI.ADD_TO_WISHLIST);
		clickToElement(driver, ProductPageUI.ADD_TO_WISHLIST);
	}

	public String getActualNotification() {
		waitForElementVisible(driver, ProductPageUI.BAR_NOTIFICATION);
		return getElementText(driver, ProductPageUI.BAR_NOTIFICATION);
	}
}
