package practiceTest;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.baseclass.BaseClass;
@Listeners(com.comcast.crm.listenerutility.ListenerImp.class)
public class SampleTest extends BaseClass{
	@Test
	public void sampleTest() {
		System.out.println("Sample Test");
	}

}
