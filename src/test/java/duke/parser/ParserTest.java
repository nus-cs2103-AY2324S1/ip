package duke.parser;

import duke.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void eventParsing_validEvent_success() throws DukeParseException {
        LocalDate from = LocalDate.parse("2023-11-11");
        LocalDate to = LocalDate.parse("2023-11-30");
        Event event = new Event("exams", from, to);

        Event parsedEvent = Parser.parseEvent("exams /from 2023-11-11 /to 2023-11-30");

        assertEquals(parsedEvent.getDesc(), event.getDesc());
        assertFalse(parsedEvent.isMarked());
        assertEquals(parsedEvent.toString(), "[E][ ] exams (from: Nov 11 2023 to: Nov 30 2023)");
    }

    @Test
    public void eventParsing_invalidEvent_exceptionThrown() {
        try {
            Parser.parseEvent("camp /from 2023-11-11");
            fail();
        } catch (DukeParseException e) {
            assertEquals("Event cannot be parsed", e.getMessage());
        }
    }

    @Test
    public void eventParsing_invalidDate_exceptionThrown() {
        try {
            Parser.parseEvent("camp /from 11-11-2023 /to 30-11-2023");
            fail();
        } catch (DukeParseException e) {
            assertEquals("Event cannot be parsed", e.getMessage());
        }
    }
}
