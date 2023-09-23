package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test cases for the {@link ToDo} class.
 */
public class ToDoTest {

    /**
     * Test the {@link ToDo#toString()} method.
     */
    @Test
    public void toStringTest() {
        ToDo test1 = new ToDo("test");
        assertEquals(test1.toString(), "[T][ ] test");
    }

    /**
     * Test the {@link ToDo#isDone()} method.
     */
    @Test
    public void doneTest() {
        ToDo test2 = new ToDo("test");
        test2.done();
        assertTrue(test2.isDone());
    }

    /**
     * Test the {@link ToDo#isDone()} method.
     */
    @Test
    public void unDoneTest() {
        ToDo test3 = new ToDo("test");
        assertFalse(test3.isDone());
    }
}
