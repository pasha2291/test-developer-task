package by.a1qa.tdtask.constants;

import lombok.Getter;

@Getter
public enum TestRunFields {
    NAME(1), METHOD(2), RESULT(3), START_TIME(4), END_TIME(5), DURATION(6);

    private final int id;

    TestRunFields(int id) {
        this.id = id;
    }
}
