package cyrus.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import cyrus.mocks.MockStorage;
import cyrus.parser.Parser;
import cyrus.storage.IStorage;
import cyrus.tasks.TaskList;

public class UnknownCommandTest {
    private static final IStorage MOCK_STORAGE = new MockStorage();
    private static final TaskList TASK_LIST = new TaskList(MOCK_STORAGE);
    private static final Parser PARSER = new Parser();

    @Test
    public void testExecute() {
        var info = PARSER.parse("bad");
        var command = new UnknownCommand(TASK_LIST, info);
        assertThrows(CommandError.class, command::execute);
    }
}
