package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HomePage {

    Page page;
    String ProfileIcon = "//button[@class='button button__with-icon--transparent account-flyout__button--main']//*[name()='svg']";
    String AcceptAlert ="//button[@class='button button__primary uc-list-button__accept-all']";
    String Logoutlink ="button[class='button button__with-icon--input account-flyout__logout-text account-flyout__logout-text--link']";
    String MyAccountText =".link.link--nav.navigation-list__link.active";
    String MyAccounticon ="//button[@class='button button__with-icon--transparent account-flyout__button--main']//*[name()='svg'][1]";
    public HomePage(Page page) {
        this.page = page;
    }

    public String verifyTitle() {
        String title = page.title();
        return title;}

    public void acceptAlert()
    {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Alle erlauben")).click();
    }
    public void ClickOnProfileIcon()
    {
        page.locator(ProfileIcon).click();
    }
    public void validateAccountPage() throws InterruptedException {
        Thread.sleep(3000);
        page.locator(MyAccounticon).click();
        page.locator("li").filter(new Locator.FilterOptions().setHasText("Mein Douglas")).click();
        assertThat(page.locator(MyAccountText)).containsText("Mein Douglas");
    }
    public void Logout(){
        assertThat(page.locator(MyAccounticon).first()).isInViewport();
        page.locator(MyAccounticon).first().hover();
        assertThat(page.locator(Logoutlink)).isInViewport();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Abmelden")).click();
    }

}
