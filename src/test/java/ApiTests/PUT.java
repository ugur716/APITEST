package ApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class PUT {

    @BeforeClass
    public void beseUrı(){
        RestAssured.baseURI="http://54.162.203.9:8000";
    }
    @Test
    public void test1(){
        Map<String,Object> putmap=new HashMap<>();
        putmap.put("name","MikePut");
        putmap.put("gender","Male");
        putmap.put("phone",8877445596L);


        RestAssured.given().contentType(ContentType.JSON)
                .and().pathParam("id",101)
                .and().body(putmap)
                .when().put("/api/spartans/{id}")
                .then().assertThat().statusCode(204);

    }

}
