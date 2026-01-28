package com.amazon.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.amazon.qa.baseclass.BaseClass;
import com.amazon.qa.pages.HomePage;
import com.amazon.qa.pages.SigninPage;

public class SigninTest extends BaseClass {

	WebDriver driver;
	WebDriverWait wait;
	HomePage homePage;
	SigninPage signinPage;

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		homePage = new HomePage(driver);
		signinPage = homePage.navigateToSiginPage();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyLoginWithValidCredentials() {

		signinPage.login(prop.getProperty("validEmail"), prop.getProperty("validPassword"));
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {
		signinPage.login(dataProp.getProperty("invalidEmail"), dataProp.getProperty("invalidPassword"));
	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		signinPage.login(dataProp.getProperty("invalidEmail"), prop.getProperty("validPassword"));
	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		signinPage.login(prop.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));

	}

//	@Test(priority = 5)
//	public void verifyLoginWithoutProvidingCredentials() {
//		signinPage.ClickOnSigninButton();
//	}

}