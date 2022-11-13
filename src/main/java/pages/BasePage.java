package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver driver;

    By moreButton = By.xpath("//li[contains(@class, 'nav-item')]//span[text()='More']");
    By title = By.tagName("title");
    

}
