package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class OpenPositionPage extends BasePage {

    WebDriver driver;

    List<WebElement> openPositionsList;

    public OpenPositionPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(name = "filter-by-location")
    WebElement locationFilter;
    @FindBy(name = "filter-by-department")
    WebElement departmentFilter;
    By openPositions = By.cssSelector("div.position-list-item-wrapper");
    By positionTitle = By.cssSelector("p.position-title");
    By positionDepartment = By.cssSelector("span.position-department");
    By positionLocation = By.cssSelector("div.position-location");
    By applyNowButton = By.xpath("//a[text()='Apply Now']");

    public void selectLocationFilter(String location){
        Select filterByLocation = new Select(locationFilter);
        filterByLocation.selectByVisibleText(location);
    }
    public void selectDepartmentFilter(String department) throws InterruptedException {
        Select filterByDepartment = new Select(departmentFilter);
        filterByDepartment.selectByVisibleText(department);
        scrollToElement(driver.findElement(By.id("career-position-list")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(openPositions));
    }

    public boolean checkOpenPositionItem(String department, String location, String title ){
        openPositionsList = driver.findElements(openPositions);
        for(WebElement element:openPositionsList){
            if (!element.findElement(positionDepartment).getText().toLowerCase().contains(department.toLowerCase()) ||
                    !element.findElement(positionLocation).getText().toLowerCase().contains(location.toLowerCase()) ||
                    !element.findElement(positionTitle).getText().toLowerCase().contains(title.toLowerCase()) ||
                    !(element.findElements(applyNowButton).size()>0)) {
                System.out.println(element.findElement(positionDepartment).getText() + " - " + department);
                System.out.println(element.findElement(positionDepartment).getText().toLowerCase().contains(department.toLowerCase()));
                System.out.println(element.findElement(positionLocation).getText() + " - " + location);
                System.out.println(element.findElement(positionLocation).getText().toLowerCase().contains(location.toLowerCase()));
                System.out.println(element.findElement(positionTitle).getText() + " - " + title);
                System.out.println(element.findElement(positionTitle).getText().toLowerCase().contains(title.toLowerCase()));
                System.out.println((element.findElements(applyNowButton).size()>0));
                return false;
            }
        }
        return true;
    }

    public boolean clickToApplyNowButton(){
        String originalWindow = driver.getWindowHandle();
        openPositionsList = driver.findElements(openPositions);
        for(WebElement element:openPositionsList){
            element.findElement(applyNowButton).click();
            switchTab(originalWindow, By.cssSelector("div.postings-btn-wrapper"));
            if(!driver.getCurrentUrl().contains("jobs.lever.co")){
                return false;
            }
            driver.close();
            driver.switchTo().window(originalWindow);

        }
        return true;
    }
}
