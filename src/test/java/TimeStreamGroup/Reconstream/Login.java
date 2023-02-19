package TimeStreamGroup.Reconstream;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import EventBuizz.EventBuizz.Configuration;
import EventBuizz.EventBuizz.utility;
import junit.framework.Assert;

public class Login extends Configuration {

	@Test(priority = 1)
	public void ValidateThePageTitle() {
 // String expectedresult="abc";
		logger = report.startTest("VerifyPageTitle");
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		logger.log(LogStatus.INFO, "Application is up and running");
		String title = driver.getTitle();
		//title="abc3433";
		 System.out.print("Link Text:"+ title);
		 logger.log(LogStatus.INFO, "Page Title: "+title);
		Assert.assertTrue(title.contains("ReconSTREAM"));
		logger.log(LogStatus.PASS, "Title verified");
	}
	@Test(priority = 2)

	public void ValidateThatSignInHeadingPersistOnTheLoginPage() {
		logger = report.startTest("Validate That 'Sign in' Text persist on the Login page");
		String ExpectedResult = "Sign in";
		Assert.assertEquals(ExpectedResult, this.GetSigninHeading());
		logger.log(LogStatus.PASS, "'Sign in' Text Verified On the Login page");
	}
	
	@Test(priority = 3)

	public void ValidateThatLoginTextPersistOnTheLoginPage() {
		logger = report.startTest("Validate That 'Login ' Text persist on the Login page");
		String ExpectedResult = "Login to access your account.";
		Assert.assertEquals(ExpectedResult, this.GetLoginText());
		logger.log(LogStatus.PASS, "'Login to access your account.' Text Verified On the Login page");
	}
	@Test(priority = 4)

	public void ValidateUserNameFieldPersistOnTheLoginPage() {
		logger = report.startTest("Validate That On The Login Page , User Name Field Exist");
		WebElement UserName = this.GetUserName();
		Assert.assertTrue(UserName.isDisplayed());
		logger.log(LogStatus.PASS, "'User Name' Field Exist on the Login page");
	}
	@Test(priority = 5)
	public void ValidatePasswordFieldPersistOnTheLoginPage() {
		logger = report.startTest("Validate That On The Login Page , Password Field Exist");
		WebElement Password = this.GetPassword();
		Assert.assertTrue(Password.isDisplayed());
		logger.log(LogStatus.PASS, "'Password' Field Exist on the Login page");

	}
	@Test(priority = 6)
	public void ValidateForGotPasswordPersistOnTheLoginPage() {
		logger = report.startTest("Validate That On The Login Page , For Got Password Link Exist");
		//*[@id="add-notes"]/div/div[3]/a/label
		WebElement ForGotPassword = this.GetForgotPassword();
		Assert.assertTrue(ForGotPassword.isDisplayed());
		logger.log(LogStatus.PASS, "'Forgot Password' Link Exist on the Login page");

	}
	@Test(priority = 7)
	public void ValidateSigInButtonPersistOnTheLoginPage() {
		logger = report.startTest("Validate That On The Login Page , Sign In button Exist");
		
		WebElement ForGotPassword = this.GetSignInButton();
		Assert.assertTrue(ForGotPassword.isDisplayed());
		logger.log(LogStatus.PASS, "'Sign In' Button Exist on the Login page");

	}
	@Test(priority = 8)
	public void ValidateUserNameFieldIsEnabled() {
		logger = report.startTest("Validate That On The Login Page , Email Field is Enabled");
		
		WebElement GetUserName = this.GetUserName();
		Assert.assertTrue(GetUserName.isEnabled());
		logger.log(LogStatus.PASS, "Email Field is Enabled");

	}
	@Test(priority = 9)
	public void ValidatePasswordFieldEnabled() {
		logger = report.startTest("Validate That On The Login Page , Password Field is Enabled");
		
		WebElement GetPassword = this.GetPassword();
		Assert.assertTrue(GetPassword.isEnabled());
		logger.log(LogStatus.PASS, "Password Field is Enabled");

	}

	@DataProvider(name = "EmptyString")
	public static Object[][] EmptyString() {

		return new Object[][] {{"",""} };
	}

	@Test(priority = 10, dataProvider = "EmptyString")
	@Parameters({ "UName", "Password" })

