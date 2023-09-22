package juke.tasks;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests the {@code JukeTodo} class.
 */
public class JukeTodoTest {
    /**
     * Tests if the input "this is valid" creates an instance of {@code JukeTodo} properly.
     */
    @Test
    public void constructor_valid_successOne() {
        assertDoesNotThrow(() -> new JukeTodo("this is valid"));
    }

    /**
     * Tests if the input "this is also quite valid" with true creates an instance of {@code JukeTodo} properly.
     */
    @Test
    public void constructor_valid_successTwo() {
        assertDoesNotThrow(() -> new JukeTodo("this is also quite valid", true));
    }

    /**
     * Tests if the input "this is also quite valid" with false creates an instance of {@code JukeTodo} properly.
     */
    @Test
    public void constructor_valid_successThree() {
        assertDoesNotThrow(() -> new JukeTodo("this is also quite valid", false));
    }

    /**
     * Tests if the created todos are properly converted to the correct save String.
     */
    @Test
    public void save_valid_successOne() {
        JukeTodo jukeTodo = new JukeTodo("this is valid");
        assertEquals(jukeTodo.toString(), "[T] [ ] this is valid");
    }

    /**
     * Tests if the created (not completed) todos are properly converted to the correct save String.
     */
    @Test
    public void save_valid_successTwo() {
        JukeTodo jukeTodo = new JukeTodo("this is also quite valid", true);
        assertEquals(jukeTodo.toString(), "[T] [✓] this is also quite valid");
    }

    /**
     * Tests if the created (completed) todos are properly converted to the correct save String.
     */
    @Test
    public void save_valid_successThree() {
        JukeTodo jukeTodo = new JukeTodo("this is also quite valid", false);
        assertEquals(jukeTodo.toString(), "[T] [ ] this is also quite valid");
    }

    /**
     * Tests if the matching function will return correctly if the input string is found within the task
     * description.
     */
    @Test
    public void stringMatches_valid_successOne() {
        JukeTodo jukeTodo = new JukeTodo("this is valid");
        assertTrue(jukeTodo.stringMatches("this "));
    }

    /**
     * Tests if the matching function will return correctly if the input string is found within the task
     * description.
     */
    @Test
    public void stringMatches_valid_successTwo() {
        JukeTodo jukeTodo = new JukeTodo("this is valid");
        assertTrue(jukeTodo.stringMatches(" i"));
    }

    /**
     * Tests if the matching function will return correctly if the input string is found within the task
     * description.
     */
    @Test
    public void stringMatches_valid_successThree() {
        JukeTodo jukeTodo = new JukeTodo("this is valid");
        assertTrue(jukeTodo.stringMatches("is "));
    }

    /**
     * Tests if the matching function will return correctly if the input string is found within the task
     * description. This method is supposed to fail.
     */
    @Test
    public void stringMatches_invalid_failureOne() {
        JukeTodo jukeTodo = new JukeTodo("this is valid");
        assertFalse(jukeTodo.stringMatches("x"));
    }

    /**
     * Tests if the matching function will return correctly if the input string is found within the task
     * description. This method is supposed to fail.
     */
    @Test
    public void stringMatches_invalid_failureTwo() {
        JukeTodo jukeTodo = new JukeTodo("this is valid");
        assertFalse(jukeTodo.stringMatches("si"));
    }

    /**
     * Tests if the matching function will return correctly if the input string is found within the task
     * description. This method is supposed to fail.
     */
    @Test
    public void stringMatches_invalid_failureThree() {
        JukeTodo jukeTodo = new JukeTodo("this is valid");
        assertFalse(jukeTodo.stringMatches("this is valdi"));
    }
}
