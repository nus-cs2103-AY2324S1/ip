package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {

    @Test
    public void constructor_validInput1_success() {
        try {
            Todo todo = new Todo("borrow book");
            assertEquals("[T][ ] borrow book", todo.toString());
        } catch (DukeException exception) {
            fail();
        }
    }

    @Test
    public void constructor_invalidInput_fail() {
        try {
            Todo todo = new Todo("");
            fail();
        } catch (DukeException exception) {
            assertEquals("Description is EMPTY!!!\n", exception.getMessage());
        }
    }

    @Test
    public void getDate() {
        try {
            Todo todo = new Todo("borrow book");
            assertNull(todo.getDate());
        } catch (DukeException exception) {
            fail();
        }
    }
}
