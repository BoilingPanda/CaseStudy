package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TeamPage extends BasePage {

    WebDriver driver;
    public TeamPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(linkText = "See all QA jobs")
    WebElement allJobsButton;

    public OpenPositionPage goToOpenPositionPage(){
        allJobsButton.click();

        return new OpenPositionPage(driver);
    }
}
