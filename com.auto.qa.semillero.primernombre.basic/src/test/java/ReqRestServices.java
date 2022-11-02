import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ReqRestServices {

    @Test
    public void helloTestingWorld(){
        System.out.printf("Hello Testing Luis Hernan");
    }

    @BeforeEach
    public void setup(){
        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="/api";
        RestAssured.filters(new RequestLoggingFilter(),new ResponseLoggingFilter());
    }
    @Test
    public void LoginTest(){
                given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"email\":\"eve.holt@reqres.in\",\n"+
                        "  \"password\":\"cityslicka\"\n"+
                        "}")
                .post("https://reqres.in/api/login")
                .then().statusCode(HttpStatus.SC_OK).body("token",notNullValue());;
    }
    @Test
    public void GetListUser(){
                when()
                .get("https://reqres.in/api/user?page=2")
                .then().statusCode(HttpStatus.SC_OK).body("page",equalTo(2));;
    }
   @Test
    public void deleteUser(){
       when().delete("https://reqres.in/api/users/2")
               .then().statusCode(HttpStatus.SC_OK);
   }
   @Test
    public void UpdateUser(){
       given()
               .contentType(ContentType.JSON)
               .body("{\n" +
                       "  \"email\":\"eve.holt@reqres.in\",\n"+
                       "  \"password\":\"ctoetas\"\n"+
                       "}")
               .put("https://reqres.in/api/users/7")
               .then().statusCode(HttpStatus.SC_OK);
   }
}
