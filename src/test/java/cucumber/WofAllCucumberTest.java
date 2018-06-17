package cucumber;

import cucumber.unit.UserCucumberTest;
import cucumber.unit.VehicleCucumberTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({UserCucumberTest.class, VehicleCucumberTest.class})
public class WofAllCucumberTest {

}