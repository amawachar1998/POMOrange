package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public LoginPage(WebDriver driver) {

		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//*[@name=\"username\"]")
	private WebElement username;

	@FindBy(xpath = "//*[@name=\"password\"]")
	private WebElement password;

	@FindBy(xpath = "//*[@type=\"submit\"]")
	private WebElement logInBtn;
	
	@FindBy(xpath = "//*[text()='Time at Work']")
	private WebElement HomePage;

	@FindBy(css = ".oxd-alert-content-text")
	private WebElement errorMsg;

	public void enterUsername(String un) {
		username.clear();
		username.sendKeys(un);
	}

	public void enterPassword(String pw) {
		password.clear();
		password.sendKeys(pw);
	}

	public void clickOnLOginBtn() {
		logInBtn.click();
	}

	public boolean isHomePage() {
		return HomePage.isDisplayed();
	}

	public boolean isErrorMsg() {
		return errorMsg.isDisplayed();
	}

}
