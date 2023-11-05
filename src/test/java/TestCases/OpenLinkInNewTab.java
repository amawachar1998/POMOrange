package TestCases;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class OpenLinkInNewTab {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		driver.get("https://www.w3schools.com/java/");
		String parentWindow = driver.getWindowHandle();

		WebElement javaHomeLinkText = driver.findElement(By.xpath("//*[text()='Java Intro']"));
		javaHomeLinkText.sendKeys(Keys.CONTROL, Keys.RETURN);
		System.out.println("1" + driver.getTitle());

		Set<String> handles = driver.getWindowHandles();

		for (String handle : handles) {
			driver.switchTo().window(handle);
			if (!handle.equals(parentWindow)) {
				System.out.println("2" + driver.getTitle());
			}
		}

		driver.switchTo().window(parentWindow);
		System.out.println("3" + driver.getTitle());
	}

}
