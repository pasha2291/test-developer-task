package by.a1qa.tdtask.data;

import by.a1qa.tdtask.constants.TestDataFilePaths;
import by.a1qa.tdtask.utils.fileprocessing.JSONCustomParser;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestDataManager {
    private static final String VARIANT = "variant";
    private static final String VARIANT_TO_COMPARE = "variantToCompare";
    private static final String USER_NAME = "username";
    private static final String PASSWORD = "password";
    private static final String PROJECT_ID = "projectId";
    private static final String PROJECT_BASE_NAME = "projectBaseName";
    private static final String PROJECT_NAME = "projectName";

    public static String getVariant() {
        return JSONCustomParser.getElementByNameFromResPath(TestDataFilePaths.VARIANT_FILE_PATH, VARIANT);
    }

    public static String getVariantToCompare() {
        return JSONCustomParser.getElementByNameFromResPath(TestDataFilePaths.VARIANT_FILE_PATH, VARIANT_TO_COMPARE);
    }

    public static String getUserName() {
        return JSONCustomParser.getElementByNameFromResPath(TestDataFilePaths.CREDENTIALS_FILE_PATH, USER_NAME);
    }

    public static String getPassword() {
        return JSONCustomParser.getElementByNameFromResPath(TestDataFilePaths.CREDENTIALS_FILE_PATH, PASSWORD);
    }

    public static String getProjectId() {
        return JSONCustomParser.getElementByNameFromResPath(TestDataFilePaths.PROJECT_ID_FILE_PATH, PROJECT_ID);
    }

    public static String getProjectName() {
        return JSONCustomParser.getElementByNameFromResPath(TestDataFilePaths.PROJECT_ID_FILE_PATH, PROJECT_NAME);
    }

    public static String getCurrentProjectName() {
        return JSONCustomParser.getElementByNameFromResPath(TestDataFilePaths.PROJECT_NAME_FILE_PATH, PROJECT_BASE_NAME);
    }
}
