package pogo.parsers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import pogo.commands.AddDeadlineCommand;
import pogo.commands.AddEventCommand;
import pogo.commands.AddToDoCommand;
import pogo.commands.Command;
import pogo.commands.DeleteTaskCommand;
import pogo.commands.InvalidCommand;
import pogo.commands.MarkTaskCommand;
import pogo.commands.UnmarkTaskCommand;

public class TaskParserTest {

    @Test
    void parseDeadlineCommand_validInput_returnsAddDeadlineCommand() throws Exception {
        String input = "Finish report /by 2023-09-01 18:00";
        Command command = TaskParser.parseDeadlineCommand(input);
        assertTrue(command instanceof AddDeadlineCommand);
    }

    @Test
    void parseToDoCommand_validInput_returnsAddToDoCommand() throws Exception {
        String input = "Buy groceries";
        Command command = TaskParser.parseToDoCommand(input);
        assertTrue(command instanceof AddToDoCommand);
    }

    @Test
    void parseEventCommand_validInput_returnsAddEventCommand() throws Exception {
        String input = "Party /from 2023-09-01 20:00 /to 2023-09-01 23:00";
        Command command = TaskParser.parseEventCommand(input);
        assertTrue(command instanceof AddEventCommand);
    }

    @Test
    void parseMarkCommand_validInput_returnsMarkTaskCommand() throws Exception {
        String input = "2";
        Command command = TaskParser.parseMarkCommand(input, true);
        assertTrue(command instanceof MarkTaskCommand);
    }

    @Test
    void parseMarkCommand_validInput_returnsUnmarkTaskCommand() throws Exception {
        String input = "3";
        Command command = TaskParser.parseMarkCommand(input, false);
        assertTrue(command instanceof UnmarkTaskCommand);
    }

    @Test
    void parseDeleteCommand_validInput_returnsDeleteTaskCommand() throws Exception {
        String input = "4";
        Command command = TaskParser.parseDeleteCommand(input);
        assertTrue(command instanceof DeleteTaskCommand);
    }

    @Test
    void parseInvalidDeadlineCommand_emptyDescription_returnsInvalidCommand() throws Exception {
        String input = " /by 2023-09-01 18:00";
        Command command = TaskParser.parseDeadlineCommand(input);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    void parseInvalidToDoCommand_emptyDescription_returnsInvalidCommand() throws Exception {
        String input = "";
        Command command = TaskParser.parseToDoCommand(input);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    void parseInvalidEventCommand_emptyDescription_returnsInvalidCommand() throws Exception {
        String input = "/from 2023-09-01 20:00 /to 2023-09-01 23:00";
        Command command = TaskParser.parseEventCommand(input);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    void parseInvalidMarkCommand_invalidIndex_returnsInvalidCommand() throws Exception {
        String input = "abc";
        Command command = TaskParser.parseMarkCommand(input, true);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    void parseInvalidDeleteCommand_invalidIndex_returnsInvalidCommand() throws Exception {
        String input = "xyz";
        Command command = TaskParser.parseDeleteCommand(input);
        assertTrue(command instanceof InvalidCommand);
    }
}
