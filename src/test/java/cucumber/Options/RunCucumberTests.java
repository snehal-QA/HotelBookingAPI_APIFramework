package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
	tags = "@Regression",
	features="src/test/java/features",
	plugin ="html:target/jsonReports/cucumber-report.html"
	,glue= {"stepDefinitions"}
	,dryRun = true)
public class RunCucumberTests {

}
