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

/*
public class MyListeners implements ITestListener {

    ExtentReports extentReport;
    ExtentTest extentTest;

    private static final Logger log = LogManager.getLogger(MyListeners.class);

    @Override
    public void onStart(ITestContext context) {
        extentReport = ExtentReporter.generateExtentReport();
        log.info("===== Test Execution Started =====");
    }

    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extentReport.createTest(result.getName());
        extentTest.log(Status.INFO, result.getName() + " Started executing!");

        log.info("STARTED: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS, result.getName() + " Executed Successfully!");

        log.info("PASSED: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {

        log.error("FAILED: " + result.getName());
        log.error("Reason: " + result.getThrowable());

        WebDriver driver = null;

        //  Better approach (recommended)
        try {
            Object currentClass = result.getInstance();
            driver = ((com.amazon.qa.base.BaseTest) currentClass).getDriver();
        } catch (Exception e) {
            log.error("Driver fetch failed: " + e.getMessage());
        }

        // Capture Screenshot
        String screenshotPath = Utilities.captureScreenshot(driver, result.getName());

        // Attach to report
        extentTest.addScreenCaptureFromPath(screenshotPath);

        extentTest.log(Status.FAIL, result.getName() + " Failed!");
        extentTest.log(Status.FAIL, result.getThrowable());

        log.error("Screenshot saved at: " + screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.log(Status.SKIP, result.getName() + " Test Skipped!");

        log.warn("SKIPPED: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {

        extentReport.flush();

        log.info("===== Test Execution Finished =====");

        String path = System.getProperty("user.dir")
                + "\\test-output\\ExtentReports\\extentReport.html";

        File report = new File(path);

        try {
            Desktop.getDesktop().browse(report.toURI());
        } catch (Exception e) {
            log.error("Unable to open report: " + e.getMessage());
        }
    }
}
*/

//public WebDriver getDriver() {
//    return driver;
//}
//}
