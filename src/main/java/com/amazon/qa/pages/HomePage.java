package com.amazon.qa.pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	WebDriver driver;
	WebDriverWait wait;
	@FindBy(xpath = "//span[@id='nav-link-accountList-nav-line-1']")
	WebElement clickOnSigninLink;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	// Navigate to sign up page
	public SignupPage navigateToSignupPage() {
		wait.until(ExpectedConditions.elementToBeClickable(clickOnSigninLink)).click();
		return new SignupPage(driver);
	}

	// Navigate to sign up page
	public SigninPage navigateToSiginPage() {
		wait.until(ExpectedConditions.elementToBeClickable(clickOnSigninLink)).click();
		return new SigninPage(driver);
	}
}
