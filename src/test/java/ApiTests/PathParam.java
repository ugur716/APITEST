package ApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PathParam {

    @BeforeClass
    public void baseUrl(){
        RestAssured.baseURI="http://api.zippopotam.us";
    }

    /*
    Given Accept application/json
And path zipcode is 22031
When I send a GET request to /us endpoint
Then status code must be 200
And content type must be application/json
     */
    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("zipcode", 22031)
                .when().get("/us/{zipcode}");
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");

    }
    /*Given Accept application/json
    And path zipcode is 22087
    When I send a GET request to /us endpoint
    Then status code must be 404


     */
    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("zipcode", 22087)
                .when().get("/us/{zipcode}");
        Assert.assertEquals(response.statusCode(),404);
        response.prettyPrint();
    }
    /*
    Given Accept application/json
    And path zipcode is 50000
    When I send a GET request to /us endpoint
    Then status code must be 404
    And content type must be application/json
     */
    @Test
    public void test3(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("zipcode", 50000)
                .when().get("/us/{zipcode}");
        Assert.assertEquals(response.statusCode(),404);
        Assert.assertEquals(response.contentType(),"application/json");

    }
}
