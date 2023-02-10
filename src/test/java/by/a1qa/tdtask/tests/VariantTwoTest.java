package by.a1qa.tdtask.tests;

import by.a1qa.tdtask.apisteps.PostSteps;
import by.a1qa.tdtask.apisteps.ResponseSteps;
import by.a1qa.tdtask.constants.JsonSchemasPaths;
import by.a1qa.tdtask.constants.Parameters;
import by.a1qa.tdtask.constants.TestStatus;
import by.a1qa.tdtask.data.TestDataManager;
import by.a1qa.tdtask.models.TestRun;
import by.a1qa.tdtask.pages.AddProjectPage;
import by.a1qa.tdtask.pages.MainPage;
import by.a1qa.tdtask.pages.ProjectPage;
import by.a1qa.tdtask.utils.BrowserUtil;
import by.a1qa.tdtask.utils.ListUtil;
import by.a1qa.tdtask.utils.ProjectNameUtil;
import by.a1qa.tdtask.utils.logger.CustomLogger;
import by.a1qa.tdtask.utils.logger.LogUtil;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class VariantTwoTest extends BaseTest {

    @Test
    public void runTest() {
        CustomLogger.info("Start of main test scenario;");

        String actualVariant = TestDataManager.getVariant();
        Response tokenResponse = PostSteps.getTokenByVariant(actualVariant);
        String token = ResponseSteps.getString(tokenResponse);

        CustomLogger.info("Step 1: Assert that token is generated");
        ResponseSteps.checkStatusCode(tokenResponse, HttpStatus.SC_OK);
        Assert.assertTrue(token != null && !token.isEmpty(), "Token should be generated!");

        BrowserUtil.addCookie(Parameters.TOKEN, token);
        browser.refresh();
        MainPage mainPage = new MainPage();

        CustomLogger.info("Step 2: Assert that MainPage is opened and has a true variant label value.");
        Assert.assertTrue(mainPage.state().isDisplayed(), "MainPage should be opened!");
        Assert.assertEquals(mainPage.getVariant(), TestDataManager.getVariantToCompare(), "MainPage should have a true variant label value!");

        String projectName = TestDataManager.getProjectName();
        ProjectPage nexageProjectPage = mainPage.openProjectByName(projectName);

        String nexageId = TestDataManager.getProjectId();
        Response nexageJsonTestListResponse = PostSteps.getJsonTestListByProjectId(nexageId);

        CustomLogger.info("Step 3.0: Assert that api request returns a json format.");
        ResponseSteps.checkResponseIsJson(nexageJsonTestListResponse, JsonSchemasPaths.TEST_RUN_SCHEMA_PATH);

        List<TestRun> nexageTestRunListFromApi = ResponseSteps.getList(nexageJsonTestListResponse, TestRun.class);
        List<TestRun> nexageTestRunListFromUi =  nexageProjectPage.getTestRunList();

        CustomLogger.info("Step 3.1: Assert that UI test records are in descending order");
        Assert.assertTrue(ListUtil.isTestRunListOrderedByDateDesc(nexageTestRunListFromUi), "UI test records should be in descending order!");

        CustomLogger.info("Step 3.2: Assert that UI test records equal to API test records.");
        Assert.assertTrue(nexageTestRunListFromApi.containsAll(nexageTestRunListFromUi), "UI test records should be equal to API test records!");

        browser.goBack();
        String mainPageTab = browser.tabs().getCurrentTabHandle();

        mainPage.addProjectButtonClick();
        browser.tabs().switchToLastTab();

        AddProjectPage addProjectPage = new AddProjectPage();
        String uniqueProjectName = ProjectNameUtil.getUniqueProjectName();
        addProjectPage.addProjectNameToTextBox(uniqueProjectName);
        addProjectPage.submitProjectNameButtonClick();

        CustomLogger.info("Step 4.0: Assert that success saving message appeared.");
        Assert.assertTrue(addProjectPage.isSuccessMessageDisplayed(), "Success saving message should be visible.");

        browser.tabs().closeTab();
        browser.tabs().switchToTab(mainPageTab);
        browser.refresh();

        CustomLogger.info("Step 4.1: Assert that new project appeared.");
        Assert.assertTrue(mainPage.isProjectCreatedByName(uniqueProjectName), "New project should be visible!");

        ProjectPage newProjectPage = mainPage.openProjectByName(uniqueProjectName);
        List<TestRun> recordList = newProjectPage.getTestRunList();

        CustomLogger.info("Step 5.0: Assert that test record contains no records yet.");
        Assert.assertTrue(recordList.isEmpty(), "New project should not contain any test records!");

        Response addTestResponse = PostSteps.addNewTestRecord(SID, uniqueProjectName, testRun.getName(), testRun.getMethod());
        String testRecordId = ResponseSteps.getString(addTestResponse);
        PostSteps.updateTestStatus(testRecordId, TestStatus.PASSED.toString());
        PostSteps.addLog(testRecordId, LogUtil.getLog());
        PostSteps.addPicture(testRecordId, browser.getScreenshot());

        newProjectPage.isFirstTestRecordAppeared();
        recordList = newProjectPage.getTestRunList();

        CustomLogger.info("Step 5.1: Assert that new test record was added.");
        Assert.assertFalse(recordList.isEmpty(), "New project should contain new test record!");

        CustomLogger.info("Step 5.2: Assert that new test record equals to our record.");
        Assert.assertTrue(ListUtil.isTestListContainsSpecifiedRecord(recordList, testRun), "New project should contain new test record!");

        CustomLogger.info("End of main test scenario;");
    }
}
