package duke.tasks;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void testConvertTask1() {
        Deadline deadline = new Deadline(0,"bake cookies", LocalDateTime.now());
        String expected = "D | 0 | bake cookies | " + LocalDateTime.now().format(formatter);
        assertEquals(expected, deadline.convertTask());
    }

    @Test
    public void testConvertTask2() {
        Deadline deadline = new Deadline(1,"bake cookies", LocalDateTime.now());
        String expected = "D | 1 | bake cookies | " + LocalDateTime.now().format(formatter);
        assertEquals(expected, deadline.convertTask());
    }

    @Test
    public void testToString1() {
        Deadline deadline = new Deadline(0,"bake cookies", LocalDateTime.now());
        String expected = "[D] [ ] bake cookies (by: " + LocalDateTime.now().format(formatter) + ")";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void testToString2() {
        Deadline deadline = new Deadline(1,"bake cookies", LocalDateTime.now());
        String expected = "[D] [X] bake cookies (by: " + LocalDateTime.now().format(formatter) + ")";
        assertEquals(expected, deadline.toString());
    }
}
