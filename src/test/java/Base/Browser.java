package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Browser {
    public static WebDriver browser(String browser){
        if(browser.toLowerCase().contains("chrome")){
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--no-sandbox");
            //chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("disable-gpu");
            //chromeOptions.addArguments("--window-size=3840,2160");
            return new ChromeDriver(chromeOptions);
        }
        else if(browser.toLowerCase().contains("firefox")){
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--no-sandbox");
            //firefoxOptions.addArguments("--headless");
            firefoxOptions.addArguments("disable-gpu");
            //firefoxOptions.addArguments("--window-size=3840,2160");
            return new FirefoxDriver(firefoxOptions);
        }

        return null;
    }
}
