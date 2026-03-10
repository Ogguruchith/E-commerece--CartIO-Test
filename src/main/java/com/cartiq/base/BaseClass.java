package com.cartiq.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.cartiq.utils.AIBugReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public WebDriver driver;
	public WebDriverWait wait;
	public static ExtentReports extent;
	public static ExtentTest extentTest;

	@BeforeSuite
	public void setupExtent() {
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("ExtenetReport.html");

		extentSparkReporter.config().setReportName("Automation Testing Project");
		extentSparkReporter.config().setDocumentTitle("Project E-commerece");

		extent = new ExtentReports();
		extent.attachReporter(extentSparkReporter);

		extent.setSystemInfo("Os", System.getProperty("os.name"));
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		extent.setSystemInfo("Tester", "Ruchith");
	}

	@BeforeMethod
	public void setup(Method method) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-save-password-bubble");
		options.addArguments("--disable-features=PasswordLeakDetection");
		options.addArguments("--password-store=basic");
		options.addArguments("--disable-notifications");
		options.addArguments("--no-default-browser-check");

		// ✅ Remove automation bar
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });

		// ✅ Disable password manager completely!
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("profile.password_manager_leak_detection", false);
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		extentTest = extent.createTest(method.getName());
	}

	@AfterMethod
	public void CheckResutl(Method method, ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String path = capturescreenshot(result.getName());
			extentTest.fail(result.getThrowable().getMessage());
			extentTest.addScreenCaptureFromPath(path);
			String aiAnalysis = AIBugReporter.analyzeBug(result.getName(), result.getThrowable().getMessage());
			extentTest.info("🤖 AI Analysis: " + aiAnalysis);
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.pass(result.getName() + "Passed");
		}

		String[] groups = method.getAnnotation(org.testng.annotations.Test.class).groups();
		extentTest.assignCategory(groups);
		if (driver != null) {
			driver.quit();
		}
	}

	public String capturescreenshot(String testname) throws IOException {
		try {
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

			String folderPath = "screenshots/" + timestamp + "/";

			File folder = new File(folderPath);
			if (!folder.exists()) {
				folder.mkdirs();
			}

			String filePath = folderPath + testname + ".png";

			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File sourcefile = screenshot.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(sourcefile, new File(filePath));

			return new File(filePath).getAbsolutePath();

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	@AfterSuite
	public void exit() {
		extent.flush();
	}
}
