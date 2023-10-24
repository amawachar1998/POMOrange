package setup;

import com.aventstack.extentreports.ExtentTest;

public class ReturnTest extends SetUp {

	public static ExtentTest testSetupExtent(String TestCase, String tester) {

		test = extent.createTest("Login Scenario :" + TestCase).assignAuthor(tester)
				.assignCategory("Automation Testing");

		return test;
	}

}
