package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("filter-by-location")));
        return new OpenPositionPage(driver);
    }
}
