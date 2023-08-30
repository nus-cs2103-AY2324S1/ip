package bruno.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    void testConstructor_incorrectDateFormat_exceptionThrown() {
        assertThrows(DateTimeParseException.class, ()
                -> new Deadline("sign up for competition", "01-09-2023 5pm"));
    }

    @Test
    void testGetString() {
        Task task = new Deadline("project", "2023-08-29 18:00");
        assertEquals("[D][ ] project (by: 29 August 2023 18:00)", task.getString());
    }

    @Test
    void testGetString_markedTask_stringGenerated() {
        Task task = new Deadline("ip tasks", "2023-09-01 16:00");
        task.isDone = true;
        assertEquals("[D][X] ip tasks (by: 01 September 2023 16:00)", task.getString());
    }

    @Test
    void testGetFileString() {
        Task task = new Deadline("clean fridge", "2023-08-31 23:59");
        assertEquals("D|⭕️|clean fridge|2023-08-31 23:59", task.getFileString());
    }

    @Test
    void testGetFileString_markedTask_stringGenerated() {
        Task task = new Deadline("complete registration", "2023-09-02 10:00");
        task.isDone = true;
        assertEquals("D|✅|complete registration|2023-09-02 10:00", task.getFileString());
    }
}
