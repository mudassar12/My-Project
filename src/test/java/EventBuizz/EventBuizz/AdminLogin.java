package EventBuizz.EventBuizz;
import java.io.File;
import org.apache.tools.ant.types.*;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.apache.tools.ant.types.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

public class AdminLogin extends Configuration {

	@Test(priority = 1)
	public void ValidateThePageTitle() {
 // String expectedresult="abc";
		logger = report.startTest("VerifyPageTitle");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		logger.log(LogStatus.INFO, "Application is up and running");
		String title = driver.getTitle();
		//title="abc3433";
		// System.out.print("Link Text:"+ title);
		Assert.assertTrue(title.contains("EventBuizz"));
		logger.log(LogStatus.PASS, "Title verified");
	}

	@Test(priority = 2)

	public void ValidateThatLOGINHEREHeadingPersistOnTheLoginPage() {
		logger = report.startTest("Validate That 'Login Here' Text persist on the Login page");
		String ExpectedResult = "LOG IN HERE";
		Assert.assertEquals(ExpectedResult, this.GetLoginHeading());
		logger.log(LogStatus.PASS, "'Login Here' Text Verified On the Login page");
	}

	@Test(priority = 3)

	public void ValidateUserNameFieldPersistOnTheLoginPage() {
		logger = report.startTest("Validate That On The Login Page , User Name Field Exist");
		WebElement UserName = this.GetUserName();
		Assert.assertTrue(UserName.isDisplayed());
		logger.log(LogStatus.PASS, "'User Name' Field Exist on the Login page");
	}

	@Test(priority = 4)
	public void ValidatePasswordFieldPersistOnTheLoginPage() {
		logger = report.startTest("Validate That On The Login Page , Password Field Exist");
		WebElement Password = this.GetPassword();
		Assert.assertTrue(Password.isDisplayed());
		logger.log(LogStatus.PASS, "'Password' Field Exist on the Login page");

	}

	@DataProvider(name = "Authentication")
	public static Object[][] credentials() {

		return new Object[][] { { "Test@mailinator.com", "test" },{"mmu@eventbuizz.com", "test"},{"Test@mailinator.com", "Eventbuizz@123"} };
	}

	@Test(priority = 5, dataProvider = "Authentication")
	@Parameters({ "UName", "Password" })

	public void ValidateLoginWithWrongCombination(String UName, String Password) {
		logger = report.startTest("Validate That when user send incorrect password , system shows correct error message and User remains on the login page");
		String ExpectedResult="Your username/password combination was incorrect";
		
		SetUserName(UName);
		logger.log(LogStatus.INFO, "'Enter User Name = "+UName);
		SetPassword(Password);
		logger.log(LogStatus.INFO, "'Enter Password = "+Password);
		this.GetLoginButton();
		WebElement loginBtn=this.GetLoginButton();
		loginBtn.click();
		logger.log(LogStatus.INFO, "'Click On The Login Button ");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement errorMessage=GetLoginErrorMessage();
		//System.out.println("error message text"+errorMessage.getText());
		Assert.assertEquals(ExpectedResult,errorMessage.getText() );
		
		logger.log(LogStatus.PASS, "Test case Pass:When User enters wrong combination of lgin then Correct Error message is displayed on the Login page");
		
	}
	
	
	
	
	@DataProvider(name = "EmptyString")
	public static Object[][] EmptyString() {

		return new Object[][] {{"",""} };
	}

	@Test(priority = 6, dataProvider = "EmptyString")
	@Parameters({ "UName", "Password" })

	public void ValidateLoginWithEmptyStrtings(String UName, String Password) {
		logger = report.startTest("Validate With Empty Fields user tries to login then system shows Correct Error message on the screen");
		String ExpectedResult="The email field is required. The password field is required.";
		SetUserName(UName);
		logger.log(LogStatus.INFO, "'Enter User Name = "+UName);
		SetPassword(Password);
		logger.log(LogStatus.INFO, "'Enter Password = "+Password);
		this.GetLoginButton();
		WebElement loginBtn=this.GetLoginButton();
		loginBtn.click();
		logger.log(LogStatus.INFO, "'Click On The Login Button");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement errorMessage=GetLoginErrorMessage();
		//System.out.println("error message text"+errorMessage.getText());
		Assert.assertEquals(ExpectedResult,errorMessage.getText() );
		logger.log(LogStatus.PASS, "Test case Pass:When User enter Nothing in the login fields then system shows an Error messag on the screen");
		
	}
	@DataProvider(name = "EmptyUserNameField")
	public static Object[][] EmptyUserNameField() {

		return new Object[][] {{"","123456"} };
	}

	@Test(priority = 7, dataProvider = "EmptyUserNameField")
	@Parameters({ "UName", "Password" })

