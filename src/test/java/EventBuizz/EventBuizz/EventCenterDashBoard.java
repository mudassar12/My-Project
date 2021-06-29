package EventBuizz.EventBuizz;

import model.Date_Model;

import java.util.List;
import org.apache.tools.ant.types.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;
import java.lang.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import org.apache.tools.ant.types.DataType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.apache.tools.ant.types.*;
import com.relevantcodes.extentreports.LogStatus;

import freemarker.core.ReturnInstruction.Return;
import junit.extensions.TestSetup;
import junit.framework.Assert;

public class EventCenterDashBoard extends Configuration {
	public ArrayList<Date_Model> listReturened_spilt_dates = new ArrayList<Date_Model>();

	@Test(priority = 1)
	public void ValidateThatSearchFieldExistOnTheDashBoard() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
		logger = report.startTest("Verify Search Field Exist on the DashBoard");
		WebElement SearchField = GetSearchField();
		Assert.assertTrue(SearchField.isDisplayed());
		logger.log(LogStatus.PASS, "Test case Pass:Search Field is displaying on the DashBaord");

	}

	@Parameters({ "EventName" })
	@Test(priority = 2)
	public void ValidateThatSearchIsWorkingOnTheDashBoard(String EventName) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		logger = report.startTest("Verify Search Is Working correctly on the DashBoard");
		this.SetSearch(EventName);
		logger.log(LogStatus.INFO, "'Search Event = " + EventName);
		WebElement SearchBtn = GetSearchButton();
		SearchBtn.click();
		logger.log(LogStatus.INFO, "Click On the search Button");
		int EventCount = FindEventCount(EventName, "h2");
		// System.out.println("Event Text"+EventCount);
		logger.log(LogStatus.INFO, "' Matching Record on the list are = " + EventCount);
		int ListSize = GetListSize();
		// ListFound();
		logger.log(LogStatus.INFO, "'Record on the list after the searching  = " + ListSize);
		Assert.assertEquals(EventCount, ListSize);
		logger.log(LogStatus.PASS, "Test case Pass:Search is working correctly on the DashBoard");

	}

	@DataProvider(name = "Searchinput")
	public static Object[][] EmptyPassword() {

		return new Object[][] { { "TEST" }, { "" }, { "MY Event" }, { "EVENT" } };
	}

	@Test(priority = 3, dataProvider = "Searchinput")
	@Parameters({ "Event" })
	public void ValidateThatSearchIsWorkingCorrectly(String Event) throws InterruptedException {
		GetSearchField().clear();
		GetSearchButton().click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		logger = report.startTest("Verify Search Is Working correctly on the DashBoard");
		this.SetSearch(Event);
		logger.log(LogStatus.INFO, "'Search Event = " + Event);
		WebElement SearchBtn = GetSearchButton();
		SearchBtn.click();
		logger.log(LogStatus.INFO, "Click On the search Button");
		int EventCount = FindEventCount(Event, "h2");
		// System.out.println("Event Text"+EventCount);
		logger.log(LogStatus.INFO, "' Matching Record on the list are = " + EventCount);
		int ListSize = GetListSize();
		// ListFound();
		logger.log(LogStatus.INFO, "'Record on the list after the searching  = " + ListSize);
		Assert.assertEquals(EventCount, ListSize);
		logger.log(LogStatus.PASS, "Test case Pass:Search is working correctly on the DashBoard");

	}

	@Test(priority = 4)
	public void ValidateFiltersFieldisDsiplayedOnTheDashBoard() throws InterruptedException {
		GetSearchField().clear();
		GetSearchButton().click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		logger = report.startTest("Verify That Filters Filed is displayed on the DashBoard");
		WebElement Filters = GetFilters();
		Assert.assertTrue(Filters.isDisplayed());
		logger.log(LogStatus.PASS, "Test case Pass:Filters are displayed on the Dashbaord");

	}

	@Test(priority = 5)
	public void ValidateActiveandFutureFilterIsWorkingCorrectly() throws InterruptedException, ParseException {
		Date_Model DateModel = new Date_Model();
		GetSearchField().clear();
		GetSearchButton().click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		logger = report
				.startTest("Verify That when user select Active and future Filter then correct data is displayed  ");
		WebElement Filters = GetFilters();

		Filters.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		logger.log(LogStatus.INFO, "'Click On the Filter");
		selectFirstIndexFromDropDown();
		logger.log(LogStatus.INFO, "Select 'active and future' Filter from the dropdown");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		TestJsoup list = new TestJsoup();
		int listSize = GetListSize();
		if (listSize == 0) {
			logger.log(LogStatus.INFO, "'Record Not Found on the List= " + listSize);
		} else {
			String tagName = "p";
			ArrayList<String> listReturened_dates = new ArrayList<String>();

			listReturened_dates = list.GetJsoup(tagName);
			listReturened_spilt_dates = SpiltStrings(listReturened_dates);
			for (int i = 0; i < listReturened_spilt_dates.size(); i++) {
				if (listReturened_spilt_dates.get(i).getEnd_date().equals(GetCurrentDate())
						|| (GetCurrentDate().after(listReturened_spilt_dates.get(i).getEnd_date()))) {
					logger.log(LogStatus.INFO, "Test case Failed :Active and futre Filter shows wrong End Dates: "
							+ listReturened_spilt_dates.get(i).getEnd_date());
					Assert.assertTrue(false);

				}

				else {
					logger.log(LogStatus.PASS,
							"Test case Pass:Active and future filters shows correct Data: " + "End Date "
									+ listReturened_spilt_dates.get(i).getEnd_date() + "Start Date: "
									+ listReturened_spilt_dates.get(i).getStart_date());
					Assert.assertTrue(true);
					logger.log(LogStatus.PASS, "Test case Pass:on the selection of Active and Future correct data is displayed on the DashBaord");
				}

			}
		}

	}

	@Test(priority = 6)
	public void ValidateActiveFilterIsWorkingCorrectly() throws InterruptedException, ParseException {
		Date_Model DateModel = new Date_Model();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		logger = report.startTest("Verify That when user select Active Filter then correct data is displayed  ");
		WebElement Filters = GetFilters();

		Filters.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		logger.log(LogStatus.INFO, "'Click On the Filter");
		selectActiveIndexFromDropDown();
		logger.log(LogStatus.INFO, "'Select 'Active'  Filter from drop down");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		TestJsoup list = new TestJsoup();
		int listSize = GetListSize();
		if (listSize == 0) {
			logger.log(LogStatus.INFO, "'Record Not Found on the List= " + listSize);
		} else {
			String tagName = "p";
			ArrayList<String> listReturened_dates = new ArrayList<String>();

			listReturened_dates = list.GetJsoup(tagName);
			listReturened_spilt_dates = SpiltStrings(listReturened_dates);
			for (int i = 0; i < listReturened_spilt_dates.size(); i++) {
				if (listReturened_spilt_dates.get(i).getEnd_date().equals(GetCurrentDate())
						|| (GetCurrentDate().after(listReturened_spilt_dates.get(i).getEnd_date()))) {
					logger.log(LogStatus.INFO, "Test case Failed :Active  Filter shows wrong End Dates: "
							+ listReturened_spilt_dates.get(i).getEnd_date());
					Assert.assertTrue(false);

				}

				else {
					logger.log(LogStatus.PASS,
							"Test case Pass:Active filters shows correct Data: " + "End Date "
									+ listReturened_spilt_dates.get(i).getEnd_date() + "Start Date:  "
									+ listReturened_spilt_dates.get(i).getStart_date());
					Assert.assertTrue(true);
					logger.log(LogStatus.PASS, "Test case Pass:on the selection of Active correct data is displayed on the DashBaord");
				}

			}
		}

	}

	@Test(priority = 7)
	public void ValidateExpiredFilterIsWorkingCorrectly() throws InterruptedException, ParseException {
		Date_Model DateModel = new Date_Model();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		logger = report
				.startTest("Verify That when user select Expired Filter then correct data is displayed in the List  ");
		WebElement Filters = GetFilters();

		Filters.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		logger.log(LogStatus.INFO, "'Click On the Filter");
		selectExpiredIndexFromDropDown();
		logger.log(LogStatus.INFO, "Select 'Expired' Filter From the Drop down");
		;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		TestJsoup list = new TestJsoup();
		int listSize = GetListSize();
		if (listSize == 0) {
			logger.log(LogStatus.INFO, "'Record Not Found on the List= " + listSize);
		} else {
			String tagName = "p";
			ArrayList<String> listReturened_dates = new ArrayList<String>();

			listReturened_dates = list.GetJsoup(tagName);
			listReturened_spilt_dates = SpiltStrings(listReturened_dates);
			for (int i = 0; i < listReturened_spilt_dates.size(); i++) {
				if (listReturened_spilt_dates.get(i).getEnd_date().equals(GetCurrentDate())
						|| (GetCurrentDate().before(listReturened_spilt_dates.get(i).getEnd_date()))) {
					logger.log(LogStatus.INFO, "Test case Failed :Active  Filter shows wrong End Dates: "
							+ listReturened_spilt_dates.get(i).getEnd_date());
					Assert.assertTrue(false);

				}

				else {
					logger.log(LogStatus.PASS,
							"Test case Pass:Active filters shows correct Data" + "End Date: "
									+ listReturened_spilt_dates.get(i).getEnd_date() + "Start Date "
									+ listReturened_spilt_dates.get(i).getStart_date());
					Assert.assertTrue(true);
					logger.log(LogStatus.PASS, "Test case Pass:on the selection of Expired Filter, correct data is displayed on the DashBaord");
				}

			}
		}

	}

	@Test(priority = 8)
	public void ValidateFutureFilterIsWorkingCorrectly() throws InterruptedException, ParseException {

		Date_Model DateModel = new Date_Model();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		logger = report.startTest(
				"Verify That when user select Future events options from the Filter then correct data is displayed in the List  ");
		WebElement Filters = GetFilters();

		Filters.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		logger.log(LogStatus.INFO, "'Click On the Filter");
		selectFutureIndexFromDropDown();
		logger.log(LogStatus.INFO, "Select 'Future' Filter From the Drop down");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		TestJsoup list = new TestJsoup();
		int listSize = GetListSize();
		if (listSize == 0) {
			logger.log(LogStatus.INFO, "'Record Not Found on the List= " + listSize);
		} else {
			String tagName = "p";
			ArrayList<String> listReturened_dates = new ArrayList<String>();

			listReturened_dates = list.GetJsoup(tagName);
			listReturened_spilt_dates = SpiltStrings(listReturened_dates);
			for (int i = 0; i < listReturened_spilt_dates.size(); i++) {
				if (GetCurrentDate().after(listReturened_spilt_dates.get(i).getEnd_date())) {
					logger.log(LogStatus.INFO, "Test case Failed :Future  Filter shows wrong End Dates: "
							+ listReturened_spilt_dates.get(i).getStart_date());
					Assert.assertTrue(false);

				}

				else {
					logger.log(LogStatus.PASS,
							"Test case Pass:Future filters shows correct Data: " + "End Date: "
									+ listReturened_spilt_dates.get(i).getEnd_date() + "Start Date "
									+ listReturened_spilt_dates.get(i).getStart_date());
					Assert.assertTrue(true);
					logger.log(LogStatus.PASS, "Test case Pass:on the selection of Future Filter, correct data is displayed on the DashBaord");
				}

			}
		}

	}

	@Test(priority = 9)
	public void ValidateAllFilterIsWorkingCorrectly() throws InterruptedException, ParseException {

		Date_Model DateModel = new Date_Model();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		logger = report.startTest(
				"Verify That when user select ALL events  from the Filter then correct data is displayed in the List  ");

		WebElement FilterActive = GetFilters();
		FilterActive.click();// Click on the Filter For showing the dropdown
		logger.log(LogStatus.INFO, "Click on the Filter ");
		selectActiveIndexFromDropDown();
		logger.log(LogStatus.INFO, "Select Active Events From the Dropdown ");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		int ActiveEventCount = GetListSize();
		WebElement FilterExpired = GetFilters();
		FilterExpired.click();// Click on the Filter For showing the dropdown
		logger.log(LogStatus.INFO, "click on the Filter");
		selectExpiredIndexFromDropDown();
		logger.log(LogStatus.INFO, "Select Expired Events From the Dropdown");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		int ExpiredEventCount = GetListSize();
		WebElement FilterFuture = GetFilters();
		FilterFuture.click();// Click on the Filter For showing the dropdown
		logger.log(LogStatus.INFO, "click on the Filter");
		selectFutureIndexFromDropDown();
		logger.log(LogStatus.INFO, "Select Future Events From the Dropdown");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		int FutureEventCsount = GetListSize();
		WebElement Filters = GetFilters();
		Filters.click();
		logger.log(LogStatus.INFO, "click on the Filter");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		selectAllEventsIndexFromDropDown();
		logger.log(LogStatus.INFO, "Select Future Events From the Dropdown");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		int AllCount = GetListSize();
		int allEventCount = ActiveEventCount + ExpiredEventCount + FutureEventCsount;
		Assert.assertEquals(AllCount, allEventCount);
		logger.log(LogStatus.PASS, "Test case Pass:on the selection of ALL Events, correct data is displayed on the DashBaord");

	}
	@Test(priority =10)
	@Parameters({ "EventName" })
	public void EventSelect(String Event) throws InterruptedException {
		logger = report.startTest(
				"Verify that after the search of the event , user can select the event successfully and event Opens successfully ");
		GetSearchField().clear();
		GetSearchButton().click();
		
		this.SetSearch(Event);
		logger.log(LogStatus.INFO, "'Search Event = " + Event);
		WebElement SearchBtn = GetSearchButton();
		SearchBtn.click();
		logger.log(LogStatus.INFO, "Click On the search Button");
		SelectEvent(Event);
		logger.log(LogStatus.INFO, "Select the Event =" +Event);
		WebElement EventNameActual= GetEventNameHeader();
	    Assert.assertEquals(Event, EventNameActual.getText());
	    logger.log(LogStatus.PASS, "Test case Pass : On the Selection of Event then event Seleect Successfully");
		
	}

	public Date GetCurrentDate() throws ParseException {
		String CuurentDate;
		Date ConvertedCDate;
		DateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
		Date Cdate = new Date();
		CuurentDate = formatter.format(Cdate);
		ConvertedCDate = (Date) formatter.parse(CuurentDate);
		;
		// System.out.println(formatter.format(ConvertedCDate));
		return ConvertedCDate;

	}

	public ArrayList<Date_Model> SpiltStrings(ArrayList<String> listReturened_dates) throws ParseException {
		ArrayList<Date_Model> list_Date = new ArrayList<Date_Model>();
		for (int i = 0; i < listReturened_dates.size(); i++) {
			String value = listReturened_dates.get(i);
			String temp[] = listReturened_dates.get(i).split("-");
			list_Date.add(new Date_Model(temp[0], temp[1]));

		}
		return list_Date;
	}

	public void selectAllEventsIndexFromDropDown() {
		WebElement autoComplete = driver
				.findElement(By.xpath("/html/body/div[2]/div/section/div[2]/ul/form/li[2]/div/div/ul/li[2]"));
		autoComplete.click();
	}

	public void selectFutureIndexFromDropDown() {
		WebElement autoComplete = driver
				.findElement(By.xpath("/html/body/div[2]/div/section/div[2]/ul/form/li[2]/div/div/ul/li[5]"));
		autoComplete.click();
	}

	public void selectExpiredIndexFromDropDown() {
		WebElement autoComplete = driver
				.findElement(By.xpath("/html/body/div[2]/div/section/div[2]/ul/form/li[2]/div/div/ul/li[4]"));
		autoComplete.click();
	}

	public void selectFirstIndexFromDropDown() {
		WebElement autoComplete = driver
				.findElement(By.xpath("/html/body/div[2]/div/section/div[2]/ul/form/li[2]/div/a/span"));
		autoComplete.click();
	}

	public void selectActiveIndexFromDropDown() {
		WebElement autoComplete = driver
				.findElement(By.xpath("/html/body/div[2]/div/section/div[2]/ul/form/li[2]/div/div/ul/li[3]"));
		;

		autoComplete.click();
	}

	public WebElement GetFilters() {
		WebElement SearchButton = driver.findElement(By.id("selectSorter_chosen"));
		return SearchButton;
	}
	public WebElement GetEventNameHeader() {
		WebElement EventNameHeader = driver.findElement(By.className("eventNameHeader"));
		return EventNameHeader;
	}
