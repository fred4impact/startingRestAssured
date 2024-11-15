package org.jsonplaceholder;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLOutput;

import  static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetSamples {

    private static RequestSpecification reQspec;
    private static ResponseSpecification resPse;

    @BeforeClass
    public void setUp(){

        // build the request and response specification here.


    }



  @Test(testName = "Test 1: to Get all users ")
   public void getAllUsersTest(){

         given()
                 .when().get("http://jsonplaceholder.typicode.com/users")
                 .then().assertThat().statusCode(200)
                  .log().all();


   }




    @Test(testName = "Test 2: get a single user")
    public void getauserAndValidateUsername(){

        given()
                .when().get("http://jsonplaceholder.typicode.com/users")
                .then().assertThat().statusCode(200)
                .and().body("username" , hasItem("Antonette"))
                .log().all();


    }


    @Test(description = "Test to validate company name RobelCorkery", testName = "Test 3 to Validate company Name")
    public void ValidateCompany(){

        given()
                .when().get("http://jsonplaceholder.typicode.com/users")
                .then().assertThat().statusCode(200)
                .and().body("company.name[3]" , equalTo("Robel-Corkery"))
                .log().all();


    }



    @Test(testName = "Test using Query Param")
    public void getNestedPostUsing_QueryParam(){

        given()
                .queryParam("userId", "1")
                .when()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .assertThat().statusCode(200)
                .log().all();



    }




    @Test(testName = "5")
    public void createNewPost(){

      Post newPost = new Post("8Creative ways", "creative post ","1");

      String url = "https://jsonplaceholder.typicode.com/posts";

      given().header("Accept", "application/json")
              .header("Content-type", "application/json; charset=UTF-8")
              .body(newPost)
              .when().post(url)
              .then().assertThat().statusCode(201).and().body("title", equalTo("8Creative ways"))
              .log().body();


    }


    @Test(testName = "Test Using Jsonpath")
    public void getBodyJsonPath(){

        Response getResponse = given()
                .when().get("https://jsonplaceholder.typicode.com/posts")
                .andReturn();


        System.out.println(" Response Body :"  + getResponse.getBody().prettyPrint());

        String ID = getResponse.jsonPath().getString("id");
        System.out.printf(ID);




    }






//
//    @Test(testName = "6")
//    public void deleteMethod(){
//
//        given()
//                .when().delete("https://jsonplaceholder.typicode.com/posts/101")
//                .then().assertThat().statusCode(204)
//        ;
//
//
//
//    }


    @Test
    public void deleteUser2_Test(){
        given().delete("https://reqres.in/api/users/2")
                .then()
                .assertThat().statusCode(204)
                .and().statusLine("HTTP/1.1 204 No Content")
                .log().all();

    }




}

// POJO Class: Plain Old Java Object” or POJO for short.

class Post{

    public Post(String title, String body, String userId){
        this.title = title;
        this.body = body;
        this.userId = userId;

    }

    @JsonProperty("title")
    String title;

    @JsonProperty("body")
    String body;

    @JsonProperty("userId")
    String userId;


}




