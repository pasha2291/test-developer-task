package by.a1qa.tdtask.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TestDataFilePaths {
    private static final String TEST_DATA_PATH = "testdata/%s";
    public static final String VARIANT_FILE_PATH = String.format(TEST_DATA_PATH, "variant.json");
    public static final String CREDENTIALS_FILE_PATH = String.format(TEST_DATA_PATH, "credentials.json");
    public static final String PROJECT_ID_FILE_PATH = String.format(TEST_DATA_PATH, "projectid.json");
    public static final String PROJECT_NAME_FILE_PATH = String.format(TEST_DATA_PATH, "project.json");
}
