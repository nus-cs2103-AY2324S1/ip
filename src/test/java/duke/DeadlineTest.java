package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tasks.Deadline;

public class DeadlineTest {
    @Test
    public void deadlineTest() {
        Deadline dl = new Deadline("test", "2019-10-15");
        assertEquals("Oct 15 2019", dl.getBy());
    }
}
