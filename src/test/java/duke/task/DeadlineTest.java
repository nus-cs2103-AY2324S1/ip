package duke.task;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Assertions;

/**
 * This class contains JUnit test cases to verify the functionality of the Deadline class.
 * It tests various methods of the Deadline class, such as taskString(), markAsDone(), markAsNotDone(),
 * and getTask().
 */
public class DeadlineTest {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    @Test
    void testTaskString() {
        Deadline test = new Deadline("assignment submission", LocalDateTime.parse("2023-01-11 2359", formatter));
        Assertions.assertEquals(test.taskString(), "[D][ ] assignment submission (by: Jan 11 2023 2359)");
    }

    @Test
    void testMarkAsDone() {
        Deadline test = new Deadline("assignment submission", LocalDateTime.parse("2023-01-11 2359", formatter));
        test.markAsDone();
        Assertions.assertEquals(test.taskString(), "[D][X] assignment submission (by: Jan 11 2023 2359)");
    }

    @Test
    void testNotMarkAsDone() {
        Deadline test = new Deadline("assignment submission", LocalDateTime.parse("2023-01-11 2359", formatter));
        test.markAsDone();
        test.markAsNotDone();
        Assertions.assertEquals(test.taskString(), "[D][ ] assignment submission (by: Jan 11 2023 2359)");
    }

    @Test
    void testGetTask() {
        Deadline test = new Deadline("assignment submission", LocalDateTime.parse("2023-09-11 2359", formatter));
        assert test.getTask().trim().equals("assignment submission");
    }

}
