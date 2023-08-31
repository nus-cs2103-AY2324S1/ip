package duke;

import duke.exceptions.DukeParseException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    private static final Parser PARSER = new Parser();

    @Test
    public void parseTodo_validInput_expectedOutcome() {
        assertEquals(new Todo("read book"), PARSER.parseTodo("todo read book"));
    }

    @Test
    public void parseTodo_missingParameters_throwException() {
        try {
            assertEquals(new Todo("read book"), PARSER.parseTodo("todo"));
            fail();
        } catch (DukeParseException e) {
            assertEquals("OOPS!!! Missing parameters in todo", e.getMessage());
        }
    }

    @Test
    public void parseDeadline_validInput_expectedOutcome() {
        assertEquals(new Deadline("return book",
                LocalDateTime.parse("01 Sep 2023 - 16:00", Duke.TIME_FORMAT)),
                PARSER.parseDeadline("deadline return book /by 01 Sep 2023 - 16:00"));
    }

    @Test
    public void parseDeadline_missingParameters_throwException() {
        try {
            assertEquals(new Deadline("return book",
                    LocalDateTime.parse("01 Sep 2023 - 16:00", Duke.TIME_FORMAT)),
                    PARSER.parseDeadline("deadline return book"));
            fail();
        } catch (DukeParseException e) {
            assertEquals("OOPS!!! Missing parameters in deadline", e.getMessage());
        }
    }

    @Test
    public void parseEvent_validInput_expectedOutcome() {
        assertEquals(new Event("project meeting",
                LocalDateTime.parse("06 Aug 2023 - 14:00", Duke.TIME_FORMAT),
                LocalDateTime.parse("06 Aug 2023 - 16:00", Duke.TIME_FORMAT)),
                PARSER.parseEvent("event project meeting /from 06 Aug 2023 - 14:00 /to 06 Aug 2023 - 16:00"));
    }

    @Test
    public void parseEvent_missingParameters_throwException() {
        try {
            assertEquals(new Event("project meeting",
                    LocalDateTime.parse("06 Aug 2023 - 14:00", Duke.TIME_FORMAT),
                    LocalDateTime.parse("06 Aug 2023 - 16:00", Duke.TIME_FORMAT)),
                    PARSER.parseEvent("event project meeting /from 06 Aug 2023 - 14:00"));
            fail();
        } catch (DukeParseException e) {
            assertEquals("OOPS!!! Missing parameters in event", e.getMessage());
        }
    }
}
