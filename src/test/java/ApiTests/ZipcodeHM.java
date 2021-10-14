package ApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ZipcodeHM {
    String baseUrl="http://api.zippopotam.us";
    /*
    Given Accept application/json
    When I send a GET request to /TR/01000 endpoint
    Then status code must be 200
    And content type must be application/json
     */

    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(baseUrl + "/TR/01000");
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");


    }
}
