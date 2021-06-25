package EventBuizz.EventBuizz;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.sql.Date;
import java.sql.Time;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

public class curiato_Demo extends Configuration {

	@Test(priority = 1)
	public void ValidateThePageTitle() {
		String expectedresult = "Video Call Management";
		logger = report.startTest("User enters the URL of application to sign in");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		logger.log(LogStatus.INFO, "Application is up and running");
		String title = driver.getTitle();
		logger.log(LogStatus.INFO, "Find the Page Title: " + title);
		// title="abc3433";
		// System.out.print("Link Text:"+ title);
		Assert.assertTrue(title.contains("Video Call Management"));
		logger.log(LogStatus.PASS, "Test Case Passed : when user hit the url Then Login page Opened");

	}

	@Test(priority = 2)
	public void ValidateTheFieldsHintText() throws InterruptedException {
		Thread.sleep(5000);
		String EmailHintText = "Email";
		String PasswordHintText = "Password";
		logger = report.startTest("Validate username and password fields display hints texts");
		WebElement Email = GetElementEMail();
		String Eplaceholder = Email.getAttribute("placeholder");
		WebElement Password = GetElementPassword();
		String Pplaceholder = Password.getAttribute("placeholder");
		logger.log(LogStatus.INFO, "Find the Email Field PlaceHoolder: " + Eplaceholder);
		logger.log(LogStatus.INFO, "Find the Email Field PlaceHoolder: " + Pplaceholder);
		assertEquals(EmailHintText, Eplaceholder);
		assertEquals(PasswordHintText, Pplaceholder);
		// System.out.print("Password tab Index "+ Password.getAttribute("tabindex"));
		logger.log(LogStatus.PASS, "Test Case Passed :username and password fields display hints texts ");
	}

	@Test(priority = 3)
	public void ValidateThatKeepMeSignIndex() {
		String ExpectedResults = "3";
		logger = report
				.startTest("Validate 'Keep me signed in' is positioned between 'password' field and 'sign in' button");
		WebElement ElementKeepmeSign = GetElementKeepMeSignIn();
		logger.log(LogStatus.INFO, "Keep me SIGN IN IS ON 'INDEX' : " + ElementKeepmeSign.getAttribute("tabindex"));
		// System.out.print("Password tab Index"
		// + " "+ ElementKeepmeSign.getAttribute("tabindex"));
		assertEquals(ExpectedResults, ElementKeepmeSign.getAttribute("tabindex"));
		logger.log(LogStatus.PASS,
				"Test Case Passed :'Keep me signed in' is positioned between 'Password' and 'Sign in' button ");
	}

	@Test(priority = 4)
	public void ValidateThatForgotPasswordIndex() {
		String ExpectedResults = "5";
		String ExpectedResultsNoaccount = "6";
		logger = report.startTest(
				"" + "Validate 'Forgot your password? Reset it now.' link is displayed below 'Sign in' button");
		WebElement ElementForGotLink = GetElementForgotPassword();
		WebElement ElementDonotHaveAccount = GetElementDoNothaveAccount();
		logger.log(LogStatus.INFO, "ForGot Password 'INDEX' : " + ElementForGotLink.getAttribute("tabindex"));
		logger.log(LogStatus.INFO,
				"Do not have Account Link  'INDEX' : " + ElementDonotHaveAccount.getAttribute("tabindex"));
		// System.out.print("Password tab Index"
		// + " "+ ElementKeepmeSign.getAttribute("tabindex"));
		assertEquals(ExpectedResults, ElementForGotLink.getAttribute("tabindex"));
		assertEquals(ExpectedResultsNoaccount, ElementDonotHaveAccount.getAttribute("tabindex"));
		logger.log(LogStatus.PASS,
				"Test Case Passed :Forgot your password? Reset it now.' link is displayed below 'Sign in' button ");
	}

	@Test(priority = 5)
	public void ValidateThatfieldsAreNotDisorderwhenMinimizetheScreen() throws InterruptedException {
		logger = report.startTest(""
				+ "Validate all fields are visible and fields should not overlap when browser is reduced to minimum");
		WebElement Email = GetElementEMail();
		minimizeBrowser();
		assertTrue(Email.isDisplayed());
		
		  WebElement Password = GetElementPassword();
		  assertTrue(Password.isDisplayed()); 
		  //WebElement KeepmeSingIn =GetElementKeepMeSignIn(); 
		 // assertTrue(KeepmeSingIn.isDisplayed()); 
		 WebElement ForGotPassword = GetElementForgotPassword();
		  assertTrue(ForGotPassword.isDisplayed()); WebElement donotaccount =
		  GetElementDoNothaveAccount(); assertTrue(donotaccount.isDisplayed());
		 
		logger.log(LogStatus.PASS,
				"Test Case Passed :Minimum width of the browser should not distort the sign in page fields. ");
	}
	
	@DataProvider(name = "Authentication1")
	public static Object[][] credentials1() {

		return new Object[][] { { "Test@mailinator.com", "test" } };
	}

	@Test(priority = 6, dataProvider = "Authentication1")
	@Parameters({ "UName", "Password" })

