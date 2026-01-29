package com.amazon.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupPage {
	WebDriver driver;
	WebDriverWait wait;

	// Objects
	@FindBy(xpath = "//input[@id='ap_email_login']")
	WebElement emailAddressField;
	@FindBy(xpath = "//input[@type='submit']")
	WebElement clickOnContinue;
	@FindBy(xpath = "//input[@id='ap_password']")
	WebElement passwordField;
	@FindBy(xpath = "//div[contains(text(),'Enter your mobile number or email')]")
	WebElement emptyEmailFieldWarning;
	@FindBy(xpath = "//div[contains(text(),'Enter your password')]")
	WebElement emptyPasswordFieldWarning;

	public SignupPage(WebDriver driver) {
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
		return getWarningText(emptyEmailFieldWarning);

	}

	public String retrievepasswordWarning() {
		return getWarningText(emptyEmailFieldWarning);
	}
	
	public void enterEmailAddressField( String emailText)
	{
		emailAddressField.sendKeys(emailText);
	}
	
	public void enterPasswordField(String passwordText)
	{
		passwordField.sendKeys(passwordText);
	}
}
