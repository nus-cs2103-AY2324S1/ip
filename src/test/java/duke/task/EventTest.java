package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    void testString() {
        Event task = new Event("test", "2024-12-01 1532", "2024-12-03 1600");
        assertEquals(task.toString(), "[E][ ] test (from: Dec 1 2024 3:32PM to Dec 3 2024 4:00PM)");
    }

    @Test
    void testInvalidDeadline() {
        try {
            new Event("test", "1234", "1234");
        } catch (Exception e) {
            assertEquals("Please use the following formats:\n"
                    + "event task /from yyyy-mm-dd hhmm /to yyyy-mm-dd hhmm\n"
                    + "deadline task /from dd/mm/yyyy hhmm /to dd/mm/yyyy hhmm", e.getMessage());
        }
    }
}