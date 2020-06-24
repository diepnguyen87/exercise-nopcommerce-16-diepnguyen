package pageUI;

public class MyProductReviewPageUI {

	public static final String DYNAMIC_PRODUCT_REVIEW_ITEM = "//strong[text()='abcReview']//parent::div[@class='review-title']/following-sibling::div";
	public static final String MY_LATEST_REVIEW_TITLE = "(//div[@class='review-title'])[1]";
	public static final String MY_LATEST_REVIEW_TEXT = "(//div[@class='review-text'])[1]";
	public static final String MY_LATEST_REVIEW_PRODUCT_NAME = "(//div[@class='review-info']//a)[1]";
	public static final String MY_LATEST_REVIEW_DATE_TIME = "(//span[@class='date']/span)[1]";
	
}
