package com.comcast.crm.baseclass;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.comcast.crm.generic.databaseutility.DatabaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

/**
 * This BaseClass is for Parallel and Group Execution 
 * @author Ponselvi
 */
public class BaseClass_Parallel {

	/**
	 * Object Creation
	 */
	public DatabaseUtility dbu = new DatabaseUtility();
	public WebDriverUtility wu = new WebDriverUtility();
	public ExcelUtility eu = new ExcelUtility();
	public FileUtility fu = new FileUtility();
	public JavaUtility ju = new JavaUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver = null;

	/**
	 * This Configuration Annotation is used for Database Connection and Report Configuration
	 * @throws Throwable
	 */
	@BeforeSuite(groups = { "SmokeTest", "RegressionTest" })
	public void connectToDB() throws Throwable {
		Reporter.log("====Connect To DB, Report Configuration====", true);
		dbu.getDBConnection("jdbc:mysql://49.249.28.218:3307/projects", "root@%", "root");
	}

	/**
	 * This Configuration Annotation is used for Launching the Browser
	 * @param browser
	 * @throws IOException
	 */
	@Parameters("BROWSER")
	@BeforeClass(groups = { "SmokeTest", "RegressionTest" })
	public void openBrowser(@Optional("chrome") String browser) throws IOException {
		Reporter.log("====Open Browser====", true);
		String BROWSER = browser;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("friefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}
		wu.maximize(driver);
		wu.waitForPageToLoad(driver);
		sdriver = driver;
	}

	/**
	 * This Configuration Annotation is used for Login to Application
	 * @param url
	 * @param username
	 * @param password
	 * @throws IOException
	 */
	@Parameters({ "URL", "USERNAME", "PASSWORD" })
	@BeforeMethod(groups = { "SmokeTest", "RegressionTest" })
	public void loginToApplication(@Optional("http://localhost:8888/") String url, @Optional("admin") String username,
			@Optional("password") String password) throws IOException {
		Reporter.log("====Login To Application====", true);
		String URL = url;
		String USERNAME = username;
		String PASSWORD = password;
		driver.get(URL);
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
	}

	/**
	 * This Configuration Annotation is used for Logout of Application 
	 */
	@AfterMethod(groups = { "SmokeTest", "RegressionTest" })
	public void logout() {
		Reporter.log("====Logout of Application====", true);
		HomePage hp = new HomePage(driver);
		hp.logout();
	}

	/**
	 * This Configuration Annotation is used for Closing the Browser
	 */
	@AfterClass(groups = { "SmokeTest", "RegressionTest" })
	public void closeBrowser() {
		Reporter.log("====Close Browser====", true);
		driver.quit();
	}

	/**
	 * This Configuration Annotation is used for Closing the Database Connection and Report Backup
	 */
	@AfterSuite(groups = { "SmokeTest", "RegressionTest" })
	public void closeDBConnection() {
		Reporter.log("====Close DB Connection, Report Backup====", true);
		dbu.closeDBConnection();
	}
}