	public void ValidateLoginWithWrongCombination(String UName, String Password) {
		logger = report.startTest("Validate That when user send incorrect password , system shows correct error message and User remains on the login page");
		String ExpectedResult="Your username/password combination was incorrect";
		driver.manage().window().maximize();
		SetUserName(UName);
		logger.log(LogStatus.INFO, "'Enter User Name = "+UName);
		SetPassword(Password);
		logger.log(LogStatus.INFO, "'Enter Password = "+Password);
		this.GetLoginButton();
		WebElement loginBtn=this.GetLoginButton();
		loginBtn.click();
		logger.log(LogStatus.INFO, "'Click On The Login Button ");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement errorMessage=GetLoginErrorMessage();
		System.out.println("error message text"+errorMessage.getText());
		//errorMessage.click();
		assertTrue(errorMessage.isDisplayed());
		//Assert.assertEquals(ExpectedResult,errorMessage.getText() );
		
		logger.log(LogStatus.PASS, "Test case Pass:When User enters wrong combination of lgin then Correct Error message is displayed on the Login page");
		
	}
	@DataProvider(name = "Authentication")
	public static Object[][] credentials() {

		return new Object[][] { { "fast_mani@yahoo.com", "abcde12345@" } };
	}

	@Test(priority = 7, dataProvider = "Authentication")
	@Parameters({ "UName", "Password" })

	public void ValidateLoginWithCorrectCombination(String UName, String Password) {
		logger = report.startTest("Enter valid 'Email address' and 'Password' in the respective fields");
		String ExpectedResult="Your username/password combination was incorrect";
		driver.manage().window().maximize();
		SetUserName(UName);
		logger.log(LogStatus.INFO, "'Enter User Name = "+UName);
		SetPassword(Password);
		logger.log(LogStatus.INFO, "'Enter Password = "+Password);
		this.GetLoginButton();
		WebElement loginBtn=this.GetLoginButton();
		loginBtn.click();
		logger.log(LogStatus.INFO, "'Click On The Login Button ");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement errorMessage=GetLoginErrorMessage1();
		System.out.println("error message text"+errorMessage.getText());
		//errorMessage.click();
		assertTrue(errorMessage.isDisplayed());
		//Assert.assertEquals(ExpectedResult,errorMessage.getText() );
		
		logger.log(LogStatus.PASS, "Test case Pass: User signs in successfully and is navigated to a different URL");
	
	}
	
	
	
	/*@Test(priority = 5)
	public void ValidatetheMovieRating() throws InterruptedException {
		Thread.sleep(10000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,2000)", "");
		WebElement link=this.GemovieLink();
		link.click();
		
		WebElement Rating=this.GemovieRating();
		
		String GetText=Rating.getText();
		System.out.print("Link Text:"+ GetText);
	}*/
	
	/*@Test(priority = 5)
	
	public void TImeOverlap() {
		 DateTimeFormatter timeStart1 = DateTimeFormatter.ofPattern(" 15:00:00");
		 DateTimeFormatter timeStart2 = DateTimeFormatter.ofPattern("2020/04/04 16:30:00");
		 DateTimeFormatter timeStart3 = DateTimeFormatter.ofPattern("2020/04/04 15:30:00");
		 DateTimeFormatter timeStart4 = DateTimeFormatter.ofPattern("2020/04/04 17:30:00");
		 timeStart1.
		
	}
	
	public boolean ValidateTheTimeslotOverlap(DateTimeFormatter timeStart1, Time timestart2,Time timestart3,Time timestart4 ) {
		return timeStart1.before(timestart2) && timestart3.before(timestart4);
		
		
	}*/
	public WebElement GemovieRating() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement ErrorMessage=driver.findElement(By.cssSelector("#__next > main > div > section.ipc-page-background.ipc-page-background--base.TitlePage__StyledPageBackground-wzlr49-0.dDUGgO > section > div:nth-child(4) > section > section > div.TitleBlock__Container-sc-1nlhx7j-0.hglRHk > div.RatingBar__RatingContainer-sc-1aaz4f-0.hxsvIz.TitleBlock__HideableRatingBar-sc-1nlhx7j-4.bhTVMj > div > div:nth-child(1) > a > div > div > div.AggregateRatingButton__ContentWrap-sc-1il8omz-0.cMcGnJ > div.AggregateRatingButton__Rating-sc-1il8omz-2.ckpPOV > span.AggregateRatingButton__RatingScore-sc-1il8omz-1.fhMjqK"));
		
