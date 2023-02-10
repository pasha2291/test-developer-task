package by.a1qa.tdtask.utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.Cookie;

@UtilityClass
public class BrowserUtil {
    private static Browser browser = AqualityServices.getBrowser();

    public static void addCookie(String parameter, String value) {
        browser.getDriver().manage().addCookie(new Cookie(parameter, value));
    }
}
