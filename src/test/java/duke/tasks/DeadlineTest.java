package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void convertTask_uncompleted() {
        Deadline deadline = new Deadline(0, "bake cookies", LocalDateTime.now());
        String expected = "D | 0 | bake cookies | " + LocalDateTime.now().format(formatter);
        assertEquals(expected, deadline.convertTask());
    }

    @Test
    public void convertTask_completed() {
        Deadline deadline = new Deadline(1, "bake cookies", LocalDateTime.now());
        String expected = "D | 1 | bake cookies | " + LocalDateTime.now().format(formatter);
        assertEquals(expected, deadline.convertTask());
    }

    @Test
    public void toString_uncompleted() {
        Deadline deadline = new Deadline(0, "bake cookies", LocalDateTime.now());
        String expected = "[D] [ ] bake cookies (by: " + LocalDateTime.now().format(formatter) + ")";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void toString_completed() {
        Deadline deadline = new Deadline(1, "bake cookies", LocalDateTime.now());
        String expected = "[D] [X] bake cookies (by: " + LocalDateTime.now().format(formatter) + ")";
        assertEquals(expected, deadline.toString());
    }
}
