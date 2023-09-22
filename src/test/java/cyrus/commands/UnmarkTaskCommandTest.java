package cyrus.commands;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import cyrus.mocks.MockStorage;
import cyrus.parser.Parser;
import cyrus.storage.IStorage;
import cyrus.tasks.TaskList;
import cyrus.tasks.ToDo;

public class UnmarkTaskCommandTest {
    private static final IStorage MOCK_STORAGE = new MockStorage();
    private static final TaskList TASK_LIST = new TaskList(MOCK_STORAGE);
    private static final Parser PARSER = new Parser();
    @Test
    public void testExecuteWithMissingTaskIndex() {
        var info = PARSER.parse("unmark  ");
        var command = new UnmarkTaskCommand(TASK_LIST, info);
        assertThrows(CommandError.class, command::execute);
    }

    @Test
    public void testExecuteWithNonIntegerIndex() {
        var info = PARSER.parse("unmark bad");
        var command = new UnmarkTaskCommand(TASK_LIST, info);
        assertThrows(CommandError.class, command::execute);
    }

    @Test
    public void testExecuteWithNegativeIndex() {
        var info = PARSER.parse("unmark -10");
        var command = new UnmarkTaskCommand(TASK_LIST, info);
        assertThrows(CommandError.class, command::execute);
    }

    @Test
    public void testExecuteWithOutOfBoundsIndex() {
        var info = PARSER.parse("unmark 100");
        var command = new UnmarkTaskCommand(TASK_LIST, info);
        assertThrows(CommandError.class, command::execute);
    }

    @Test
    public void testExecuteWithValidIndex() throws CommandError {
        var task = new ToDo("only task");
        TASK_LIST.addTask(task);
        new MarkTaskCommand(TASK_LIST, PARSER.parse("mark 1")).execute();

        var info = PARSER.parse("unmark 1");
        var command = new UnmarkTaskCommand(TASK_LIST, info);
        var output = command.execute();
        var expected = new String[]{
            "OK, I've marked this task as not done yet:",
            task.toString()
        };
        assertArrayEquals(expected, output);
        assertFalse(task.getIsDone());
        assertNull(task.getCompletedDate());
    }
}
