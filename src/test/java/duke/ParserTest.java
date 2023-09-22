package duke;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parseTodo_withSimpleDescription_parserTypeMatch() {
        assertEquals(Parser.parseTask("event do not steal"), Parser.TaskType.EVENT);
    }

    @Test
    public void parseTodo_withSimpleDescription_taskTextMatch() {
        assertEquals(Parser.parseToDo("todo why"), "why");
    }

    @Test
    public void parseTodo_withNoDescription_stringExceptionThrown() {
        try {
            Parser.parseToDo("todo");
        } catch (Exception e) {
            assertInstanceOf(StringIndexOutOfBoundsException.class, e);
        }
    }

    @Test
    public void parseDeadline_withSimpleDescription_parserTypeMatch() {
        assertEquals(Parser.parseTask("deadline read /by 2000-01-01"), Parser.TaskType.DEADLINE);
    }

    @Test
    public void parseDeadline_withSimpleDescription_taskTextMatch() {
        assertArrayEquals(Parser.parseDeadline("deadline read /by 2000-01-01"), new String[]{"read", "2000-01-01"});
    }

    @Test
    public void parseDeadline_withIncorrectDate_dateExceptionThrown() {
        try {
            Parser.parseDeadline("deadline read /by -01-01");
        } catch (Exception e) {
            assertInstanceOf(DateTimeParseException.class, e);
        }
    }

    @Test
    public void parseDeadline_withNoDescription_stringExceptionThrown() {
        try {
            Parser.parseDeadline("deadline read");
        } catch (Exception e) {
            assertInstanceOf(StringIndexOutOfBoundsException.class, e);
        }
    }
}
