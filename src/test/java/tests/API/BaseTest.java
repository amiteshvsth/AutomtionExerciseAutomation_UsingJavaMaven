package tests.API;

import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import tests.CommonBaseTest;

import java.lang.reflect.Method;

public class BaseTest extends CommonBaseTest {

    protected ExtentTest test;
    protected SoftAssert softAssert;

    @BeforeMethod
    public void beforeMethodSetup(Method method) {
        softAssert = new SoftAssert();
        
        String className = this.getClass().getSimpleName();
        String methodName = method.getName();

        extentTest.set(
                extent.createTest(methodName)
                        .assignCategory("API - " + className)
        );

        test = extentTest.get();
    }
}