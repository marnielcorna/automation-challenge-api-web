package Runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/features/api/apitest.feature","src/test/resources/features/web/webtest.feature"},
        glue = {"StepDefinitions"},
        tags = "@api_test",
        plugin ={""}
)
public class Run extends AbstractTestNGCucumberTests {

}
