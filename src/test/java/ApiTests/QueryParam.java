package ApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class QueryParam {
    /*
    Given accept type is JSON
    Query Parameter
    gender/Female
    nameContains/J
    When get /api/spartans/search
  */


    @BeforeClass
    public void baseUrl(){
        RestAssured.baseURI="http://54.162.203.9:8000";
    }

    @Test
    public void test1(){
        Response response = RestAssured.given().when().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "J")
                .when().get("/api/spartans/search");
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");
        response.prettyPrint();
    }

    @Test
    public void test2(){
        Map<String,Object> QueryMap=new HashMap<>();
        QueryMap.put("gender","Female");
        QueryMap.put("nameContains","J");

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParams(QueryMap)
                .when().get("/api/spartans/search");
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");
    }
}
