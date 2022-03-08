package EventBuizz.EventBuizz;
import java.lang.*;
import java.io.File;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Configuration {
	public static WebDriver driver;
	public static ExtentReports report;
	public static ExtentTest logger;


	@BeforeSuite
	public void LaunchApplication() {

		//System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\\\chromedriver.exe");
		//serever configurations
		//System.setProperty("webdriver.chrome.driver", "/usr/bin/google-chrome");
		//driver = new ChromeDriver();
		 driver=new FirefoxDriver();
		// 
		//driver = new FirefoxDriver();
		driver.manage().window().maximize();
		//driver.get("https://my.eventbuizz.com/_admin");
		// driver.get("https://www.curiato.com/");
		//driver.get("https://consult.attendanywhere.com/login");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		//logger.log(LogStatus.INFO, "Application is up and running");
		ExtentReport ();

		// driver.get("https://www.seleniumeasy.com/jenkins-tutorials");
	}
	public void ExtentReport () {

		// report=new ExtentReports("C:\\Report\\LearnAutomation.html");
		// report=new ExtentReports("C:\\Report\\LearnAutomation.html");
		String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/target//surefire-reports//extent-Report/";
		//File  destFile = new File((String) reportDirectory+"/ExtentReport"+".html"); 
		report=new ExtentReports( reportDirectory+"/EventBuizz-AuotmationReport"+".html"); 


		/* @AfterSuite

		 public void Aftertest() {
				driver.quit();

		 }*/
	}



}