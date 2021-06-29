package EventBuizz.EventBuizz;
import java.lang.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

public class utility extends TestListenerAdapter
{

	/*public static void captureScreenshot(WebDriver driver, String screenshotname) 
	{
		
		try
		{
			TakesScreenshot screenshot =(TakesScreenshot)driver;
			
			File source = screenshot.getScreenshotAs(OutputType.FILE);
			
			FileUtils.copyFile(source, new File("./Screenshots/"+screenshotname+".png"));
			
			System.out.println("Screenshot Taken");
			
		}catch(Exception excep)
		{
			System.out.println("Throwing exception while taking screenshot" +excep.getMessage());
		}

	}*/
	
	/*public static void captureScreenshot(WebDriver driver, String screenshotname) throws Exception {
		String timeStamp;
		File screenShotName;
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//The below method will save the screen shot in d drive with name "screenshot.png"
		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()); 
		//screenShotName = new File("D:\\MyTest\\Screenshots\\"+timeStamp+".png");
		screenShotName = new File("D:\\Automation\\EclipseWorkSpace\\WebDriverTest1\\target\\snapshot\\"+timeStamp+".png");
		FileUtils.copyFile(scrFile, screenShotName);
		 
		String filePath = screenShotName.toString();
		//String path = "<img src="\"file://"" alt="\"\"/" />";
		String path = "<img src=\"file://" + filePath + "\" alt=\"\"/>";
		Reporter.log(path);
		 
		}*/
	
	public static String captureScreenshot(WebDriver driver, String screenshotname) throws Exception {
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String methodName = screenshotname ;//result.getName();
		// File scrFile = ((TakesScreenshot)SomeStaticWebDriver.driver).getScreenshotAs(OutputType.FILE);
		 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 try {
			 String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/target//surefire-reports/";
             destFile = new File((String) reportDirectory+"/failure_screenshots/"+methodName+"_"+formater.format(calendar.getTime())+".png");
             FileUtils.copyFile(scrFile, destFile);
             Reporter.log("<a href='"+ destFile.getAbsolutePath() + "'> <img src='"+ destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
             System.out.println("Screenshot Taken");
         } catch (IOException e) {
             e.printStackTrace();
         }
		 String ImagePath=destFile.getAbsolutePath();
		return ImagePath;
     }
	}



