package by.a1qa.tdtask.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonSchemasPaths {
    private static final String JSON_SCHEMAS_PATH = "jsonschemas/%s";
    public static final String TEST_RUN_SCHEMA_PATH = String.format(JSON_SCHEMAS_PATH, "testRunSchema.json");
}
