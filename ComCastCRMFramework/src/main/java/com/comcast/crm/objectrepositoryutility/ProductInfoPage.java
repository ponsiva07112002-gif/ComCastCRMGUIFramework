package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This class is for ProductInfoPage which contains webelements and getter methods 
 * @author Ponselvi
 */
public class ProductInfoPage {

	public ProductInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath="//span[@class='lvtHeaderText']")
	private WebElement headerInfo;
	
	public WebElement getHeaderInfo() {
		return headerInfo;
	}
	
}
