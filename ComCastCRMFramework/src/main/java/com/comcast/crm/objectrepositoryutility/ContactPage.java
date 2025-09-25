package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This class is for ContactPage which contains webelements and getter methods 
 * @author Ponselvi
 */
public class ContactPage {

	public ContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath="//img[@alt='Create Contact...']")
	private WebElement createContact;
	
	@FindBy (xpath="//input[@value='Delete']")
	private WebElement deleteContact;

	public WebElement getCreateContact() {
		return createContact;
	}

	public WebElement getDeleteContact() {
		return deleteContact;
	}	
}
