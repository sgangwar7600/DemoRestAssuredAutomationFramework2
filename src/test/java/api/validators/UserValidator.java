package api.validators;

import api.utilities.BaseSpec;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class UserValidator {

	public static void validateCreateUser(Response res) {

	    res.then()
	       .spec(BaseSpec.getResponseSpec())
           .body("code", equalTo(200));
	}
	
	public static void validateGetUser(Response res, String expectedUsername) {

	    res.then()
	       .spec(BaseSpec.getResponseSpec())
	       .body("username", equalTo(expectedUsername))
	       .body("id", notNullValue());
	}
	
	public static void validateUserSchema(Response res) {

	    res.then()
	       .body(matchesJsonSchemaInClasspath("schemas/userSchema.json"));
	}


}
