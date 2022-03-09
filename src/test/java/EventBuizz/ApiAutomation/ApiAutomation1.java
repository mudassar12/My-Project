package EventBuizz.ApiAutomation;

import org.junit.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
public class ApiAutomation1 {
	
	@Test(priority = 1)
	public void GetAPi() {
		Response res=RestAssured.get("https://my.eventbuizz.com/api/attendees/?language_code=en&event_id=6639");
		 System.out.print("get status code"+ res.getStatusCode());
		 System.out.print("As string"+ res.asString());
		 System.out.print("Get Body"+ res.getBody());
		 
		 RequestSpecification request = RestAssured.given().auth().basic("username", "password");

	}

}
