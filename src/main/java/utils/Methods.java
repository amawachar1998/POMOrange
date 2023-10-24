package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import setup.SetUp;

public class Methods extends SetUp {

	private WebDriver driver;

	public Methods(WebDriver driver) {
		this.driver = driver;
	}

	public void verifynormal(String ec, String stepDesc, ExtentTest test) throws IOException, InterruptedException {
		Methods methods = new Methods(driver);

		try {

			WebElement ele = driver.findElement(By.xpath(ec));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.background='yellow'", ele);
			js.executeScript("arguments[0].style.border='2px solid Blue'", ele);

			Thread.sleep(1000);

			boolean displayed = driver.findElement(By.xpath(ec)).isDisplayed();

			if (displayed) {

				test.info("<b style='color:Blue;'>" + stepDesc + " Passed" + "</b>");
				test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(methods.tcSC(test)).build());
				Assert.assertTrue(true);
			} else {
				test.info("<b style='color:Red;'>" + stepDesc + " Failed" + "</b>");
				test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(methods.tcSC(test)).build());
				Assert.assertTrue(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
			String scBase64 = methods.SCBase64(test);
			Thread.sleep(2000);
			test.info("<b style='color:Red;'>" + stepDesc + " Failed" + "</b>");
			test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(scBase64).build());
			Assert.assertTrue(false);
		}
	}

	public void verifybase64(String ec, String stepDesc, ExtentTest test) throws InterruptedException, IOException {
		Methods methods = new Methods(driver);

		try {

			WebElement ele = driver.findElement(By.xpath(ec));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.background='yellow'", ele);
			js.executeScript("arguments[0].style.border='2px solid Blue'", ele);

			Thread.sleep(1000);

			boolean displayed = driver.findElement(By.xpath(ec)).isDisplayed();

			if (displayed) {
				String scBase64 = methods.SCBase64(test);
				Thread.sleep(2000);
				test.info("<b style='color:Blue;'>" + stepDesc + " Passed" + "</b>");
				test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromBase64String(scBase64).build());
				Assert.assertTrue(true);
			} else {
				String scBase64 = methods.SCBase64(test);
				Thread.sleep(2000);
				test.info("<b style='color:Red;'>" + stepDesc + " Failed" + "</b>");
				test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(scBase64).build());

			}

		} catch (Exception e) {
			e.printStackTrace();
			String scBase64 = methods.SCBase64(test);
			Thread.sleep(2000);
			test.info("<b style='color:Red;'>" + stepDesc + " Failed" + "</b>");
			test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(scBase64).build());
			Assert.assertTrue(false);
		}
	}

	public void verifybase64_2(ExtentTest test) throws InterruptedException, IOException {
		String stepDesc = "Validation";
		Methods methods = new Methods(driver);

		try {
			String scBase64 = methods.SCBase64(test);
			Thread.sleep(2000);
			test.info("<b style='color:Red;'>" + stepDesc + " Failed" + "</b>");
			test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(scBase64).build());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String tcSC(ExtentTest test) throws IOException {

		String concat = ".";

		String timeStamp = new SimpleDateFormat("dd_MM_yy_ss_SSSS").format(new Date());
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File src = screenshot.getScreenshotAs(OutputType.FILE);

		String imagepath = "./AutomationReports/SCREENSHOT/SC_" + timeStamp + ".png";

		File dest = new File(imagepath);
		FileUtils.copyFile(src, dest);
		String screenShotName = concat + imagepath;
		return screenShotName;

	}

	public String SCBase64(ExtentTest test) throws IOException {

		String timeStamp = new SimpleDateFormat("dd_MM_yy_ss_SSSS").format(new Date());
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File src = screenshot.getScreenshotAs(OutputType.FILE);

		String imagepath = System.getProperty("user.dir") + "/AutomationReports/SCREENSHOT/SC_" + timeStamp + ".png";

		File dest = new File(imagepath);
		FileUtils.copyFile(src, dest);

		byte[] byteArray = IOUtils.toByteArray(new FileInputStream(imagepath));
		return Base64.getEncoder().encodeToString(byteArray);

	}
}
