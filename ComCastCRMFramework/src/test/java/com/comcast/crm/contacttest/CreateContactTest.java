package com.comcast.crm.contacttest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.baseclass.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContact;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;
/**
 * This class is for create contact
 * @author Ponselvi
 */
public class CreateContactTest extends BaseClass{
	/**
	 * This method is for create contact
	 * 
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	@Test(groups = "SmokeTest")
	public void createContact() throws EncryptedDocumentException, IOException {

		// Read Data from Excel //
		UtilityClassObject.getTest().log(Status.INFO, "Read data from excel");
		String LASTNAME = eu.getDataFromExcel("Contacts", 1, 2) + ju.getRandomNumber();

		// Navigate and Create Contact
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to contact page");
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContact().click();
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to create new contact page");
		CreatingNewContact cnc = new CreatingNewContact(driver);
		cnc.createContact(LASTNAME);

		// Verify Header
		ContactInfoPage cip = new ContactInfoPage(driver);
		String header = cip.getHeaderInfo().getText(); // Hard Assert
		boolean status = header.contains(LASTNAME);
		Assert.assertEquals(status, true);
		UtilityClassObject.getTest().log(Status.PASS, "Contact is created");
	}
	
	/**
	 * This method is for create contact with organization
	 * 
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test(groups = "RegressionTest")
	public void createContactWithOrganization() throws EncryptedDocumentException, IOException, InterruptedException {
		// Read Data from Excel
		UtilityClassObject.getTest().log(Status.INFO, "Read data from excel");
		String LASTNAME = eu.getDataFromExcel("Contacts", 7, 2) + ju.getRandomNumber();
		String ORGNAME = eu.getDataFromExcel("Contacts", 7, 3) + ju.getRandomNumber();

		// Navigate and Create Organization
		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrganization().click();
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(ORGNAME);
		Thread.sleep(2000);

		// Navigate and Create Contact
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to contact page");
		hp.getContactsLink().click();
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContact().click();
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to create new contact page");
		CreatingNewContact cnc = new CreatingNewContact(driver);
		cnc.searchOrganization(LASTNAME, ORGNAME);
		driver.findElement(By.xpath("//a[contains(text(),'" + ORGNAME + "')]")).click();

		// Switch to Parent Window
		wu.switchToTabOnURL(driver, "module=Contacts");
		cnc.getSaveBtn().click();

		// Verify Header
		ContactInfoPage cip = new ContactInfoPage(driver);
		String header = cip.getHeaderInfo().getText();
		boolean status = header.contains(LASTNAME);
		Assert.assertEquals(status, true);

		// Verify the Organization
		String org = cip.getOrgInfo().getText();
		SoftAssert assertobj = new SoftAssert();
		assertobj.assertEquals(org.trim(), ORGNAME);
		assertobj.assertAll();
		UtilityClassObject.getTest().log(Status.PASS, "Contact is created with organization");
	}
	
	/**
	 * This method is for create contact with Date
	 * 
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	@Test(groups = "RegressionTest")
	public void createContactWithDate() throws EncryptedDocumentException, IOException {
		// Read Data from Excel
		UtilityClassObject.getTest().log(Status.INFO, "Read data from excel");
		String LASTNAME = eu.getDataFromExcel("Contacts", 1, 2) + ju.getRandomNumber();

		// Navigate and Create Contact
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to contact page");
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContact().click();
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to create new contact page");
		String STARTDATE = ju.getSystemDateYYYYDDMM();
		String ENDDATE = ju.getRequiredDateYYYYMMDD(30);
		CreatingNewContact cnc = new CreatingNewContact(driver);
		cnc.createContactWithSupportDate(LASTNAME, STARTDATE, ENDDATE);

		// Verify Header
		ContactInfoPage cip = new ContactInfoPage(driver);
		String header = cip.getHeaderInfo().getText();
		boolean status = header.contains(LASTNAME);
		Assert.assertEquals(status, true);

		// Verify Support Date
		String supportStartDate = cip.getStartDateInfo().getText();
		SoftAssert assertobj = new SoftAssert();
		assertobj.assertEquals(supportStartDate, STARTDATE);

		String supportEndDate = cip.getEndDateInfo().getText();
		assertobj.assertEquals(supportEndDate, ENDDATE);
		assertobj.assertAll();
		UtilityClassObject.getTest().log(Status.PASS, "Contact is created with Date");
	}
}
