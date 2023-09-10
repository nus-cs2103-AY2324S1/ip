package jerma.tasks; //same package as the class being tested

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeParseException;

public class EventTest {
    private final Task event = new Event("desc", "2019-08-09", "2019-09-09");

    @Test
    public void deadline_constructor_success() {
        assertEquals("[E][ ] desc (2019-08-09 to 2019-09-09)",
                event.toString());
    }

    @Test
    public void deadline_constructor_invalidDate_exception() {
        assertThrows(DateTimeParseException.class,
                () -> new Event("desc", "from", "to"));
    }

    @Test
    public void todo_save_success() {
        assertEquals("E|0|desc|2019-08-09|2019-09-09", event.save());
    }

    @Test
    public void todo_setDone_success() {
        event.setDone();
        assertEquals("[E][X] desc (2019-08-09 to 2019-09-09)",
                event.toString());
    }

    @Test
    public void todo_setUndone_success() {
        event.setUndone();
        assertEquals("[E][ ] desc (2019-08-09 to 2019-09-09)",
                event.toString());
    }

}