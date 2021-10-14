package ApiTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstTest {

    String baseUrl="http://api.zippopotam.us";

    @Test
    public void test1(){
        Response response = RestAssured.get(baseUrl + "/TR/01000");
        System.out.println(response.statusCode());
        response.body().prettyPrint();


    }

    @Test
    public void test2(){
        Response response = RestAssured.get(baseUrl + "/TR/01000");
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.body().asString().contains("Koyuncu"));
    }

}
