package api.testcases;

import api.endpoints.userEndPoints;
import api.payload.pojoClass;
import api.validators.UserValidator;
import io.restassured.response.Response;
import java.io.FileNotFoundException;
import org.testng.annotations.Test;

public class testCases {

	@Test(priority = 1)
	public void testCreateUser() throws FileNotFoundException {

	    pojoClass payload = new pojoClass();
	    payload.setUsername("Shashi");


	    Response response = userEndPoints.createUser(payload);

	    UserValidator.validateCreateUser(response);
	}
	
	
	@Test(priority = 2)
	public void getDetails() {

	    Response response = userEndPoints.getUser("Shashi");

	    UserValidator.validateGetUser(response, "Shashi");
	}

	
	

	}

    

