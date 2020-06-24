package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.AbstractPage;
import pageUI.SearchPageUI;

public class SearchPageObject extends AbstractPage{

	private WebDriver driver;
	private WebElement element;
	
	public SearchPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickSearchButton() {
		waitForElementClickable(driver, SearchPageUI.SEARCH_BUTTON);
		clickToElement(driver, SearchPageUI.SEARCH_BUTTON);
	}

	public void inputSearchKeyword(String searchKeyword) {
		waitForElementVisible(driver, SearchPageUI.SEARCH_KEYWORD_TEXTBOX);
		sendKeyToElement(driver, SearchPageUI.SEARCH_KEYWORD_TEXTBOX, searchKeyword);
	}

	public String getWarningErrorMessage() {
		waitForElementVisible(driver, SearchPageUI.DYNAMIC_ERROR_MESSAGE, "warning");
		return getElementText(driver, SearchPageUI.DYNAMIC_ERROR_MESSAGE, "warning");
	}

	public String getNoResultErrorMessage() {
		waitForElementVisible(driver, SearchPageUI.DYNAMIC_ERROR_MESSAGE, "no-result");
		return getElementText(driver, SearchPageUI.DYNAMIC_ERROR_MESSAGE, "no-result");
	}

	public List<WebElement> getProductItems() {
		waitForElementVisible(driver, SearchPageUI.PRODUCT_ITEM);
		return findElements(driver, SearchPageUI.PRODUCT_ITEM);
	}

	public void selectCategory(String category) {
		waitForElementVisible(driver, SearchPageUI.CATEGORY);
		selectItemInDropdown(driver, SearchPageUI.CATEGORY, category);
	}

	public void selectAdvancedSeach() {
		waitForElementVisible(driver, SearchPageUI.ADVANCED_SEARCH_CHECKBOX);
		element = findElement(driver, SearchPageUI.ADVANCED_SEARCH_CHECKBOX);
		if(!element.isSelected()) {
			element.click();
		}
	}

	public void selectSearchSubCategories() {
		waitForElementVisible(driver, SearchPageUI.SEARCH_SUB_CATEGORIES);
		element = findElement(driver, SearchPageUI.SEARCH_SUB_CATEGORIES);
		if(!element.isSelected()) {
			element.click();
		}
		
	}

	public void selectManufacturer(String manufacturer) {
		waitForElementVisible(driver, SearchPageUI.MANUFACTURER);
		selectItemInDropdown(driver, SearchPageUI.MANUFACTURER, manufacturer);
	}

	public void inputPrice(int priceFrome, int priceTo) {
		sendKeyToElement(driver, SearchPageUI.PRICE_FROM, String.valueOf(priceFrome));
		sendKeyToElement(driver, SearchPageUI.PRICE_TO, String.valueOf(priceTo));
	}
	
}
