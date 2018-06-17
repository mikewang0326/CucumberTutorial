package cucumber.step_definitions;

import com.wof.db.SQLiteConnectionInstance;
import com.wof.db.SQLiteJDBC;
import com.wof.db.UserItem;
import com.wof.db.VehicleItem;
import com.wof.entity.VehicleEntity;
import com.wof.utils.TextUtils;
import com.wof.utils.TimeUtil;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.sql.SQLException;

public class Vehicle {
    public static String SQL_ERROR_MESSAGE = "[SQLITE_CONSTRAINT_PRIMARYKEY]  A PRIMARY KEY constraint failed (UNIQUE constraint failed: vehicle.plate)";
    public static int SQL_ERROR_CODE = 19;

    public VehicleEntity mSubscribeVehicleEntity;
    public SQLException mSQLException;
    public UserItem mLoginUser;


    @Given("^I have login successfully by email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iHaveLoginSuccessfullyByEmailAndPassword(String email, String password) throws Throwable {
        Assert.assertTrue(TextUtils.isValidEmail(email));
        Assert.assertTrue(!TextUtils.isEmpty(password));
        SQLiteJDBC jdbc = new SQLiteJDBC();
        mLoginUser = jdbc.selectUserByEmailAndPassword(SQLiteConnectionInstance.getInstance().getConnection(), email, password);
        Assert.assertTrue("Login failed", null != mLoginUser && mLoginUser.isValid());
    }

    @Given("^vehicle information contains plate \"([^\"]*)\" type \"([^\"]*)\"  make \"([^\"]*)\" model \"([^\"]*)\" manufacture_date \"([^\"]*)\" and fuel_type \"([^\"]*)\" are all valid$")
    public void vehicleInformationContainsPlateTypeMakeModelManufacture_dateAndFuel_typeAreAllValid(String plate, String type, String make, String model, String manufacture_date, String fuel_type) throws Throwable {

        Assert.assertTrue("plate is invalid", !TextUtils.isEmpty(plate));
        Assert.assertTrue("type is invalid", !TextUtils.isEmpty(type));
        Assert.assertTrue("make is invalid", !TextUtils.isEmpty(make));
        Assert.assertTrue("model is invalid", !TextUtils.isEmpty(model));
        Assert.assertTrue("manufacture_date is invalid", TimeUtil.isValidDate(manufacture_date));
        Assert.assertTrue("fuel_type is invalid", !TextUtils.isEmpty(fuel_type));

        mSubscribeVehicleEntity = new VehicleEntity();
        mSubscribeVehicleEntity.plate = plate;
        mSubscribeVehicleEntity.type = type;
        mSubscribeVehicleEntity.make = make;
        mSubscribeVehicleEntity.model = model;
        mSubscribeVehicleEntity.manufacture_date = TimeUtil.convertTimeStringToSqlDate(manufacture_date);
        mSubscribeVehicleEntity.fuel_type = fuel_type;
        mSubscribeVehicleEntity.user_id = mLoginUser.user_id;

        Assert.assertTrue(mSubscribeVehicleEntity.isValid());
    }

    @Given("^The plate does not exists in the database$")
    public void thePlateDoesNotExistsInTheDatabase() throws Throwable {
        SQLiteJDBC jdbc = new SQLiteJDBC();
        VehicleItem vehicleDao = jdbc.selectVehicleByPlate(SQLiteConnectionInstance.getInstance().getConnection(), mSubscribeVehicleEntity.plate);
        Assert.assertFalse(vehicleDao.isValid());
    }

    @When("^I subscribe a vehicle by these information with a new plate$")
    public void iSubscribeAVehicleByTheseInformationWithANewPlate() throws Throwable {
        SQLiteJDBC jdbc = new SQLiteJDBC();
        int succeed = jdbc.insertVehicle(SQLiteConnectionInstance.getInstance().getConnection(), mSubscribeVehicleEntity);
        Assert.assertTrue("insert db failed", succeed == 1);
    }

    @Then("^The vehicle should be subscribed successfully, the information should as same as what I subscribed$")
    public void theVehicleShouldBeSubscribedSuccessfullyTheInformationShouldAsSameAsWhatISubscribed() throws Throwable {
        SQLiteJDBC jdbc = new SQLiteJDBC();
        VehicleItem vehicleDao  = jdbc.selectVehicleByPlate(SQLiteConnectionInstance.getInstance().getConnection(), mSubscribeVehicleEntity.plate);
        Assert.assertTrue(mSubscribeVehicleEntity.equals(VehicleItem.convertItemToEntity(vehicleDao)));
    }

    @Given("^The plate exists in the database$")
    public void thePlateExistsInTheDatabase() throws Throwable {
        SQLiteJDBC jdbc = new SQLiteJDBC();
        VehicleItem vehicleDao = jdbc.selectVehicleByPlate(SQLiteConnectionInstance.getInstance().getConnection(), mSubscribeVehicleEntity.plate);
        Assert.assertTrue(vehicleDao.isValid());
    }

    @When("^I subscribe a vehicle by these information with an existed plate$")
    public void iSubscribeAVehicleByTheseInformationWithAnExistedPlate(){
        int succeed = -1;
        try {
            SQLiteJDBC jdbc = new SQLiteJDBC();
            succeed = jdbc.insertVehicle(SQLiteConnectionInstance.getInstance().getConnection(), mSubscribeVehicleEntity);
        }catch (SQLException e) {
            mSQLException = e;
            succeed = -1;
        }

        Assert.assertTrue("insert db failed", succeed == -1);
    }

    @Then("^Screen will display vehicle subscription error information$")
    public void screenWillDisplayVehicleSubscriptionErrorInformation() throws Throwable {
        boolean isFail = null != mSQLException && mSQLException.getMessage().equals(SQL_ERROR_MESSAGE)
                && mSQLException.getErrorCode() == SQL_ERROR_CODE;
        if (isFail) {
            System.out.println(mSQLException.getMessage());
        }

        Assert.assertTrue(isFail);
    }
}
