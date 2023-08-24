package Pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage {

    Page page;
    String emailfield ="//div[@class='input__container login__email']//input[@placeholder='E-Mail-Adresse*']";
    String passwordField ="input[autocomplete='current-password']";
    String Loginbutton=".button.button__primary.login__button";
    String emailValidationIcon ="//div[@class='input__inner-wrapper input__inner-wrapper--error']//*[name()='svg']";
    String emailInvalidIcon ="//div[@class='input__container login__email']//div[@class='input__inner-wrapper input__inner-wrapper--error']//*[name()='svg']";
    String RequiredEmailMessage ="//div[@class='input__container login__email']//div[@class='input__error']";
    String passwordEyeIcon ="//div[@class='input__inner-wrapper input__inner-wrapper--error']//button[@type='button']//*[name()='svg']";
    String LoginErrorTitle ="div[class='alert alert--error'] div span[class='google-translate-fallback']";
    String RequiredPasswordMessage ="//div[@class='input__container login__password']//div[@class='input__error']";
    String InvalidCredentialsMessage ="//div[@class='alert alert--error']";
    String RememberMe ="checkbox-remember-me";
    String EmailSentText=".headline-thin.headline-thin--large";
    String EmailConfirm="//div[@class='forgot-password__confirmation-email']";
    String emailSentText ="E-Mail verschickt";
    String InvalidEmailError=".input__error";
    String ForgotPasswordText ="Passwort vergessen?";
    String LoginButtonText ="Anmelden";
    String DialogCloseText="Schliessen";

    public LoginPage(Page page) {
        this.page = page;
    }
    public void EnterUserCredentials(String username, String password)
    {
        if(page.locator(emailfield).isEditable()) {
        page.locator(emailfield).fill(username);
    }
        if(page.locator(passwordField).isEditable()){
        page.locator(passwordField).fill(password);
        }
    }

    public void ClickOnLoginButton(){
        assertThat(page.locator(Loginbutton)).containsText(LoginButtonText);
        page.getByTestId("grid").getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName(LoginButtonText)).click();
    }

    public void ClickOnForgotPasswordLink()
    {
        page.getByText(ForgotPasswordText).click();
    }

    public void EnterEmailAddressToResetPassword(String emailaddress) throws InterruptedException {
        Thread.sleep(3000);
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("E-Mail-Adresse*")).fill(emailaddress);
    }

    public void clickOnSubmitButton(){
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("E-Mail absenden")).click();
    }

    public void validateErrorMessageForInvalidEmail() {
        if (page.locator(emailValidationIcon).isVisible()) {
            assertThat(page.locator(InvalidEmailError)).containsText("Ungültige E-Mail-Adresse");
        }
    }

    public void validateErrorMessageforBlankEmail()
    {
        if (page.locator(emailInvalidIcon).isVisible()) {
            assertThat(page.locator(RequiredEmailMessage)).containsText("* Pflichtfeld");
        }
    }

    public void validateErrorMessageforBlankPassword()
    {
        if (page.locator(passwordEyeIcon).isVisible()) {
            assertThat(page.locator(RequiredPasswordMessage)).containsText("* Pflichtfeld");
        }
    }
    public void validateLoginErrorTitle(){

        assertThat(page.locator(LoginErrorTitle)).containsText("Bitte überprüfe deine Angaben");
    }
    public void validateRequiredFieldMessageOnLogin(){
        validateLoginErrorTitle();
        validateErrorMessageforBlankEmail();
        validateErrorMessageforBlankPassword();
    }
    public void ValidateInvalidCredentialsError()
    {
        assertThat(page.locator(InvalidCredentialsMessage)).containsText("Falsche Zugangsdaten\n");
    }

    public void EnableRememberMeCheckBox(){
        if(!page.getByTestId(RememberMe).isChecked()){
        page.getByTestId(RememberMe).check();}
    }
    public void validateEmailSentDialog(String emailaddress)
    {
        assertThat(page.locator(EmailConfirm)).containsText(emailaddress);
        assertThat(page.locator(EmailSentText)).containsText(emailSentText);
    }
    public void CloseEmailSentConfirmationDialog()
    {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(DialogCloseText)).click();
    }

}
