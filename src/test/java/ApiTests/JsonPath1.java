package ApiTests;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;


public class JsonPath1 {
   /*
      Given Accept application/json
And path state is ma
And path city is belmont
When I send a GET request to /us endpoint
Then status code must be 200
And content type must be application/json
And payload should contains following information

    country  United States

    state": "Massachusetts

     */
    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("state", "ma")
                .and().pathParam("city", "belmon")
                .when().get("http://api.zippopotam.us/us/{state}/{city}");
        Assert.assertEquals(response.statusCode(),200);

        JsonPath jsonData=response.jsonPath();
        String country=jsonData.getString("country");
        String state=jsonData.getString("state");

        Assert.assertEquals(country,"United States");
        Assert.assertEquals(state,"Massachusetts");
    }
}
