package junit;

import junit.suit.UserUnitTestSuit;
import junit.suit.VehicleUnitTestSuit;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({UserUnitTestSuit.class, VehicleUnitTestSuit.class})
public class WofAllUnitTest {

}