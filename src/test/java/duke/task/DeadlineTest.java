package duke.task;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class contains JUnit test cases to verify the functionality of the Deadline class.
 * It tests various methods of the Deadline class, such as taskString(), markAsDone(), markAsNotDone(),
 * and getTask().
 */
public class DeadlineTest {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    Deadline test = new Deadline("assignment submission", LocalDateTime.parse("2023-09-11 2359", formatter));
    @Test
    void testTaskString() {
        assert test.taskString().equals("[D][ ] assignment submission (by: Sep 11 2023 2359)");
    }

    @Test
    void testMarkAsDone() {
        test.markAsDone();
        assert test.taskString().equals("[D][X] assignment submission (by: Sep 11 2023 2359)");
    }

    @Test
    void testNotMarkAsDone() {
        test.markAsNotDone();
        assert test.taskString().equals("[D][ ] assignment submission (by: Sep 11 2023 2359)");
    }

    @Test
    void testGetTask() {
        assert test.getTask().equals("assignment submission");
    }

}
