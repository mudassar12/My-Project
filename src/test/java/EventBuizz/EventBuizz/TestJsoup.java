package EventBuizz.EventBuizz;

import java.util.ArrayList;
import org.apache.tools.ant.types.*;
import java.util.LinkedList;
import java.util.List;
import java.lang.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.jsoup.select.Elements;
import com.beust.jcommander.Strings;

public class TestJsoup extends Configuration {
	public ArrayList<String> GetJsoup(String TagName) {
		
		//LinkedList<Elements> list_date=new LinkedList<Elements>();
		
		ArrayList<String> List_Date=new ArrayList<String>();
	WebElement autoComplete = driver.findElement(By.xpath("/html/body/div[2]/div/section/div[4]/ul"));
	//
	try {
		(new WebDriverWait(driver, 5/* sec */)).until(ExpectedConditions
				.presenceOfElementLocated((By.xpath("/html/body/div[2]/div/section/div[4]/ul"))));

	} catch (org.openqa.selenium.TimeoutException e) {
		// aSystem.out.println(e.getMessage());
	}
	List<WebElement> autoCompleteList = autoComplete
			.findElements(By.className("main-listing-detail"));
//
	//main-listing-wrapper
	
	for (WebElement ac : autoCompleteList) {
	
		// System.out.println("Text: " + ac.getText());
		 String InnerHtml=ac.getAttribute("innerHTML"); 
		  Document html = Jsoup.parse(InnerHtml);
		  
		  Elements H2 = html.select(TagName);
		  for (Element src : H2)
		  {
           
              List_Date.add(src.text());
			//System.out.println("Text: "+src.text());
		  break;
		  }
		  //System.out.println("Text: "+H2.text());
		  
	}
	return List_Date;
 
    
}
	
}
