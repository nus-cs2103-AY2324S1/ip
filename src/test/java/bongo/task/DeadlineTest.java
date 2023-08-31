package bongo.task;

import bongo.helper.BongoException;
import bongo.helper.DateHelper;
import bongo.helper.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {
    @Test
    public void testDeadlineString() throws BongoException {
        Deadline sampleDeadline = new Deadline("homework", DateHelper.formatDateTime("14/6/2030 1200"));
        String expectedOutput = "[D][ ] homework (by: Friday, Jun 14, 2030 12:00 PM)";
        assertEquals(expectedOutput, sampleDeadline.toString());
    }

    @Test
    public void testInvalidDeadline_exceptionThrown() {
        Exception exception = assertThrows(BongoException.class, () -> {
            Parser.parse("deadline homework /by 14/6/2001 1200");
        });
        String expectedMessage = "Deadline must be in the future.";
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    public void testEmptyDeadlineDateTime_exceptionThrown() {
        Exception exception1 = assertThrows(BongoException.class, () -> {
            Parser.parse("deadline homework");
        });
        Exception exception2 = assertThrows(BongoException.class, () -> {
            Parser.parse("deadline homework /by");
        });
        String expectedMessage = "Please include the deadline.";
        assertEquals(expectedMessage, exception1.getMessage());
        assertEquals(expectedMessage, exception2.getMessage());
    }

    @Test
    public void testEmptyDeadlineDescription_exceptionThrown() {
        Exception exception = assertThrows(BongoException.class, () -> {
            Parser.parse("deadline");
        });
        String expectedMessage = "Please include the description and datetime of your deadline.";
        assertEquals(expectedMessage, exception.getMessage());
    }
}
