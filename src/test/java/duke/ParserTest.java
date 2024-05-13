package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.DukeIllegalArgumentsException;

public class ParserTest {
    @Test
    public void testEventWithoutAnything() {
        Exception exception = assertThrows(DukeIllegalArgumentsException.class, () ->
                Parser.parse("event"));
        assertEquals("☹ OOPS!!! The description of an event cannot be empty\n", exception.getMessage());
    }

    @Test
    public void testEventWithoutDescription() {
        Exception exception = assertThrows(DukeIllegalArgumentsException.class, () ->
                Parser.parse("event /from"));
        assertEquals("☹ OOPS!!! The description of an event cannot be empty\n", exception.getMessage());
    }

    @Test
    public void testEventWithoutFrom() {
        Exception exception = assertThrows(DukeIllegalArgumentsException.class, () ->
                Parser.parse("event description"));
        assertEquals("☹ OOPS!!! The start time of the event must be specified! (after /from)\n",
                exception.getMessage());
    }

    @Test
    public void testEventWithoutToTime() {
        Exception exception = assertThrows(DukeIllegalArgumentsException.class, () ->
                Parser.parse("event description /from 29/2/2012"));
        assertEquals("☹ OOPS!!! The end time of the event must be specified! (after /to)\n",
                exception.getMessage());
    }

    @Test
    public void testDeadlineWithInvalidFromTime() {
        Exception exception = assertThrows(DukeIllegalArgumentsException.class, () ->
                Parser.parse("deadline description /by 9/29/2012 1234"));
        assertEquals(
                "☹ OOPS!!! The deadline date provided must be in the format: dd/mm/yyyy HHmm (in 24h format)\n",
                exception.getMessage());
    }
}
