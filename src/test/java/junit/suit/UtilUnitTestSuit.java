package junit.suit;

import com.wof.db.UserDaoTest;
import com.wof.db.UserItemTest;
import com.wof.entity.UserEntityTest;
import com.wof.utils.TextUtilsTest;
import com.wof.utils.TimeUtilTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({TextUtilsTest.class, TimeUtilTest.class})
public class UtilUnitTestSuit {

}
