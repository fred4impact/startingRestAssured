package regres;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class E2eTesting {


    private static final Logger log = LoggerFactory.getLogger("End2End ");

    @Test(testName = "Test using jsonpath")
    public void Json_Path_Test(){

        String URL = "https://gorest.co.in/public/v2/users";
        log.info("Step 1: Resource url :" + URL);

        log.info("Step 2:  Get user response");
        Response  response =

                given().when().get(URL).andReturn();

        log.info(" Step 3: Assert status ");
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println("Status code" + response.getStatusCode());;

        log.info(" Step 4: Print the Body response");
        System.out.println(response.getBody().prettyPrint());

        log.info("Step 5: Get list of IDs in the user database ");
        System.out.println(response.getBody().jsonPath().getList("id"));

        log.info(" Step 6: Assert that the array of the data contains");
        Assert.assertTrue(response.getBody()
                .jsonPath()
                .getList("id")
                        .contains(4098));

        log.info("Test Successful ......");



    }




    // Test using Query Parameter
    @Test(testName = "Test: Providing data to query state", dataProvider = "state")
    public void useDataProvider_Test_Query_State(String state){

        String URL = "https://api.openbrewerydb.org/breweries";

        given()
                .queryParam("by_state", state).when().get(URL)
                .then().assertThat().statusCode(200).log().body();
    }


    // data provider for state
    @DataProvider(name="state")
    public Object[][] createStateData() {
        return new Object[][]
                {
                        {"ohio"},
                        {"new_york"},
                        {"new%20mexico"}
                };

    }



}
