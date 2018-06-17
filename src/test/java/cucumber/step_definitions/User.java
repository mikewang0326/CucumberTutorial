package cucumber.step_definitions;


import com.wof.db.SQLiteConnectionInstance;
import com.wof.db.SQLiteJDBC;
import com.wof.db.UserItem;
import com.wof.entity.UserEntity;
import com.wof.utils.TextUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.sql.SQLException;


public class User {
    public static String SQL_ERROR_MESSAGE = "[SQLITE_CONSTRAINT_UNIQUE]  A UNIQUE constraint failed (UNIQUE constraint failed: user.email)";
    public static int SQL_ERROR_CODE = 19;

    public UserEntity mRegisterUserEntity;
    public SQLException mSQLException;

    @Given("^I am connected to the WOF database$")
    public void i_am_connected_to_the_WOF_database(){
        Assert.assertNotNull(SQLiteConnectionInstance.getInstance().getConnection());
    }

    @Given("^The WOF database is reseted$")
    public void theWOFDatabaseIsReseted() throws Throwable {
        SQLiteJDBC jdbc = new SQLiteJDBC();
        boolean result = jdbc.reset(SQLiteConnectionInstance.getInstance().getConnection());
        Assert.assertTrue(result);
    }

    @Given("^These information contains firstname \"([^\"]*)\" lastname \"([^\"]*)\" email \"([^\"]*)\" and password \"([^\"]*)\" are all valid$")
    public void theseInformationContainsFirstnameLastnameEmailAndPasswordAreAllValid(String firstname, String lastname, String email, String password) throws Throwable {
        Assert.assertTrue("firstname is invalid", !TextUtils.isEmpty(firstname));
        Assert.assertTrue("lastname is invalid", !TextUtils.isEmpty(lastname));
        Assert.assertTrue("email is invalid", TextUtils.isValidEmail(email));
        Assert.assertTrue("password is invalid", !TextUtils.isEmpty(password));

        mRegisterUserEntity = new UserEntity();
        mRegisterUserEntity.firstname = firstname;
        mRegisterUserEntity.lastname = lastname;
        mRegisterUserEntity.email = email;
        mRegisterUserEntity.password = password;
    }

    @Given("^The email does not exists in the database$")
    public void theEmailDoesNotExistsInTheDatabase() throws Throwable {
        SQLiteJDBC jdbc = new SQLiteJDBC();
        UserItem user = jdbc.selectUserByEmail(SQLiteConnectionInstance.getInstance().getConnection(), mRegisterUserEntity.email);
        Assert.assertFalse(user.isValid());
    }

    @When("^I Register an account by these information$")
    public void iRegisterAnAccountByTheseInformation() throws Throwable{
        SQLiteJDBC jdbc = new SQLiteJDBC();
        int succeed = jdbc.insertUser(SQLiteConnectionInstance.getInstance().getConnection(), mRegisterUserEntity);

        Assert.assertTrue("insert db failed", succeed == 1);
    }

    @Then("^The account should be registered successfully, the information should as same as what I registered$")
    public void theAccountShouldBeRegisteredSuccessfullyTheInformationShouldAsSameAsWhatIRegistered() throws Throwable{
        SQLiteJDBC jdbc = new SQLiteJDBC();
        UserItem userItem  = jdbc.selectUserByEmail(SQLiteConnectionInstance.getInstance().getConnection(), mRegisterUserEntity.email);
        Assert.assertTrue(mRegisterUserEntity.equals(UserItem.convertItemToEntity(userItem)));
    }

    @Given("^The email exists in the database$")
    public void theEmailExistsInTheDatabase() throws Throwable {
        SQLiteJDBC jdbc = new SQLiteJDBC();
        UserItem user = jdbc.selectUserByEmail(SQLiteConnectionInstance.getInstance().getConnection(), mRegisterUserEntity.email);
        Assert.assertTrue(user.isValid());
    }

    @When("^I Register an account by these information with exist email$")
    public void iRegisterAnAccountByTheseInformationWithExistEmail() {
        int succeed = -1;
        try {
            SQLiteJDBC jdbc = new SQLiteJDBC();
            succeed = jdbc.insertUser(SQLiteConnectionInstance.getInstance().getConnection(), mRegisterUserEntity);
        }catch (SQLException e) {
            mSQLException = e;
            succeed = -1;
        }

        Assert.assertTrue("insert db failed", succeed == -1);
    }

    @Then("^Screen will display error information$")
    public void screenWillDisplayErrorInformation() throws Throwable {
        boolean isFail = null != mSQLException && mSQLException.getMessage().equals(SQL_ERROR_MESSAGE)
                && mSQLException.getErrorCode() == SQL_ERROR_CODE;
        if (isFail) {
            System.out.println(mSQLException.getMessage());
        }

        Assert.assertTrue(isFail);
    }

}
