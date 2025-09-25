package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This class is for OpportunitiesInfoPage which contains webelements and getter methods 
 * @author Ponselvi
 */
public class OpportunitiesInfoPage {

	public OpportunitiesInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement headerInfo;

	@FindBy(id = "dtlview_Opportunity Name")
	private WebElement opportunityNameInfo;

	@FindBy(xpath = "//a[@title='Contacts']")
	private WebElement relatedToInfo;

	public WebElement getHeaderInfo() {
		return headerInfo;
	}

	public WebElement getOpportunityNameInfo() {
		return opportunityNameInfo;
	}

	public WebElement getRelatedToInfo() {
		return relatedToInfo;
	}

}
