package cyrus.commands;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import cyrus.mocks.MockStorage;
import cyrus.parser.Parser;
import cyrus.storage.IStorage;
import cyrus.tasks.TaskList;
import cyrus.tasks.ToDo;

public class FindTaskCommandTest {
    private static final IStorage MOCK_STORAGE = new MockStorage();
    private static final TaskList TASK_LIST = new TaskList(MOCK_STORAGE);
    private static final Parser PARSER = new Parser();

    @BeforeAll
    public static void init() {
        TASK_LIST.addTask(new ToDo("first todo"));
        TASK_LIST.addTask(new ToDo("weird task"));
        TASK_LIST.addTask(new ToDo("another task"));
    }

    @Test
    public void testExecuteWithNoArgument() {
        var info = PARSER.parse("find   ");
        var command = new FindTaskCommand(TASK_LIST, info);
        assertThrows(CommandError.class, command::execute);
    }

    @Test
    public void testExecuteWithNoResultsFound() throws CommandError {
        var info = PARSER.parse("find abcdefg");
        var command = new FindTaskCommand(TASK_LIST, info);
        var output = command.execute();
        var expected = new String[]{"No tasks found."};
        assertArrayEquals(expected, output);
    }

    @Test
    public void testExecuteWithMatchingResults() throws CommandError {
        var info = PARSER.parse("find task");
        var command = new FindTaskCommand(TASK_LIST, info);
        var output = command.execute();
        var expected = new String[]{
            "Here are the matching tasks in your list:",
            TASK_LIST.getTask(1).toString(),
            TASK_LIST.getTask(2).toString()
        };
        assertArrayEquals(expected, output);
    }
}
