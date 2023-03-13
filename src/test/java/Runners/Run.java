package Runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import reporting.Reporting;

@CucumberOptions(
        features = {"src/test/resources/features/api/apitest.feature","src/test/resources/features/web/webtest.feature"},
        glue = {"StepDefinitions"},
        tags = "@api_test",
        plugin = {"json:target/cucumber-json-report.json"}
)
public class Run extends AbstractTestNGCucumberTests {

    @AfterSuite
    public static void generateReport() {
        Reporting.generateReport();
    }
}
