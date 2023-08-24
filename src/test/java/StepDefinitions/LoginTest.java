package StepDefinitions;
import Pages.LoginPage;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import Pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import Steps.Context;

public class LoginTest {
    HomePage home;
    LoginPage loginpg;
    //    Playwright playwright;
//    BrowserType browsertype;
//    Browser browser;
    Page page;
    private final String domain;

    public LoginTest(Page page){
        this.page = page;
        domain = new Context().getDomain();
    }

    @Given("User Access Douglas Application")
    public void setUp() {
        //        playwright = Playwright.create();
//        browsertype = playwright.chromium();
//        browser = browsertype.launch(new BrowserType.LaunchOptions().setHeadless(false));
//        page = browser.newPage();
        page.navigate(domain);
        home = new HomePage(page);
    }
    @When("user navigate to the login page")
    public void usernavigatetotheloginpage() throws InterruptedException {
        home = new HomePage(page);
        Thread.sleep(5000);
        home.acceptAlert();
        home.ClickOnProfileIcon();
    }
    @And("User enter username {string} and password {string}")
    public void userEnterUsernameAndPassword(String username, String password) {
        loginpg= new LoginPage(page);
        loginpg.EnterUserCredentials(username,password);
    }

    @And("User click on Login button")
    public void UserclickonLoginbutton() {
        loginpg = new LoginPage(page);
        loginpg.ClickOnLoginButton();
    }

    @Then("Validate my account page")
    public void validateMyAccountPage() throws InterruptedException{
       home = new HomePage(page);
        home.validateAccountPage();
    }

    @And("User click on Forgot Password link on the login page")
    public void userClickOnForgotPasswordLinkOnTheLoginPage() {
        loginpg = new LoginPage(page);
        loginpg.ClickOnForgotPasswordLink();
    }

    @And("User provide my registered email address {string}")
    public void iProvideMyRegisteredEmailAddress(String emailaddress) throws InterruptedException{
        loginpg = new LoginPage(page);
        loginpg.EnterEmailAddressToResetPassword(emailaddress);
    }

    @And("User click on the Submit button")
    public void userClickOnTheSubmitButton() {
        loginpg = new LoginPage(page);
        loginpg.clickOnSubmitButton();
    }

    @Then("validate the error message if email address is invalid")
    public void validateTheErrorMessageIfEmailAddressIsInvalid() {
        loginpg = new LoginPage(page);
        loginpg.validateErrorMessageForInvalidEmail();
    }

    @Then("validate an error message for blank email and password")
    public void validateAnErrorMessageForBlankEmailAndPassword() {
        loginpg = new LoginPage(page);
        loginpg.validateRequiredFieldMessageOnLogin();
    }

    @Then("Validate an error message indicating that the login credentials are invalid")
    public void validateAnErrorMessageIndicatingThatTheLoginCredentialsAreInvalid() {
        loginpg = new LoginPage(page);
        loginpg.ValidateInvalidCredentialsError();
    }

    @And("logout from the application")
    public void logoutFromTheApplication() {
        home = new HomePage(page);
        home.Logout();
    }

    @And("User enables the Remember me checkbox {string}")
    public void userEnablesTheRememberMeCheckbox(String rememberMe) {
        loginpg = new LoginPage(page);
        if(rememberMe.equals("true")){loginpg.EnableRememberMeCheckBox();}
    }

    @Then("validate the confirmation dialog contains email {string}")
    public void validateTheConfirmationDialogContainsEmail(String emailaddress) {
        loginpg = new LoginPage(page);
        loginpg.validateEmailSentDialog(emailaddress);
    }

    @And("close the Email sent confirmation dialog")
    public void closeTheEmailSentConfirmationDialog() {
        loginpg = new LoginPage(page);
        loginpg.CloseEmailSentConfirmationDialog();
    }
}
