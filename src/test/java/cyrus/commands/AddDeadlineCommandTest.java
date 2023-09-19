package cyrus.commands;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import cyrus.mocks.MockStorage;
import cyrus.parser.Parser;
import cyrus.storage.IStorage;
import cyrus.tasks.TaskList;

public class AddDeadlineCommandTest {
    private static final IStorage MOCK_STORAGE = new MockStorage();
    private static final TaskList TASK_LIST = new TaskList(MOCK_STORAGE);
    private static final Parser PARSER = new Parser();

    @Test
    public void testExecuteWithNoArgumentFails() {
        var info = PARSER.parse("deadline   ");
        var command = new AddDeadlineCommand(TASK_LIST, info);
        assertThrows(CommandError.class, command::execute);
    }

    @Test
    public void testExecuteWithMissingByDate() {
        var info = PARSER.parse("deadline do something");
        var command = new AddDeadlineCommand(TASK_LIST, info);
        assertThrows(CommandError.class, command::execute);
    }

    @Test
    public void testExecuteWithInvalidByDateFormat() {
        var info = PARSER.parse("deadline do something /by tomorrow");
        var command = new AddDeadlineCommand(TASK_LIST, info);
        assertThrows(CommandError.class, command::execute);
    }

    @Test
    public void testExecuteWithValidCommand() throws CommandError {
        var info = PARSER.parse("deadline do something /by 19/09/2023");
        var command = new AddDeadlineCommand(TASK_LIST, info);
        var output = command.execute();
        var expected = new String[]{
            "Got it. I've added this task:",
            "[D] [ ] do something (by: 19 September 2023)",
            "Now you have 1 tasks in the list."
        };
        assertArrayEquals(expected, output);
        assertEquals(1, TASK_LIST.size());
    }
}
