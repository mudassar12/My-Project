package TimeStreamGroup.Reconstream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import EventBuizz.EventBuizz.Configuration;
import EventBuizz.EventBuizz.utility;
import junit.framework.Assert;

public class Administration_ATM extends Configuration {
	public String TerminalID="1111";
	public String CIT_defined_Terminal_id="CIT Field";
	public String DeviceLocation="main Gulberg Lahore";
	public String Region;
	public String IPAddress="192.193.168.100";
	public String DeviceName="main branch";
	public String Latitude="31°26'20.6";
	public String Longitude="31°26'20.6";
			

	@Test(priority =1) public void VerifyThatAdministrationisVisible() throws InterruptedException {
		 logger = report.startTest("Validate that Verify Administration is Visible on the left hand side");
		//Locating element by link text and store in variable "Element" 
		 Thread.sleep(2000);
			scrolldown();
		 WebElement GetAdministration=this.GetAdministration();
		 GetAdministration.click();
		 logger.log(LogStatus.INFO, "Click on the Adminstration");
			Assert.assertTrue(GetAdministration.isDisplayed());
       // Scrolling down the page till the element is found		
			logger.log(LogStatus.PASS, "'Validate that Administration is Visible");
		
		
	}
	@Test(priority =2) public void VerifyThatATMManagmentisVisible() throws InterruptedException {
		 logger = report.startTest("Validate that Verify Get ATM is  isVisible on the left hand side");
		//Locating element by link text and store in variable "Element"        		
		 WebElement GetATMManagement=this.GetATMMnnagement();
		Assert.assertTrue(GetATMManagement.isDisplayed());
			logger.log(LogStatus.PASS, "'Validate that ATM Management is Displayed");
      // Scrolling down the page till the element is found		
		Thread.sleep(2000);
		scrolldown();
		
	}
	@Test(priority =3) public void VerifyThatALLATMListIsShowing() throws InterruptedException {
		logger = report.startTest("Validate that ALL ATM List is showing");
		String ExpectedResults="All ATMs";
		WebElement GetATMManagement=this.GetATMMnnagement();
		GetATMManagement.click();
		logger.log(LogStatus.PASS, "'Click on the ATM Management");
		//Locating element by link text and store in variable "Element"        		
		 WebElement GetATMListlabel=this.GetALLATMLabel();
			logger.log(LogStatus.INFO, "'Listing Screen = "+GetATMManagement.getText());
			Assert.assertEquals(ExpectedResults,GetATMListlabel.getText() );
			logger.log(LogStatus.PASS, "'Validate that ATM Management is Displayed");
     // Scrolling down the page till the element is found		
		Thread.sleep(2000);
		scrolldown();
		Thread.sleep(2000);
		
	}
	@Test(priority =4) public void VerifyADDATMScreenOpened() throws InterruptedException {
		logger = report.startTest("Validate that When User Click on the 'ADD ATM 'button then ADD ATM Screen Opened");
		String ExpectedResults="ATM Records1";
		Thread.sleep(2000);
		WebElement GetAddBTN=this.GetAddATMBTN();
		GetAddBTN.click();
		logger.log(LogStatus.PASS, "'Click on the Button 'ADD ATM'");
		//Locating element by link text and store in variable "Element"        		
		 WebElement GetLabel=this.GetATMRecodLabels();
			logger.log(LogStatus.INFO, "'ADD ATM Screen is opened = "+GetLabel.getText());
			Assert.assertEquals(ExpectedResults,GetLabel.getText() );
			logger.log(LogStatus.PASS, "'Validate that Add ATM Screen is opened successfully");
     // Scrolling down the page till the element is found		
		Thread.sleep(2000);
		
	}
	@Test(priority =5) public void VerifyThatterminalIDIsDisplayed() throws InterruptedException {
		logger = report.startTest("Validate that terminal Id Field is displayed");
		WebElement GetterminalID=this.GetTerminalID();
		SetTerminalID();
		Assert.assertTrue(GetterminalID.isDisplayed());
			logger.log(LogStatus.PASS, "'Validate that Terminal ID field Exist on the ADD ATM Screen");
     // Scrolling down the page till the element is found		
		Thread.sleep(2000);
		
	}
	@Test(priority =6) public void VerifyCITFieldIsDisplayed() throws InterruptedException {
		logger = report.startTest("Verify that CIT terminal ID Field is displayed and user can enter the data in the field");
		WebElement GetCITfield=this.GetCITField();
		SetCITField();
		logger.log(LogStatus.PASS, "'Input the CIT Field"+this.CIT_defined_Terminal_id);
		Assert.assertTrue(GetCITfield.isDisplayed());
		logger.log(LogStatus.PASS, "'Validate that CIT Field is displayed and user can enter the CIT in the field");
     // Scrolling down the page till the element is found		
		Thread.sleep(2000);
		
	}
	@Test(priority =7) public void VerifyDeviceLocationIsDisplayed() throws InterruptedException {
		logger = report.startTest("Verify that Device Location Field is displayed and user can enter the data in the field");
		WebElement GetDLocationfield=this.GetDeviceLocationField();
		SetDeviceLocationField();
		logger.log(LogStatus.PASS, "'InPut the device location"+this.DeviceLocation);
		Assert.assertTrue(GetDLocationfield.isDisplayed());
		logger.log(LogStatus.PASS, "'Validate that Device location field is displayed and user can enter the Device location in the field");
     // Scrolling down the page till the element is found		
		Thread.sleep(2000);
		
	}
	@Test(priority =8) public void VerifyRegionDropDownIsDisplayed() throws InterruptedException {
		logger = report.startTest("Verify that Region Drop down is displayed and user can select the Region");
		//Select GetRegionDropdown= this.GeTRegionDropdown();
		 GetRegion();
		 GeTRegionDropdown();
		 WebElement region= setDropdownvalues();
		 Assert.assertTrue(region.isDisplayed());
		logger.log(LogStatus.PASS, "Validate that user can select the the region from the drop down: "+this.Region);
     // Scrolling down the page till the element is found		
		Thread.sleep(2000);
		
	}
	@Test(priority =9) public void VerifyThatIpAddressFieldIsdisplayed() throws InterruptedException {
		logger = report.startTest("Verify that 'IP Address Port' Field is displayed");
		WebElement IpAddress=GetIPAddressField();
		 Assert.assertTrue(IpAddress.isDisplayed());
		logger.log(LogStatus.PASS, "'Input the IP Address"+this.IPAddress);
		this.SetIPAddress();
		
		 Assert.assertTrue(IpAddress.isEnabled());
		 logger.log(LogStatus.PASS, "Validate that User can enter the IP address: ");
	}
	@Test(priority =10) public void VerifyThatDeviceNameFieldIsdisplayed() throws InterruptedException {
		logger = report.startTest("Verify that 'Device Name' Field is displayed");
		WebElement DeviceName=GeTDeviceName();
		logger.log(LogStatus.PASS, "'Input the device Name"+this.DeviceName);
		this.SetDeviceName();
		
		 Assert.assertTrue(DeviceName.isEnabled());
		 logger.log(LogStatus.PASS, "Validate that User can enter the Device name: ");
	}
	@Test(priority =11) public void VerifyThatLatitudeFieldIsDisplayed() throws InterruptedException {
		logger = report.startTest("Verify that 'LATITUDE' Field is displayed");
		WebElement Latitude=GeLatitudeField();
		logger.log(LogStatus.PASS, "'Input the 'LATITUDE'"+this.Latitude);
		this.SetLatitude();

		 Assert.assertTrue(Latitude.isEnabled());
		 logger.log(LogStatus.PASS, "Validate that User can enter Latitude: ");
	}
	@Test(priority =11) public void VerifyThatLongitudeIsDisplayed() throws InterruptedException {
		logger = report.startTest("Verify that 'LATITUDE' Field is displayed");
		WebElement Latitude=GeLatitudeField();
		logger.log(LogStatus.PASS, "'Input the 'LATITUDE'"+this.Latitude);
		this.SetLatitude();

		 Assert.assertTrue(Latitude.isEnabled());
		 logger.log(LogStatus.PASS, "Validate that User can enter Latitude: ");
	}
	public void SetLatitude() {
		WebElement latitude= this.GeLatitudeField();
		latitude.clear();
		latitude.sendKeys(this.Latitude);
	}
	public void SetDeviceName() {
		WebElement DeviceName= this.GeTDeviceName();
		DeviceName.clear();
		DeviceName.sendKeys(this.DeviceName);
	}
	public void SetIPAddress() {
		WebElement Ipaddress= this.GetIPAddressField();
		Ipaddress.clear();
		Ipaddress.sendKeys(this.IPAddress);
	}
	public WebElement GeLatitudeField() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement Latitude= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Latitude']")));		
		return Latitude;

	}	
	public WebElement GeTDeviceName() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement Daname= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='deviceName']")));		
		return Daname;

	}	
	
	
	public WebElement GetIPAddressField() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement GetIP= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='ipPort']")));		
		return GetIP;

	}	
	public  WebElement setDropdownvalues() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement Getregion= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dropdownMenuButtonPakistan")));
		System.out.println("Region valyesText"+Getregion.getText());
		this.Region=Getregion.getText();
		return Getregion ;
	}

	public void GeTRegionDropdown() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement GetRegionDropdown= driver.findElement(By.xpath("//input[contains(@class, 'custom-control-input dropdown-checkbox')]"));
		// GetRegionDropdown= new Select(driver.findElement(By.id("dropdownMenuButtonSelect Region")));
		GetRegionDropdown.click();
		Thread.sleep(2000);
	
		//*[@id="dropdownMenuButtonSelect Region"]
	

	}
	
	public void GetRegion() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement GetDLocation= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dropdownMenuButtonSelect Region")));
		GetDLocation.click();
	}

	public WebElement GetDeviceLocationField() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement GetDLocation= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='deviceLocation']")));		
		return GetDLocation;

	}

	public WebElement GetCITField() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement GetCIT= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='citTerminalId']")));		
		return GetCIT;

	}
	public void SetDeviceLocationField() {
		WebElement Devicelocation= this.GetDeviceLocationField();
		Devicelocation.clear();
		Devicelocation.sendKeys(this.DeviceLocation);
	}
	public void SetCITField() {
		WebElement CITField= this.GetCITField();
		CITField.clear();
		CITField.sendKeys(this.CIT_defined_Terminal_id);
	}
	
	public void SetTerminalID() {
		WebElement TerminalID= this.GetTerminalID();
		TerminalID.clear();
		TerminalID.sendKeys(this.TerminalID);
	}
	public WebElement GetTerminalID() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement GetATMRecordLabel= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='terminalId']")));		
		return GetATMRecordLabel;

	}

	public WebElement GetATMRecodLabels() {
		WebDriverWait wait = new WebDriverWait(driver,20);
		WebElement GetATMRecordLabel= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]//*[text()='ATM Records']")));
				
		return GetATMRecordLabel;

	}
	public WebElement GetAddATMBTN() {
		WebDriverWait wait = new WebDriverWait(driver,20);
		WebElement GetAddBTN= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]//*[text()='Add ATM']")));
				
		return GetAddBTN;

	}
	public WebElement GetALLATMLabel() {
		WebDriverWait wait = new WebDriverWait(driver,30);
		WebElement GetALLATMLabel= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]//*[text()='All ATMs']")));
				
		return GetALLATMLabel;

	}
	
	public WebElement GetATMMnnagement() {
		WebDriverWait wait = new WebDriverWait(driver,30);
		WebElement GetATM= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]//*[text()='ATM Management']")));
				
		return GetATM;

	}
	
	public WebElement GetAdministration() {
		WebElement GetAdministration= driver.findElement(By.xpath("//*[@id=\"root\"]//*[text()='Administration']"));
		//*[@id="add-notes"]//*[text(),'']

		return GetAdministration;

	}
	
	public void scrolldown() throws InterruptedException{
		Thread.sleep(2000);
		 //  JavascriptExecutor js = (JavascriptExecutor) driver;
	       //js.executeScript("window.scrollBy(0,350)", "");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement Element = driver.findElement(By.xpath("//*[@id=\"root\"]//*[text()='Transactions']"));
		//*[@id=\"root\"]//*[text()='Branch']
        // Scrolling down the page till the element is found		
        js.executeScript("arguments[0].scrollIntoView();", Element);

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
