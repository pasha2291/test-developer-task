package by.a1qa.tdtask.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainPage extends Form {
    private static final IElementFactory FACTORY = AqualityServices.getElementFactory();
    private static final IButton ADD_PROJECT_BUTTON = FACTORY.getButton(By.xpath("//*[contains(@class, 'btn-xs')]"), "add project button");
    private static final ILabel VERSION_LABEL = FACTORY.getLabel(By.xpath("//footer[@class='footer']//span"), "version label");
    private static final ILabel PROJECTS_FORM_LABEL = FACTORY.getLabel(By.xpath("//div[contains(@class, 'panel-default')]"), "projects form");
    private static final String PROJECT_NAME_DYNAMIC_LOCATOR = "//a[text()='%s']";

    public MainPage() {
        super(PROJECTS_FORM_LABEL.getLocator(), "projects form");
    }

    public String getVariant() {
        return VERSION_LABEL.getText();
    }

    public void addProjectButtonClick() {
        ADD_PROJECT_BUTTON.click();
    }

    public boolean isProjectCreatedByName(String projectName) {
        return getElementFactory().getLabel(By.xpath(String.format(PROJECT_NAME_DYNAMIC_LOCATOR, projectName)),"project label").state().isExist();
    }

    public ProjectPage openProjectByName(String projectName) {
        ProjectPage projectPage = new ProjectPage(projectName);
        getElementFactory().getLabel(By.xpath(String.format(PROJECT_NAME_DYNAMIC_LOCATOR, projectName)),"project label").click();
        AqualityServices.getConditionalWait().waitFor(() -> projectPage.state().isDisplayed());
        return projectPage;
    }
}