//	public WebElement GetFilterss() {
//		WebElement SearchButton = driver.findElement(By.className("chosen-single"));
//		return SearchButton;
//	}
	public void SetSearch(String EventName) {
		WebElement getSearch = GetSearchField();
		getSearch.sendKeys(EventName);

	}

	public WebElement GetSearchButton() {
		WebElement SearchButton = driver.findElement(By.id("btn_sbt"));
		return SearchButton;
	}

	public WebElement GetSearchField() {
		WebElement SearchField = driver.findElement(By.name("searchText"));
		return SearchField;
	}

	public int FindEventCount(String EName, String TagName) throws InterruptedException {
		Thread.sleep(1000);
		int Eventcount = 0;
		WebElement autoComplete = driver.findElement(By.xpath("/html/body/div[2]/div/section/div[4]/ul"));
		//
		try {
			(new WebDriverWait(driver, 5/* sec */)).until(
					ExpectedConditions.presenceOfElementLocated((By.xpath("/html/body/div[2]/div/section/div[4]/ul"))));

		} catch (org.openqa.selenium.TimeoutException e) {
			// aSystem.out.println(e.getMessage());
		}
		List<WebElement> autoCompleteList = autoComplete.findElements(By.className("main-listing-wrapper"));
//
		// main-listing-wrapper

		for (WebElement ac : autoCompleteList) {

			if (ac.getText().toLowerCase().contains(EName.toLowerCase())) {
				String InnerHtml = ac.getAttribute("innerHTML");
				Document html = Jsoup.parse(InnerHtml);
				Elements Tag = html.select(TagName);
				logger.log(LogStatus.INFO, "' Matching Record on the list are = " + Tag);

				Eventcount++;

				// Thread.sleep(1000);
				// break;
			}

		}
		return Eventcount;
	}
	public void SelectEvent(String EName) throws InterruptedException {
		Thread.sleep(1000);
		int Eventcount = 0;
		WebElement autoComplete = driver.findElement(By.xpath("/html/body/div[2]/div/section/div[4]/ul"));
		//
		try {
			(new WebDriverWait(driver, 5/* sec */)).until(
					ExpectedConditions.presenceOfElementLocated((By.xpath("/html/body/div[2]/div/section/div[4]/ul"))));

		} catch (org.openqa.selenium.TimeoutException e) {
			// aSystem.out.println(e.getMessage());
		}
		List<WebElement> autoCompleteList = autoComplete.findElements(By.className("main-listing-wrapper"));
//
		// main-listing-wrapper

		for (WebElement ac : autoCompleteList) {

			if (ac.getText().toLowerCase().contains(EName.toLowerCase())) {
				
				WebElement EventImage=GetEventImage();
				EventImage.click();
				
				//logger.log(LogStatus.INFO, "' Matching Record on the list are = " + Tag);


				// Thread.sleep(1000);
				 break;
			}

		}
	}
	
	public WebElement GetEventImage() {
		WebElement EventImage = driver.findElement(By.className("img-center"));
		return EventImage;
	}


	public int GetListSize() throws InterruptedException {
		Thread.sleep(1000);
		int Eventcount = 1;
		WebElement autoComplete = driver.findElement(By.xpath("/html/body/div[2]/div/section/div[4]/ul"));
		//
		try {
			(new WebDriverWait(driver, 5/* sec */)).until(
					ExpectedConditions.presenceOfElementLocated((By.xpath("/html/body/div[2]/div/section/div[4]/ul"))));

		} catch (org.openqa.selenium.TimeoutException e) {
			// System.out.println(e.getMessage());
		}
		List<WebElement> autoCompleteList = autoComplete.findElements(By.className("main-listing-wrapper"));
		// main-listing-wrapper
		int getListsize = autoCompleteList.size();
		// System.out.println("List Size"+getListsize);
		return getListsize;
		// System.out.println("List Size"+EventListCount);

	}

