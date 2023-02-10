package by.a1qa.tdtask.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class HostNameUtil {
    private static final String COMPUTER_NAME = "COMPUTERNAME";

    public static String getHostname() {
        return System.getenv(COMPUTER_NAME);
    }
}
