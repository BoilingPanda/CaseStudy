package Base;


import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshot implements MethodRule{

    WebDriver driver;
    String path;

    public Screenshot(WebDriver driver, String path) {
        this.driver = driver;
        this.path = path;
    }

    @Override
    public Statement apply(final Statement statement, final FrameworkMethod method, Object target) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    statement.evaluate();
                } catch (Throwable throwable) {
                    SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmm");
                    captureScreenshot(driver, method.getName() + "_" +formatter.format(new Date()));
                    throw throwable;
                }
            }

            public void captureScreenshot(WebDriver driver, String fileName) {
                try {
                    new File(path).mkdirs();
                    try ( FileOutputStream out = new FileOutputStream(path + File.separator + "screenshot_" + fileName + ".png")) {
                        out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
                    }
                } catch (IOException | WebDriverException e) {
                    System.out.println("screenshot failed:" + e.getMessage());
                }
            }

        };
    }

}

