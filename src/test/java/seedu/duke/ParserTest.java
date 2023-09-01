package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.Parser;
import exception.InvalidCommandException;

public class ParserTest {

    @Test
    public void parse_invalidEditCommandIndex_exceptionThrown() {
        InvalidCommandException test = assertThrows(InvalidCommandException.class, () -> Parser.parse("mark hi"));

        assertEquals("Please input an integer to identify task", test.getMessage());
    }

    @Test
    public void parse_invalidTaskCommand_exceptionThrown() {
        assertThrows(InvalidCommandException.class, () ->
                Parser.parse("deadline  "));

        assertThrows(InvalidCommandException.class, () ->
                Parser.parse("deadline /"));

        assertThrows(InvalidCommandException.class, () ->
                Parser.parse("deadline /by 2022-05-12 24:10"));

        assertThrows(InvalidCommandException.class, () ->
                Parser.parse("event /from 2022-5-12 23:00 /to "));
    }

    @Test
    public void testDateTimeReformat() {
        assertEquals("Jan 1 2023 11.35PM",
                Parser.reformatDateTime(
                        Parser.parseDateTime("2023-01-01 23:35")));
    }

    @Test
    public void parse_invalidEventDate_exceptionThrown() {
        InvalidCommandException thrown = assertThrows(InvalidCommandException.class, () ->
                Parser.parse("event party /from 2022-01-01 23:00 /to 2021-01-01 23:00"));

        assertEquals("/from date should be before /to date given", thrown.getMessage());
    }
}
