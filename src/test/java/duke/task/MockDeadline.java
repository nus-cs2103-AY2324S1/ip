package duke.task;

import java.time.LocalDateTime;

public class MockDeadline extends Deadline {
    public MockDeadline(String description) {
        super(description, null);
    }

    @Override
    public String toFileString() {
        return "Mock Deadline File String";
    }
}
