package duke;

import duke.task.Deadline;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {

    @Test
    public void check_deadline_contentLine() {

        Deadline d = new Deadline("Checking", false, LocalDateTime.of(2023, 8, 31, 2, 20));
        assertEquals("D/ /Checking/2023-08-31T02:20", d.contentLine());
    }

    @Test
    public void check_todo_toString() {

        Deadline d = new Deadline("Checking", false, LocalDateTime.of(2023, 8, 31, 2, 20));
        assertEquals("[D][ ] Checking (by: 31/Aug/2023 02:20)", d.toString());
    }
}
