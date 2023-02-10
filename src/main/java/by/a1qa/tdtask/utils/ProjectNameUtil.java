package by.a1qa.tdtask.utils;

import by.a1qa.tdtask.data.TestDataManager;
import by.a1qa.tdtask.pages.MainPage;
import by.a1qa.tdtask.utils.logger.CustomLogger;
import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class ProjectNameUtil {
    private static final Random RANDOM = new Random();
    private static final int BOUND = 9999;

    private static String getRandomProjectName() {
        StringBuilder result = new StringBuilder();
        result.append(TestDataManager.getCurrentProjectName());
        result.append(RANDOM.nextInt(BOUND));
        CustomLogger.info(String.format("ProjectNameUtil.getUniqueProjectName() : %s", result));
        return result.toString();
    }

    public static String getUniqueProjectName() {
        String result = getRandomProjectName();
        MainPage mainPage = new MainPage();
        while(mainPage.isProjectCreatedByName(result)) {
            result = getRandomProjectName();
        }
        return result;
    }
}
