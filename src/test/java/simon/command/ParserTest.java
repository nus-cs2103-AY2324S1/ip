package simon.command;

import org.junit.jupiter.api.Test;
import simon.SimonException;
import simon.task.Task;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void testParseAddTask_validToDoInput() throws SimonException {
        String input = "todo Sample Task";
        Task task = Parser.parseAddTask(input, Parser.Command.TODO);
        assertEquals("Sample Task", task.taskName);
    }

    @Test
    public void testParseAddTask_invalidInput() {
        String input = "todo";
        assertThrows(SimonException.class, () -> Parser.parseAddTask(input, Parser.Command.TODO));
    }
}
