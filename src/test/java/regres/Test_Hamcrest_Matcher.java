package regres;



import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.List;

import  static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class Test_Hamcrest_Matcher {

 private static final Logger log = LoggerFactory.getLogger("Test_Hamcrest_Matcher");

    @Test(testName = "Test 1: Get all users on regres")
    public void allUsers_on_regres(){

        given().when().get("https://reqres.in/api/users?page=2")
                .then().assertThat().statusCode(200).body("size()", is(not(6))).log().all();

    }

    // .log().all() .log().body().log().headers()


    @Test(testName = "Test 2: Validate first_name = Michael ")
    public void validate_record1(){

        given().when().get("https://reqres.in/api/users?page=2")
                .then().assertThat().statusCode(200).
                 and().body("data.first_name[0]", equalTo("Michael"))
                .log().body();

    }



    @Test(testName = "Test 3: validate size of the response  ")
    public void validate_response_size(){

        // using hasSize to get the size of the id returns

        given().when().get("https://reqres.in/api/users?page=2")
                .then().assertThat().statusCode(200).
                 and().body("data.id", hasSize(6))
                .log().body();

    }


    @Test(testName = "Test 4: validate anyOf()  ")
    public void validate_anyOff(){

        // using hasEntry to get the size of the id returns

        given().when().get("https://reqres.in/api/users?page=2")
                .then().assertThat().statusCode(200)
                .body("data.email[-1]",  anyOf(startsWith("rachel"), containsString("reqres")))
                .log().body()
                ;

        // this test validate the last user entry email contains rachel

    }


    @Test(testName = "Test: instanceOf(String.class)  ")
    public void validate_instanceOf(){

        // using hasEntry to get the size of the id returns

        given().when().get("https://reqres.in/api/users?page=2")
                .then().assertThat().statusCode(200)
                .and().body("data.last_name[1]", instanceOf(String.class))
                .log().body()
        ;

        // this test validate the last user entry email contains rachel

    }

//
//    @Test(testName = "Test: that page is not  nullValue() ")
//    public void validate_retruns_nullValue(){
//
//        // using hasEntry to get the size of the id returns
//
//        given().when().get("https://reqres.in/api/users?page=2")
//                .then().assertThat().statusCode(200)
//                .and().body("data.id", nullValue())
//                .log().body()
//        ;
//
//        //  Validate Body response had not nullValue()
//
//    }


    @Test(testName = "Test: that page is not  hasItems ")
    public void validate_body_has_items(){

        // using hasEntry to get the size of the id returns

        given().when().get("https://reqres.in/api/users?page=2")
                .then().assertThat().statusCode(200)
                .and().body("data.email", hasItem("lindsay.ferguson@reqres.in"))
                .log().body()
        ;

        //  Validate Body response hasItem("lindsay.ferguson@reqres.in")

    }


    @Test(testName = "Test:  contains(6) arrays ")
    public void getListofUsers(){

        String uRl = "https://reqres.in/api/users";
        log.info(" Resource URL : " + uRl);


        Response response = given().get(uRl).andReturn();


        log.info("Print the Body Response" + response.body().prettyPrint() );


        log.info("Assert the Response Statuscode");
        Assert.assertEquals(response.getStatusCode(), 200);

        System.out.println("list of User's Id " +response.getBody().jsonPath().getList("data.id"));

        log.info("Assert the response Body ID Contains 7 data in the Array ");
        Assert.assertTrue(response.getBody()
                .jsonPath()
                .getList("data.id")
                .contains(6)
        );

        log.info("End of Get  Request ...... ");

    }






}
