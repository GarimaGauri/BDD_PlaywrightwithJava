package Steps;

import Pages.HomePage;
import com.microsoft.playwright.*;
import io.cucumber.java.*;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;


public class Hooks {
    Context context;

    //    public Hooks(Context cucumberContext){
//        this.context = cucumberContext;
//    }
    public static Browser getPlaywrightBrowserInstance(){
        Playwright playwright = Playwright.create();
        return playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    public BrowserContext createBrowserContext(){
        Browser browser = getPlaywrightBrowserInstance();
        return  browser.newContext(new Browser.NewContextOptions().setViewportSize(1920, 1080));
    }
    @BeforeAll
    public static void beforeAll(){
        BrowserContext browserContext = getPlaywrightBrowserInstance().newContext();
        Page page = browserContext.newPage();
        new HomePage(page);
        page.close();
    }

    @Before
    public void Setup()
    {
        context.browserContext= createBrowserContext();
        context.page = context.browserContext.newPage();
    }

    @After
    public void tearDown(Scenario scenario){
        String imagePath = System.getProperty("user.dir") + "/target/images/" + new Date().getTime() + ".png";
        if(scenario.isFailed())
        {
            Path path = Paths.get(imagePath);
            Allure.addAttachment("Error screenshot", new ByteArrayInputStream(context.getPage().screenshot()));
            context.getPage().screenshot(new Page.ScreenshotOptions().setFullPage(true).setPath(path));
            scenario.log(path.toAbsolutePath().toString());
        }
        context.browserContext.close();
    }

    @AfterAll
    public static void afterAll(){
    }

}

