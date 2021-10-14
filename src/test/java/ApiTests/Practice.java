package ApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class Practice {
    /*
    Given accept type is json
   And path param id is 10
   When user sends a get request to "api/spartans/{id}"
   Then status code is 200
   And content-type is "application/json;charset=UTF-8"
   And response payload values match the following:
           id is 10,
           name is "Lorenza",
           gender is "Female",
           phone is 3312820936
     */

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI="http://54.162.203.9:8000";
    }
    @Test
    public void tc1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 10)
                .when().get("/api/spartans/{id}");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");

        int id=response.path("id");
        Assert.assertEquals(id,10);

        JsonPath jsonPath=response.jsonPath();
        String name= jsonPath.getString("name");
        Assert.assertEquals(name,"Lorenza");

        Map<String,Object> map1=response.as(Map.class);
        String gender= (String) map1.get("gender");
        Assert.assertEquals(gender,"Female");

        System.out.println(map1.get("phone"));

    }
    /*
    Given accept type is Json
        And query parameter values are :
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json;charset=UTF-8
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */

    @Test
    public void tc2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .queryParam("nameContains", "e")
                .when().get("/api/spartans/search");
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");
        Assert.assertTrue(response.body().asString().contains("Female"));
        Assert.assertTrue(response.body().asString().contains("Janette"));

    }

    @Test
    public void tc3(){
        RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id",11)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);

    }

    @Test
    public void tc4(){
        Map<String,Object> map1=new HashMap<>();
        map1.put("name","Alex");

         RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 10)
                .and()
                .body(map1)
                .when().patch("/api/spartans/{id}")
                 .then().assertThat().statusCode(204);

    }
}
