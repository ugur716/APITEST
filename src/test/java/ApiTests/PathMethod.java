package ApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class PathMethod {

    @BeforeClass
    public void baseUrl(){
        RestAssured.baseURI="http://api.zippopotam.us";
    }
    //one of the navigating to json body--> path method
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
                .and().pathParam("city", "belmont")
                .when().get("/us/{state}/{city}");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");

        String country=response.path("country");
        Assert.assertEquals(country,"United States");
        String state=response.path("state");
        Assert.assertEquals(state,"Massachusetts");




    }
    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("http://54.162.203.9:8000/api/spartans");
        Assert.assertEquals(response.statusCode(),200);

        //response.prettyPrint();

        //get 3.id
        int id3=response.path("id[2]");
        System.out.println(id3);
        //last name
        String lastname=response.path("name[-1]");
        System.out.println(lastname);

        //get all phone

        List<Object> phone=response.path("phone");
        System.out.println(phone);
        System.out.println(phone.size());

    }
}
