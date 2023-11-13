package chad.task;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testFromFileFormatForToDo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Task task = Task.fromFileFormat("T | 1 | Read book", formatter);

        assertTrue(task instanceof ToDo);
        assertEquals("Read book", task.getDescription());
        assertTrue(task.isDone());
    }

    @Test
    public void testFromFileFormatForDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Task task = Task.fromFileFormat("D | 0 | Submit Assignment | 2023-12-01 23:59", formatter);

        assertTrue(task instanceof Deadline);
        assertEquals("Submit Assignment", task.getDescription());
        assertFalse(task.isDone());
        assertEquals(LocalDateTime.of(2023, 12, 1, 23, 59), ((Deadline) task).getDueDate());
    }

    @Test
    public void testFromFileFormatForEvent() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Task task = Task.fromFileFormat("E | 1 | Team Meeting | 2023-12-01 14:00 | 2023-12-01 16:00", formatter);

        assertTrue(task instanceof Event);
        assertEquals("Team Meeting", task.getDescription());
        assertTrue(task.isDone());
        assertEquals(LocalDateTime.of(2023, 12, 1, 14, 0), ((Event) task).getStart());
        assertEquals(LocalDateTime.of(2023, 12, 1, 16, 0), ((Event) task).getEnd());
    }
}

