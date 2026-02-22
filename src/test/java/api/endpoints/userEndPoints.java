package api.endpoints;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;

import api.payload.pojoClass;
import api.utilities.BaseSpec;
import api.utilities.Routes;
import io.restassured.response.Response;

// 🔹 Create User (POST)
//Endpoints class handles API actions
//Response is an object that stores the complete HTTP response returned by the API, including status code, headers, and body.
// Endpoint = API URL + HTTP Method

//
public class userEndPoints 
{
	
    public static Response createUser(pojoClass payload) throws FileNotFoundException 
    {

        return given()
                .spec(BaseSpec.getRequestSpec())
                .body(payload)
        .when()
                .post(Routes.post_url);
    }
    
 
    public static Response getUser(String username) {

        return given()
                .spec(BaseSpec.getRequestSpec())
                .pathParam("username", username)
        .when()
                .get(Routes.get_url);
    }
    
}
