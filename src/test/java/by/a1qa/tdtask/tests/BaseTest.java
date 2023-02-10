package by.a1qa.tdtask.tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import by.a1qa.tdtask.constants.TestStatus;
import by.a1qa.tdtask.data.ConfigDataManager;
import by.a1qa.tdtask.data.TestDataManager;
import by.a1qa.tdtask.models.TestRun;
import by.a1qa.tdtask.utils.LogInUtil;
import by.a1qa.tdtask.utils.TimeUtil;
import by.a1qa.tdtask.utils.logger.CustomLogger;
import by.a1qa.tdtask.utils.logger.LogUtil;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public abstract class BaseTest {
    protected Browser browser;
    protected TestRun testRun;
    protected String SID;

    @BeforeMethod
    public void initializeTestScenario(Method method) {
        LogUtil.deleteLogFile();
        CustomLogger.info("initializeTestScenario()");
        ITestResult result = Reporter.getCurrentTestResult();
        SID = TimeUtil.getCurrentTime();
        testRun = new TestRun();
        testRun.setName(result.getInstanceName());
        testRun.setMethod(method.getName());
        testRun.setStatus(TestStatus.PASSED.toString());
        browser = AqualityServices.getBrowser();
        browser.maximize();
        String username = TestDataManager.getUserName();
        String password = TestDataManager.getPassword();
        String baseUrl = ConfigDataManager.getBaseWebUrl();
        String url = LogInUtil.pasteCredentialsInUrl(baseUrl, username, password);
        browser.goTo(url);
    }

    @AfterMethod
    public void finalizeTestScenario() {
        browser.quit();
        CustomLogger.info("finalizeTestScenario()");
    }
}
