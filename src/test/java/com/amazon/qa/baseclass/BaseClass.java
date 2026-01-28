package com.amazon.qa.baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClass {

	WebDriver driver;
	public Properties prop;
	public Properties dataProp;

	public BaseClass() {
		prop = new Properties();
		File proFile = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\amazon\\qa\\config\\config.properties");
		try {
			FileInputStream fis = new FileInputStream(proFile);
			prop.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		dataProp = new Properties();
		File dataProFile = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\amazon\\qa\\testdata\\testdata.properties");
		try {
			FileInputStream datafis = new FileInputStream(dataProFile);
			dataProp.load(datafis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public WebDriver initializeBrowserAndOpenApplicationURL(String BrowserName) {

		if (BrowserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (BrowserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (BrowserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		return driver;
	}

}
