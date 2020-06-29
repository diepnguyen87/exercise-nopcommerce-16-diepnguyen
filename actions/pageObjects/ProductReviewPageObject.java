package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.ProductPageUI;
import pageUI.ProductReviewPageUI;

public class ProductReviewPageObject extends AbstractPage{

	private WebDriver driver;
	
	public ProductReviewPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputReviewTitle(String title) {
		waitForElementVisible(driver, ProductReviewPageUI.REVIEW_TITLE);
		sendKeyToElement(driver, ProductReviewPageUI.REVIEW_TITLE, title);
	}

	public void inputReviewText(String reviewText) {
		waitForElementVisible(driver, ProductReviewPageUI.REVIEW_TEXT);
		sendKeyToElement(driver, ProductReviewPageUI.REVIEW_TEXT, reviewText);
	}

	public void selectRating(String rating) {
		waitForElementVisible(driver, ProductReviewPageUI.DYNAMIC_RATING_OPTIONS, rating);
		clickToElement(driver, ProductReviewPageUI.DYNAMIC_RATING_OPTIONS, rating);
	}

	public void clickSubmitReview() {
		waitForElementClickable(driver, ProductReviewPageUI.SUBMIT_REVIEW);
		clickToElement(driver, ProductReviewPageUI.SUBMIT_REVIEW);	
	}
	
}
