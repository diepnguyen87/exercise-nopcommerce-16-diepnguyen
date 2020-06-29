package pageUI;

public class WishlistPageUI {

	public static final String LATEST_WISHTLIST_ITEM = "(//table[@class='cart']//a[@class='product-name'])[position()>last()-%s]";
	public static final String SHARE_LINK = "//a[@class='share-link']";
	public static final String SHARE_LABEL = "//div[@class='page-title']/h1";
	public static final String ADD_TO_CART_CHECKBOX = "//a[contains(@href, '%s')]/preceding::td[@class='add-to-cart']/input[@name='addtocart']";
	public static final String WISHLIST_QUANTITY = "//span[@class='wishlist-qty']";
	public static final String CART_QUANTITY = "//span[@class='cart-qty']";
}
