package tests.API;

import API.client.ApiClient;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import tests.CommonBaseTest;

import java.lang.reflect.Method;

public class BaseTest extends CommonBaseTest {

    protected ApiClient apiClient;
    protected ExtentTest test;

    @BeforeClass
    public void setup() {
        apiClient = new ApiClient();
    }

    @BeforeMethod
    public void beforeMethodSetup(Method method) {

        String className = this.getClass().getSimpleName();
        String methodName = method.getName();

        extentTest.set(
                extent.createTest(methodName)
                        .assignCategory("API - " + className)
        );

        test = extentTest.get();
    }
}