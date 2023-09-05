package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test cases for the {@link DateAndTime} class.
 */
public class DateAndTimeTest {

    /**
     * Test the {@link DateAndTime#isValidDate()} method.
     */
    @Test
    public void validTest() {
        DateAndTime test1 = new DateAndTime();
        assertTrue(test1.isValidDate("2023-04-01", "2023-04-02"));
    }

    /**
     * Test the {@link DateAndTime#dayParse()} method.
     */
    @Test
    public void dateStringTest() {
        DateAndTime test2 = new DateAndTime();
        assertEquals("APRIL 1, 2023", test2.dayParse("2023-04-01"));
    }

    /**
     * Test the {@link DateAndTime#dayParse()} method.
     */
    @Test
    public void dateTimeStringTest() {
        DateAndTime test3 = new DateAndTime();
        assertEquals("APRIL 3, 2023, 18:00", test3.dayParse("2023-04-03", "18:00"));
    }
}
