package dialogix.main;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dialogix.command.AddDeadlineCommand;
import dialogix.command.AddTodoCommand;
import dialogix.command.DeleteCommand;
import dialogix.command.DoneCommand;
import dialogix.command.ExitCommand;
import dialogix.command.ListCommand;
import dialogix.exception.DialogixException;

class ParserTest {
    @Test
    void parserTest() throws DialogixException {
        // Testing exit command
        assertTrue(Parser.parse("bye") instanceof ExitCommand);

        // Testing list command
        assertTrue(Parser.parse("list") instanceof ListCommand);

        // Testing done command
        assertTrue(Parser.parse("done 2") instanceof DoneCommand);

        // Testing delete command
        assertTrue(Parser.parse("delete 2") instanceof DeleteCommand);

        // Testing add todo command
        assertTrue(Parser.parse("todo Todo Task") instanceof AddTodoCommand);

        // Testing add deadline command with date
        assertTrue(Parser.parse("deadline new deadline /by 25/07/2023 1500") instanceof AddDeadlineCommand);
    }
}

