package bruno.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;
public class EventTest {

    @Test
    void testConstructor_incorrectDateFormat_exceptionThrown() {
        assertThrows(DateTimeParseException.class, ()
                -> new Event("hackathon", "31 August 6pm", "1 September 6pm"));

    }

    @Test
    void testGetString() {
        Task task = new Event("career fair", "2023-08-29 10:00", "2023-08-31 18:00");
        assertEquals("[E][ ] career fair (from: 29 August 2023 10:00 to: 31 August 2023 18:00)",
                task.getString());
    }

    @Test
    void testGetString_markedTask_stringGenerated() {
        Task task = new Event("office hours", "2023-08-29 19:00", "2023-08-29 22:00");
        task.isDone = true;
        assertEquals("[E][X] office hours (from: 29 August 2023 19:00 to: 29 August 2023 22:00)",
                task.getString());
    }

    @Test
    void testGetFileString() {
        Task task = new Event("hackathon", "2023-08-31 18:00", "2023-09-01 18:00");
        assertEquals("E|⭕️|hackathon|2023-08-31 18:00|2023-09-01 18:00", task.getFileString());
    }

    @Test
    void testGetFileString_markedTask_stringGenerated() {
        Task task = new Event("career fair", "2023-08-29 10:00", "2023-08-31 18:00");
        task.isDone = true;
        assertEquals("E|✅|career fair|2023-08-29 10:00|2023-08-31 18:00", task.getFileString());
    }
}
