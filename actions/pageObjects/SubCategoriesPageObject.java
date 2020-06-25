package pageObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.AbstractPage;
import pageUI.SubCategoriesPageUI;

public class SubCategoriesPageObject extends AbstractPage {

	private WebDriver driver;
	private boolean status;
	private List<WebElement> elementList;
	
	public SubCategoriesPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectSortBy(String value) {
		waitForElementVisible(driver, SubCategoriesPageUI.SORT_BY);
		selectItemInDropdown(driver, SubCategoriesPageUI.SORT_BY, value);
	}

	public void selectDisplayPerPage(int numOfProductsPerPage) {
		waitForElementVisible(driver, SubCategoriesPageUI.DISPLAY_PER_PAGE);
		selectItemInDropdown(driver, SubCategoriesPageUI.DISPLAY_PER_PAGE, String.valueOf(numOfProductsPerPage));
	}

	public boolean isNumOfProductsPerPageCorrected(int numOfProductsPerPage) {
		List<WebElement> elementList = findElements(driver, SubCategoriesPageUI.ALL_PRODUCT_TITLE_FILTERED);
		if(elementList.size() <= numOfProductsPerPage) {
			return true;
		}
		return false;
	}

	public boolean isPagingDisplayed() {
		try {
//			waitForElementVisible(driver, SubCategoriesPageUI.PAGING);
			return isElementDisplayed(driver, SubCategoriesPageUI.PAGING);
		}catch (Exception e) {
			return false;
		}
	}
	
//	public boolean isPagingNotDisplayed() {
//		waitForElementVisible(driver, SubCategoriesPageUI.PAGING);
//		status = isElementDisplayed(driver, SubCategoriesPageUI.PAGING);
//		if(status == false) {
//			return true;
//		}
//		return false;
//	}

	public boolean isNextPageDisplayed() {
		try {
//			waitForElementVisible(driver, SubCategoriesPageUI.PAGING_NEXT_PAGE);
			return isElementDisplayed(driver, SubCategoriesPageUI.PAGING_NEXT_PAGE);
		}catch (Exception e) {
			return false;
		}
	}

//	public boolean isPreviousPageNotDisplayed() {
//		waitForElementVisible(driver, SubCategoriesPageUI.PAGING_PREVIOUS_PAGE);
//		status = isElementDisplayed(driver, SubCategoriesPageUI.PAGING_PREVIOUS_PAGE);	
//		if (status == false) {
//			return true;
//		}
//		return false;
//	}

	public void moveToPage(int pageNumber) {
		clickToElement(driver, SubCategoriesPageUI.PAGING_INDIVIDUAL_PAGE, String.valueOf(pageNumber));
	}

	public boolean isPreviousPageDisplayed() {
		try {
//			waitForElementVisible(driver, SubCategoriesPageUI.PAGING_PREVIOUS_PAGE);
			return isElementDisplayed(driver, SubCategoriesPageUI.PAGING_PREVIOUS_PAGE);			
		}catch(Exception e) {
			return false;
		}
	}

	public boolean isSortByAttributeName(WebDriver driver, String sortedAttributeName, String sortType) {
		List<String> originalElements, sortedElements;

		if (sortedAttributeName.contentEquals("name")) {
			elementList = findElements(driver, SubCategoriesPageUI.ALL_PRODUCT_TITLE_FILTERED);
		} else if (sortedAttributeName.equals("price")) {
			elementList = findElements(driver, SubCategoriesPageUI.ALL_PRICE_FILTERED);
		}
		originalElements = new ArrayList<String>();
		
		for (WebElement element : elementList) {
			originalElements.add(element.getText());
		}
		sortedElements = new ArrayList<String>(originalElements);
		if (sortType.equals("desc")) {
			Collections.sort(sortedElements, Collections.reverseOrder());
		} else {
			Collections.sort(sortedElements);
		}
		return originalElements.equals(sortedElements);
	}


//	public boolean isNextPageNotDisplayed() {
//		waitForElementVisible(driver, SubCategoriesPageUI.PAGING_NEXT_PAGE);
//		status = isElementDisplayed(driver, SubCategoriesPageUI.PAGING_NEXT_PAGE);
//		if(status == false) {
//			return true;
//		}
//		return false;
//	}
	
}
