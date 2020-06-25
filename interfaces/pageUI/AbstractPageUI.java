package pageUI;

public class AbstractPageUI {

	public static final String DYNAMIC_HEADER_LINK = "//a[@class='ico-%s']";
	public static final String DYNAMIC_FOOTER_LINK = "//a[text()='%s']";
	public static final String DYNAMIC_HEADER_MENU = "//ul[@class='top-menu notmobile']//a[contains(text(),'%s')]";
	public static final String DYNAMIC_HEADER_SUB_MENU = "//ul[@class='top-menu notmobile']//a[contains(text(), '%s')]/following-sibling::ul[@class='sublist first-level']//a[contains(text(),'%s')]";
}
