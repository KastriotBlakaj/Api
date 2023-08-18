package Api.tests;

import static org.junit.Assert.assertEquals;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertNotEquals;

import io.restassured.response.Response;
import org.junit.Test;

import Api.data.HelperClass;
import static org.hamcrest.Matchers.*;
public class ApiTests {

  HelperClass helperClass =  new HelperClass();

  @Test
  public void getTheURLBodyAndAssertData() {

    Response response = get("https://reqres.in/api/users?page=2"); // We perform a get request on this Url


    int statusCode = response.getStatusCode();  //Getting the StatusCode
    String contentType = response.contentType(); // Getting the Content type
    long responseTime = response.getTime();      // Getting the Response Time


    response.then().body("data[1].id",equalTo(8));  //Verifying the Specific user of the Payload
    assertEquals(statusCode, helperClass.rightStatus);   //Verifying the right Status
    assertNotEquals(statusCode, helperClass.wrongStatus);  //Verifying the wrong Status
    assertEquals(contentType,helperClass.contentType); //Verifying the content type
    assertEquals(statusCode, 200); //Verifying the wrong Status

    System.out.println("Response Time :" + response.getBody().asString()); //Full body of Request
    System.out.println("Response Time :" + responseTime);
    System.out.println("Status Code :" + statusCode);
    System.out.println("Verify the User with ID 2 : " + response.then().body("data[1].id",equalTo(8)));
  }

  @Test
  public void verifyId(){
    Response responseWrongId = get("https://reqres.in/api/users/2");


    //Assertion for the right ID
    int extractedID = responseWrongId.path("data.id");
    assertEquals("The extracted ID match the expected value.",extractedID, helperClass.expectedId);

    //Assertion for the wrong ID
    assertNotEquals("The extracted ID doesn't match the correct value.",extractedID,helperClass.notExpectedId);

    //Assertion for the non-existing ID
    assertNotEquals("The extracted ID doesn't match the expected value.",6, helperClass.expectedId);



  }
}
