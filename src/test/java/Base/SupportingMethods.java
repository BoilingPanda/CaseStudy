package Base;

import org.checkerframework.common.reflection.qual.GetClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SupportingMethods {

    public static String getBrowserParameter(){
        String browserParam = null;

        try {
            Properties property = new Properties();
            InputStream input = null;
            input = SupportingMethods.class.getClassLoader().getResourceAsStream("testConfig.properties");

            property.load(input);

            browserParam = property.getProperty("browser");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return browserParam;
    }
}
