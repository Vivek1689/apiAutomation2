package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.extentReport.ExtentManager;

import java.util.stream.Collectors;

public class ApiHooks {

    @Before
    public void beforeMethod(Scenario scenario){

        ExtentManager.createTest(scenario.getName());
        ExtentManager.getTest().assignAuthor(getAuthorName(scenario));
        ExtentManager.getTest().assignCategory(System.getProperty("cucumber.filter.tag","Sanity").startsWith("@Regression") ? "Regression": "Smoke");
    }

    @After
    public void afterMethod(){}

    public String getAuthorName(Scenario scenario){
        String name = scenario.getSourceTagNames().stream().filter(e -> e.contains("Author")).collect(Collectors.toList()).get(0).toString();
        return name.split("\\.")[1];
    }
}
