import cucumber.WofAllCucumberTest;
import junit.WofAllUnitTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({WofAllUnitTest.class, WofAllCucumberTest.class})
public class WofAllTest {

}
