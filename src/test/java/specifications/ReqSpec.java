package specifications;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class ReqSpec {



    private static RequestSpecification respecc;
    private static ResponseSpecification responss;


    @BeforeClass
    public void use_RequestSpec(){

        respecc = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("https://gorest.co.in/public/v2/users")
                .setAuth(oauth2("3b03ac67d8ff4eda624c215babc78dcbf6dc8af304395c031c66e4a66d3a4d73"))
                .build();




       responss = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .expectBody("size()", is(20))
                .expectBody("id", hasItem(3453))
                .build();

    }


     @Test(testName = "Test Get Method")
     public void getAllUserTest(){
         given().spec(respecc )
                 .when().get()
                 .then()
                 .assertThat().
                 statusCode(200)
                .log().body();
     }


    @Test(testName = "Test to verify response body")
    public void verifyResponse_size_and_item(){
        given().spec(respecc )
                .when().get()
                .then().spec(responss)
                .log().all();

    }

    @Test(testName = "Test hasItem ")
    public void checkHasItem(){

        given().when().get("http://jsonplaceholder.typicode.com/users")
                .then().assertThat().statusCode(200).body("email", hasItem("Shanna@melissa.tv")).log().body();
    }



    @Test(testName = "Test for Size  ")
    public void verifySize(){

        given().when().get("http://jsonplaceholder.typicode.com/users")
                .then().body("size()", is(10)).log().body();
    }

    @Test(testName = "Test for address Array")
    public void verifyAddress(){

        given().when().get("http://jsonplaceholder.typicode.com/users")
                .then().assertThat().body("address.street[1]", equalTo("Victor Plains")).log().body();
    }

    @Test(testName = "Test for Logging")
    public void verifyLoggin() {

        given().when().get("http://jsonplaceholder.typicode.com/users")
                .then().log().all().and().assertThat().statusCode(200);


    }

    @Test(testName = "Test for path paramter")
    public void testingPathParam() {

        given().pathParam("breweries", "madtree-brewing-cincinnati")
                .when()
                 .get("https://api.openbrewerydb.org/breweries/{breweries}")
                 .then()
                 .log().body();



    }

    @Test(testName = "Test for auth preemptive")
    public void  authorizationsBasic() {

        given().header("","")
                .auth().preemptive().basic("","")
                .body("")
                .when().post("")
                .then().log().all();

    }


    @Test(testName = "Test for auth Basic preemptive")
    public void  authorizationAuth2Token() {

        given().header("","")
                .auth().oauth2("")
                .body("")
                .when().post("")
                .then().log().all();

    }



    @Test(testName = "Test for path query param")
    public void testingQueryParam() {

        given().queryParam("by_name", "cooper")
                .when()
                .get("https://api.openbrewerydb.org/breweries")
                .then()
                .log().body();

    }

   /* https://api.openbrewerydb.org/breweries?by_name=cooper https://api.openbrewerydb.org/breweries?by_name=modern%20times*/
    // Using data provider
    @Test(testName = "Test dataprovider", dataProvider = "name")
    public void dataTesting(String name) {

        given().queryParam("by_name", name)
                .when()
                .get("https://api.openbrewerydb.org/breweries")
                .then()
                .log().body();

    }

  @DataProvider(name = "name")
  public  Object[][] sendName(){
        return new Object[][]{
                {"cooper"},
                {"modern%20times"}
        };
  }

}


// Plain old java Object POJO
class Create{

    public Create(String name, String email, String body){
        this.name = name ;
        this.email = email;
        this.body = body;
    }

    @JsonProperty("name")
    String name;

    @JsonProperty("email")
    String email;

    @JsonProperty("body")
    String body;



}