package duke.utility;

import duke.exception.BobiException;
import duke.exception.EmptyTaskException;
import duke.exception.InvalidEventException;
import duke.exception.MissingTimeException;
import duke.task.Event;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseEventTest_validInput_success() throws InvalidEventException, EmptyTaskException, MissingTimeException {
        LocalDateTime start = LocalDateTime.of(2023, 10, 10, 8, 00);
        LocalDateTime end = LocalDateTime.of(2023, 10, 11, 18, 00);
        Event expectedEvent = new Event(false, "Bobi birthday party ", start, end);
        Event actualEvent = Parser.parseEvent("event Bobi birthday party /from 2023-10-10 0800 /to 2023-10-11 1800");
        assertEquals(expectedEvent.toString(), actualEvent.toString());
    }

    @Test
    public void parseEventTest_missingName_exceptionThrown() {
        try {
            assertEquals(new Object(), Parser.parseEvent("event /from 2023-10-10 0800 /to 2023-10-11 1800"));
            fail();
        } catch (BobiException e) {
            assertEquals("Oh no! Bobi cannot add an empty task. :/", e.getMessage());
        }
    }

    @Test
    public void parseEventTest_missingDateTime_exceptionThrown() {
        try {
            assertEquals(new Object(), Parser.parseEvent("event Bobi birthday party"));
            fail();
        } catch (BobiException e) {
            assertEquals("Please input a date and time for this task.", e.getMessage());
        }
    }

    @Test
    public void parseEventTest_InvalidDateTime_exceptionThrown() {
        try {
            assertEquals(new Object(), Parser.parseEvent("event Bobi birthday party /from date time /to 2111-50-2131 21394"));
            fail();
        } catch (BobiException e) {
            assertEquals(
                    "Oh no! It seems like you have indicated an invalid event :/ \n"
                    + "Please follow this format:\n"
                    + "event <task name> /from <yyyy-mm-dd> <HHmm> /to <yyyy-mm-dd> <HHmm>",
                    e.getMessage()
            );
        }
    }

    @Test
    public void parseActionsTest_validInput_success() throws EmptyTaskException {
        assertEquals(1, Parser.parseActions("mark 1"));
        assertEquals(2, Parser.parseActions("unmark 2"));
        assertEquals(3, Parser.parseActions("delete 3"));
    }

    @Test
    public void parseActionsTest_missingNumber_exceptionThrown() {
        try {
            assertEquals(0, Parser.parseActions("mark "));
            fail();
        } catch (BobiException e) {
            assertEquals("Oh no! Bobi cannot add an empty task. :/", e.getMessage());
        }
    }
}
