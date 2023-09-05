package duke.test;
import org.junit.jupiter.api.Test;
import duke.task.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
