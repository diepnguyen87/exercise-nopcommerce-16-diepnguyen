package pageUI;

public class SubCategoriesPageUI {

	public static final String SORT_BY = "//select[@id='products-orderby']";
	public static final String ALL_PRODUCT_TITLE_FILTERED = "//h2[@class='product-title']/a";
	public static final String ALL_PRICE_FILTERED = "//span[contains(@class, 'actual-price')]";
	public static final String DISPLAY_PER_PAGE = "//select[@id='products-pagesize']";
	public static final String PAGING = "//div[@class='pager']";
	public static final String PAGING_NEXT_PAGE = "//li[@class='next-page']";
	public static final String PAGING_PREVIOUS_PAGE = "//li[@class='previous-page']";
	public static final String PAGING_CURRENT_PAGE = "//li[@class='current-page']";
	public static final String PAGING_INDIVIDUAL_PAGE = "//li[@class='individual-page']/a[text()='%s']";
	
}
