package Runners;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import net.masterthought.cucumber.Configuration;

@CucumberOptions(
        features = {"src/test/resources/features/api/apitest.feature","src/test/resources/features/web/webtest.feature"},
        glue = {"StepDefinitions"},
        tags = "@api_test",
        plugin ={""}
)
public class Run extends AbstractTestNGCucumberTests {

    @AfterClass
    public static void generateReport() {
        File reportOutputDirectory = new File("target");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber-json-report.json");
        String projectName = "My Project";

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        configuration.addClassification("Environment", "DEV");
        configuration.addClassification("Browser", "Chrome");
        configuration.addClassification("Branch", "master");
        configuration.addClassification("Build Number", "1234");

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }
}
