package cyrus.commands;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import cyrus.mocks.MockStorage;
import cyrus.parser.Parser;
import cyrus.storage.IStorage;
import cyrus.tasks.TaskList;
import cyrus.tasks.ToDo;

public class ViewStatisticsCommandTest {
    private static final IStorage MOCK_STORAGE = new MockStorage();
    private static final TaskList TASK_LIST = new TaskList(MOCK_STORAGE);
    private static final Parser PARSER = new Parser();

    @Test
    public void testExecuteWithEmptyTaskList() {
        var info = PARSER.parse("statistics");
        var command = new ViewStatisticsCommand(TASK_LIST, info);
        assertThrows(CommandError.class, command::execute);
    }

    @Test
    public void testExecuteWithNonEmptyTaskList() throws CommandError {
        var info = PARSER.parse("statistics");
        TASK_LIST.addTask(new ToDo("test"));
        var command = new ViewStatisticsCommand(TASK_LIST, info);
        var output = command.execute();
        var expected = new String[]{"Pulling up your latest statistics!"};
        assertArrayEquals(expected, output);
    }
}
