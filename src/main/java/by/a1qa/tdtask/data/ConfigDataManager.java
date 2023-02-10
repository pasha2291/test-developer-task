package by.a1qa.tdtask.data;

import by.a1qa.tdtask.utils.fileprocessing.JSONCustomParser;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ConfigDataManager {
    private static final String CONFIG_FILE = "config.json";
    private static final String HOST = "host";
    private static final String PORT = "port";
    private static final String API = "api";
    private static final String WEB = "web";

    private static String getPropertyFromConfigFile(String propertyName) {
        return JSONCustomParser.getElementByNameFromResPath(CONFIG_FILE, propertyName);
    }

    private static String getBaseUrl() {
        StringBuilder baseUrl = new StringBuilder();
        baseUrl.append(getPropertyFromConfigFile(HOST))
                .append(getPropertyFromConfigFile(PORT));
        return baseUrl.toString();
    }

    public static String getBaseApiUrl() {
        StringBuilder baseApi = new StringBuilder();
        baseApi.append(getBaseUrl())
                .append(getPropertyFromConfigFile(API));
        return baseApi.toString();
    }

    public static String getBaseWebUrl() {
        StringBuilder baseWeb = new StringBuilder();
        baseWeb.append(getBaseUrl())
                .append(getPropertyFromConfigFile(WEB));
        return baseWeb.toString();
    }
}
