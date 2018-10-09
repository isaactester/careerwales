package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;

/**
 * Created by isaacthomas on 08/10/2018.
 */
public class DriverUtil {
    public static WebDriver driver;
    public static File file;


    public static void setDriver() {


        if (driver == null) {




			file = new File ("/Users/isaacthomas/Downloads/chromedriver-2");
			System.setProperty("webdriver.chrome.driver",file.getAbsolutePath());
			driver = new ChromeDriver();
		}


		/*	driver = new FirefoxDriver();
			file = new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefoxDriver.exe");
			System.setProperty("webdriver.firefox.driver", file.getAbsolutePath());
			driver = new FirefoxDriver();

		}
	}*/


        /*    file = new File("C:\\Users\\Isaac\\Desktop\\IEDriverServer.exe");
            System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
            driver = new InternetExplorerDriver();
            driver.manage().window().maximize();
        }*/
    }


    public static WebDriver getDriver() {
        setDriver();
        return driver;

    }

    public static void closeDriver() {
        driver.close();
    }
}

