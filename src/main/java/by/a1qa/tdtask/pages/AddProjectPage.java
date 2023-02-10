package by.a1qa.tdtask.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AddProjectPage extends Form {
    private static final IElementFactory FACTORY = AqualityServices.getElementFactory();
    private static final ITextBox PROJECT_NAME_INPUT_FIELD = FACTORY.getTextBox(By.id("projectName"), "add project name text box");
    private static final IButton SUBMIT_PROJECT_NAME_BUTTON = FACTORY.getButton(By.xpath("//button[@type='submit']"), "submit project name button");
    private static final ILabel SAVING_SUCCESS_MESSAGE = FACTORY.getLabel(By.xpath("//div[contains(@class, 'alert-success')]"),"saving success message");

    public AddProjectPage() {
        super(PROJECT_NAME_INPUT_FIELD.getLocator(), "add project name text box");
    }

    public void addProjectNameToTextBox(String projectName) {
        PROJECT_NAME_INPUT_FIELD.clearAndType(projectName);
    }

    public void submitProjectNameButtonClick() {
        SUBMIT_PROJECT_NAME_BUTTON.click();
    }

    public boolean isSuccessMessageDisplayed() {
        return SAVING_SUCCESS_MESSAGE.state().isDisplayed();
    }
}
