package duke;

import duke.tasks.Deadline;
import duke.tasks.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testToString() {
        Task task = new Deadline(LocalDate.of(2023, 6, 23), "abc");
        assertEquals("[D][ ] abc (by: 2023-06-23)", task.toString());
    }

    @Test
    public void testTaskToString() {
        Task task = new Deadline(LocalDate.of(2023, 6, 23), "abc");
        assertEquals("D | 0 | abc | 2023-06-23", task.tasktoString());
    }

    @Test
    public void testMarkAsDone() {
        Task task = new Deadline(LocalDate.of(2023, 6, 23), "abc");
        task.markAsDone();
        assertEquals("D | 1 | abc | 2023-06-23", task.tasktoString());
    }

    @Test
    public void testUnMark() {
        Task task = new Deadline(LocalDate.of(2023, 6, 23), "abc");
        task.markAsDone();
        task.unMark();
        assertEquals("D | 0 | abc | 2023-06-23", task.tasktoString());
    }
}
