package frodo.test.parser;

import core.Duke;
import core.DukeException;
import frodo.parser.Parser;
import frodo.tasks.Deadlines;
import frodo.tasks.Events;
import frodo.tasks.Task;
import frodo.tasks.ToDos;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    private Parser parser = new Parser();

    @Test
    public void parseCommand_WithTodoCommandType_ReturnsCorrectCommandType_ThrowsException() {
        String command = "todo run 2 miles";

        assertEquals(parser.getCommandType(command), Duke.CommandType.TODO);
    }

    @Test
    public void parseCommand_WithInvalidCommandType_ReturnsUnknownCommandType_ThrowsException() {
        String command = "Hello";

        assertEquals(parser.getCommandType(command), Duke.CommandType.UNKNOWN);
    }

    @Test
    public void testGetCommandType() {
        assertEquals(Duke.CommandType.BYE, parser.getCommandType("bye"));
        assertEquals(Duke.CommandType.LIST, parser.getCommandType("list"));
        assertEquals(Duke.CommandType.DELETE, parser.getCommandType("delete 1"));
        assertEquals(Duke.CommandType.MARK, parser.getCommandType("mark 1"));
        assertEquals(Duke.CommandType.TODO, parser.getCommandType("todo read book"));
        assertEquals(Duke.CommandType.DEADLINE, parser.getCommandType("deadline homework /by 2023-09-23 12:00"));
        assertEquals(Duke.CommandType.EVENT, parser.getCommandType("event meeting /from 2023-09-23 12:00 /to 2023-09-23 14:00"));
        assertEquals(Duke.CommandType.UNKNOWN, parser.getCommandType("randomCommand"));
    }

    @Test
    public void testParseTaskFromFile_validTaskFormat() throws DukeException {
        String validTodo = "T | 0 | read book";
        String validDeadline = "D | 0 | submit homework | 2023-09-23 12:00";

        Task parsedDeadline = parser.parseTaskFromFile(validDeadline);

        assertTrue(parsedDeadline instanceof Deadlines);
        assertEquals("submit homework", parsedDeadline.getDescription());
        assertTrue(!parsedDeadline.isCompleted());

        Task parsedTodo = parser.parseTaskFromFile(validTodo);
        assertTrue(parsedTodo instanceof ToDos);
        assertEquals("read book", parsedTodo.getDescription());
        assertTrue(!parsedTodo.isCompleted());
    }

    @Test
    public void testParseTaskFromFile_invalidTaskFormat() {
        String invalidTask = "X | 1 | invalid task";
        assertThrows(DukeException.class, () -> parser.parseTaskFromFile(invalidTask));
    }


}
