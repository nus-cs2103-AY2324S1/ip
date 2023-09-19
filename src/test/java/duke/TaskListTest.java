package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

public class TaskListTest {

    @Test
    public void testToDoToString() {
        ToDo todo = new ToDo("Buy groceries");
        assertEquals(" [T][ ] Buy groceries", todo.toString());
    }

    @Test
    public void testEventToString() {
        LocalDateTime from = LocalDateTime.of(2023, 9, 15, 10, 0);
        LocalDateTime to = LocalDateTime.of(2023, 9, 15, 12, 0);
        Event event = new Event("Meeting", from, to);
        assertEquals(" [E][ ] Meeting (from: Sep 15 2023 10:00 to: Sep 15 2023 12:00)", event.toString());
    }

    @Test
    public void testDeadlineToString() {
        LocalDateTime deadlineTime = LocalDateTime.of(2023, 9, 30, 23, 59);
        Deadline deadline = new Deadline("Submit report", deadlineTime);
        assertEquals(" [D][ ] Submit report (by: Sep 30 2023 23:59)", deadline.toString());
    }

    @Test
    public void testToDoMarkAsDone() {
        ToDo todo = new ToDo("Read a book");
        todo.markAsDone();
        assertEquals(" [T][X] Read a book", todo.toString());
    }

    @Test
    public void testEventMarkAsDone() {
        LocalDateTime from = LocalDateTime.of(2023, 9, 15, 10, 0);
        LocalDateTime to = LocalDateTime.of(2023, 9, 15, 12, 0);
        Event event = new Event("Meeting", from, to);
        event.markAsDone();
        assertEquals(" [E][X] Meeting (from: Sep 15 2023 10:00 to: Sep 15 2023 12:00)", event.toString());
    }

    @Test
    public void testDeadlineMarkAsDone() {
        LocalDateTime deadlineTime = LocalDateTime.of(2023, 9, 30, 23, 59);
        Deadline deadline = new Deadline("Submit report", deadlineTime);
        deadline.markAsDone();
        assertEquals(" [D][X] Submit report (by: Sep 30 2023 23:59)", deadline.toString());
    }
}

