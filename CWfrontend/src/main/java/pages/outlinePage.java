package pages;

import helpers.CommonUtil;
import helpers.PropertyUtil;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static helpers.DriverUtil.getDriver;

/**
 * Created by isaacthomas on 08/10/2018.
 */
public class outlinePage {
    private CommonUtil commonUtil = new CommonUtil();
    private PropertyUtil propUtil = new PropertyUtil();
    public static WebDriver driver = getDriver();


    private String first = propUtil.getElementProperty("firsttext.css");



}
