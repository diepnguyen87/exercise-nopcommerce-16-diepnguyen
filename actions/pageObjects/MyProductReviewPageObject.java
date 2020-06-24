package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.MyProductReviewPageUI;

public class MyProductReviewPageObject extends AbstractPage{

	private WebDriver driver;
	
	public MyProductReviewPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getMyLastestReviewTitle() {
		waitForElementVisible(driver, MyProductReviewPageUI.MY_LATEST_REVIEW_TITLE);
		return getElementText(driver, MyProductReviewPageUI.MY_LATEST_REVIEW_TITLE);
	}

	public String getMyLastestReviewText() {
		waitForElementVisible(driver, MyProductReviewPageUI.MY_LATEST_REVIEW_TEXT);
		return getElementText(driver, MyProductReviewPageUI.MY_LATEST_REVIEW_TEXT);
	}

	public String getMyLastestReviewProductName() {
		waitForElementVisible(driver, MyProductReviewPageUI.MY_LATEST_REVIEW_PRODUCT_NAME);
		return getElementText(driver, MyProductReviewPageUI.MY_LATEST_REVIEW_PRODUCT_NAME);
	}

	public String[] getMyLastestReviewDateTime() {
		waitForElementVisible(driver, MyProductReviewPageUI.MY_LATEST_REVIEW_DATE_TIME);
		return getElementText(driver, MyProductReviewPageUI.MY_LATEST_REVIEW_DATE_TIME).split("\\s");
	}

	public boolean isCurrentTimeGreaterThanLastestReviewTime(String lastestReviewTime, String currentReviewTime) {
		String[] lastestTime = lastestReviewTime.split(":");
		String[] currentTime = currentReviewTime.split(":");
		int latestHour = Integer.parseInt(lastestTime[0]);
		int lastestMins = Integer.parseInt(lastestTime[1]);
		
		int currentHour = Integer.parseInt(currentTime[0]);
		int currentMins = Integer.parseInt(currentTime[1]);
		
		if(currentMins >= lastestMins && currentMins <= lastestMins+5 ) {
			return true;
		}else {
			if(currentHour > latestHour+1) {
				return true;
			}
		}
		return false;
	}
}
