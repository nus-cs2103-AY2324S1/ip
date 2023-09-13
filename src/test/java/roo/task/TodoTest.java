package roo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import roo.RooException;

import java.util.ArrayList;

public class TodoTest {

    @Test
    public void constructor_validInput1_success() {
        try {
            Todo todo = new Todo("borrow book", new ArrayList<>());
            assertEquals("[T][ ] borrow book", todo.toString());
        } catch (RooException exception) {
            fail();
        }
    }

    @Test
    public void constructor_invalidInput_fail() {
        try {
            Todo todo = new Todo("", new ArrayList<>());
            fail();
        } catch (RooException exception) {
            assertEquals("Description is EMPTY!!!\n", exception.getMessage());
        }
    }

    @Test
    public void getDate() {
        try {
            Todo todo = new Todo("borrow book", new ArrayList<>());
            assertNull(todo.getDate());
        } catch (RooException exception) {
            fail();
        }
    }
}
