package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.commands.Command;
import duke.commands.CommandType;
import duke.exceptions.DukeInvalidCommandException;
import duke.tasks.TaskList;

public class ParserTest {
    @Test
    public void parseValidCommandTypes_success() {
        String[] validCommands = { "bye", "list", "todo", "deadline", "event", "mark", "unmark", "delete" };
        TaskList dummy = new TaskList();
        for (String c : validCommands) {
            assertEquals(CommandType.fromString(c), new Parser(dummy).parse(c).getCommandType());
        }
    }

    @Test
    public void parseDeadline_success() {
        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList);
        Command command = parser.parse("deadline homework /by 2023-08-30 23:59");
        command.execute();
        taskList.getTask(0).markDone();
        assertEquals("[D][X] homework (by: Wed 30 Aug 2023 23:59)", taskList.getTask(0).toString());
    }

    @Test
    public void parseInvalidCommands_exceptionThrown() {
        String[] invalidCommands = { "wow", "hi" };
        TaskList dummy = new TaskList();
        for (String c : invalidCommands) {
            assertThrows(DukeInvalidCommandException.class, () -> new Parser(dummy).parse(c));
        }
    }
}
