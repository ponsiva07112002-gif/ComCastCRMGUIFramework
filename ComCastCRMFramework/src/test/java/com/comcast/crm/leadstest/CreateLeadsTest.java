package com.comcast.crm.leadstest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.baseclass.BaseClass_Parallel;
import com.comcast.crm.objectrepositoryutility.CreatingNewLeads;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LeadsInfoPage;
import com.comcast.crm.objectrepositoryutility.LeadsPage;
/**
 * This class is for create Lead
 * @author Ponselvi
 */
public class CreateLeadsTest extends BaseClass_Parallel{
	@Test
	/**
	 * This method is for crate Lead
	 * @throws Throwable
	 * @throws IOException
	 */
	public void createLeads() throws Throwable, IOException {
		String LASTNAME = eu.getDataFromExcel("Leads", 1, 2) + ju.getRandomNumber();
		String COMPANY = eu.getDataFromExcel("Leads", 1, 3) + ju.getRandomNumber();

		// Create Leads
		HomePage hp = new HomePage(driver);
		hp.getLeadsLink().click();
		LeadsPage lep = new LeadsPage(driver);
		lep.getCreateLeadBtn().click();
		CreatingNewLeads cnl = new CreatingNewLeads(driver);
		cnl.createLeads(LASTNAME, COMPANY);

		// Verify the Header
		LeadsInfoPage lip = new LeadsInfoPage(driver);
		String header = lip.getHeaderInfo().getText();
		boolean status = header.contains(LASTNAME);
		Assert.assertEquals(status, true);
		
		// Verify the Company
		String company = lip.getCompany().getText();
		SoftAssert assertobj = new SoftAssert();
		assertobj.assertEquals(company.trim(), COMPANY);
		assertobj.assertAll();
				
	}
}
