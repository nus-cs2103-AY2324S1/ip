package dude.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void createDeadlineTest() {
        String description = "Assignment";
        LocalDateTime by = LocalDateTime.of(2023, 9, 11, 23, 59);
        Deadline deadline = new Deadline(description, by);
        String expected = "[D][ ] Assignment (by: 11 Sep 2023 11:59 PM)";
        assertEquals(expected, deadline.toString());
    }

}
