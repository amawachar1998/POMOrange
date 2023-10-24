package TestCases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import POM.LoginPage;
import setup.ReturnTest;
import setup.SetUp;
import utils.Methods;

public class LoginTest extends SetUp {

	@Test
	public void loginValideData() {

		try {

			Methods methods = new Methods(driver);
			TestCase = "TC_01 : loginValideData ";
//				
//				test = extent.createTest("Login Scenario :" + TestCase).assignAuthor("Akash Awachar")
//						.assignCategory("Automation Testing");

			ExtentTest test = ReturnTest.testSetupExtent(TestCase, "Akash");
			LoginPage lg = new LoginPage(driver);
			lg.enterUsername("Admin");
			lg.enterPassword("admin123");
			lg.clickOnLOginBtn();
			assertTrue(lg.isHomePage());

//			methods.verify("//*[text()='Time at Work']", TestCase, test);//Normal SC
			methods.verifybase64("//*[text()='Time at Work']", TestCase, test);// Base 64 sc

			System.out.println("loginValideData  Testcase Passed..");
			TestCase = TestCase.replaceAll(TestCase, "");
		}

		catch (Exception e) {
			ReturnTest.testSetupExtent(TestCase, "Akash");
			System.out.println("Testcase Failed due to..");
			e.printStackTrace();
			TestCase = TestCase.replaceAll(TestCase, "");

		}

	}

	@Test
	public void loginInvalidePassword() throws InterruptedException, IOException {

		Methods methods = new Methods(driver);

		TestCase = "TC_02 : loginInvalidePassword ";
		ExtentTest test = ReturnTest.testSetupExtent(TestCase, "Shikha");

		LoginPage lg = new LoginPage(driver);
		lg.enterUsername("Admin");
		lg.enterPassword("Invalid");
		lg.clickOnLOginBtn();
		assertTrue(lg.isErrorMsg());
		methods.verifybase64("//*[text()='Invalid credentials']", TestCase, test);// Base 64 sc
		System.out.println("loginInvalidePassword Testcase Passed..");

	}

	public void loginInvalideUsername() {
		LoginPage lg = new LoginPage(driver);
		lg.enterUsername("Invalid");
		lg.enterPassword("admin123");
		lg.clickOnLOginBtn();
		assertTrue(lg.isErrorMsg());
	}

	public void loginInvalideUsernameAndPassword() {
		LoginPage lg = new LoginPage(driver);
		lg.enterUsername("Invalid");
		lg.enterPassword("Invalid");
		lg.clickOnLOginBtn();
		assertTrue(lg.isErrorMsg());

	}

}
