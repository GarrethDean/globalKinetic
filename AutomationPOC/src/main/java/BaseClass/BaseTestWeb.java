package BaseClass;

import Config.DriverFactory;
import Config.GlobalVariables;
import CustomUtils.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;



import java.io.IOException;
import java.lang.reflect.Method;

public class BaseTestWeb {

    public WebDriver driver;
    public WebDriverWait wait;



    public WebDriver getDriver() {
        return driver;
    }
    @BeforeSuite
    public void suiteSetup() throws IOException{

        String url = GlobalVariables.RootURL;
        DriverFactory myDriverFactory = new DriverFactory();
        driver = myDriverFactory.getWebDriver(GlobalVariables.browser);
        driver.manage().window().maximize();
        driver.get(url);
    }

    @BeforeMethod
    public void getMethodName(Method method) {
        // Get the name of the test being run
        GlobalVariables.currentTestName = method.getName();

    }

    @AfterMethod
    public void teardown(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SKIP){
            driver.get(GlobalVariables.RootURL);
        }
    }

    @AfterSuite
    public void endSuite()throws IOException{
        // Close the browser
       //End everything
    }

    @AfterClass
    public void ensClass()throws IOException{
        // Close the browser
        //End everything
        driver.close();
        driver.quit();
    }

}
