package sally;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


public class ParserTest {
    @Test
    public void invalidInput() {
        Exception exception = assertThrows(SallyException.class, () -> Parser.parse("sleep"));
        assertEquals("Sorry, I do not understand the command :-(",
                exception.getMessage());
    }

    @Test
    public void testValidListCommand() throws SallyException {
        Command command = Parser.parse("list");
        assertEquals(ListCommand.class, command.getClass());
    }

    @Test
    public void testValidMarkCommand() throws SallyException {
        Command command = Parser.parse("mark 1");
        assertEquals(MarkCommand.class, command.getClass());
    }

    @Test
    public void testValidUnmarkCommand() throws SallyException {
        Command command = Parser.parse("unmark 1");
        assertEquals(UnmarkCommand.class, command.getClass());
    }

    @Test
    public void testValidDeleteCommand() throws SallyException {
        Command command = Parser.parse("delete 1");
        assertEquals(DeleteCommand.class, command.getClass());
    }

    @Test
    public void testValidTodoCommand() throws SallyException {
        Command command = Parser.parse("todo Buy groceries");
        assertEquals(AddTodoCommand.class, command.getClass());
    }

    @Test
    public void testValidDeadlineCommand() throws SallyException {
        Command command = Parser.parse("deadline Submit report /by 2023-09-30 1400");
        assertEquals(AddDeadlineCommand.class, command.getClass());
    }

    @Test
    public void testValidEventCommand() throws SallyException {
        Command command = Parser.parse("event Team meeting /from 2023-09-25 1200 /to 2023-09-25 1400");
        assertEquals(AddEventCommand.class, command.getClass());
    }
}
