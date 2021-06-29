package EventBuizz.EventBuizz;
import java.lang.*;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import org.apache.tools.ant.types.*;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.apache.tools.ant.types.*;

import org.apache.tools.ant.types.*;

import com.google.common.base.Verify;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

public class RegSite_generalRegSiteSettings extends Configuration {
	
private static final ITestResult FAIL = null;
private static final ITestResult FAILURE = null;
public String TotalTickets="100";
public int TEventAttendees;

	/**
 * @return the eventAttendees
 */
public int getEventAttendees() {
	return TEventAttendees;
}

/**
 * @param eventAttendees the eventAttendees to set
 */
public void setEventAttendees(int eventAttendees) {
	TEventAttendees = eventAttendees;
}

	/**
 * @return the totalTickets
 */
public String getTotalTickets() {
	return TotalTickets;
}

/**
 * @param totalTickets the totalTickets to set
 */
public void setTotalTickets(String totalTickets) {
	TotalTickets = totalTickets;
}

	@Test(priority = 1)
	public void OpenRegistrationSiteSettings() throws InterruptedException {
		String ExpetedResult = "Registration site settings";
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		logger = report.startTest(
				"Verify That when user click on the Button 'Reg Site' then general reg siste settings screen OPens successfully");
		WebElement RegsiteBtn = GetRegSiteButton();
		RegsiteBtn.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		logger.log(LogStatus.INFO, "Click On te the Reg Button");
		WebElement HeadingText = this.GetHeading();
		Assert.assertEquals(ExpetedResult, HeadingText.getText());
		logger.log(LogStatus.PASS,
				"Test case Pass:When user Click on the Button 'Reg Site'then general reg site settings screen open sucessfully");

	}

	@Parameters({ "LUName", "LPassword" })

	@Test(priority = 2)
	public void ValidateTheEnableToRegisterWithLinkedinSettings(String LUName, String LPassword)
			throws InterruptedException {
		logger = report.startTest("Verify The the Enable to register with linkedin Settings working correctly");
		WebElement LinikedInToggleBTn = GetRegisterWithLinkedIn();
		if (LinikedInToggleBTn.isSelected() == true) {

			logger.log(LogStatus.INFO, "LinkedIn  ALready Selected");
			OpenRegSite();
			logger.log(LogStatus.INFO, "Open Registeration Site");
			TabSwtichingToRegSite();
			logger.log(LogStatus.INFO, "Switch on the Registeration SIte");
			ScrollDown();
			logger.log(LogStatus.INFO, "Scroll Down To Find the LinkedIn Button");
			WebElement LinkedInBtnOnRegSite = GetRegisterWithLinkedInBTnOnRegSite();
			LinkedInBtnOnRegSite.click();
			logger.log(LogStatus.INFO, "Click On the LinkedIn Button");
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			SetLinkedInUserName(LUName);
			logger.log(LogStatus.INFO, "Enter LinkedIn userName =" + LUName);
			SetLinkedInPassword(LPassword);
			logger.log(LogStatus.INFO, "Enter LinkedIn Passwrord =" + LPassword);
			WebElement LSignIn = GetLinkedSign();
			LSignIn.click();
			Thread.sleep(5000);
			logger.log(LogStatus.INFO, "Click On the SignIn Button =");
			WebElement LinkedInAllow = GetLinkedInAllow();
			LinkedInAllow.click();
			logger.log(LogStatus.INFO, "Click On the Allow button=");
			Thread.sleep(10000);

			WebElement GetEmail = GetEmail();
			String Efiledvalue = GetEmail.getAttribute("value");
			logger.log(LogStatus.INFO, "Email Found On the Page =" + Efiledvalue);
			// System.out.println("Email Text"+Efiledvalue);
			CloseTab();
			Assert.assertEquals(LUName, Efiledvalue);
			logger.log(LogStatus.PASS, "Test case Pass:LinkedIn Data Import successfully");

		} else {

			this.ClickOnLinedInToggleButton();
			logger.log(LogStatus.INFO, "Selecte LinkedIn Button");
			this.ScrollDown();
			logger.log(LogStatus.INFO, "Scrolll Down");
			WebElement savebtn = GetsaveButton();
			savebtn.click();
			logger.log(LogStatus.INFO, "Press Save Button");
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			OpenRegSite();
			logger.log(LogStatus.INFO, "Open Registeration Site");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			TabSwtichingToRegSite();
			logger.log(LogStatus.INFO, "Switch on the Registeration SIte");
			ScrollDown();
			logger.log(LogStatus.INFO, "Scroll Down To Find the LinkedIn Button");
			WebElement LinkedInBtnOnRegSite = GetRegisterWithLinkedInBTnOnRegSite();
			LinkedInBtnOnRegSite.click();
			logger.log(LogStatus.INFO, "Click On the LinkedIn Button");
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			// WebElement LinkedInUserName=GetLinkedInUserName();
			SetLinkedInUserName(LUName);
			logger.log(LogStatus.INFO, "Enter LinkedIn userName =" + LUName);
			SetLinkedInPassword(LPassword);
			logger.log(LogStatus.INFO, "Enter LinkedIn Passwrord =" + LPassword);
			WebElement LSignIn = GetLinkedSign();
			LSignIn.click();
			Thread.sleep(5000);
			logger.log(LogStatus.INFO, "Click On the SignIn Button =");
			WebElement LinkedInAllow = GetLinkedInAllow();
			LinkedInAllow.click();
			logger.log(LogStatus.INFO, "Click On the Allow button=");
			Thread.sleep(1000);
			WebElement GetEmail = GetEmail();
			String Efiledvalue = GetEmail.getAttribute("value");
			logger.log(LogStatus.INFO, "Email Found on  the Page=" + Efiledvalue);
			CloseTab();
			Assert.assertEquals(LUName, Efiledvalue);
			logger.log(LogStatus.PASS, "Test case Pass:LinkedIn Data Import successfully");

		}

	}

