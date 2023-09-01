package corgi.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

public class TaskTest {
    @Test
    public void markAsDone_alreadyDoneTask_throwsTaskStatusException() {
        // Create todo, deadline, event with status initially marked as done
        ToDo todo = new ToDo(true, "Test Task");
        Deadline deadline = new Deadline(true, "Test Deadline", LocalDate.of(2023, 9, 22));
        Event event = new Event(true, "Test Event", LocalDate.of(2023, 9, 22), LocalDate.of(2023, 10, 1));


        // Attempt to mark them as done again and expect a TaskStatusException
        assertThrows(TaskStatusException.class, () -> todo.markAsDone());
        assertThrows(TaskStatusException.class, () -> deadline.markAsDone());
        assertThrows(TaskStatusException.class, () -> event.markAsDone());
    }

    @Test
    public void markAsNotDone_alreadyNotDoneTask_throwsTaskStatusException() {
        // Create todo, deadline, event with status initially marked as undone
        ToDo todo = new ToDo(false, "Test Task");
        Deadline deadline = new Deadline(false, "Test Deadline", LocalDate.of(2023, 9, 22));
        Event event = new Event(false, "Test Event", LocalDate.of(2023, 9, 22), LocalDate.of(2023, 10, 1));

        // Attempt to mark them as not done again and expect a TaskStatusException
        assertThrows(TaskStatusException.class, () -> todo.markAsNotDone());
        assertThrows(TaskStatusException.class, () -> deadline.markAsNotDone());
        assertThrows(TaskStatusException.class, () -> event.markAsNotDone());
    }
}
