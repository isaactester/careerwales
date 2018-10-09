package helpers;

import cucumber.api.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static helpers.DriverUtil.getDriver;

/**
 * Created by isaacthomas on 08/10/2018.
 */
public class CommonUtil {

    private static final SimpleDateFormat SCREENSHOT_FILENAME_DATE_FORMAT=new SimpleDateFormat("yyyyMMdd'-'HHmmssSSS");
    private int TIMEOUT_IN_SECONDS=120;

    public static WebDriver driver = getDriver();

    WebDriverWait wait = new WebDriverWait(driver,TIMEOUT_IN_SECONDS);

    public static String scenario;

    // scenario name set methods
    public static void setScenarioName(Scenario scenarioArg)  {
        scenario=scenarioArg.getName();
    }

    //methods to wait for elements Implicit and Explict waits
    private static WebDriver RunTest() {
        return null;
    }

    public void waitForElementToBeClickable(By selector){
        wait.until(ExpectedConditions.elementToBeClickable(selector));
    }
    public void waitForElementToBeVisible(By selector){
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));

    }
    public void waitFotTextToBePresentInElement(By selector,int time,String text){
        WebDriverWait wait =new WebDriverWait(driver,time);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(selector, text));
    }
    public void implicitWaitForElement(int time,WebDriver dr){
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    //method to find element by different properties
    public WebElement findElement(String selector, String locatorType){
        WebElement element =null;
        if (locatorType.equals("css")){
            element=driver.findElement(By.cssSelector(selector));
        }else if (locatorType.equals("xpath")){
            element=driver.findElement(By.xpath(selector));
        }else if(locatorType.equals("id")){
            element=driver.findElement(By.id(selector));
        }else if(locatorType.equals("name")){
            element=driver.findElement(By.name(selector));
        }else if(locatorType.equals("className")){
            element=driver.findElement(By.name(selector));

        }
        return element;
    }
    public List<WebElement> findElements(String selector, String locatorType){
        List<WebElement> elements= null;
        if(locatorType.equals("css")){
            elements=driver.findElements(By.cssSelector(selector));
        }else if (locatorType.equals("xpath")){
            elements=driver.findElements(By.xpath(selector));
        }else if(locatorType.equals("id")){
            elements=driver.findElements(By.id(selector));
        }
        return elements;
    }
    //general methods

    public boolean isElementDisplayed(WebElement ele){
        return ele.isDisplayed();
    }
    public void isTextDisplayed(String selector, String text){
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(selector), text));
    }
    public void checkAlert(){
        try{
            WebDriverWait waitAlert= new WebDriverWait(driver,5);
            if(waitAlert.until(ExpectedConditions.alertIsPresent())!=null){
                Alert alert =driver.switchTo().alert();
                alert.accept();
            }
        }catch(Exception e){
            //exception handling
        }
    }

    //operations on objects
    public void enterText(String selector,String locatorType,String backSpace){
        WebElement element=findElement(selector,locatorType);
        element.sendKeys(backSpace);

    }
    public void clickElement(String selector,String locatorType){
        WebElement element=findElement(selector,locatorType);
        element.click();
    }
    public String getAltText(String selector,String locatorType){
        WebElement element = findElement(selector,locatorType);
        return element.getAttribute("alt");
    }
    public void selectDropDownByVisibleText(String selector,String locatorType,String visibleText){
        WebElement dropDownElement=findElement(selector,locatorType);
        Select dropDown =new Select(dropDownElement);
        dropDown.selectByVisibleText(visibleText);
    }
    public void selectDropDownByIndex(String selector,String locatorType,int number){
        WebElement dropDownElement=findElement(selector,locatorType);
        Select dropDown =new Select(dropDownElement);
        dropDown.selectByIndex(number);
    }
    public void selectionRadioButtonRandom(String selector,String selector1,String locatorType,String radioText){
        String text =new String();
        int i=1;
        List<WebElement>elements = findElements(selector,locatorType);
        for(WebElement element:elements){
            WebElement ele =findElement(selector+"["+i+"]"+"//label",locatorType);
            WebElement elerandom =findElement(selector,locatorType);
            text =ele.getText();
            if(text.equals(radioText)){
                ele.click();
                break;
            }else{
                elerandom.click();
            }
            i++;
        }

    }
    public void currentdayofmonth(String selector,String locatorType){
        //String text = new String();
        int i=5;
        List<WebElement> elements = findElements(selector,locatorType);
        //for(WebElement element:elements)
        for(int j=5;j>=1;j--)
        {
            WebElement ele = findElement(selector+"["+i+"]"+"//div[2]//tablethread//tr//td"+"["+j+"]",locatorType);
            String text = ele.getText();
            int text1= Integer.parseInt(text);
            Calendar cal =Calendar.getInstance();
            int day= cal.get(Calendar.DAY_OF_MONTH);
            if(text1==day)
            {
                ele.click();
                break;
            }
        }
    }

    public void executeScript(String script){
        if(driver instanceof JavascriptExecutor){
            ((JavascriptExecutor)driver).executeScript(script);
        }
    }
    public void takeScreenshot(){
        takeScreenShot(null);
    }
    public void takeScreenShot(String label){
        if (driver instanceof TakesScreenshot){
            String workingDir =System.getProperty("user.dir");
            String screenShotDir=workingDir +"//target//selenium-test-screenshots//";
            String fileName=SCREENSHOT_FILENAME_DATE_FORMAT.format(new Date())
                    +(label !=null ? "-" +label : "")+ ".png";
            try{
                byte[]screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
                File file=new File (screenShotDir+fileName);
                int i =0;
                while (file.exists()){
                    file=new File (screenShotDir +fileName+"-" + i++);
                }
                FileUtils.writeByteArrayToFile(file, screenshot);
            } catch (IOException e){
                e.printStackTrace();

            }
        }
    }

    public void takeScreenshot(String nameOfOutputFileIncludingExtension)throws IOException{
        File scrFile=new File("");
        scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        File destination = new File ("target/surefire-reports/"+nameOfOutputFileIncludingExtension);
        System.out.println("Screenshot stored at:"+ destination.getAbsolutePath());
        FileUtils.copyFile(scrFile, destination);
    }

    public void sendKeys(Keys tab){
    }

    public void methodToValidate(String actualMsg, String locatorType, String expectedMsg) throws Throwable{
        Thread.sleep(2000);
        String errorMsg= findElement(actualMsg,locatorType).getText();
        System.out.println(errorMsg);
        Thread.sleep(2000);
        if(errorMsg.equals(expectedMsg)){
            System.out.println("Validation Pass & verified with copy");
        }
        else{
            System.err.println("Ooops!!! copy check does not match &Expected copy should be:" +expectedMsg);
        }

    }
    public void methodToCopyCheck(String actualMsg, String locatorType, String expectedCopy) throws Throwable{
        Thread.sleep(2000);
        String copyMsg = findElement(actualMsg,locatorType).getText();
        System.out.println(copyMsg);
        Thread.sleep(2000);
        if(copyMsg.equals(expectedCopy)){
            System.out.println("Copy check Pass & verified");
        }
        else{
            System.err.println("Ooops!!! copy check does not match &Expected copy should be:" +expectedCopy);
        }

    }



}

