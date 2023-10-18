package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testToStringDeadline() {
        Deadline deadline = new Deadline("finish individual project", "2023-09-09");
        assertEquals(deadline.toString(), "[D][ ] finish individual project (by: Sep 9 2023)");
    }
}
