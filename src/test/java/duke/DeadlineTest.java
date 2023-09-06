package duke;

import Tasks.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadlineTest() {
        Deadline dl = new Deadline("test", "2019-10-15");
        assertEquals("Oct 15 2019", dl.getBy());
    }
}
