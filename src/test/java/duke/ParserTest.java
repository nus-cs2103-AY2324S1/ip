package duke;

import duke.exception.DukeIllegalArgumentsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void testEventWithoutDescription() {
        Exception exception = assertThrows(DukeIllegalArgumentsException.class,
                () -> Parser.parse("event /from abc /to 123"));
        assertEquals("☹ OOPS!!! The description of an event cannot be empty\n", exception.getMessage());
    }

    @Test
    public void testEventWithoutToTime() {
        Exception exception = assertThrows(DukeIllegalArgumentsException.class,
                () -> Parser.parse("event event /from 29/2/2012"));
        assertEquals("☹ OOPS!!! The end time of the event must be specified! (after /to)\n",
                exception.getMessage());
    }

    @Test
    public void testDeadlineWithInvalidFromTime() {
        Exception exception = assertThrows(DukeIllegalArgumentsException.class,
                () -> Parser.parse("deadline deadline /by 9/29/2012 1234"));
        assertEquals(
                "☹ OOPS!!! The deadline date provided must be in the format: dd/mm/yyyy HHmm (in 24h format)\n",
                exception.getMessage());
    }
}
