package api.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public synchronized static ExtentReports getInstance() {

        if (extent == null) {

            // 🔥 Generate timestamp
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                    .format(new Date());

            String reportPath = System.getProperty("user.dir")
                    + "/Reports/ExtentReport_" + timeStamp + ".html";

            // Create Reports folder if not exists
            File folder = new File(System.getProperty("user.dir") + "/Reports");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            ExtentSparkReporter spark =
                    new ExtentSparkReporter(reportPath);

            spark.config().setReportName("API Automation Report");
            spark.config().setDocumentTitle("REST Assured Report");
            spark.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Framework", "REST Assured");
            extent.setSystemInfo("Tester", "Shashikant");
        }

        return extent;
    }
}
