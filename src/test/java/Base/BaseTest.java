package Base;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.CareersPage;
import pages.HomePage;
import pages.OpenPositionPage;
import pages.TeamPage;

import static Base.Browser.browser;


public class BaseTest extends SupportingMethods {

    protected static WebDriver driver;
    protected static final String BASE_URL = "https://useinsider.com/";
    protected static HomePage homePage;
    protected static CareersPage careersPage;
    protected static TeamPage teamPage;
    protected OpenPositionPage openPositionPage;


    @BeforeClass
    public static void setUp(){
        String browser = getBrowserParameter();
        driver = browser(browser);
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        driver.findElement(By.id("wt-cli-accept-btn")).click();

        homePage = new HomePage(driver);

    }


    @AfterClass
    public static void tearDown(){
        //driver.quit();
    }
}
