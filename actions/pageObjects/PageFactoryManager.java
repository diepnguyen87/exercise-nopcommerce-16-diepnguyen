package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageFactoryManager {

	public static HomePageObject getHomePageObject(WebDriver driver) {
		return new HomePageObject(driver);
	}
	
	public static StartPageObject getStartPageObject(WebDriver driver) {
		return new StartPageObject(driver);
	}

	public static MyAccountPageObject getMyAccountPageObject(WebDriver driver) {
		return new MyAccountPageObject(driver);
	}
		
}
