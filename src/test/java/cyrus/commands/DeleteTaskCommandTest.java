package cyrus.commands;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import cyrus.mocks.MockStorage;
import cyrus.parser.Parser;
import cyrus.storage.IStorage;
import cyrus.tasks.TaskList;

public class DeleteTaskCommandTest {
    private static final IStorage MOCK_STORAGE = new MockStorage();
    private static final TaskList TASK_LIST = new TaskList(MOCK_STORAGE);
    private static final Parser PARSER = new Parser();

    @Test
    public void testExecuteWithMissingTaskIndex() {
        var info = PARSER.parse("delete  ");
        var command = new DeleteTaskCommand(TASK_LIST, info);
        assertThrows(CommandError.class, command::execute);
    }

    @Test
    public void testExecuteWithNonIntegerIndex() {
        var info = PARSER.parse("delete bad");
        var command = new DeleteTaskCommand(TASK_LIST, info);
        assertThrows(CommandError.class, command::execute);
    }

    @Test
    public void testExecuteWithNegativeIndex() {
        var info = PARSER.parse("delete -10");
        var command = new DeleteTaskCommand(TASK_LIST, info);
        assertThrows(CommandError.class, command::execute);
    }

    @Test
    public void testExecuteWithOutOfBoundsIndex() {
        var info = PARSER.parse("delete 100");
        var command = new DeleteTaskCommand(TASK_LIST, info);
        assertThrows(CommandError.class, command::execute);
    }

    @Test
    public void testExecuteWithValidIndex() throws CommandError {
        new AddTodoCommand(TASK_LIST, PARSER.parse("todo test")).execute();
        var todo = TASK_LIST.getTask(0);
        var info = PARSER.parse("delete 1");
        var command = new DeleteTaskCommand(TASK_LIST, info);
        var output = command.execute();
        var expected = new String[]{
            "Noted. I've removed this task:",
            todo.toString(),
            "Now you have 0 tasks in the list."
        };
        assertArrayEquals(expected, output);
        assertEquals(0, TASK_LIST.size());
    }
}
