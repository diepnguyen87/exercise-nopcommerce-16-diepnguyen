package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.ProductOverviewPageUI;

public class ProductOverviewPageObject extends AbstractPage{

	private WebDriver driver;
	
	public ProductOverviewPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public ProductReviewPageObject clickAddYourReviewLink() {
		waitForElementClickable(driver, ProductOverviewPageUI.ADD_REVIEW_LINK);
		clickToElement(driver, ProductOverviewPageUI.ADD_REVIEW_LINK);
		return PageFactoryManager.getProductReviewPageObject(driver);
	}

	public String getProductName() {
		waitForElementVisible(driver, ProductOverviewPageUI.PRODUCT_NAME);
		return getElementText(driver, ProductOverviewPageUI.PRODUCT_NAME);
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
}
