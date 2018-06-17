package junit.suit;

import com.wof.db.UserDaoTest;
import com.wof.db.UserItemTest;
import com.wof.db.VehicleDaoTest;
import com.wof.db.VehicleItemTest;
import com.wof.entity.UserEntityTest;
import com.wof.entity.VehicleEntityTest;
import com.wof.utils.TextUtilsTest;
import com.wof.utils.TimeUtilTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({VehicleItemTest.class,VehicleDaoTest.class, VehicleEntityTest.class})
public class VehicleUnitTestSuit {

}
