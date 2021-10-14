package ApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class Hamcrest_Chaining {
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
    @Test
    public void test1(){
        RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id",15)
                .when().get("http://54.162.203.9:8000/api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .assertThat().contentType("application/json");
    }
    @Test
    public void test2(){
        RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id",15)
                .when().get("http://54.162.203.9:8000/api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType("application/json")
                .body("id", Matchers.equalTo(15),"name",Matchers.equalTo("Meta"),"gender",Matchers.equalTo("Female"),
                        "phone",Matchers.equalTo(1938695106));
    }
}
