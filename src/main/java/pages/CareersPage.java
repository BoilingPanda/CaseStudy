package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class CareersPage extends BasePage{
    WebDriver driver;


    public CareersPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);

    }

    @FindBy(className = "elementor-element-a8e7b90")
    WebElement lifeAtInsiderSection;
    @FindBy(className = "elementor-element-8ab30be")
    WebElement locationSection;
    @FindBy(className = "elementor-element-b6c45b2")
    WebElement teamsSection;
    @FindBy(css = "li.glide__slide")
    List<WebElement> locationItems;
    @FindBy(className = "loadmore")
    WebElement loadMoreTeamsButton;

    By teamItem = By.className("job-item");

    public TeamPage clickTeamButton(String team) throws InterruptedException {
        WebElement teamButton = driver.findElement(teamItem);
        WebElement currentTeamButton = teamButton.findElement(
                By.xpath(String.format("//a/h3[contains(@class,'text-center') and text()='%s']",team)));
        scrollToElement(currentTeamButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",currentTeamButton);

        return new TeamPage(driver);
    }

    public WebElement getLocationSection(){
        return locationSection;
    }

    public WebElement getTeamsSection(){
        return teamsSection;
    }

    public WebElement getLifeAtInsiderSection(){
        return lifeAtInsiderSection;
    }

    public int getLocationItemsCount(){
        return locationItems.size();
    }

    public void clickLoadMoreButton() throws InterruptedException {
        scrollToElement(loadMoreTeamsButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loadMoreTeamsButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a/h3[contains(@class,'text-center') and text()='Quality Assurance']")));

    }


}
