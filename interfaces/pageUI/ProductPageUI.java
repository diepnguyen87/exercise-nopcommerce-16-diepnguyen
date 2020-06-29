package pageUI;

public class ProductPageUI {

//	public static final String ADD_REVIEW_LINK = "//a[contains(text(),'Add your review')]";
	public static final String ADD_REVIEW_LINK = "(//div[@class='product-reviews-overview']/div/a)[last()]";
	public static final String PRODUCT_NAME = "//div[@class='product-name']/h1";
	public static final String ADD_TO_WISHLIST = "//input[contains(@id, 'add-to-wishlist')]";
	public static final String BAR_NOTIFICATION = "//p[@class='content']";
}
