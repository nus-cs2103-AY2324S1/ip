package jerma.tasks; //same package as the class being tested

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeParseException;

public class DeadlineTest {
    private final Task deadline = new Deadline("desc", "2019-08-09");

    @Test
    public void deadline_constructor_success() {
        assertEquals("[D][ ] desc (by 2019-08-09)", deadline.toString());
    }

    @Test
    public void deadline_constructor_invalidDate_exception() {
        assertThrows(DateTimeParseException.class,
                () -> new Deadline("desc", "by"));
    }

    @Test
    public void todo_save_success() {
        assertEquals("D|0|desc|2019-08-09", deadline.save());
    }

    @Test
    public void todo_setDone_success() {
        deadline.setDone();
        assertEquals("[D][X] desc (by 2019-08-09)", deadline.toString());
    }

    @Test
    public void todo_setUndone_success() {
        deadline.setUndone();
        assertEquals("[D][ ] desc (by 2019-08-09)", deadline.toString());
    }

}