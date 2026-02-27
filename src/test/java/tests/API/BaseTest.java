package tests.API;

import API.client.ApiClient;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.BeforeClass;

import java.lang.reflect.Method;

public class BaseTest {

    protected ApiClient apiClient;
    protected ExtentTest test;
    protected static ExtentReports extent;


    @BeforeClass
    public void setup(Method method) {
        apiClient = new ApiClient();
        String className = this.getClass().getSimpleName();
        String methodName = method.getName();
        test = extent.createTest(methodName).assignCategory(className);
    }
}