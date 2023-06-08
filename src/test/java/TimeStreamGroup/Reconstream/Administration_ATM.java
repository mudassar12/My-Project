package TimeStreamGroup.Reconstream;

import java.util.List;

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
	public String TerminalID="14333";
	public String CIT_defined_Terminal_id="CIT Field";
	public String DeviceLocation="main Gulberg Lahore";
	public String Region;
	public String IPAddress="192.193.168.100";
	public String DeviceName="main branch";
	public String Latitude="31°26'20.6";
	public String Longitude="31°26'20.6";
	public String Vendor;		

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
			 //Scrolling down the page till the element is found
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
		//logger.log(LogStatus.PASS, "'Input the CIT Field"+this.CIT_defined_Terminal_id);
		logger.log(LogStatus.INFO, "'Input the CIT Field"+this.CIT_defined_Terminal_id);
		Assert.assertTrue(GetCITfield.isDisplayed());
		logger.log(LogStatus.PASS, "'Validate that CIT Field is displayed and user can enter the CIT in the field");
     // Scrolling down the page till the element is found		
		Thread.sleep(2000);
		
	}
	@Test(priority =7) public void VerifyDeviceLocationIsDisplayed() throws InterruptedException {
		logger = report.startTest("Verify that Device Location Field is displayed and user can enter the data in the field");
		WebElement GetDLocationfield=this.GetDeviceLocationField();
		SetDeviceLocationField();
		//logger.log(LogStatus.PASS, "'InPut the device location"+this.DeviceLocation);
		logger.log(LogStatus.INFO, "'InPut the device location"+this.DeviceLocation);
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
		//logger.log(LogStatus.PASS, "'Input the 'LATITUDE'"+this.Latitude);
		logger.log(LogStatus.INFO, "'Input the 'LATITUDE'"+this.Latitude);
		this.SetLatitude();

		 Assert.assertTrue(Latitude.isEnabled());
		 logger.log(LogStatus.PASS, "Validate that User can enter Latitude: ");
	}
	@Test(priority =12) public void VerifyThatLongitudeFieldIsDisplayed() throws InterruptedException {
		logger = report.startTest("Verify that 'LONGITUDE' Field is displayed");
		WebElement longitude=GeLONGITUDEField();
		logger.log(LogStatus.PASS, "'Input the 'LONGITUDE'"+this.Longitude);
		this.SetLongitude();

		 Assert.assertTrue(longitude.isEnabled());
		 logger.log(LogStatus.PASS, "Validate that User can enter LONGITUDE: ");
	}
	@Test(priority =13) public void VerifyThatUserSelectTheVendor() throws InterruptedException {
		logger = report.startTest("Verify that User can select the Vendor");
		WebElement vendorField=GetVendorField();
		vendorField.click();
		//logger.log(LogStatus.PASS, "click on the vendor Field for selection the values from the drop down");
		logger.log(LogStatus.INFO, "click on the vendor Field for selection the values from the drop down");
		WebElement vendorvalueselect=this.SelectVendorValue();
		vendorvalueselect.click();
		WebElement vendor=GetVendorAfterSelection();
		logger.log(LogStatus.PASS, "Select the Vendor Values"+vendor.getText());
		this.Vendor=vendor.getText();
		
		// Assert.assertTrue(longitude.isEnabled());
		 logger.log(LogStatus.PASS, "Validate that User has selected the vendor value"+this.Vendor);
	}
	@Test(priority =14) public void VerifyThatActiveOptionisSelectedByDefault() throws InterruptedException {
		logger = report.startTest("Verify that Active Option is selected By Default");
		this.scrolldownADDATm();
		WebElement  GetActive=GetActive();
		Assert.assertTrue(GetActive.isSelected());
		 logger.log(LogStatus.PASS, "Validate that Active Checkbox is already");
	}
	
	@Test(priority =15) public void VerifyThatAfterAddingtheATMUserLanonOnTHeListingScreen() throws InterruptedException {
		String ExpectedResults="All ATMs";
		logger = report.startTest("Verify that when user press Add button then user land on the ATM listing screen");
		WebElement  GetAddATM=GetADDATM();
		GetAddATM.click();//click on the Add ATM button
		Thread.sleep(1000);
		logger.log(LogStatus.INFO, "Click on the ADD ATM button");
		WebElement  GetALlATMLabels=GetALLATMLabel();
		Assert.assertEquals(ExpectedResults,GetALlATMLabels.getText() );
		 logger.log(LogStatus.PASS, "Validate that ATM has been added successfully and user land on the ATM listing screen");
	}
	@Test(priority =16) public void VerifyThaATMExistInList() throws InterruptedException {
		logger = report.startTest("Verify that Latest Added ATM is showing in the ATM list");
		//WebElement  GetAddATM=GetADDATM();
		FindATM();
		//Assert.assertEquals(ExpectedResults,GetALlATMLabels.getText() );
		 logger.log(LogStatus.PASS, "Validate that ATM has been added successfully and user land on the ATM listing screen");
	}
	public void  FindATM() {
		// Find the table element
		WebElement table = driver.findElement(By.tagName("table"));
		System.out.print("Find table");
		// Get all the rows of the table
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		System.out.print("Find Tr");
		// Loop through each row and extract the data
		for (WebElement row : rows) {
		    // Get all the columns of the current row
		    List<WebElement> columns = row.findElements(By.tagName("td"));
		    
		    // Loop through each column and extract the text value
		    for (WebElement column : columns) {
		        String data = column.getText();
		    	if (column.getText().toLowerCase().contains(this.TerminalID.toLowerCase()))
		    	{
		    		System.out.print("terminal ID" + column.getText());
		    	}
		    
		       // System.out.print(data + "\t");
		    }
		    //System.out.println();
		}
	}
	
	public WebElement GetVendorAfterSelection() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement ATMType= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dropdownMenuButtonNCR")));		
		return ATMType;

	}
	public WebElement GetADDATM() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement ATMType= wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("submit")));		
		return ATMType;

	}
	public WebElement GetActive() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement ATMType= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("active")));		
		return ATMType;

	}
	public WebElement GetATMType() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement ATMType= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='atm']")));		
		return ATMType;

	}
	public WebElement SelectVendorValue() {
		WebDriverWait wait = new WebDriverWait(driver,20);
		WebElement vendorvalue= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='NCR']")));		
		return vendorvalue;

	}
	public void SetLongitude() {
		WebElement Longitude= this.GeLONGITUDEField();
		Longitude.clear();
		Longitude.sendKeys(this.Longitude);
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
	public WebElement GeLONGITUDEField() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement longitude= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Longitude']")));		
		return longitude;

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
	public WebElement GetVendorField() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement GetVendor= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dropdownMenuButtonSelect Vendor")));
		return GetVendor;
		
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
	public void scrolldownADDATm() throws InterruptedException{
		Thread.sleep(2000);
		 //  JavascriptExecutor js = (JavascriptExecutor) driver;
	       //js.executeScript("window.scrollBy(0,350)", "");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement Element =driver.findElement(By.name("submit")); //driver.findElement(By.xpath("//*[@id=\"root\"]//*[text()='Transactions']"));
		//*[@id=\"root\"]//*[text()='Branch']
        // Scrolling down the page till the element is found		
        js.executeScript("arguments[0].scrollIntoView();", Element);

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
	
	/*public int Random() {
		int min = 50; // Minimum value of range
	      int max = 100; // Maximum value of range
	      // Print the min and max  
	      System.out.println("Random value in int from "+ min + " to " + max + ":");
	      // Generate random int value from min to max
	      int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);
	      return
	}*/
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
