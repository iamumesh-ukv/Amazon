package com.amazon.qa.listeners;

import java.awt.Desktop;
import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.amazon.qa.utilities.ExtentReporter;
import com.amazon.qa.utilities.Utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class MyListeners implements ITestListener {

	// Need to call the Extent report page from this page
	ExtentReports extentReport;
	ExtentTest extentTest;

	@Override
	public void onStart(ITestContext context) {

		extentReport = ExtentReporter.generateExtentReport();

		// System.out.println("Execution Of Project Tests Started!");
		// TODO Auto-generated method stub
		// ITestListener.super.onStart(context);
	}
	@Override
	public void onTestStart(ITestResult result) {

		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName() + "Started executing!");

		// String testName = result.getName();
		// System.out.println(testName + " Execution Started!");

		// TODO Auto-generated method stub
		// ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		extentTest.log(Status.PASS, result.getName() + "Got Succussfully Executed!");

		// String testName = result.getName();
		// System.out.println(testName + " Got Succussfully Executed!");

		// TODO Auto-generated method stub
		// ITestListener.super.onTestSuccess(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {

		WebDriver driver = null;

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		String destinationScreenshotPath = Utilities.captureScreenshot(driver, result.getName());

		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);

		extentTest.log(Status.INFO, result.getName());
		extentTest.log(Status.FAIL, result.getName() + " Got Failed! ");

		// String testName = result.getName();
		// System.out.println(testName + " Got Failed!");
		// System.out.println(result.getThrowable());

		// TODO Auto-generated method stub
		// ITestListener.super.onTestFailure(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		extentTest.log(Status.INFO, result.getName());
		extentTest.log(Status.SKIP, result.getName() + "Test got Skipped!");

		// String testName = result.getName();
		// System.out.println(testName + " Test Got Skipped!");
		// System.out.println(result.getThrowable());
		// TODO Auto-generated method stub
		// ITestListener.super.onTestSkipped(result);
	}

	/*
	 * @Override public void onTestFailedButWithinSuccessPercentage(ITestResult
	 * result) { // TODO Auto-generated method stub
	 * //ITestListener.super.onTestFailedButWithinSuccessPercentage(result); }
	 */

	/*
	 * @Override public void onTestFailedWithTimeout(ITestResult result) { // TODO
	 * Auto-generated method stub
	 * //ITestListener.super.onTestFailedWithTimeout(result); }
	 */

	@Override
	public void onFinish(ITestContext context) {

		extentReport.flush();

		String pathOfExtentReport = System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);

		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// System.out.println("Finished Executing Project Tests");
		// TODO Auto-generated method stub
		// ITestListener.super.onFinish(context);
	}
}
