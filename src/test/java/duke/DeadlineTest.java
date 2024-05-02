package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test cases for the {@link Deadline} class.
 */
public class DeadlineTest {
    /**
     * Test the {@link Deadline#toString()} method.
     */
    @Test
    public void toStringTest() {
        Deadline test1 = new Deadline("HAMBURGER", "2023-09-24" );
        assertEquals(test1.toString(), "[D][ ] HAMBURGER (by: Sep 24 2023)");
    }

    /**
     * Test the {@link Deadline#isDone()} method.
     */
    @Test
    public void doneTest() {
        Deadline test2 = new Deadline("HAMBURGER", "2023-09-25");
        test2.done();
        assertTrue(test2.isDone());
    }

    /**
     * Test the {@link Deadline#isDone()} method.
     */
    @Test
    public void unDoneTest() {
        Deadline test3 = new Deadline("FRIES", "2023-09-06");
        assertFalse(test3.isDone());
    }
}
