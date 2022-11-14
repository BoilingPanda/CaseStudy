package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    WebDriver driver;
    static WebDriverWait wait;
    protected Actions action;

    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        action = new Actions(driver);
        waitUntilPageLoad();
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//li[contains(@class, 'nav-item')]//span[text()='More']")
    WebElement moreButton;
    @FindBy(xpath = "//h5[text()='Careers']")
    WebElement careersButton;

    public String getPageTitle(){
        return driver.getTitle();
    }

    public WebElement clickMoreMenuButton(){
        moreButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.dropdown-menu.show")));
        return driver.findElement(By.cssSelector("div.dropdown-menu.show"));
    }

    public CareersPage goToCareersPage(){
        careersButton.click();
        return new CareersPage(driver);
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }




    public static void waitUntilPageLoad() {
        wait.until((ExpectedCondition<Boolean>) driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    public void scrollToElement(WebElement element) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        wait.until(ExpectedConditions.visibilityOf(element));
        Thread.sleep(2000);;
    }

    public void switchTab(String originalWindow, By selector) {
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

}
