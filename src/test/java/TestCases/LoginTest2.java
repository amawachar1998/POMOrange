package TestCases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import POM.LoginPage;
import setup.ReturnTest;
import setup.SetUp;
import utils.Methods;

public class LoginTest2 extends SetUp {

	@Test(description = "Entering valid username and valid password")
	public void test1() throws InterruptedException, IOException {

		TestCase = "TC_01 : loginValideData ";
		ExtentTest test = ReturnTest.testSetupExtent(TestCase, "Akash");
		Methods methods = new Methods(driver);
		LoginPage lg = new LoginPage(driver);

		try {

			test.info("Entering username");
			lg.enterUsername("Admin");

			System.err.println(10 / 0);

			test.info("Entering password");
			lg.enterPassword("admin123");
			test.info("Click on login Btn");
			lg.clickOnLOginBtn();
			assertTrue(lg.isHomePage());

			methods.verifybase64("//*[text()='Time at Work']", "Validate condition", test);// Base 64 sc

			System.out.println("loginValideData  Testcase Passed..");
			TestCase = TestCase.replaceAll(TestCase, "");
		}

		catch (Exception e) {

			methods.verifybase64_2(test);// Base 64 sc
			System.out.println("Testcase Failed due to..");
			e.printStackTrace();
			TestCase = TestCase.replaceAll(TestCase, "");
			Assert.assertTrue(false);

		}

	}

	@Test(description = "Entering valid username and invalid password")
	public void test2() throws InterruptedException, IOException {
		Methods methods = new Methods(driver);
		LoginPage lg = new LoginPage(driver);

		TestCase = "TC_02 : loginInvalidePassword ";
		ExtentTest test = ReturnTest.testSetupExtent(TestCase, "Shikha");

		try {
			lg.enterUsername("Admin");
			test.info("Entering username");
			lg.enterPassword("Invalid");
			test.info("Entering password");
			lg.clickOnLOginBtn();
			test.info("Click on Login Btn");
			assertTrue(lg.isErrorMsg());

//			methods.verifybase64("//*[text()='Invalid credentials']", "Validate condition", test);// Base 64 sc
			methods.verifybase64("//*[text()='Invalid ']", "Validate condition", test);// Base 64 sc
			System.out.println("loginInvalidePassword Testcase Passed..");

		} catch (Exception e) {
			methods.verifybase64_2(test);// Base 64 sc
			System.out.println("Testcase Failed due to..");
			e.printStackTrace();
			TestCase = TestCase.replaceAll(TestCase, "");
			Assert.assertTrue(false);
		}

	}
}