	public void ValidateLoginWithEmptyStrtings(String UName, String Password) {
		logger = report.startTest("Validate With Empty Fields user tries to login then system shows Correct Error message on the screen");
		String ExpectedResultEmail="Email is required *";
		String ExpectedResultPassword="password is required *";
		SetUserName(UName);
		logger.log(LogStatus.INFO, "'Enter User Name = "+UName);
		SetPassword(Password);
		logger.log(LogStatus.INFO, "'Enter Password = "+Password);
		this.GetSignInButton();
		WebElement loginBtn=this.GetSignInButton();
		loginBtn.click();
		logger.log(LogStatus.INFO, "'Click On The Login Button");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement EmailerrorMessage=GetUserNameErrorMessage();
		WebElement PasswordErrorMessage=GetPasswordErrorMessage();
		
	    Assert.assertEquals(ExpectedResultPassword,PasswordErrorMessage.getText() );
		//System.out.println("error message text"+errorMessage.getText());
		Assert.assertEquals(ExpectedResultEmail,EmailerrorMessage.getText() );
		
		logger.log(LogStatus.PASS, "Test case Pass:When User Tries with empty fields ,then system shows an Error messag on the screen");
		
	}
	@DataProvider(name = "EmptyPasswordField")
	public static Object[][] EmptyPasswordField() {

		return new Object[][] {{"ch.mudassar.munir@gmail.com",""} };
	}

	@Test(priority = 11, dataProvider = "EmptyPasswordField")
	@Parameters({ "UName", "Password" })

	public void ValidateLoginWithEmptyUserName(String UName, String Password) throws InterruptedException {
		logger = report.startTest("Validate when user enters only Username without Password then system shows correct Error message On the login screen");
		String ExpectedResultPassword="password is required *";
		SetUserName(UName);
		logger.log(LogStatus.INFO, "'Enter User Name = "+UName);
		SetPassword(Password);
		logger.log(LogStatus.INFO, "'Enter Password = "+Password);
		Thread.sleep(1000);
		this.GetSignInButton();
		WebElement loginBtn=this.GetSignInButton();
		loginBtn.click();
		logger.log(LogStatus.INFO, "'Click On The Login Button ");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement errorMessage=GetPasswordErrorMessage();
		//System.out.println("error message text"+errorMessage.getText());
		Assert.assertEquals(ExpectedResultPassword,errorMessage.getText());
		logger.log(LogStatus.PASS, "Test case Pass:When User tries to Login with Only UserName then system shows Correct Error Message on the Login Screen");
		
		
	}
	@DataProvider(name = "EmptyUserNameField")
	public static Object[][] EmptyUserName() {

		return new Object[][] {{"", "12345678"} };
	}

	@Test(priority = 12, dataProvider = "EmptyUserNameField")
	@Parameters({ "UName", "Password" })

	public void ValidateLoginWithEmpPassword (String UName, String Password) {
		logger = report.startTest("Validate when user enters only Password withOut User Name then system shows correct Error message On the login screen");
		String ExpectedResult="Email is required *";
		SetUserName(UName);
		logger.log(LogStatus.INFO, "'Enter User Name = "+UName);
		SetPassword(Password);
		logger.log(LogStatus.INFO, "'Enter Password = "+Password);
		this.GetSignInButton();
		WebElement loginBtn=this.GetSignInButton();
		loginBtn.click();
		logger.log(LogStatus.INFO, "'Click On The Login Button");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement errorMessage=GetUserNameErrorMessage();
		//System.out.println("error message text"+errorMessage.getText());
		Assert.assertEquals(ExpectedResult,errorMessage.getText() );
		logger.log(LogStatus.PASS, "Test case Pass:When User tries to Login with Only Password then system shows Correct Error Message on the Login Screen");
		}
	@DataProvider(name = "Authentication")
	public static Object[][] credentials() {

		return new Object[][] { { "Test@mailinator.com", "test" },{"mudassar.munir@timestreamgroup.com", "test"},{"Test@mailinator.com", "Password1!"} };
	}

	@Test(priority = 13, dataProvider = "Authentication")
	@Parameters({ "UName", "Password" })
	public void ValidateLoginWithWrongCombination(String UName, String Password) throws InterruptedException {
		logger = report.startTest("Validate That when user send incorrect password , system shows correct error message and User remains on the login page");
		String ExpectedResult="Invalid Username or Password";
		
		SetUserName(UName);
		logger.log(LogStatus.INFO, "'Enter User Name = "+UName);
		SetPassword(Password);
		logger.log(LogStatus.INFO, "'Enter Password = "+Password);
		Thread.sleep(1000);
		this.GetSignInButton();
		WebElement loginBtn=this.GetSignInButton();
		loginBtn.click();
		logger.log(LogStatus.INFO, "'Click On The Login Button ");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement errorMessage=GetErrorMessage();
		//System.out.println("error message text"+errorMessage.getText());
		Assert.assertEquals(ExpectedResult,errorMessage.getText() );
		
		logger.log(LogStatus.PASS, "Test case Pass:When User enters wrong combination of lgin then Correct Error message is displayed on the Login page");
		
	}
	@Parameters({ "UName", "Password"})
	
