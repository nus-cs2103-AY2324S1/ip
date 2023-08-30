package bruno.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    void testGetString_unmarkedTask_stringGenerated() {
        Task task = new Task(TaskType.TODO, "work");
        assertEquals("[ ] work", task.getString());
    }

    @Test
    void testGetString_markedTask_stringGenerated() {
        Task task = new Task(TaskType.DEADLINE, "project submission");
        task.isDone = true;
        assertEquals("[X] project submission", task.getString());
    }

    @Test
    void testGetFileString_unmarkedTask_stringGenerated() {
        Task task = new Task(TaskType.EVENT, "ctf");
        assertEquals("⭕️|ctf", task.getFileString());
    }

    @Test
    void testGetFileString_markedTask_stringGenerated() {
        Task task = new Task(TaskType.TODO, "laundry");
        task.isDone = true;
        assertEquals("✅|laundry", task.getFileString());
    }

    @Test
    void testConvertToLocalDateTime() {
        String s = "2023-08-29 18:00";
        LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 29, 18, 00);
        Task task = new Task(TaskType.EVENT, "work");
        assertEquals(localDateTime, task.convertToLocalDateTime(s));
    }

    @Test
    void testConvertToLocalDateTime_incorrectFormat_exceptionThrown() {
        String s = "01-09-2023 10:00";
        LocalDateTime localDateTime = LocalDateTime.of(2023, 9, 1, 10, 00);
        Task task = new Task(TaskType.TODO, "work");
        assertThrows(DateTimeException.class, () -> task.convertToLocalDateTime(s));
    }

    @Test
    void testConvertToLocalDateTime_invalidDate_exceptionThrown() {
        String s = "2023-13-01 23:59";
        Task task = new Task(TaskType.DEADLINE, "sign up");
        assertThrows(DateTimeException.class, () -> task.convertToLocalDateTime(s));
    }

    @Test
    void testConvertDateTimeToString() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 9, 2, 10, 0);
        Task task = new Task(TaskType.EVENT, "interview");
        assertEquals("02 September 2023 10:00", task.convertDateTimeToString(localDateTime));
    }
}
