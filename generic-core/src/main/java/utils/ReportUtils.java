package utils;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import net.masterthought.cucumber.json.support.Status;
import net.masterthought.cucumber.presentation.PresentationMode;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReportUtils {

    public static void generateJVMReport(){



        File reportOutputDirectory = new File("reports");

        // File reportOutputDirectory1 = new File("reports1");

        List<String> jsonFiles = new ArrayList<>();

        jsonFiles.add("target/cucumber.json");

        /*

         * List<String> jsonFiles1 = new ArrayList<>();

         * jsonFiles1.add("target1/cucumber.json");

         */





        String buildNumber = "1";

        String projectName = "cucumberProject";



        Configuration configuration = new Configuration(reportOutputDirectory, projectName);

        // Configuration configuration1 = new Configuration(reportOutputDirectory1, projectName);

// optional configuration - check javadoc for details

        configuration.addPresentationModes(PresentationMode.RUN_WITH_JENKINS);

        //   configuration1.addPresentationModes(PresentationMode.RUN_WITH_JENKINS);

// do not make scenario failed when step has status SKIPPED

        configuration.setNotFailingStatuses(Collections.singleton(Status.SKIPPED));

        //    configuration1.setNotFailingStatuses(Collections.singleton(Status.SKIPPED));

        configuration.setBuildNumber(buildNumber);

        //    configuration1.setBuildNumber(buildNumber);

// addidtional metadata presented on main page

        configuration.addClassifications("Platform", "Windows");

        configuration.addClassifications("Browser", "Firefox");

        configuration.addClassifications("Branch", "release/1.0");



// optionally add metadata presented on main page via properties file

//        List<String> classificationFiles = new ArrayList<>();

//        classificationFiles.add("properties-1.properties");

//        classificationFiles.add("properties-2.properties");

//        configuration.addClassificationFiles(classificationFiles);



        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);

        //  ReportBuilder reportBuilder1 = new ReportBuilder(jsonFiles1, configuration1);

        Reportable result = reportBuilder.generateReports();

        //    Reportable result1 = reportBuilder1.generateReports();

// and here validate 'result' to decide what to do if report has failed





    }
}
