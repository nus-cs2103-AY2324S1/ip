package juke.tasks;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

/**
 * Tests the {@code JukeDeadline} class.
 */
public class JukeDeadlineTest {
    /**
     * Tests if the input "this is valid" and 01-01-2001 0001 creates an instance of {@code JukeTodo} properly.
     */
    @Test
    public void constructor_valid_successOne() {
        assertDoesNotThrow(() -> new JukeDeadline("this is valid", LocalDateTime.of(
                2001, 1, 1, 0, 1
        )));
    }

    /**
     * Tests if the input "this is also valid", 31-12-9999 2359 and true
     * creates an instance of {@code JukeTodo} properly.
     */
    @Test
    public void constructor_valid_successTwo() {
        assertDoesNotThrow(() -> new JukeDeadline("this is also valid", LocalDateTime.of(
                9999, 12, 31, 23, 59
        ), true));
    }

    /**
     * Tests if the input "this is obviously valid", 31-12-9999 2359 and false creates an instance of
     * {@code JukeTodo} properly.
     */
    @Test
    public void constructor_valid_successThree() {
        assertDoesNotThrow(() -> new JukeDeadline("this is also valid", LocalDateTime.of(
                9999, 12, 31, 23, 59
        ), false));
    }

    /**
     * Tests if an illegal date is permitted.
     */
    @Test
    public void constructor_invalid_failureOne() {
        assertThrows(DateTimeException.class, () -> new JukeDeadline("this is valid", LocalDateTime.parse(
                "2001-13-01T01:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME
        )));
    }

    /**
     * Tests if an illegal date is permitted.
     */
    @Test
    public void constructor_invalid_failureTwo() {
        assertThrows(DateTimeException.class, () -> new JukeDeadline("this is valid", LocalDateTime.parse(
                "2023-01-2022T25:59", DateTimeFormatter.ISO_LOCAL_DATE_TIME
        )));
    }

    /**
     * Tests if an illegal date is permitted.
     */
    @Test
    public void constructor_invalid_failureThree() {
        assertThrows(DateTimeException.class, () -> new JukeDeadline("this is valid", LocalDateTime.parse(
                "1-01-2022T25:59", DateTimeFormatter.ISO_LOCAL_DATE_TIME
        )));
    }

    /**
     * Tests if the created deadlines are properly converted to the correct save String.
     */
    @Test
    public void save_valid_successOne() {
        JukeDeadline jukeDeadline = new JukeDeadline("this is valid", LocalDateTime.of(
                2001, 1, 1, 0, 1
        ));

        assertEquals(jukeDeadline.toString(), "[D] [ ] this is valid (by 01 Jan 2001, 0001 hrs)");
    }

    /**
     * Tests if the created (not completed) deadline are properly converted to the correct save String.
     */
    @Test
    public void save_valid_successTwo() {
        JukeDeadline jukeDeadline = new JukeDeadline("this is also valid", LocalDateTime.of(
                9999, 12, 31, 23, 59
        ), true);
        assertEquals(jukeDeadline.toString(), "[D] [✓] this is also valid (by 31 Dec 9999, 2359 hrs)");
    }

    /**
     * Tests if the created (completed) deadline are properly converted to the correct save String.
     */
    @Test
    public void save_valid_successThree() {
        JukeDeadline jukeDeadline = new JukeDeadline("this is also valid", LocalDateTime.of(
                9999, 12, 31, 23, 59
        ), false);
        assertEquals(jukeDeadline.toString(), "[D] [ ] this is also valid (by 31 Dec 9999, 2359 hrs)");
    }

    /**
     * Tests if the matching function will return correctly if the input string is found within the task
     * description.
     */
    @Test
    public void stringMatches_valid_successOne() {
        JukeDeadline jukeDeadline = new JukeDeadline("this is valid", LocalDateTime.of(
                2001, 1, 1, 0, 1
        ));
        assertTrue(jukeDeadline.stringMatches("this "));
    }

    /**
     * Tests if the matching function will return correctly if the input string is found within the task
     * description.
     */
    @Test
    public void stringMatches_valid_successTwo() {
        JukeDeadline jukeDeadline = new JukeDeadline("this is valid", LocalDateTime.of(
                2001, 1, 1, 0, 1
        ));
        assertTrue(jukeDeadline.stringMatches(" i"));
    }

    /**
     * Tests if the matching function will return correctly if the input string is found within the task
     * description.
     */
    @Test
    public void stringMatches_valid_successThree() {
        JukeDeadline jukeDeadline = new JukeDeadline("this is valid", LocalDateTime.of(
                2001, 1, 1, 0, 1
        ));
        assertTrue(jukeDeadline.stringMatches("is "));
    }

    /**
     * Tests if the matching function will return correctly if the input string is found within the task
     * description. This method is supposed to fail.
     */
    @Test
    public void stringMatches_invalid_failureOne() {
        JukeDeadline jukeDeadline = new JukeDeadline("this is valid", LocalDateTime.of(
                2001, 1, 1, 0, 1
        ));
        assertFalse(jukeDeadline.stringMatches("x"));
    }

    /**
     * Tests if the matching function will return correctly if the input string is found within the task
     * description. This method is supposed to fail.
     */
    @Test
    public void stringMatches_invalid_failureTwo() {
        JukeDeadline jukeDeadline = new JukeDeadline("this is valid", LocalDateTime.of(
                2001, 1, 1, 0, 1
        ));
        assertFalse(jukeDeadline.stringMatches("si"));
    }

    /**
     * Tests if the matching function will return correctly if the input string is found within the task
     * description. This method is supposed to fail.
     */
    @Test
    public void stringMatches_invalid_failureThree() {
        JukeDeadline jukeDeadline = new JukeDeadline("this is valid", LocalDateTime.of(
                2001, 1, 1, 0, 1
        ));
        assertFalse(jukeDeadline.stringMatches("this is valdi"));
    }
}
