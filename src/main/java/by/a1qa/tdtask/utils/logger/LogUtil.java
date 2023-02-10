package by.a1qa.tdtask.utils.logger;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;

@UtilityClass
public class LogUtil {
    private static final String FILE_NAME = "log.log";
    private static final String PATH_FROM_TARGET = "target/log";
    private static final String INFO = "INFO";
    private static final File FILE = new File(getLogFullPath());
    private static final int LINES_QUANTITY_LIMIT = 50;
    private static final int LINE_LENGTH_LIMIT = 150;

    @SneakyThrows
    private static String getLogFullPath() {
        return Paths.get(PATH_FROM_TARGET, FILE_NAME).toUri().toURL().getPath();
    }

    @SneakyThrows
    public static String getLog() {
        BufferedReader br = new BufferedReader(new FileReader(FILE));
        StringBuilder result = new StringBuilder();
        String line = br.readLine();
        int linesCount = 0;
        while (line != null && linesCount <= LINES_QUANTITY_LIMIT) {
            linesCount++;
            if(!line.contains(INFO) && line.length() <= LINE_LENGTH_LIMIT) {
                result.append(line);
                result.append(System.lineSeparator());
            }
            line = br.readLine();
        }
        br.close();
        return result.toString();
    }

    public static void deleteLogFile() {
        FILE.delete();
    }
}
