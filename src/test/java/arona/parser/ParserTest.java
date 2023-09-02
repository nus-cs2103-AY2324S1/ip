package arona.parser;

import arona.exception.IllegalArgumentAronaException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Parser class.
 */
public class ParserTest {

    /**
     * Tests parsing user input into an array of tokens.
     */
    @Test
    public void parseUserInput_ValidInput_ReturnsArray() {
        String input = "todo description";
        String[] result = Parser.parseUserInput(input);
        String[] expected = {"todo", "description"};
        assertArrayEquals(expected, result);
    }

    /**
     * Tests extracting the command from an array of tokens.
     */
    @Test
    public void getCommand_ValidInput_ReturnsCommand() {
        String[] tokens = {"deadline", "description", "/by", "2023-09-15"};
        String result = Parser.getCommand(tokens);
        assertEquals("deadline", result);
    }

    /**
     * Tests extracting the description from a "todo" command.
     */
    @Test
    public void getToDoDescription_ValidInput_ReturnsDescription() throws IllegalArgumentAronaException {
        String[] tokens = {"todo", "description"};
        String result = Parser.getToDoDescription(tokens);
        assertEquals("description", result);
    }

    /**
     * Tests extracting description and date from a "deadline" command.
     */
    @Test
    public void getDeadlineDescription_ValidInput_ReturnsDescriptionsArray() throws IllegalArgumentAronaException {
        String[] tokens = {"deadline", "description", "/by", "2023-09-15"};
        String[] result = Parser.getDeadlineDescription(tokens);
        String[] expected = {"description", "2023-09-15"};
        assertArrayEquals(expected, result);
    }

    /**
     * Tests extracting description, start date, and end date from an "event" command.
     */
    @Test
    public void getEventDescription_ValidInput_ReturnsDescriptionsArray() throws IllegalArgumentAronaException {
        String[] tokens = {"event", "description", "/from", "2023-09-15", "/to", "2023-09-16"};
        String[] result = Parser.getEventDescription(tokens);
        String[] expected = {"description", "from: 2023-09-15", "to: 2023-09-16"};
        assertArrayEquals(expected, result);
    }

    /**
     * Tests parsing a date string into a LocalDate object.
     */
    @Test
    public void parseDate_ValidDate_ReturnsLocalDate() throws IllegalArgumentAronaException {
        String dateStr = "2023-09-15";
        LocalDate result = Parser.parseDate(dateStr);
        LocalDate expected = LocalDate.of(2023, 9, 15);
        assertEquals(expected, result);
    }

    /**
     * Tests extracting a task index from an array of tokens.
     */
    @Test
    public void getTaskIndex_ValidInput_ReturnsIndex() {
        String[] tokens = {"done", "2"};
        int result = Parser.getTaskIndex(tokens);
        assertEquals(1, result);
    }
}
