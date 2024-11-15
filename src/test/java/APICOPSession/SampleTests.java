
package APICOPSession;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SampleTests {


    //  RULE 1: Given i set all the headers parameters the resource need fro GET Request
    //  RULE 2: when I Send THE REQUEST METHOD WHICH IS THE CONDITION FOR TEST
    //  RULE 3: Then I CAN VALIDATE RESPONSE HEADER OR BODY from the RESOURCE ENDPOINTS
    //  RULE 4: And I CAN ALSO DO EXTRA CONDITIONAL VALIDATION FOR THE RESPONSE
    //  http://fastapi-groceries.herokuapp.com/api/v1/groceries


    // given i set header, contect-type, auth url    (Pre-requisite)
    // when i send in a request eg. get, post, put delete  (Condition to test Method)
    // then i can validate a response from the database (Validation Point)

   @Test
   public  void newTest(){

       given()
               .when().get("https://fastapi-groceries.herokuapp.com/api/v1/groceries")
               .then().assertThat().statusCode(200)
               .log().all();

   }





    @Test
    public void getRequest(){

        // "https://fastapi-groceries.herokuapp.com/api/v1/groceries
     // given i set my headers


        given()
//                .baseUri("https://fastapi-groceries.herokuapp.com/api/v1/groceries")
//                .accept(ContentType.JSON)
//                .header("Content-type", "application/json")
//
//                Apply to :

//                // POST & UPDATE  set
//                .auth().preemptive().basic("username", "password")
//                .auth().oauth2("")
//                .body("bodyPayload")


                // GET & DELETE you don't need to set an header
//

         // when i send a request method eg GET, post
         .when()
            .get()



        // Then you can validate response from the backend
        .then().assertThat().statusCode(200).log().body();

        //  You can perform extra validation here
    }




    @Test()
    public void GetListGrocery(){

         given()
                 .when()
                 .get("http://fastapi-groceries.herokuapp.com/api/v1/groceries")
                 .then().assertThat().statusCode(200)
                 .log().all();
    }









//
//    @Test(testName = "get first grocery name")
//    public void getAllGroceries(){
//        given()
//                .when()
//                .get("http://fastapi-groceries.herokuapp.com/api/v1/groceries")
//                .then().assertThat().statusCode(200)
//                .and().body("name[0]", equalTo("Bananas")).log().body();
//    }


//
//    @Test(testName = "Groceries contains Peanut Butter")
//    public void Groceries_Contains_Peanut_Butter(){
//        given()
//                .when()
//                .get("http://fastapi-groceries.herokuapp.com/api/v1/groceries")
//                .then().assertThat().statusCode(200)
//                .and().body("name", hasItem("Peanut Butter")).log().body();
//    }



//  @Test
//   void CreateGrocery(){
//
//        String grocery = "{\"grocery_id\":\"4217c573-cf8e-4262-9d1a-168a2c591574\",\"name\":\"string\"," +
//                "\"brand\":\"string\",\"origin\":\"string\",\"quantity\":\"string\",\"size\":0}";
//
//        given().baseUri("http://fastapi-groceries.herokuapp.com/api/v1/groceries")
//                .header("Accept", "application/json")
//                .header("content-type", "application/json")
//                .body(grocery)
//        .when().post()
//        .then().assertThat().statusCode(200).log().body();
//   }
//


}
