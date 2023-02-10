package by.a1qa.tdtask.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import by.a1qa.tdtask.constants.TestRunFields;
import by.a1qa.tdtask.models.TestRun;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class ProjectPage extends Form {
    private static final String PROJECT_DYNAMIC_LOCATOR = "//li[contains(text(),'%s')]";
    private static final String TEST_RECORD_DYNAMIC_LOCATOR = "//table[@id='allTests']//tr[%d]//td[%d]";
    private static final By TEST_RECORDS_LOCATOR = By.xpath("//table[@id='allTests']//tr");
    private static final String EMPTY_LIST_MESSAGE = "there is no tests";
    private static final int COUNT_FROM = 2;

    public ProjectPage(String projectName) {
        super(By.xpath(String.format(PROJECT_DYNAMIC_LOCATOR, projectName)), String.format("%s project page", projectName));
    }

    private TestRun getTestRunObject(String name, String method, String status, String startTime, String endTime, String duration) {
        TestRun testRun = new TestRun();
        testRun.setName(name.isEmpty() ? null : name);
        testRun.setMethod(method.isEmpty() ? null : method);
        testRun.setStatus(status.isEmpty() ? null : status);
        testRun.setStartTime(startTime.isEmpty() ? null : startTime);
        testRun.setEndTime(endTime.isEmpty() ? null : endTime);
        testRun.setDuration(duration.isEmpty() ? null : duration);
        return testRun;
    }

    public List<TestRun> getTestRunList() {
        List<TestRun> testRunList = new ArrayList<>();
        int runRecordsQuantity = getElementFactory().findElements(TEST_RECORDS_LOCATOR, ILabel.class).size();
        for(int i = COUNT_FROM; i <= runRecordsQuantity; i++) {
            String name = getElementFactory().getLabel(By.xpath(String.format(TEST_RECORD_DYNAMIC_LOCATOR, i, TestRunFields.NAME.getId())), "Name test record label").getText();
            if(name.contains(EMPTY_LIST_MESSAGE)) {
                return testRunList;
            }
            String method = getElementFactory().getLabel(By.xpath(String.format(TEST_RECORD_DYNAMIC_LOCATOR, i, TestRunFields.METHOD.getId())), "Method test record label").getText();
            String status = getElementFactory().getLabel(By.xpath(String.format(TEST_RECORD_DYNAMIC_LOCATOR, i, TestRunFields.RESULT.getId())), "Result test record label").getText();
            String startTime = getElementFactory().getLabel(By.xpath(String.format(TEST_RECORD_DYNAMIC_LOCATOR, i, TestRunFields.START_TIME.getId())), "Start time test record label").getText();
            String endTime = getElementFactory().getLabel(By.xpath(String.format(TEST_RECORD_DYNAMIC_LOCATOR, i, TestRunFields.END_TIME.getId())), "End time test record label").getText();
            String duration = getElementFactory().getLabel(By.xpath(String.format(TEST_RECORD_DYNAMIC_LOCATOR, i, TestRunFields.DURATION.getId())), "Duration test record label").getText();
            TestRun testRun = getTestRunObject(name, method, status, startTime, endTime, duration);
            testRunList.add(testRun);
        }
        return testRunList;
    }

    public boolean isFirstTestRecordAppeared() {
        AqualityServices.getConditionalWait().waitFor(() -> !this.getTestRunList().isEmpty());
        return !getTestRunList().isEmpty();
    }
}
