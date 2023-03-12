package reporting;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Reporting {

    public static void generateReport() {
        File reportOutputDirectory = new File("target");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber-json-report.json");
        String projectName = "My Project";

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        configuration.addClassifications("Environment", "Test");
        configuration.addClassifications("Browser", "Chrome");
        configuration.addClassifications("Branch", "master");
        configuration.addClassifications("Build Number", "10");

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }
}
