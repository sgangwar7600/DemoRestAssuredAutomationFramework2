package api.utilities;

import com.aventstack.extentreports.ExtentTest;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.*;

import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentRestFilter implements Filter {

    // 🔥 Centralized Logger
    private static final Logger log =
            LoggerUtil.getLogger(ExtentRestFilter.class);

    private String getTime() {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
                .format(new Date());
    }

    @Override
    public Response filter(
            FilterableRequestSpecification requestSpec,
            FilterableResponseSpecification responseSpec,
            FilterContext ctx) {

        ExtentTest test = ExtentListener.getTest();

        long startTime = System.currentTimeMillis();

        // ===============================
        // 🔹 LOG REQUEST
        // ===============================

        log.info("Request URI: {}", requestSpec.getURI());

        if (requestSpec.getBody() != null) {
        	log.info("Request Body: {}", 
        	        requestSpec.getBody().toString());        }

        if (test != null) {
            test.info("[" + getTime() + "] Request URI: "
                    + requestSpec.getURI());

            if (requestSpec.getBody() != null) {
                test.info("[" + getTime() + "] Request Body:<pre>"
                        + requestSpec.getBody()
                        + "</pre>");
            }
        }

        // ===============================
        // 🔹 EXECUTE REQUEST
        // ===============================

        Response response = ctx.next(requestSpec, responseSpec);

        long endTime = System.currentTimeMillis();
        long responseTime = endTime - startTime;

        // ===============================
        // 🔹 LOG RESPONSE
        // ===============================

        log.info("Status Code: {}", response.getStatusCode());
        log.info("Response Time: {} ms", responseTime);
        log.info("Response Body: {}",
                response.getBody().asPrettyString());

        if (test != null) {
            test.info("[" + getTime() + "] Status Code: "
                    + response.getStatusCode());

            test.info("[" + getTime() + "] Response Time: "
                    + responseTime + " ms");

            test.info("[" + getTime() + "] Response Body:<pre>"
                    + response.getBody().asPrettyString()
                    + "</pre>");
        }

        return response;
    }
}
