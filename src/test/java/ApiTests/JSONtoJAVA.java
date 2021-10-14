package ApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class JSONtoJAVA {
    //this called de-serailization
    //one way to navigate json body to read data from json body
    /*
    given accept type JSON
    And path param id is 15
    When user get request spartans/{id}
    Then status code 200
    And json data has following:
    "id": 15,
    "name": "Meta",
    "gender": "Female",
    "phone": 1938695106
 */

    @BeforeClass
    public void baseUrl(){
        RestAssured.baseURI="http://54.162.203.9:8000";
    }
    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/api/spartans/{id}");
        System.out.println(response.statusCode());

        Assert.assertEquals(response.statusCode(),200);


        //how to converst json body to java collection
        Map<String,Object> jsonMap = response.body().as(Map.class);
        System.out.println(jsonMap.get("id"));
        System.out.println(jsonMap.get("name"));


    }

    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        //get first list of spartnas
        List<Map<String,Object>> listofmap=response.body().as(List.class);
        System.out.println(listofmap.get(5));

        Map<String,Object> mapoflist=listofmap.get(0);
        System.out.println(mapoflist.get("name"));
        for (Map<String, Object> map : listofmap) {
            //System.out.println(listofmap);
        }

    }
}
