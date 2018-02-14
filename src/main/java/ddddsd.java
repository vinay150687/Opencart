import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class ddddsd {
	
	
	WebDriver driver;
    String url;

 public void name()
{
url = "http://10.159.34.101:4444/wd/hub";
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("chrome");
            capabilities.setPlatform(Platform.WINDOWS);
            driver = new RemoteWebDriver(new URL(url), capabilities);
        }catch(Exception e){
            e.printStackTrace();
        }

}
}
