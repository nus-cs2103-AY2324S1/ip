package duke.main;

import duke.exceptions.InvalidArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ParserTest {

    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    @Test
    public void getCommand_validInput_returnsCommand() {
        assertEquals("todo", parser.getCommand("todo read book"));
        assertEquals("deadline", parser.getCommand("deadline return book /due tomorrow"));
        assertEquals("event", parser.getCommand("event book club /from 2pm /to 4pm"));
    }

    @Test
    public void parseToDo_validInput_returnsTaskName() throws InvalidArgumentException {
        assertEquals("read book", parser.parseToDo("todo read book"));
    }

    @Test
    public void parseToDo_noDescription_throwsInvalidArgumentException() {
        assertThrows(InvalidArgumentException.class, () -> parser.parseToDo("todo"));
        assertThrows(InvalidArgumentException.class, () -> parser.parseToDo("todo "));
    }

    @Test
    public void parseDeadline_validInput_returnsTaskAndDueDate() throws InvalidArgumentException {
        String[] expected = new String[]{"return book", "22/09/2023 00:00"};
        assertArrayEquals(expected, parser.parseDeadline("deadline return book /due 22/09/2023 00:00"));
    }

    @Test
    public void parseDeadline_misingDueDate_throwsInvalidArgumentException() {
        assertThrows(InvalidArgumentException.class, () -> parser.parseDeadline("deadline return book"));
    }

    @Test
    public void parseEvent_validInput_returnsEventAndDates() throws InvalidArgumentException {
        String[] expected = new String[]{"book club", "22/10/2023 00:00", "22/10/2023 12:00"};
        assertArrayEquals(expected, parser.parseEvent("event book club /from 22/10/2023 00:00 /to 22/10/2023 12:00"));
    }

    @Test
    public void parseEvent_missingDates_throwsInvalidArgumentException() {
        assertThrows(InvalidArgumentException.class, () -> parser.parseEvent("event book club /from 22/10/2023 00:00"));
        assertThrows(InvalidArgumentException.class, () -> parser.parseEvent("event book club"));
    }
}
