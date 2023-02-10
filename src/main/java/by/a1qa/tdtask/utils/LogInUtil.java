package by.a1qa.tdtask.utils;

import by.a1qa.tdtask.utils.logger.CustomLogger;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LogInUtil {
    private static final String SEMICOLON = ":";
    private static final String AT = "@";
    private static final String HTTP = "http://";

    public static String pasteCredentialsInUrl(String baseUrl, String username, String password) {
        StringBuilder result = new StringBuilder();
        result
                .append(HTTP)
                .append(username)
                .append(SEMICOLON)
                .append(password)
                .append(AT)
                .append(baseUrl.substring(HTTP.length()));
        CustomLogger.info(String.format("LogInUtil.getCredentialsInUrl(): %s", result.toString()));
        return result.toString();
    }
}
