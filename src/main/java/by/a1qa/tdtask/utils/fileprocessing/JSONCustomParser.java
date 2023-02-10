package by.a1qa.tdtask.utils.fileprocessing;

import by.a1qa.tdtask.utils.logger.CustomLogger;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

@UtilityClass
public class JSONCustomParser {
    private static JSONParser parser = new JSONParser();

    @SneakyThrows
    public static String getElementByNameFromResPath(String path, String elementName) {
        CustomLogger.info(String.format("JSONCustomParser.getElementByNameFromResourcePath() : %s : %s", path, elementName));
        path = FileFullPath.getFileFullPath(path);
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(path));
        return (String) jsonObject.get(elementName);
    }
}
