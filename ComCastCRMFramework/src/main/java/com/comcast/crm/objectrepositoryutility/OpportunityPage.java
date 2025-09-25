package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This class is for OpportunityPage which contains webelements and getter methods 
 * @author Ponselvi
 */
public class OpportunityPage {

	public OpportunityPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[@alt='Create Opportunity...']")
	private WebElement createOpportunityBtn;

	@FindBy(xpath = "//input[@value='Delete']")
	private WebElement deleteBtn;

	public WebElement getCreateOpportunityBtn() {
		return createOpportunityBtn;
	}

	public WebElement getDeleteBtn() {
		return deleteBtn;
	}

}
