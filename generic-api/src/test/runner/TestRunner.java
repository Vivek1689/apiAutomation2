package runner;

import com.aventstack.extentreports.ExtentReports;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.ReportUtils;
import utils.extentReport.ExtentManager;

import java.io.IOException;

@CucumberOptions(
        features = "src/test/java/features/"
        ,glue={"hooks","stepdefs"},
        plugin = {"html:target/site/cucumber-pretty", "json:target/cucumber.json"},
        tags = "@Smoke"
)
public class TestRunner extends AbstractTestNGCucumberTests{

    public static ExtentReports extent;
//    @Override
//    @DataProvider(parallel = true)
//    public Object[][] scenarios() {
//        return super.scenarios();
//    }

    @BeforeSuite
    public void beforeSuite() throws IOException {
        System.out.println("Before suite");
        extent = ExtentManager.createExtentReport();
    }

    @AfterSuite
    public void afterSuite(){
        extent.flush();
        System.out.println("After suite");
        ReportUtils.generateJVMReport();
    }


}
