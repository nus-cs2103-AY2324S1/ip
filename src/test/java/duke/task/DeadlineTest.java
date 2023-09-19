package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void deadline_invalidDate_exceptionThrown() {
        try {
            assertEquals("deadline deadline (by: Oct 12 2020)", new Deadline("deadline", "2020-10-12"));
            fail();
        } catch (Exception e) {
            assertEquals("date is before today", e.getMessage());
        }
    }

}
