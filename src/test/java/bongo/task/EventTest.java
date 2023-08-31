package bongo.task;

import bongo.helper.BongoException;
import bongo.helper.DateHelper;
import bongo.helper.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTest {
    @Test
    public void testEventString() throws BongoException {
        Event sampleEvent = new Event("party", DateHelper.formatDateTime("14/6/2030 1200"), DateHelper.formatDateTime("14/6/2030 1500"));
        String expectedOutput = "[E][ ] party (from: Friday, Jun 14, 2030 12:00 PM to: Friday, Jun 14, 2030 03:00 PM)";
        assertEquals(expectedOutput, sampleEvent.toString());
    }

    @Test
    public void testEmptyEvent_exceptionThrown() {
        Exception exception = assertThrows(BongoException.class, () -> {
            Parser.parse("event");
        });
        String expectedMessage = "Please include the description and datetime of your event.";
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    public void testInvalidEvent_exceptionThrown() {
        Exception exception1 = assertThrows(BongoException.class, () -> {
            Parser.parse("event party /from 14/6/2030 1200");
        });
        Exception exception2 = assertThrows(BongoException.class, () -> {
            Parser.parse("event party /to 14/6/2030 1200");
        });
        String expectedMessage = "Please include the to and from datetime of the event.";
        assertEquals(expectedMessage, exception1.getMessage());
        assertEquals(expectedMessage, exception2.getMessage());
    }
    @Test
    public void testEmptyEventDateTime_exceptionThrown() {
        Exception exception = assertThrows(BongoException.class, () -> {
            Parser.parse("event party");
        });
        String expectedMessage = "Please include the datetime of your event.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testFromIsBeforeCurrentTime_exceptionThrown() {
        Exception exception = assertThrows(BongoException.class, () -> {
            Parser.parse("event party /from 30/8/2023 1200 /to 30/8/2030 1200");
        });
        String expectedMessage = "Start and end of event must be in the future.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testFromIsAfterTo_exceptionThrown() {
        Exception exception = assertThrows(BongoException.class, () -> {
            Parser.parse("event party /from 30/8/2031 1200 /to 30/8/2030 1200");
        });
        String expectedMessage = "End of event must be after start of event.";
        assertEquals(expectedMessage, exception.getMessage());
    }
}
