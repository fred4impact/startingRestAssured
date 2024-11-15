package regres;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Test_Post_Put {

    private static final Logger logg = LoggerFactory.getLogger("Test_Post_Put");


    @Test
    public void postUser(){


        String uRl = "https://reqres.in/api/users";
        logg.info("App Resource :" + uRl);


        RegUser postData = new RegUser("Tobiaz", "Executive");

        given().header("Content-type", "application/json")
        .body(postData)
        .when().post(uRl)
        .then().assertThat().statusCode(201)
                .and().body("name", equalTo("Tobiaz"))
                .log().body();
    }




    @Test
    public void putTest_On_Regres(){

//        String data = "{\n" +
//                "    \"name\": \"Joko\",\n" +
//                "    \"job\": \"BA\"\n" +
//                "}";

        RegUser data = new RegUser("John Brown", " IT SAvy");
        given().header("Content-type", "application/json").body(data)
                .when().put("https://reqres.in/api/users/2")
                .then().assertThat().statusCode(200).log().all();
    }


}

// POJO
class RegUser{

     public RegUser(String name, String job){
       this.name = name ;
       this.job = job;

     }

     @JsonProperty("name")
     String name ;


     @JsonProperty("job")
     String job;


}