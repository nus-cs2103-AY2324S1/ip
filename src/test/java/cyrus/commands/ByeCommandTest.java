package cyrus.commands;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

import cyrus.mocks.MockStorage;
import cyrus.parser.Parser;
import cyrus.storage.IStorage;
import cyrus.tasks.TaskList;

public class ByeCommandTest {
    private static final IStorage MOCK_STORAGE = new MockStorage();
    private static final TaskList TASK_LIST = new TaskList(MOCK_STORAGE);
    private static final Parser PARSER = new Parser();

    @Test
    public void testExecuteWithValidCommand() throws CommandError {
        var info = PARSER.parse("bye");
        var command = new ByeCommand(TASK_LIST, info);
        var output = command.execute();
        var expected = new String[]{
            "Bye. Hope to see you again!"
        };
        assertArrayEquals(expected, output);
    }
}
