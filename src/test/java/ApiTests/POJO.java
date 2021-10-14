package ApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class POJO {
    @BeforeClass
    public void baseUrl(){
        RestAssured.baseURI="http://54.162.203.9:8000";
    }

    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/api/spartans/{id}");

        Spartan spartan=response.body().as(Spartan.class);
        System.out.println(spartan.toString());
        Assert.assertEquals(spartan.getName(),"Meta");

    }
}