	  @Test(priority = 14) public void ValidateLoginWithCorrectUserNameAndPassword(String UName, String Password) throws InterruptedException
	  {
		  logger = report.startTest("Validate when user enters Correct Login credentials then system Login successfully");
		  String ExpectedResult="Summary Dashboard";
		  SetUserName(UName);
			logger.log(LogStatus.INFO, "'Enter User Name = "+UName);
			SetPassword(Password);
			logger.log(LogStatus.INFO, "'Enter Password = "+Password);
			this.GetSignInButton();
			WebElement loginBtn=this.GetSignInButton();
			//System.out.println("before click");
			loginBtn.click();
			//System.out.println("after click");
			logger.log(LogStatus.INFO, "'Click On The Login Button");
			Thread.sleep(2000);
			
			WebElement summaryDashboard=GetSummaryDashbaord();
			logger.log(LogStatus.INFO, "'Actual Results="+summaryDashboard.getText());
			//System.out.println("error message text"+errorMessage.getText());
			Assert.assertEquals(ExpectedResult,summaryDashboard.getText());
			logger.log(LogStatus.PASS, "Test case Pass:when user enters correct userName And Password then User land on the dashbaord screen successfully");
	  
	  }
	
	public  WebDriverWait  GetAdministration() {
		WebDriverWait Administrator = new WebDriverWait(driver,30);
		Administrator.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]//*[text()='Administration']")));
		return Administrator;
	}
	public void scrolldown() throws InterruptedException{
		Thread.sleep(2000);
		 //  JavascriptExecutor js = (JavascriptExecutor) driver;
	       //js.executeScript("window.scrollBy(0,350)", "");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement Element = driver.findElement(By.xpath("//*[@id=\"root\"]//*[text()='Branch']"));
		
        // Scrolling down the page till the element is found		
        js.executeScript("arguments[0].scrollIntoView();", Element);

	}
	public WebElement GetSummaryDashbaord() {
		WebElement GetSummaryDashbaord= driver.findElement(By.xpath("//*[@id=\"root\"]//*[text()='Summary Dashboard']"));
		//*[@id="add-notes"]//*[text(),'']

		return GetSummaryDashbaord;

	}

	 
	public WebElement GetPasswordErrorMessage() {
		WebElement PasswordError = driver.findElement(By.xpath("//*[@id=\"add-notes\"]//*[text()='password is required *']"));
		//*[@id="add-notes"]//*[text(),'']

		return PasswordError;

	}
	public WebElement GetErrorMessage() {
		WebElement SignBtn = driver.findElement(By.xpath("//*[@id=\"add-notes\"]//*[text()='Invalid Username or Password']"));
		//*[@id="add-notes"]//*[text(),'']

		return SignBtn;

	}
	public WebElement GetUserNameErrorMessage() {
		WebElement SignBtn = driver.findElement(By.xpath("//*[@id=\"add-notes\"]//*[text()='Email is required *']"));
		//*[@id="add-notes"]//*[text(),'']

		return SignBtn;

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
	
	public WebElement GetSignInButton() {
		WebElement SignBtn = driver.findElement(By.name("submit"));

		return SignBtn;

	}
	
	public WebElement GetForgotPassword() {
		WebElement Forgot = driver.findElement(By.xpath("//*[@id=\"add-notes\"]//*[text()='Forgot Password?']"));

		return Forgot;

	}

	
	public WebElement GetPassword() {
		WebElement UserName = driver.findElement(By.name("password"));

		return UserName;

	}

	public WebElement GetUserName() {

		/*
		 * WebElement UserName= driver
		 * .findElement(By.xpath("/html/body/div[1]/section/div/h1"));
		 */
		WebElement UserName = driver.findElement(By.name("email"));

		return UserName;
	}
	public String GetLoginText() {

		WebElement Heading = driver.findElement(By.xpath("//*[@id=\"root\"]//*[text()='Login to access your account.']"));
		
		String HeadingText = Heading.getText();

		return HeadingText;
	}
	
	

	public String GetSigninHeading() {
		//*[@id="root"]/div/div/div/div/div[2]/div/div/div/div/h4

		WebElement Heading = driver.findElement(By.xpath("//*[@id=\"root\"]//*[text()='Sign in']"));
		String HeadingText = Heading.getText();

		return HeadingText;
	}
	
	
	@AfterMethod
	public void testIT(ITestResult result) throws Exception {
		if (ITestResult.FAILURE == result.getStatus()) {
			String screenShotPath = utility.captureScreenshot(driver, result.getName());
			// System.out.println("screenshot path:"+screenShotPath);
			String image = logger.addScreenCapture(screenShotPath);
			logger.log(LogStatus.FAIL, result.getName(), image);
		}
		//*[@id="add-notes"]/div/div[4]/div/div/button
		
		
		

		report.endTest(logger);
		report.flush();
		// driver.quit();
	}
	

}