	@Test(priority = 3)
	public void ValidateTheDisablefunctionalityRegisteredWithLinkedinSettings() throws InterruptedException {
		logger = report.startTest("Verify That the registered  with linkedin 'Disable' Settings working correctly");
		WebElement LinikedInToggleBTn = GetRegisterWithLinkedIn();
		if (LinikedInToggleBTn.isSelected() == true) {
			logger.log(LogStatus.INFO, "LinkedIn Enabled");
			this.ClickOnLinedInToggleButton();
			logger.log(LogStatus.INFO, "Disable the Linked functionality");
			WebElement savebtn = GetsaveButton();
			savebtn.click();
			logger.log(LogStatus.INFO, "Press Save Button");
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			OpenRegSite();
			logger.log(LogStatus.INFO, "Open Registeration Site");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			TabSwtichingToRegSite();
			logger.log(LogStatus.INFO, "Switch on the Registeration SIte");
			ScrollDown();
			Boolean IsPresentLinkedBtn = IsPersistGetRegisterWithLinkedInBTnOnRegSite();
			logger.log(LogStatus.INFO, "Linked Button Status on the Registeration Site :" + IsPresentLinkedBtn);
			if (IsPresentLinkedBtn == false) {
				CloseTab();
				Assert.assertTrue(IsPresentLinkedBtn == false);
				logger.log(LogStatus.PASS, "Test case Pass:Lined Button Not Found On the Registeration Site");

			} else {
				SoftAssert softAssert = new SoftAssert();
				softAssert.assertTrue(IsPresentLinkedBtn == false);
				CloseTab();
				logger.log(LogStatus.PASS, "Test case Failed:Lined Button Found On the Registeration Site");
			}

		} else {
			logger.log(LogStatus.INFO, "LinkedIn  ALready Disabled");
			OpenRegSite();
			logger.log(LogStatus.INFO, "Open Registeration Site");
			TabSwtichingToRegSite();
			logger.log(LogStatus.INFO, "Switch on the Registeration SIte");
			ScrollDown();
			logger.log(LogStatus.INFO, "Scroll Down To Find the LinkedIn Button");
			Boolean IsPresentLinkedBtn = IsPersistGetRegisterWithLinkedInBTnOnRegSite();
			logger.log(LogStatus.INFO, "Linked Button Status on the Registeration Site :" + IsPresentLinkedBtn);
			if (IsPresentLinkedBtn == false) {
				CloseTab();
				Assert.assertTrue(IsPresentLinkedBtn == false);

				logger.log(LogStatus.PASS, "Test case Pass:Lined Button Not Found On the Registeration Site");
			} else {
				SoftAssert softAssert = new SoftAssert();
				softAssert.assertTrue(IsPresentLinkedBtn == false);
				
				CloseTab();
				logger.log(LogStatus.FAIL, "Test case Failed:Lined Button Found On the Registeration Site");
			}
		}

	}
	@Test(priority = 4)
	public void ValidateEnablefunctionalityShowTickets() throws InterruptedException {
		Thread.sleep(3000);
		logger = report.startTest("Verify That Ebnable Show Ticket Functionaltiy is working correctly ");
		SetTotalTicketsOnEcenter(getTotalTickets());
		ScrollDownForShowingTicketSetting();
		logger.log(LogStatus.INFO, "Scroll Down For the Show ticket settings:" );
		WebElement ShowTicketsLeftToggleBTn = GetShowTicketsLeft();
		if (ShowTicketsLeftToggleBTn.isSelected() == true) {
			logger.log(LogStatus.INFO, "SHow Tickets Left Already Enabled");
			this.ScrollDown();
			logger.log(LogStatus.INFO, "Scrolll Down");
			WebElement savebtn = GetsaveButton();
			savebtn.click();
			OpenAttendeeList();
			logger.log(LogStatus.INFO, "Open Attendee List");
			RegisteredAttendeesCountInTheEvent();
			logger.log(LogStatus.INFO, "Registered Attendee In the Event are : "+getEventAttendees());
			OpenRegistrationSiteSettings();
			logger.log(LogStatus.INFO, "OPen Regisiteration Settings: ");
			OpenRegSite();
			logger.log(LogStatus.INFO, "Open Registeration Site");
			TabSwtichingToRegSite();
			logger.log(LogStatus.INFO, "Switch on the Registeration SIte");
			ScrollDown();
			int TTicketsOnTheRegSite=TotalTicketOnTheRegSite();
			logger.log(LogStatus.INFO, "Total Tickets Found on the Registration site: "+TTicketsOnTheRegSite);
			int Remainingtickets=FindRemainingTicekts();
			System.out.println("Remaning ticekts: "+Remainingtickets);
			logger.log(LogStatus.INFO, "Remaing Tickets in the System: "+Remainingtickets);
			if(TTicketsOnTheRegSite==Remainingtickets) {
				CloseTab();
				SoftAssert softAssert = new SoftAssert();
				softAssert.assertEquals(TTicketsOnTheRegSite, Remainingtickets);
				logger.log(LogStatus.PASS, "Test case Pass:Correct Ticekts are showing on the Registeration Site");
			}
			else {
				SoftAssert softAssert = new SoftAssert();
				softAssert.assertEquals(TTicketsOnTheRegSite, Remainingtickets);
				CloseTab();
				logger.log(LogStatus.FAIL, "Test case Failed:Total Tickets are not Correct showing on the Regiseration Site");
			}
		
		}
		else {
			this.ClickOnShowTicketLeftToggleButton();
			logger.log(LogStatus.INFO, "Selecte total tickets button");
			this.ScrollDown();
			logger.log(LogStatus.INFO, "Scrolll Down");
			WebElement savebtn = GetsaveButton();
			savebtn.click();
			logger.log(LogStatus.INFO, "Click On the save Button");
			OpenAttendeeList();
			logger.log(LogStatus.INFO, "Open Attendees List");
			RegisteredAttendeesCountInTheEvent();
			logger.log(LogStatus.INFO, "Registered Attendee In the Event are : "+getEventAttendees());
			OpenRegistrationSiteSettings();
			logger.log(LogStatus.INFO, "OPen Regisiteration Settings: ");
			OpenRegSite();
			logger.log(LogStatus.INFO, "Open Registeration Site");
			TabSwtichingToRegSite();
			logger.log(LogStatus.INFO, "Switch on the Registeration SIte");
			ScrollDown();
			int TTicketsOnTheRegSite=TotalTicketOnTheRegSite();
			logger.log(LogStatus.INFO, "Total Tickets Found on the Registration site: "+TTicketsOnTheRegSite);
			int Remainingtickets=FindRemainingTicekts();
			//System.out.println("Remaning ticekts: "+Remainingtickets);
			logger.log(LogStatus.INFO, "Remaing Tickets in the System: "+Remainingtickets);
			if(TTicketsOnTheRegSite==Remainingtickets) {
				CloseTab();
				SoftAssert softAssert = new SoftAssert();
				softAssert.assertEquals(TTicketsOnTheRegSite, Remainingtickets);
				logger.log(LogStatus.PASS, "Test case Pass:Correct Ticekts are showing on the Registeration Site");
			}
			else {
				SoftAssert softAssert = new SoftAssert();
				softAssert.assertEquals(TTicketsOnTheRegSite, Remainingtickets);
				CloseTab();
				logger.log(LogStatus.FAIL, "Test case Failed:Total Tickets are not Correct showing on the Regiseration Site");
			}
		
		}
	} 
	@Test(priority = 5)
	public void ValidateTheDisablefunctionalityShowTicketLeft() throws Exception{
		Thread.sleep(3000);
		logger = report.startTest("Verify That Disable Show Ticket Functionaltiy is working correctly ");
		SetTotalTicketsOnEcenter(getTotalTickets());
		ScrollDownForShowingTicketSetting();
		logger.log(LogStatus.INFO, "Scroll Down For the Show ticket settings:" );
		WebElement ShowTicketsLeftToggleBTn = GetShowTicketsLeft();
		if (ShowTicketsLeftToggleBTn.isSelected() == true) {
			logger.log(LogStatus.INFO, "SHow Tickets Left Already Enabled");
			this.ClickOnShowTicketLeftToggleButton();
			logger.log(LogStatus.INFO, "Disable the Show  Tickets Left Functionaltiy");
			this.ScrollDown();
			logger.log(LogStatus.INFO, "Scrolll Down");
			WebElement savebtn = GetsaveButton();
			savebtn.click();
			logger.log(LogStatus.INFO, "OPen Regisiteration Settings: ");
			OpenRegSite();
			logger.log(LogStatus.INFO, "Open Registeration Site");
			TabSwtichingToRegSite();
			logger.log(LogStatus.INFO, "Switch on the Registeration SIte");
			ScrollDown();
			boolean IspersistTotalTickets=IsPersistTotalTicketsOnRegSite();
			IspersistTotalTickets=true;
			if (IspersistTotalTickets == false) {
				CloseTab();
				Assert.assertTrue(IspersistTotalTickets == false);
				logger.log(LogStatus.PASS, "Test case Pass:Tickets are Not showing On the Registeration Site");

			} else {
				SoftAssert softAssert = new SoftAssert();
				softAssert.assertTrue(IspersistTotalTickets == false);
				
				softAssert.assertAll();
				CloseTab();
				//ITestResult result1=FAILURE;
				//this.testIT(result1);
//				Assert.assertTrue(IspersistTotalTickets == false);
				logger.log(LogStatus.FAIL, "Test case Failed:Total Tickets are Found On the Registeration Site");
			}
			}
		else {
			logger.log(LogStatus.INFO, "Selecte total tickets button");
			this.ScrollDown();
			logger.log(LogStatus.INFO, "Scrolll Down");
			WebElement savebtn = GetsaveButton();
			savebtn.click();
			logger.log(LogStatus.INFO, "Click On the save Button"); 
			logger.log(LogStatus.INFO, "OPen Regisiteration Settings: ");
			OpenRegSite();
			logger.log(LogStatus.INFO, "Open Registeration Site");
			TabSwtichingToRegSite();
			logger.log(LogStatus.INFO, "Switch on the Registeration SIte");
			ScrollDown();
			boolean IspersistTotalTickets=IsPersistTotalTicketsOnRegSite();
			 IspersistTotalTickets=true;
			if (IspersistTotalTickets == false) {
				CloseTab();
				Assert.assertTrue(IspersistTotalTickets == false);
				logger.log(LogStatus.PASS,"Test case Pass:Tickets are Not showing On the Registeration Site");

			} else {
				SoftAssert softAssert = new SoftAssert();
				softAssert.assertTrue(IspersistTotalTickets == false);
				
				//ITestResult result1=FAILURE;
				//this.testIT(result1);
				
				softAssert.assertAll();
				CloseTab();
				logger.log(LogStatus.FAIL, "Test case Failed:Total Tickets are Found On the Registeration Site");
			}
			
			
		}
		
	}
	public int FindRemainingTicekts() {
		int TotalTicketsOnTheEventcenter=(Integer.parseInt(getTotalTickets()));
		int RTicket =TotalTicketsOnTheEventcenter -getEventAttendees();
		//System.out.println("Remaining tickets "+RTicket);
		return RTicket;
	}
	public int TotalTicketOnTheRegSite() {
		WebElement TotalTickets=GetTTOnTheRegSite();
		String ttickets=TotalTickets.getText();
		int ConvertTicket= Integer.parseInt(ttickets);
		//System.out.println("Total Tickets On the Registeration Site: "+ttickets);
		return ConvertTicket ;
		
	}
	public void RegisteredAttendeesCountInTheEvent() {
		
		WebElement GetTotalAttendees=GetTotalAttendees();
		String TAttendees=GetTotalAttendees().getText();
		setEventAttendees(Integer.parseInt(TAttendees));//convert String  in to Int
		
	}