	public void ValidateLoginWithEmptyUserName(String UName, String Password) {
		logger = report.startTest("Validate when user enters only Username without Password then system shows correct Error message On the login screen");
		String ExpectedResult="The email field is required.";
		SetUserName(UName);
		logger.log(LogStatus.INFO, "'Enter User Name = "+UName);
		SetPassword(Password);
		logger.log(LogStatus.INFO, "'Enter Password = "+Password);
		this.GetLoginButton();
		WebElement loginBtn=this.GetLoginButton();
		loginBtn.click();
		logger.log(LogStatus.INFO, "'Click On The Login Button ");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement errorMessage=GetLoginErrorMessage();
		//System.out.println("error message text"+errorMessage.getText());
		Assert.assertEquals(ExpectedResult,errorMessage.getText());
		logger.log(LogStatus.PASS, "Test case Pass:When User tries to Login with Only UserName then system shows Correct Error Message on the Login Screen");
		
		
	}
	@DataProvider(name = "EmptyPassword")
	public static Object[][] EmptyPassword() {

		return new Object[][] {{"mmu@eventbuizz.com", ""} };
	}

	@Test(priority = 8, dataProvider = "EmptyPassword")
	@Parameters({ "UName", "Password" })

	public void ValidateLoginWithEmpPassword (String UName, String Password) {
		logger = report.startTest("Validate when user enters only Password withOut User Name then system shows correct Error message On the login screen");
		String ExpectedResult="The password field is required.";
		SetUserName(UName);
		logger.log(LogStatus.INFO, "'Enter User Name = "+UName);
		SetPassword(Password);
		logger.log(LogStatus.INFO, "'Enter Password = "+Password);
		this.GetLoginButton();
		WebElement loginBtn=this.GetLoginButton();
		loginBtn.click();
		logger.log(LogStatus.INFO, "'Click On The Login Button");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement errorMessage=GetLoginErrorMessage();
		//System.out.println("error message text"+errorMessage.getText());
		Assert.assertEquals(ExpectedResult,errorMessage.getText() );
		logger.log(LogStatus.PASS, "Test case Pass:When User tries to Login with Only Password then system shows Correct Error Message on the Login Screen");
		}
	@Parameters({ "UName", "Password"})
	
	  @Test(priority = 9) public void ValidateLoginWithCorrectUserNameAndPassword(String UName, String Password)
	  {
		  logger = report.startTest("Validate when user enters Correct Login credentials then system Login successfully");
		  String ExpectedResult="https://my.eventbuizz.com/_admin/dashboard";
		  SetUserName(UName);
			logger.log(LogStatus.INFO, "'Enter User Name = "+UName);
			SetPassword(Password);
			logger.log(LogStatus.INFO, "'Enter Password = "+Password);
			this.GetLoginButton();
			WebElement loginBtn=this.GetLoginButton();
			System.out.println("boefore click");
			loginBtn.click();
			System.out.println("after click");
			logger.log(LogStatus.INFO, "'Click On The Login Button");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
			//System.out.println("error message text"+errorMessage.getText());
			Assert.assertEquals(ExpectedResult,driver.getCurrentUrl());
			logger.log(LogStatus.PASS, "Test case Pass:when user enters correct userName And Password then User land on the dashbaord screen successfully");
	  
	  }
	 

	public String GetLoginHeading() {

		WebElement Heading = driver.findElement(By.xpath("/html/body/div[1]/section/div/h1"));
		String HeadingText = Heading.getText();

		return HeadingText;
	}

	public WebElement GetUserName() {

		/*
		 * WebElement UserName= driver
		 * .findElement(By.xpath("/html/body/div[1]/section/div/h1"));
		 */
		WebElement UserName = driver.findElement(By.name("email"));

		return UserName;
	}
	
	public void SetPassword(String Password) {
		WebElement GetPassword= this.GetPassword();
		GetPassword.clear();
		GetPassword.sendKeys(Password);
	}
	
	public void SetUserName(String UserName) {
		WebElement userName= this.GetUserName();
		userName.clear();
		userName.sendKeys(UserName);
	}

	public WebElement GetPassword() {
		WebElement UserName = driver.findElement(By.name("password"));

		return UserName;

	}
	public WebElement GetLoginButton() {
		//WebElement LoginBtn=driver.findElement(By.xpath("/html/body/div[1]/section/div/form/ul/li[3]/input[2]"));
		WebElement LoginBtn=driver.findElement(By.xpath("/html/body/div[1]/section/div/form/ul/li[3]/input"));
		return LoginBtn;
	}
	public WebElement GetLoginErrorMessage() {
		WebElement ErrorMessage=driver.findElement(By.className("error"));
		return ErrorMessage;
	}
	@AfterMethod
	public void testIT(ITestResult result) throws Exception {
		if (ITestResult.FAILURE == result.getStatus()) {
			String screenShotPath = utility.captureScreenshot(driver, result.getName());
			// System.out.println("screenshot path:"+screenShotPath);
			String image = logger.addScreenCapture(screenShotPath);
			logger.log(LogStatus.FAIL, result.getName(), image);
		}

		report.endTest(logger);
		report.flush();
		// driver.quit();
	}
	

}
