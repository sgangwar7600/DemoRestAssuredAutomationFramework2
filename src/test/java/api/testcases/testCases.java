package api.testcases;

import api.endpoints.userEndPoints;
import api.payload.pojoClass;
import api.validators.UserValidator;
import io.restassured.response.Response;
import java.io.FileNotFoundException;

import org.testng.annotations.Test;

public class testCases {

    private String username = "Shashi";
    private Response getUserResponse;

    @Test(priority = 1)
    public void createUser() throws FileNotFoundException {

        pojoClass payload = new pojoClass();
        payload.setUsername(username);

        Response response = userEndPoints.createUser(payload);
        UserValidator.validateCreateUser(response);
    }

    @Test(priority = 2, dependsOnMethods = "createUser")
    public void getDetails() {

        getUserResponse = userEndPoints.getUser(username);

        UserValidator.validateGetUser(getUserResponse, username);
    }

    @Test(priority = 3, dependsOnMethods = "getDetails")
    public void SchemaValidation() {

        UserValidator.validateUserSchema(getUserResponse);
    }
}