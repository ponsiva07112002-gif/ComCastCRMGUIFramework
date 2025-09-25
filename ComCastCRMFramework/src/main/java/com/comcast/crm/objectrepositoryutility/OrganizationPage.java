package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This class is for OrganizationPage which contains webelements and getter methods 
 * @author Ponselvi
 */
public class OrganizationPage {

	public OrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[@alt='Create Organization...']")
	private WebElement createOrganization;

	@FindBy(xpath = "//input[@value='Delete']")
	private WebElement deleteOrganization;

	public WebElement getCreateOrganization() {
		return createOrganization;
	}

	public WebElement getDeleteOrganization() {
		return deleteOrganization;
	}

}
