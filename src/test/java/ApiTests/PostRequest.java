package ApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class PostRequest {

    @BeforeClass
    public void baseUrl(){
        RestAssured.baseURI="http://54.162.203.9:8000";
    }

    @Test
    public void test1(){
        Response post = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"gender\": \"Male\",\n" +
                        "  \"name\": \"Adam\",\n" +
                        "  \"phone\": 1234567890\n" +
                        "}")
                .when().post("/api/spartans");

        post.prettyPrint();
        Assert.assertEquals(post.statusCode(),201);
        Assert.assertEquals(post.contentType(),"application/json");
        //verify message
        Assert.assertEquals(post.path("success"),"A Spartan is Born!");
        //verify name
        JsonPath jsonPath=post.jsonPath();

        Assert.assertEquals(jsonPath.getString("data.name"),"Adam");

    }
    @Test
    public void test2(){
        Map<String,Object> postMap=new HashMap<>();
        postMap.put("gender","Male");
        postMap.put("name","Alan");
        postMap.put("phone",1234567890);


        Response post = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(postMap)
                .when().post("/api/spartans");



        post.prettyPrint();
        Assert.assertEquals(post.statusCode(),201);


    }

    @Test
    public void test3(){

        Spartan spartan=new Spartan();
        spartan.setName("elon");
        spartan.setGender("Male");
        spartan.setPhone(1234567890);

        Response post = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(spartan)
                .when().post("/api/spartans");

        post.prettyPrint();
        Assert.assertEquals(post.statusCode(),201);
    }
}
