package cucumber.unit;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "features/user.feature", format = {"pretty", "html:target/site/cucumberpretty",
        "json:target/cucumber.json"}, glue = "cucumber/step_definitions", snippets = SnippetType.CAMELCASE)
public class UserCucumberTest {

}