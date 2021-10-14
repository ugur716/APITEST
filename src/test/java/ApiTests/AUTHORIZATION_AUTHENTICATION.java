package ApiTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AUTHORIZATION_AUTHENTICATION {

    String accesToken="Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI1NyIsImF1ZCI6InN0dWRlbnQtdGVhbS1sZWFkZXIifQ.a_N9URDBPGOMcDdEVoaMHsJtk3jOnig0v0SCtSWcsGE";
    @BeforeClass
    public void beseurl(){
        RestAssured.baseURI="https://cybertek-reservation-api-qa2.herokuapp.com";
    }

    @Test
    public void test1(){
        Response response = RestAssured.given().header("Authorization", accesToken)
                .when().get("/api/campuses");
        Assert.assertEquals(response.statusCode(),200);
    }
}
