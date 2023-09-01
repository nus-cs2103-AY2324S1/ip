package roo.task;

import roo.RooException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNull;


public class TodoTest {

    @Test
    public void constructor_validInput1_success() {
        try {
            Todo todo = new Todo("borrow book");
            assertEquals("[T][ ] borrow book", todo.toString());
        } catch (RooException exception) {
            fail();
        }
    }

    @Test
    public void constructor_invalidInput_fail() {
        try {
            Todo todo = new Todo("");
            fail();
        } catch (RooException exception) {
            assertEquals("Description is EMPTY!!!\n", exception.getMessage());
        }
    }

    @Test
    public void getDate() {
        try {
            Todo todo = new Todo("borrow book");
            assertNull(todo.getDate());
        } catch (RooException exception) {
            fail();
        }
    }
}
