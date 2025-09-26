package practiceTest;


import org.testng.Assert;
import org.testng.annotations.Test;

public class InvoiceTest {
	@Test(retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImplementation.class)
	public void activeSim() {
		System.out.println("execute Invoice test");
		Assert.assertEquals(" ", "Login");
		System.out.println("step 1");
	}
}
