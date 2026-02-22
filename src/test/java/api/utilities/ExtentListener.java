package api.utilities;

import com.aventstack.extentreports.*;
import org.testng.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentListener implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {

        extent = ExtentManager.getInstance();

        String startTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
                .format(new Date());

        extent.setSystemInfo("Execution Start Time", startTime);
    }

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest =
                extent.createTest(result.getMethod().getMethodName());

        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {

        String endTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
                .format(new Date());

        extent.setSystemInfo("Execution End Time", endTime);

        extent.flush();
    }

    public static ExtentTest getTest() {
        return test.get();
    }
}
