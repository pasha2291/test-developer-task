package by.a1qa.tdtask.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;
import java.util.Date;

@UtilityClass
public class TimeUtil {
    private static final String TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
    private static final SimpleDateFormat SDF = new SimpleDateFormat(TIME_FORMAT);

    @SneakyThrows
    public static Date convertStringToDate(String time) {
        return SDF.parse(time);
    }

    public static String getCurrentTime() {
        return new SimpleDateFormat(TIME_FORMAT).format(new Date());
    }
}
