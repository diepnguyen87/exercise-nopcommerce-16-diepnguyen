package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.AbstractPage;
import pageUI.WishlistPageUI;

public class WishListPageObject extends AbstractPage {

	private WebDriver driver;
	private List<WebElement> elementList;
	private List<String> resultList;
	
	public WishListPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public List<String> getHrefValuesOfAddedItems(int numOfAddedItems) {
		waitForElementVisible(driver, WishlistPageUI.LATEST_WISHTLIST_ITEM, String.valueOf(numOfAddedItems));
		resultList = new ArrayList<String>();
		elementList = findElements(driver, WishlistPageUI.LATEST_WISHTLIST_ITEM, String.valueOf(numOfAddedItems));
		for(WebElement element : elementList) {
			resultList.add(element.getAttribute("href"));
		}
		return resultList;
	}

	public void clickToShareLink() {
		waitForElementClickable(driver, WishlistPageUI.SHARE_LINK);
		clickToElement(driver, WishlistPageUI.SHARE_LINK);
	}

	public String getShareLabel() {
		waitForElementVisible(driver, WishlistPageUI.SHARE_LABEL);
		return getElementText(driver, WishlistPageUI.SHARE_LABEL);
	}

	public void selectProducts(String... productNames) {
		for(String product : productNames) {
			clickToElement(driver, WishlistPageUI.ADD_TO_CART_CHECKBOX, product);
		}
	}
}
