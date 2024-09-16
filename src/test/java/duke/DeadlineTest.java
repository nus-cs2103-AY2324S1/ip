package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.tasks.Deadline;
import duke.tasks.Task;

public class DeadlineTest {

    @Test
    public void toString_correctStringReturned() {
        Task task = new Deadline(LocalDate.of(2023, 6, 23), "abc");
        assertEquals("[D][ ] abc (by: 2023-06-23)", task.toString());
    }

    @Test
    public void taskToString_correctStringReturned() {
        Task task = new Deadline(LocalDate.of(2023, 6, 23), "abc");
        assertEquals("D | 0 | abc | 2023-06-23", task.tasktoString());
    }

    @Test
    public void markAsDone_markedTaskReturned() {
        Task task = new Deadline(LocalDate.of(2023, 6, 23), "abc");
        task.markAsDone();
        assertEquals("D | 1 | abc | 2023-06-23", task.tasktoString());
    }

    @Test
    public void unMark_unMarkedTaskReturned() {
        Task task = new Deadline(LocalDate.of(2023, 6, 23), "abc");
        task.markAsDone();
        task.unMark();
        assertEquals("D | 0 | abc | 2023-06-23", task.tasktoString());
    }
}
