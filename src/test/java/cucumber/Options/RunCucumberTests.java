package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
	tags = "@Regression",
	features="src/test/resources/features",
	plugin ={"pretty", "html:target/Reports/cucumber-report.html",
			 "json:target/Reports/cucumber.json","junit:target/Reports/cucumber.xml"},
	glue= {"stepDefinitions"},
	dryRun = false,
    monochrome = true)

public class RunCucumberTests {

}
