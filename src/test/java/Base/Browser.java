import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browser {
    public WebDriver Browser(String browser){
        if(browser.toLowerCase().contains("chrome")){
            WebDriverManager.chromedriver().setup();
            
        }
    }
}