	public void OpenAttendeeList() {
		WebElement Modules=GetModules();
		Modules.click();
		logger.log(LogStatus.INFO, "Click on the Modules Button");
		WebElement Attendees=Attendees();
		Attendees.click();
		logger.log(LogStatus.INFO, "Click on the Attendees");
		WebElement AttendeesList=GetAttendeesList();
		AttendeesList.click();
		logger.log(LogStatus.INFO, "Click on the Attendee List");
		
	}
	public WebElement GetTTOnTheRegSite() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		wait2.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector(".col-sm-4")));
		WebElement TTicekt = driver.findElement(By.cssSelector(".col-sm-4"));
		return TTicekt;
	}
	public WebElement GetAttendeesList() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		wait2.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("li.has-sub:nth-child(3) > ul:nth-child(3) > li:nth-child(1) > a:nth-child(1) > span:nth-child(1)")));
		WebElement AttendeesList = driver.findElement(By.cssSelector("li.has-sub:nth-child(3) > ul:nth-child(3) > li:nth-child(1) > a:nth-child(1) > span:nth-child(1)"));

		return AttendeesList;
	}
	public WebElement GetTotalAttendees() {//This function will get the count the of Registered Attendees in the Event
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		wait2.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector(".resultInfo > span:nth-child(2)")));//Get text of attendees count
		WebElement TotalAttendees = driver.findElement(By.cssSelector(".resultInfo > span:nth-child(2)"));

		return TotalAttendees;
	}
	public WebElement Attendees() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		wait2.until(ExpectedConditions
				.visibilityOfElementLocated(By.linkText("Attendees")));
		WebElement Attendees = driver.findElement(By.linkText("Attendees"));
		return Attendees;
	}
	
	public WebElement GetModules() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		wait2.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("span.module")));
		WebElement Modules = driver.findElement(By.cssSelector("span.module"));
		return Modules;
	}
	public WebElement GetRegisterWithLinkedInBTnOnRegSite() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		wait2.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector(".login_with_linkedin > img:nth-child(1)")));
		WebElement LinkedInBTnOnRegite = driver.findElement(By.cssSelector(".login_with_linkedin > img:nth-child(1)"));
		// xpath("/html/body/div[1]/div[27]/div[4]/div[1]/a/img")));
		return LinkedInBTnOnRegite;
	}

	public Boolean IsPersistGetRegisterWithLinkedInBTnOnRegSite() {
//		WebDriverWait wait2 = new WebDriverWait(driver, 10);
//		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".login_with_linkedin > img:nth-child(1)")));
		// boolean LinkedInBTnOnRegite =
		// driver.findElements(By.cssSelector(".login_with_linkedin >
		// img:nth-child(1)")).size()>0;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Boolean isPresent = driver.findElements(By.cssSelector(".login_with_linkedin > img:nth-child(1)")).size() > 0;
		return isPresent;
	}
	
	public Boolean IsPersistTotalTicketsOnRegSite() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Boolean isPresent = driver.findElements(By.cssSelector(".col-sm-4")).size() > 0;
		return isPresent;
	}

	public void TabSwtichingToRegSite() throws InterruptedException {
		Thread.sleep(10000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public WebElement GetRegisterWithLinkedIn() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.id("eventsite_signup_linkedin")));
		WebElement LinkedInRegsiteCheckBox = driver.findElement(By.id("eventsite_signup_linkedin"));
		return LinkedInRegsiteCheckBox;
	}
	public WebElement GetShowTicketsLeft() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.id("eventsite_tickets_left")));
		WebElement ShowTicketLeft = driver.findElement(By.id("eventsite_tickets_left"));
		return ShowTicketLeft;
	}
	public WebElement GetTotalTicketsOnEcenter() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("ticket_left")));
		WebElement TicketLeft = driver.findElement(By.id("ticket_left"));
		return TicketLeft;
	}
	public void SetTotalTicketsOnEcenter(String Tickets) {
	 WebElement TotalTickets=GetTotalTicketsOnEcenter();
	 var TotalTicketsValue = TotalTickets.getAttribute("value");
	 if(TotalTicketsValue.isEmpty()) {
		 GetTotalTicketsOnEcenter().sendKeys(Tickets);
		 logger.log(LogStatus.INFO, "Enter Tickets in The field=" + Tickets);
		 //GetTotalTicketsOnEcenter().sendKeys(""+Tickets);
		 WebElement TotalticketField=GetTotalTicketsOnEcenter();
		 var TotalTicketsValue1 = TotalticketField.getAttribute("value");
		 setTotalTickets(TotalTicketsValue1);
		 
	 }
	 else {
		 
		 setTotalTickets(TotalTicketsValue);
		 }
	}
	public void SetLinkedInUserName(String LUserName) {
		GetLinkedInUserName().clear();
		GetLinkedInUserName().sendKeys(LUserName);

	}

	public void SetLinkedInPassword(String LPassword) {
		GetLinkedInPassword().clear();
		GetLinkedInPassword().sendKeys(LPassword);

	}

	public WebElement GetEmail() {
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		WebElement Email = driver.findElement(By.id("email"));
		return Email;
	}

	public WebElement GetLinkedInAllow() {
		WebElement LPasswprd = driver.findElement(By.id("oauth__auth-form__submit-btn"));
		return LPasswprd;
	}

	public WebElement GetLinkedInPassword() {
		WebElement LPasswprd = driver.findElement(By.id("password"));
		return LPasswprd;
	}

	public WebElement GetLinkedInUserName() {
		WebElement RegsiteButton = driver.findElement(By.id("username"));
		return RegsiteButton;
	}

	public WebElement GetRegSiteButton() {
		WebElement RegsiteButton = driver.findElement(By.cssSelector("span.regSite"));
		return RegsiteButton;
	}

	public WebElement GetHeading() {
		WebElement RegsiteButton = driver.findElement(By.className("headSection"));
		return RegsiteButton;
	}

	public WebElement GetEventCoporate() {
		WebElement RegsiteButton = driver.findElement(By.id("eventsite_public"));
		return RegsiteButton;
	}

	public WebElement GetLinkedSign() {
		WebElement LSignIn = driver.findElement(By.cssSelector("button.btn__primary--large.from__button--floating"));
		return LSignIn;
	}

	public void ClickOnLinedInToggleButton() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//label[@for='eventsite_signup_linkedin']")).click();
	}
	public void ClickOnShowTicketLeftToggleButton() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//label[@for='eventsite_tickets_left']")).click();
	}

	public void ScrollDown() throws InterruptedException {
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,2500)");

	}
	
	public void ScrollDownForShowingTicketSetting() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");

	}

	public WebElement GetsaveButton() {
		WebElement RegsiteButton = driver.findElement(By.className("save-event"));
		return RegsiteButton;
	}

	public void OpenRegSite() {
		driver.findElement(By.linkText("Reg. Site Prev.")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		logger.log(LogStatus.INFO, "Open Registeration Site");

	}

	public void CloseTab() throws InterruptedException {
		Thread.sleep(5000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		driver.close();
		driver.switchTo().window(tabs.get(0));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

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
