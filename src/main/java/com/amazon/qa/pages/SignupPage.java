package com.amazon.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupPage {
	WebDriver driver;
	WebDriverWait wait;

	// Objects
	@FindBy(xpath = "//input[@id='ap_email_login']")
	WebElement emailAddressField;
	@FindBy(xpath = "//input[@type='submit']")
	WebElement clickOnContinue;

	public SignupPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
}
