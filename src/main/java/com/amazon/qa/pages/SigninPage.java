package com.amazon.qa.pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SigninPage {
	WebDriver driver;
	WebDriverWait wait;
	HomePage homePage;

	// Objects
	@FindBy(xpath = "//input[@id='ap_email_login']")
	WebElement emailAddressField;
	@FindBy(xpath = "//input[@type='submit']")
	WebElement clickOnContinue;
	@FindBy(xpath = "//input[@id='ap_password']")
	WebElement passwordField;
	@FindBy(xpath = "//input[@id='signInSubmit']")
	WebElement signinButton;
	@FindBy(xpath = "//div[contains(text(),'Enter your mobile number or email')]")
	WebElement emptyEmailField;
	@FindBy(xpath = "//div[contains(text(),'Invalid email address.')]")
	WebElement invalidEmailAddress;
	@FindBy(xpath = "//div[contains(text(),'Enter your password')]")
	WebElement emptyPasswordField;
	@FindBy(xpath = "//div[@id='auth-error-message-box']//div[@class='a-box-inner a-alert-container']")
	WebElement invalidPassword;

	public SigninPage(WebDriver driver) {

		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	// Action Method
    // Generic reusable method (ONLY ONCE)
	public String getWarningText(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
			wait.until(ExpectedConditions.visibilityOf(element));
			return element.isDisplayed() ? element.getText().trim() : "";
		} catch (Exception e) {
			System.out.println("Warning not displayed: " + element);
			return "";
		}
	}

	public String retrieveEmailWarning() {
		return getWarningText(emailWarning);

	}

	public String retrievepasswordWarning() {
		return getWarningText(passwordWarning);
	}

	public void enterEmailField(String emailTxt) {
		emailAddressField.sendKeys(emailTxt);
	}

	public void clickOnContinueButton() {
		clickOnContinue.click();
	}

	public void enterPasswordField(String passwordText) {
		passwordField.sendKeys(passwordText);
	}

	public void ClickOnSigninButton() {
		signinButton.click();
	}

	public HomePage login(String emailText, String passwordText) {
		emailAddressField.sendKeys(emailText);
		clickOnContinue.click();
		passwordField.sendKeys(passwordText);
		signinButton.click();

		return new HomePage(driver);
	}
}