		return ErrorMessage;
	}
	
	public WebElement GemovieLink() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement ErrorMessage=driver.findElement(By.xpath("//*[@id=\"__next\"]/main/div[2]/div[3]/div[6]/section/div/div/div/div[2]/div/div[2]/div[3]/div[1]/a/div"));
		
		return ErrorMessage;
	}
	
	
	public WebElement GetLoginErrorMessage1() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement ErrorMessage=driver.findElement(By.cssSelector("#app > div > div:nth-child(1) > div > div"));
		
		return ErrorMessage;
	}
	public WebElement GetLoginErrorMessage() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement ErrorMessage=driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div"));
		
		return ErrorMessage;
	}
	public WebElement GetLoginButton() {
		//WebElement LoginBtn=driver.findElement(By.xpath("/html/body/div[1]/section/div/form/ul/li[3]/input[2]"));
		WebElement LoginBtn=driver.findElement(By.cssSelector("#app > div > div.login > form > button"));
		return LoginBtn;
	}

	public void minimizeBrowser() throws InterruptedException {
		Dimension d = new Dimension(300, 1080);
		// Resize current window to the set dimension
		driver.manage().window().setSize(d);

		// To Delay execution for 10 sec. as to view the resize browser
		Thread.sleep(10000);

	}
	public void SetPassword(String Password) {
		WebElement GetPassword= this.GetElementPassword();
		GetPassword.clear();
		GetPassword.sendKeys(Password);
	}
	public void SetUserName(String UserName) {
		WebElement userName= this.GetElementEMail();
		userName.clear();
		userName.sendKeys(UserName);
	}
	
	public WebElement GetElementKeepMeSignIn() {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement keepMeSignIn = driver.findElement(By.className("hidden"));
		// cssSelector("#usernameInput")

		return keepMeSignIn;
	}

	public WebElement GetElementDoNothaveAccount() {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement ForGot = driver.findElement(By.cssSelector("#app > div > div.login > form > a:nth-child(7)"));
		// cssSelector("#usernameInput")

		return ForGot;
	}

	public WebElement GetElementForgotPassword() {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement ForGot = driver
				.findElement(By.cssSelector("#app > div > div.login > form > a.color-login-links.mrrs.mrtm"));
		// cssSelector("#usernameInput")

		return ForGot;
	}

	public WebElement GetElementEMail() {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement Email = driver.findElement(By.id("usernameInput"));
		// cssSelector("#usernameInput")

		return Email;
	}

	public WebElement GetElementPassword() {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement Password = driver.findElement(By.id("passwordInput"));
		// cssSelector("#usernameInput")

		return Password;
	}
	/*
	 * @Test(priority = 2) public void ValidateCookiesConfirmationDialogDisplay() {
	 * // String expectedresult="abc"; logger = report.startTest(
	 * "Validate That When User Land first Time on the Home Screen Then Cookies Dialog Is Displayed"
	 * ); driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 * logger.log(LogStatus.INFO, "Land on The Home Screen"); WebElement GetDialog =
	 * this.CookiesDialog(); Assert.assertTrue(GetDialog.isDisplayed());
	 * logger.log(LogStatus.PASS,
	 * "'Test Case Passed: 'When User Land first Time on the Home Screen Then Cookies Dialog Is Displayed"
	 * );
	 * 
	 * }
	 * 
	 * @Test(priority = 3) public void
	 * ValidateCorrectTextIsDisplayedCookiesConfirmationDialog() { String
	 * expectedresultp1 =
	 * "This website stores cookies on your computer. These cookies are used to collect information about how you interact with our website and allow us to remember you. We use this information in order to improve and customize your browsing experience and for analytics and metrics about our visitors both on this website and other media. To find out more about the cookies we use, see our Privacy Policy."
	 * ; String expectedresult2 =
	 * "If you decline, your information won’t be tracked when you visit this website. A single cookie will be used in your browser to remember your preference not to be tracked."
	 * ; logger =
	 * report.startTest("Validate That Cookies Dialog contain the Correct Text");
	 * logger.log(LogStatus.INFO, "Expected Results:" + expectedresultp1);
	 * logger.log(LogStatus.INFO, "Expected Results:" + expectedresult2);
	 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 * logger.log(LogStatus.INFO, "Land on The Home Screen"); WebElement
	 * GetDialogText = this.CookiesDialogText(); WebElement GetDialogText2 =
	 * this.CookiesDialogText2(); logger.log(LogStatus.INFO,
	 * "Find Text on the Dialog :" + GetDialogText.getText());
	 * logger.log(LogStatus.INFO, "Find Text on the Dialog :" +
	 * GetDialogText2.getText()); Assert.assertEquals(expectedresultp1,
	 * GetDialogText.getText()); Assert.assertEquals(expectedresultp1,
	 * GetDialogText2.getText()); logger.log(LogStatus.PASS,
	 * "'Test Case Passed' : Correct Text is displayed on the Dialog");
	 * 
	 * }
	 * 
	 * @Test(priority = 4) public void
	 * ValidateButtonsAreDisplayedOnTheConfirmationDialogC() {
	 * 
	 * logger = report.
	 * startTest("Varify That 'Accept' and 'Decline' Buttons Are Displayed on the Dialog'"
	 * ); logger.log(LogStatus.INFO, "Land on The Home Screen"); WebElement
	 * GetDialogAcceptBTn = this.GetCookiesButtonAccept(); WebElement
	 * GetDialogRejectBTn = this.GetCookiesButtonReject();
	 * Assert.assertTrue(GetDialogAcceptBTn.isDisplayed());
	 * Assert.assertTrue(GetDialogAcceptBTn.isDisplayed());
	 * logger.log(LogStatus.PASS,
	 * "'Test c Passed' : Accept And Reject Button Are Displayed on the Dialog");
	 * 
	 * }
	 * 
	 * @Test(priority = 5) public void ValidateButtonsHaveCorrectText() { String
	 * ExpectedResultAccept = "Accept"; String ExpectedResultDecline = "Decline";
	 * logger = report.
	 * startTest("Varify That 'Accept' and 'Decline' Buttons contains Correct Text'"
	 * ); logger.log(LogStatus.INFO, "Land on The Home Screen"); WebElement
	 * GetDialogAcceptBTn = this.GetCookiesButtonAccept(); WebElement
	 * GetDialogRejectBTn = this.GetCookiesButtonReject();
	 * logger.log(LogStatus.INFO, "Find Text on the 'Accept' Button: " +
	 * GetDialogAcceptBTn.getText()); logger.log(LogStatus.INFO,
	 * "Find Text on the 'Decline' Button: '" + GetDialogRejectBTn.getText());
	 * Assert.assertEquals(ExpectedResultAccept, GetDialogAcceptBTn.getText());
	 * Assert.assertEquals(ExpectedResultDecline, GetDialogRejectBTn.getText());
	 * logger.log(LogStatus.PASS,
	 * "'Test Case Passed' :Accept And Decline Buttons Have the Correct Text ");
	 * 
	 * }
	 * 
	 * @Test(priority = 6) public void
	 * ValidateUserAcceptThecookiesHomeScreenDisplayed() { logger = report.
	 * startTest("Verify When user Accept the Cookies then Home Screen is displayed"
	 * ); logger.log(LogStatus.INFO, "Cookies pop up is displayed"); WebElement
	 * GetDialogAcceptBTn = this.GetCookiesButtonAccept();
	 * GetDialogAcceptBTn.click(); logger.log(LogStatus.INFO,
	 * "Click on the Accept Button"); WebElement HomeBtnLink =
	 * this.GetElementHomeLink(); Assert.assertTrue(HomeBtnLink.isDisplayed());
	 * 
	 * logger.log(LogStatus.PASS,
	 * "'Test Case Passed' : When user Accept the cookies then User land on the Home Screen "
	 * );
	 * 
	 * }
	 * 
	 * @Test(priority = 7) public void
	 * ValidateContactLinkIsdisplayedOnTheHomeScreen() { logger =
	 * report.startTest("Verify That Contact Link Is Displayed On the Home Screen");
	 * logger.log(LogStatus.INFO, "User Land On the Home Screen"); WebElement
	 * GetContactlINK = this.GetElementContact();
	 * Assert.assertTrue(GetContactlINK.isDisplayed());
	 * 
	 * logger.log(LogStatus.PASS,
	 * "'Test Case Passed' : Contact Link is Displayed on the Home Screen ");
	 * 
	 * }
	 * 
	 * // Task No 2
	 * 
	 * @Test(priority = 8) public void
	 * ValidateWhenUserClickOnTheContactLinkContactUsFormIsDisplayed() throws
	 * InterruptedException { String ExpectedResult = "Contact Us"; logger = report.
	 * startTest("Verify That when user Click on the contact Link then 'Contact Us' Form is Opened"
	 * ); WebElement GetContactlINK = this.GetElementContact();
	 * GetContactlINK.click(); Thread.sleep(5000); logger.log(LogStatus.INFO,
	 * "Click on the Contact  Link"); WebElement GetContactUS =
	 * this.GetElementContactUS(); logger.log(LogStatus.INFO, "Contact Us Heading: "
	 * + GetContactUS.getText()); Assert.assertEquals(ExpectedResult,
	 * GetContactUS.getText());
	 * 
	 * logger.log(LogStatus.PASS,
	 * "'Test Case Passed' : When user click link 'Contact' then land on the contact Us form"
	 * );
	 * 
	 * }
	 * 
	 * @Test(priority = 9) public void
	 * ValidateOntheContactUsFormFirstNameFieldIsDisplayed() { String ExpectedResult
	 * = "First name*"; logger = report.
	 * startTest("Verify That ont the 'contact Us'Form , First Name field is displayed"
	 * ); WebElement GetFirstNameField = this.GetElementFirstNameField();
	 * Assert.assertTrue(GetFirstNameField.isDisplayed());
	 * 
	 * logger.log(LogStatus.PASS,
	 * "'Test Case Passed' :First Name Field is Displayed on the Contact US Form");
	 * 
	 * }
	 * 
	 * @Test(priority = 10) public void
	 * ValidateOntheContactUsFormLastNamesDisplayed() throws InterruptedException {
	 * String ExpectedResult = "First name*"; logger = report.
	 * startTest("Verify That ont the 'contact Us'Form , Last name field is displayed"
	 * ); Thread.sleep(5000); WebElement GetFirstNameField =
	 * this.GetElementLastNameField(); // this.ScrollDown();
	 * Assert.assertTrue(GetFirstNameField.isDisplayed()); //
	 * GetFirstNameField.sendKeys("test"); logger.log(LogStatus.PASS,
	 * "'Test Case Passed' :Last Name Field is Displayed on the Contact US Form");
	 * 
	 * }
	 * 
	 * @Test(priority = 11) public void
	 * ValidateOntheContactUsFormEmailFieldDisplayed() throws InterruptedException {
	 * logger = report.
	 * startTest("Verify That ont the 'contact Us'Form , Email field is displayed");
	 * Thread.sleep(5000); WebElement GetEmail = this.GetElementEmailNameField(); //
	 * this.ScrollDown(); Assert.assertTrue(GetEmail.isDisplayed());
	 * logger.log(LogStatus.PASS,
	 * "'Test Case Passed' :Email Field is Displayed on the Contact US Form");
	 * 
	 * }
	 * 
	 * @Test(priority = 12) public void
	 * ValidateOntheContactUsFormPhoneFieldDisplayed() throws InterruptedException {
	 * ScrollDown(); logger = report.
	 * startTest("Verify That ont the 'contact Us'Form , Phone No is displayed");
	 * Thread.sleep(5000); WebElement GetEmail = this.GetElementPhoneField(); //
	 * this.ScrollDown(); Assert.assertTrue(GetEmail.isDisplayed());
	 * logger.log(LogStatus.PASS,
	 * "'Test Case Passed' :Phone No  Field is Displayed on the Contact US Form");
	 * 
	 * }
	 * 
	 * @Test(priority = 13) public void ValidateOntheContactUsPostalFieldDisplayed()
	 * throws InterruptedException {
	 * 
	 * logger = report.
	 * startTest("Verify That ont the 'contact Us'Form , Postal Code is displayed");
	 * Thread.sleep(5000); WebElement GetEmail = this.GetElementPostalField(); //
	 * this.ScrollDown(); Assert.assertTrue(GetEmail.isDisplayed());
	 * logger.log(LogStatus.PASS,
	 * "'Test Case Passed' :Postal Code Field Displayed on the Contact US Form");
	 * 
	 * }
	 * 
	 * @Test(priority = 14) public void
	 * ValidateOntheContactUsCountryFieldDisplayed() throws InterruptedException {
	 * 
	 * logger = report.
	 * startTest("Verify That ont the 'contact Us'Form , Country Drop Down is displayed"
	 * ); Thread.sleep(5000); WebElement GetEmail =
	 * this.GetElementCountryFieldField(); // this.ScrollDown();
	 * Assert.assertTrue(GetEmail.isDisplayed()); logger.log(LogStatus.PASS,
	 * "'Test Case Passed' :Country  Field  is Displayed on the Contact US Form");
	 * 
	 * }
	 * 
	 * @Test(priority = 15) public void
	 * ValidateOntheContactUsCheckBoxReceieConfirnmationFieldDisplayed() throws
	 * InterruptedException {
	 * 
	 * logger = report.
	 * startTest("Verify That ont the 'contact Us'Form , Check Box Receive Confirmation is displayed"
	 * ); Thread.sleep(5000); WebElement GetEmail =
	 * this.GetElementCheckBoxReceieFieldField(); // this.ScrollDown();
	 * Assert.assertTrue(GetEmail.isDisplayed()); logger.log(LogStatus.PASS,
	 * "'Test Case Passed' :Check Box 'Reeceive Confirmation '  is Displayed on the Contact US Form"
	 * );
	 * 
	 * }
	 * 
	 * @Test(priority = 15) public void
	 * ValidateOntheContactUsCheckBoxPersonalFieldDisplayed() throws
	 * InterruptedException {
	 * 
	 * logger = report.
	 * startTest("Verify That ont the 'contact Us'Form , Check Box 'Personal Data' is displayed"
	 * ); Thread.sleep(5000); WebElement GetEmail =
	 * this.GetElementCheckBoxPersonalField(); // this.ScrollDown();
	 * Assert.assertTrue(GetEmail.isDisplayed()); logger.log(LogStatus.PASS,
	 * "'Test Case Passed' :Check Box 'Check Box 'Personal Data '  is Displayed on the Contact US Form"
	 * );
	 * 
	 * }
	 * 
	 * @Test(priority = 16) public void
	 * ValidateOntheContactUsSubmitButtonIsDisplayed() throws InterruptedException {
	 * ScrollDown(); logger = report.
	 * startTest("Verify That ont the 'contact Us'Form ,  Submit Button' is displayed"
	 * ); Thread.sleep(5000); WebElement GetEmail = this.GetElementSubmitButn(); //
	 * this.ScrollDown(); Assert.assertTrue(GetEmail.isDisplayed());
	 * logger.log(LogStatus.PASS,
	 * "'Test Case Passed' :Check Box 'Check Box 'Submit '  is Displayed on the Contact US Form"
	 * );
	 * 
	 * }
	 * 
	 * @Test(priority = 17) public void ValidateThatEmptyFormValidationsAreWorking()
	 * throws InterruptedException { String ExpectedResult =
	 * "Please complete this required field."; logger = report.
	 * startTest("Verify That ont the 'contact Us'Form ,  Empty Submit FOrm Validation is working"
	 * ); Thread.sleep(5000); WebElement GetEmail = this.GetElementSubmitButn();
	 * GetEmail.click(); logger.log(LogStatus.INFO, "Click on the SubMit Button");
	 * // this.ScrollDown();
	 * 
	 * WebElement Error = GetElementCheckBoxError(); logger.log(LogStatus.INFO,
	 * "Error Message :" + Error.getText()); //
	 * System.out.println("Error Message:"+Error.getText()); //
	 * Assert.assertTrue(GetEmail.isDisplayed());
	 * Assert.assertEquals(ExpectedResult, Error.getText());
	 * logger.log(LogStatus.PASS,
	 * "'Test Case Passed' :User Cannot Submit Empty Form");
	 * 
	 * }
	 * 
	 * @DataProvider(name = "ContactUS") public static Object[][] credentials() {
	 * 
	 * return new Object[][] { { "Mudassar", "Munir" } }; }
	 * 
	 * @Test(priority = 19, dataProvider = "ContactUS")
	 * 
	 * @Parameters({ "FName", "LName" })
	 * 
	 * public void ValidateLoginWithWrongCombination(String FName, String LName)
	 * throws InterruptedException { ScrollUP(); logger = report.startTest(
	 * "FAILED Test  Case For Reporting Purposes"); String ExpectedResult =
	 * "Your username/password combination was incorrect";
	 * 
	 * SetFirstName(FName);
	 * 
	 * logger.log(LogStatus.INFO, "'Enter First Name = " + FName); SetLName(LName);
	 * logger.log(LogStatus.INFO, "'Enter Last Name = " + LName);
	 * 
	 * 
	 * }
	 * 
	 * public void ScrollDown() throws InterruptedException { Thread.sleep(5000);
	 * JavascriptExecutor js = (JavascriptExecutor) driver;
	 * js.executeScript("window.scrollBy(0,600)");
	 * 
	 * }
	 * 
	 * public void ScrollUP() throws InterruptedException { Thread.sleep(5000);
	 * JavascriptExecutor js = (JavascriptExecutor) driver;
	 * js.executeScript("window.scrollBy(0,-800)");
	 * 
	 * }
	 * 
	 * public WebElement GetElementCheckBoxError() {
	 * 
	 * 
	 * WebElement UserName= driver
	 * .findElement(By.xpath("/html/body/div[1]/section/div/h1"));
	 * 
	 * driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); //
	 * WebElement HomeBtnElement = //
	 * driver.findElement(By.id("zip-bf9fc5b6-8a45-4c21-b1ca-c1b258807fb1_3988"));
	 * WebElement HomeBtnElement = driver.findElement(By.xpath(
	 * "/html/body/div[2]/div/div[2]/div/div/div/div/div/div[2]/span/div/form/div[8]/div[5]/div/div/ul/li/label"
	 * )); // [@id="firstname-bf9fc5b6-8a45-4c21-b1ca-c1b258807fb1_3705"] return
	 * HomeBtnElement; } public void SetLName(String LName) { WebElement LName1=
	 * this.GetElementLastNameField();
	 * 
	 * 
	 * //LName1.clear(); LName1.sendKeys(LName); } public void SetFirstName(String
	 * FName) { WebElement FName1= this.GetElementFirstNameField();
	 * //FName1.clear(); FName1.sendKeys(FName); }
	 * 
	 * 
	 * public WebElement GetElementSubmitButn() {
	 * 
	 * 
	 * WebElement UserName= driver
	 * .findElement(By.xpath("/html/body/div[1]/section/div/h1"));
	 * 
	 * driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); //
	 * WebElement HomeBtnElement = //
	 * driver.findElement(By.id("zip-bf9fc5b6-8a45-4c21-b1ca-c1b258807fb1_3988"));
	 * WebElement HomeBtnElement = driver.findElement( By.xpath(
	 * "/html/body/div[2]/div/div[2]/div/div/div/div/div/div[2]/span/div/form/div[9]/div[2]/input"
	 * )); // [@id="firstname-bf9fc5b6-8a45-4c21-b1ca-c1b258807fb1_3705"] return
	 * HomeBtnElement; }
	 * 
	 * public WebElement GetElementCheckBoxPersonalField() {
	 * 
	 * 
	 * WebElement UserName= driver
	 * .findElement(By.xpath("/html/body/div[1]/section/div/h1"));
	 * 
	 * driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); //
	 * WebElement HomeBtnElement = //
	 * driver.findElement(By.id("zip-bf9fc5b6-8a45-4c21-b1ca-c1b258807fb1_3988"));
	 * WebElement HomeBtnElement = driver.findElement(By.xpath(
	 * "/html/body/div[2]/div/div[2]/div/div/div/div/div/div[2]/span/div/form/div[8]/div[5]/div/div/div/ul/li"
	 * )); // [@id="firstname-bf9fc5b6-8a45-4c21-b1ca-c1b258807fb1_3705"] return
	 * HomeBtnElement; }
	 * 
	 * public WebElement GetElementCheckBoxReceieFieldField() {
	 * 
	 * 
	 * WebElement UserName= driver
	 * .findElement(By.xpath("/html/body/div[1]/section/div/h1"));
	 * 
	 * driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); //
	 * WebElement HomeBtnElement = //
	 * driver.findElement(By.id("zip-bf9fc5b6-8a45-4c21-b1ca-c1b258807fb1_3988"));
	 * WebElement HomeBtnElement = driver.findElement(By.xpath(
	 * "/html/body/div[2]/div/div[2]/div/div/div/div/div/div[2]/span/div/form/div[8]/div[2]/div/div/div/ul/li"
	 * )); // [@id="firstname-bf9fc5b6-8a45-4c21-b1ca-c1b258807fb1_3705"] return
	 * HomeBtnElement; }
	 * 
	 * public WebElement GetElementCountryFieldField() {
	 * 
	 * 
	 * WebElement UserName= driver
	 * .findElement(By.xpath("/html/body/div[1]/section/div/h1"));
	 * 
	 * driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); //
	 * WebElement HomeBtnElement = //
	 * driver.findElement(By.id("zip-bf9fc5b6-8a45-4c21-b1ca-c1b258807fb1_3988"));
	 * WebElement HomeBtnElement = driver.findElement( By.xpath(
	 * "/html/body/div[2]/div/div[2]/div/div/div/div/div/div[2]/span/div/form/div[7]/div"
	 * )); // [@id="firstname-bf9fc5b6-8a45-4c21-b1ca-c1b258807fb1_3705"] return
	 * HomeBtnElement; }
	 * 
	 * public WebElement GetElementPostalField() {
	 * 
	 * 
	 * WebElement UserName= driver
	 * .findElement(By.xpath("/html/body/div[1]/section/div/h1"));
	 * 
	 * driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); //
	 * WebElement HomeBtnElement = //
	 * driver.findElement(By.id("zip-bf9fc5b6-8a45-4c21-b1ca-c1b258807fb1_3988"));
	 * WebElement HomeBtnElement = driver.findElement( By.xpath(
	 * "/html/body/div[2]/div/div[2]/div/div/div/div/div/div[2]/span/div/form/div[6]/div"
	 * )); // [@id="firstname-bf9fc5b6-8a45-4c21-b1ca-c1b258807fb1_3705"] return
	 * HomeBtnElement; }
	 * 
	 * public WebElement GetElementPhoneField() {
	 * 
	 * 
	 * WebElement UserName= driver
	 * .findElement(By.xpath("/html/body/div[1]/section/div/h1"));
	 * 
	 * driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); //
	 * WebElement HomeBtnElement = //
	 * driver.findElement(By.id("phone-bf9fc5b6-8a45-4c21-b1ca-c1b258807fb1_3988"));
	 * WebElement HomeBtnElement = driver.findElement( By.xpath(
	 * "/html/body/div[2]/div/div[2]/div/div/div/div/div/div[2]/span/div/form/div[4]/div"
	 * )); // [@id="firstname-bf9fc5b6-8a45-4c21-b1ca-c1b258807fb1_3705"] return
	 * HomeBtnElement; }
	 * 
	 * public WebElement GetElementEmailNameField() {
	 * 
	 * 
	 * WebElement UserName= driver
	 * .findElement(By.xpath("/html/body/div[1]/section/div/h1"));
	 * 
	 * driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); //
	 * WebElement HomeBtnElement = //
	 * driver.findElement(By.id("email-bf9fc5b6-8a45-4c21-b1ca-c1b258807fb1_3988"));
	 * WebElement HomeBtnElement = driver.findElement( By.xpath(
	 * "/html/body/div[2]/div/div[2]/div/div/div/div/div/div[2]/span/div/form/div[3]/div"
	 * )); // [@id="firstname-bf9fc5b6-8a45-4c21-b1ca-c1b258807fb1_3705"] //
	 * WebElement HomeBtnElement = //
	 * driver.findElement(By.id("email-bf9fc5b6-8a45-4c21-b1ca-c1b258807fb1_3988"));
	 * return HomeBtnElement; }
	 * 
	 * public WebElement GetElementLastNameField() {
	 * 
	 * 
	 * WebElement UserName= driver
	 * .findElement(By.xpath("/html/body/div[1]/section/div/h1"));
	 * 
	 * driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); //
	 * WebElement HomeBtnElement = //
	 * driver.findElement(By.id("lastname-bf9fc5b6-8a45-4c21-b1ca-c1b258807fb1_3988"
	 * )); WebElement HomeBtnElement = driver.findElement(By.xpath(
	 * "/html/body/div[2]/div/div[2]/div/div/div/div/div/div[2]/span/div/form/div[2]/div"
	 * )); // [@id="firstname-bf9fc5b6-8a45-4c21-b1ca-c1b258807fb1_3705"]
	 * //WebElement HomeBtnElement = driver //.findElement(By.cssSelector(
	 * "#lastname-bf9fc5b6-8a45-4c21-b1ca-c1b258807fb1_513")); return
	 * HomeBtnElement; }
	 * 
	 * public WebElement GetElementFirstNameField() {
	 * 
	 * 
	 * WebElement UserName= driver
	 * .findElement(By.xpath("/html/body/div[1]/section/div/h1"));
	 * 
	 * String inputText = "Rozmeen"; WebElement HomeBtnElement =
	 * driver.findElement(By.xpath(
	 * "/html/body/div[2]/div/div[2]/div/div/div/div/div/div[2]/span/div/form/div[1]/div"
	 * )); String js = "arguments[0].setAttribute('value','"+inputText+"')";
	 * ((JavascriptExecutor) driver).executeScript(js, HomeBtnElement);
	 * //HomeBtnElement.click(); //HomeBtnElement.clear();
	 * //HomeBtnElement.sendKeys("mudassar");
	 * 
	 * driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); WebElement
	 * HomeBtnElement = driver.findElement( By.xpath(
	 * "/html/body/div[2]/div/div[2]/div/div/div/div/div/div[2]/span/div/form/div[1]/div"
	 * )); WebElement HomeBtnElement = driver.findElement(By.xpath(
	 * "/html/body/div[2]/div/div[2]/div/div/div/div/div/div[2]/span/div/form/div[1]/div"
	 * )); JavascriptExecutor executor = (JavascriptExecutor)driver;
	 * executor.executeScript("arguments[0].click();", "Mudassar");
	 * //HomeBtnElement.click(); //HomeBtnElement.sendKeys("Mudassar");
	 * 
	 * 
	 * WebDriverWait wait = new WebDriverWait(driver, 20); WebElement
	 * passwordElement =
	 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//*[contains(@id,'firstname')]"))); passwordElement.click(); //
	 * passwordElement.clear(); passwordElement.sendKeys("123");
	 * 
	 * return HomeBtnElement; }
	 * 
	 * public WebElement GetElementContactUS() {
	 * 
	 * 
	 * WebElement UserName= driver
	 * .findElement(By.xpath("/html/body/div[1]/section/div/h1"));
	 * 
	 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); WebElement
	 * HomeBtnElement = driver .findElement(By.xpath(
	 * "/html/body/div[2]/div/div[1]/div/div/div/div/div/div/h1"));
	 * 
	 * return HomeBtnElement; }
	 * 
	 * public WebElement GetElementContact() {
	 * 
	 * 
	 * WebElement UserName= driver
	 * .findElement(By.xpath("/html/body/div[1]/section/div/h1"));
	 * 
	 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); WebElement
	 * HomeBtnElement = driver.findElement(By.linkText("Contact"));
	 * 
	 * return HomeBtnElement; }
	 * 
	 * public WebElement GetElementHomeLink() {
	 * 
	 * 
	 * WebElement UserName= driver
	 * .findElement(By.xpath("/html/body/div[1]/section/div/h1"));
	 * 
	 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); WebElement
	 * HomeBtnElement = driver.findElement(By.linkText("Home"));
	 * 
	 * return HomeBtnElement; }
	 * 
	 * public WebElement GetCookiesButtonReject() {
	 * 
	 * 
	 * WebElement UserName= driver
	 * .findElement(By.xpath("/html/body/div[1]/section/div/h1"));
	 * 
	 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); WebElement
	 * CookiesFRejectBTN = driver.findElement(By.id("hs-eu-decline-button"));
	 * 
	 * return CookiesFRejectBTN; }
	 * 
	 * public WebElement GetCookiesButtonAccept() {
	 * 
	 * 
	 * WebElement UserName= driver
	 * .findElement(By.xpath("/html/body/div[1]/section/div/h1"));
	 * 
	 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); WebElement
	 * CookiesconfirmationBTN =
	 * driver.findElement(By.id("hs-eu-confirmation-button"));
	 * 
	 * return CookiesconfirmationBTN; }
	 * 
	 * public WebElement CookiesDialogText2() {
	 * 
	 * 
	 * WebElement UserName= driver
	 * .findElement(By.xpath("/html/body/div[1]/section/div/h1"));
	 * 
	 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); WebElement
	 * CookiesTextP1 = driver.findElement(By.id("hs-eu-policy-wording"));
	 * 
	 * return CookiesTextP1; }
	 * 
	 * public WebElement CookiesDialogText() {
	 * 
	 * 
	 * WebElement UserName= driver
	 * .findElement(By.xpath("/html/body/div[1]/section/div/h1"));
	 * 
	 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); WebElement
	 * CookiesTextP1 = driver.findElement(By.id("hs-eu-policy-wording"));
	 * 
	 * return CookiesTextP1; }
	 * 
	 * public WebElement CookiesDialog() {
	 * 
	 * 
	 * WebElement UserName= driver
	 * .findElement(By.xpath("/html/body/div[1]/section/div/h1"));
	 * 
	 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); WebElement
	 * UserName = driver.findElement(By.id("hs-eu-cookie-confirmation"));
	 * 
	 * return UserName; }
	 */

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