//	public void ListFound() {
//	    
//	    String input="Search list Found with elements:1\r\nEvent Text:4\r\nATTENDEES\r\n?\r\nTICKETS\r\nnew testing event\r\n13 September 2018 - 31 December 2019\r\nOrganized by : ghazal\r\nCreated by: ghazal\r\n1\r\nATTENDEES\r\n?\r\nTICKETS\r\nNew Danish Testing Event\r\n26 September 2018 - 31 December 2019\r\nOrganized by : Ghazal\r\nCreated by: ghazal\r\n0\r\nATTENDEES\r\n?\r\nTICKETS\r\nGeneral testing Event\r\n28 November 2018 - 31 July 2019\r\nOrganized by : Ghazal\r\nCreated by: ghazal\r\n12\r\nATTENDEES\r\n?\r\nTICKETS\r\nModules testing event\r\n27 June 2019 - 31 December 2020\r\nOrganized by : hina\r\nCreated by: ghazal";
//
//	       List<String[]> output = SplitData(input);
//	        System.out.println("Success"+output);
//	    }
//	    
//	    
//	     public static List<String[]> SplitData(String data)
//	        {
//	    	 ArrayList<Datamodel> data_list=new ArrayList();
//	            String[] dataList =  data.split("\\?");
////	            for(int x=0;x<dataList.length;x++)
////	            {
////	            	String sublist[]=dataList[x].split("\r\n");
////	            	for(int i=0; i<sublist.length; i++)
////	            		{
////	            		 String event_name="";
////	            		 String start_date="";
////	            		 String end_date="";
////	            		 if(i==0)
////	            		 {
////	            			 event_name=sublist[0];
////	            		 }
////	            		 else if(i==1)
////	            		 {
////	            			 start_date=sublist[1];
////	            		 }
////	            		 else if(i==2)
////	            		{
////	            			 end_date=sublist[2]; 
////	            		}
////	            		 data_list.add(new Datamodel(event_name,start_date,end_date));
////	            		 
////	    
////	            		}
////	            }
////	            
//	            String[] sublist= dataList[1].split("\r\n");
//	           // List<String> finalDatalist=new ArrayList(;)
//
//	            List<String[]> finalDataList =new ArrayList();
//	            for (int i = 1; i < dataList.length; i++)
//	            { 
//	                List<String> temp = new ArrayList<String>();
//
//	                for (int j=0;j< sublist.length;j++)
//	                {
//	                    if ( (sublist[j] != null)  || (sublist[j].trim().length() != 0) )
//	                        temp.add(sublist[j]);
//	                }
//	                
//	                sublist = temp.toArray(new String[temp.size()]);
//	                finalDataList.add(sublist);
//	            }
//	            
//	            return finalDataList;
//	        }

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
