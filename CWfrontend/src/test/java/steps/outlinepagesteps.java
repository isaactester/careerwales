package steps;

import helpers.CommonUtil;
import helpers.PropertyUtil;
import org.openqa.selenium.WebDriver;
import pages.outlinePage;

import static helpers.DriverUtil.getDriver;

/**
 * Created by isaacthomas on 08/10/2018.
 */
public class outlinepagesteps {
    public static WebDriver driver = getDriver();
    private PropertyUtil propUtil = new PropertyUtil();
    private CommonUtil commonUtil = new CommonUtil();

    private pages.outlinePage outlinePage= new outlinePage();
}

