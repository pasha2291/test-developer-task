package by.a1qa.tdtask.utils;

import by.a1qa.tdtask.models.TestRun;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

@UtilityClass
public class ListUtil {
    public static boolean isTestRunListOrderedByDateDesc(List<TestRun> list) {
        for(int i = 1; i < list.size(); i++) {
            Date firstDate = TimeUtil.convertStringToDate(list.get(i - 1).getStartTime());
            Date secondDate = TimeUtil.convertStringToDate(list.get(i).getStartTime());
            if(!firstDate.after(secondDate)) {
                return false;
            }
        }
        return true;
    }

    public boolean isTestListContainsSpecifiedRecord(List<TestRun> testRunList, TestRun testRunRecord) {
        for(TestRun current : testRunList) {
            if(StringUtils.equalsIgnoreCase(current.getMethod(), testRunRecord.getMethod())
                    && StringUtils.equalsIgnoreCase(current.getName(), testRunRecord.getName())
                    && StringUtils.equalsIgnoreCase(current.getStatus(), testRunRecord.getStatus())) {
                return true;
            }
        }
        return false;
    }
}
