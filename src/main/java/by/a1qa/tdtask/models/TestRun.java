package by.a1qa.tdtask.models;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Data
public class TestRun {
    private String duration;
    private String method;
    private String name;
    private String startTime;
    private String endTime;
    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TestRun testRun = (TestRun) o;
        return Objects.equals(getDuration(), testRun.getDuration())
                && Objects.equals(getMethod(), testRun.getMethod())
                && Objects.equals(getName(), testRun.getName())
                && Objects.equals(getStartTime(), testRun.getStartTime())
                && Objects.equals(getEndTime(), testRun.getEndTime())
                && StringUtils.equalsIgnoreCase(getStatus(), testRun.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDuration(), getMethod(), getName(), getStartTime(), getEndTime(), getStatus());
    }
}
