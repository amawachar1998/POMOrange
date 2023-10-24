package practice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Base64;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base64Sc {

	public static void main(String[] args) throws IOException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://google.com");

		TakesScreenshot sc = (TakesScreenshot) driver;
		String base64screenshot = sc.getScreenshotAs(OutputType.BASE64);

		System.out.println(System.getProperty("user.dir"));

		File SCpath = new File(System.getProperty("user.dir") + "\\SCREENSHOT\\SC_1.png");

		FileOutputStream fos = new FileOutputStream(SCpath);

		byte[] decode = Base64.getDecoder().decode(base64screenshot);

		fos.write(decode);

		System.out.println("Screenshot Taken at "+SCpath);
		fos.close();

	}

}
