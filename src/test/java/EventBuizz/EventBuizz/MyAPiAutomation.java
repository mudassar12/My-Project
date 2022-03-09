package EventBuizz.EventBuizz;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Filter;
 import java.io.Serializable;
import java.io.Externalizable;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.internal.support.Prettifier;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.Method;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matcher.*;
public class MyAPiAutomation {

	@Test(priority = 1)
	public void GetBooks() throws JSONException, InterruptedException {

		/*
		 * Response res=RestAssured.get(
		 * "https://my.eventbuizz.com/api/attendees/?language_code=en&event_id=6639");
		 * System.out.print("get status code"+ res.getStatusCode());
		 * System.out.print("As string"+ res.asString()); System.out.print("Get Body"+
		 * res.getBody());
		 * 
		 * 
		 * 
		 */

		
		  try {
			//given(). contentType("application/json").accept("application/json").when() .get
			// ("https://reqres.in/api/users?page=2").then().statusCode(200).contentType(ContentType.JSON).log().all();
			/* respos.getStatusCode();
			 System.out.println("Get Body"+ respos.asString());
			 System.out.println("Get Body as string"+ respos.getBody().asString());
			 System.out.println("Get status"+ respos.getStatusCode());
			 System.out.println("Get status"+ respos.getHeader("Content-Type"));
		  // RestAssured.defaultParser = Parser.JSON; Response resp1 =*/
			  
			  
			  Response res=get("https://reqres.in/api/users?page=2");
			  System.out.println("Get Body"+ res.asString());
			  System.out.println("Get Body as string"+ res.getBody().asString());
			  System.out.println("Get status"+ res.getStatusCode());
			  System.out.println("Get status"+ res.getHeader("Content-Type"));
			  System.out.println("Get status"+ res.getHeader("Content-Type"));
			  System.out.println("Get status"+ res.getHeader("Content-Type"));
	/*	 Response res1= 
		  
		  given(). contentType("application/json").accept("application/json")
		 . header("Authorization", "DEBABC4B-3D38-28AF-6C13-2B7858C2C19A").
		  header("Accept", "application/json") .headers("Content-Type",ContentType.JSON, "Accept", ContentType.JSON)
		  //given().contentType(ContentType.JSON).log().all()
		  
		 . given().queryParam("language_code","en") .queryParam("event_id", "2489")
		  .when() .get
		  ("https://dev.eventbuizz.com/api/attendees").then().statusCode(200).log()
		  .body()//.contentType(ContentType.JSON)
		  . extract().response();
		  
		  
		  ResponseBody body = res1.getBody(); String
		  responseBdy=res1.getBody().asString(); 
	
		  
		  System.out.println("content typee"+res1.getHeader("Content-Type"));
		  System.out.println("Get Body"+ body.asString());
		  */
		  
		  
		  } catch(Exception e) { System.out.print("Exception"+e); }
		 

		// param()
		/*
		 * OkHttpClient client = new OkHttpClient().newBuilder() .build(); Request
		 * request = new Request.Builder() .url(
		 * "https://dev.eventbuizz.com/api/attendees?language_code=en&event_id=2489")
		 * .method("GET", null) .addHeader("Authorization",
		 * "DEBABC4B-3D38-28AF-6C13-2B7858C2C19A") .addHeader("Content-Type",
		 * "application/json") .addHeader("Cookie", "testcookie=testcookie") .build();
		 * Response response = client.newCall(request).execute();
		 */
		
		//Response resp1 = given().log().all().contentType("application/json")

	}
	
	/*@Test(priority = 2)
	public void UserRegistrationSuccessful() throws JSONException 
	{ 
		RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books"; 
		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject(); 
		requestParams.put("userId", "TQ123"); 
		requestParams.put("isbn", "9781449325862"); 
		request.header("Content-Type", "application/json");
	    request.body(requestParams.toString());
		Response response = request.get("/BookStoreV1BooksPost"); 
		//Response response = request.put("/User"); 
	    ResponseBody body = response.getBody();
	    System.out.println(response.getStatusLine());
	    System.out.println(body.asString());
	    
	     
	}
	/
	/*@Test
	public void UserRegistrationSuccessful1() { 
	RestAssured.baseURI ="https://demoqa.com"; 
	RequestSpecification request = RestAssured.given(); 
	JSONObject requestParams = new JSONObject(); 
	requestParams.put("UserName", "test_rest"); 
	requestParams.put("Password", "rest@123"); 
	request.body(requestParams.toString()); 
	Response response = request.post("/Account/v1/User"); 
	ResponseBody body = response.getBody(); 
	// Deserialize the Response body into JSONSuccessResponse 
	JSONSuccessResponse responseBody = body.as(JSONSuccessResponse.class); 
	// Use the JSONSuccessResponseclass instance to Assert the values of Response. 
	Assert.assertEquals("OPERATION_SUCCESS", responseBody.SuccessCode); 
	Assert.assertEquals("Operation completed successfully", responseBody.Message); }
*/
	
	/*public void testApi(){
		RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";

		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("");
		String contentType = response.header("Content-Type"); 
		System.out.println("Content-Type value: " + contentType); 
		int statuscode=response.getStatusCode();
		System.out.println("status code: " + statuscode);
		
		JsonPath jsonPathEvaluator = response.jsonPath();
		System.out.println("City received from Response " + jsonPathEvaluator.get("title"));
		Assert.assertEquals( 200 , statuscode);
		
		
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		
	}*/
	
	@Test(priority = 1)
	public void GetBooks1() throws JSONException, InterruptedException {
		
		given(). contentType("application/json").accept("application/json").when() .get
		("https://reqres.in/api/users?page=2").then().statusCode(200).contentType(ContentType.JSON).log().all();
	}
}
