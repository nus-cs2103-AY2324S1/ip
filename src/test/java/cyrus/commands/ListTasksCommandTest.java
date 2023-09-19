package cyrus.commands;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import cyrus.mocks.MockStorage;
import cyrus.parser.Parser;
import cyrus.storage.IStorage;
import cyrus.tasks.TaskList;
import cyrus.tasks.ToDo;

public class ListTasksCommandTest {
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
    public void testExecuteWithEmptyTaskList() {
        var emptyTaskList = new TaskList(MOCK_STORAGE);
        var info = PARSER.parse("list");
        var command = new ListTasksCommand(emptyTaskList, info);
        var output = command.execute();
        var expected = new String[]{"You do not have any tasks, use todo, deadline, or event to add new ones!"};
        assertArrayEquals(expected, output);
    }

    @Test
    public void testExecuteWithNonEmptyTaskList() {
        var info = PARSER.parse("list");
        var command = new ListTasksCommand(TASK_LIST, info);
        var output = command.execute();
        var expected = new String[]{TASK_LIST.toString()};
        assertArrayEquals(expected, output);
    }
}
