package cyrus.commands;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import cyrus.mocks.MockStorage;
import cyrus.parser.Parser;
import cyrus.storage.IStorage;
import cyrus.tasks.TaskList;
import cyrus.tasks.ToDo;

public class MarkTaskCommandTest {
    private static final IStorage MOCK_STORAGE = new MockStorage();
    private static final TaskList TASK_LIST = new TaskList(MOCK_STORAGE);
    private static final Parser PARSER = new Parser();

    @Test
    public void testExecuteWithMissingTaskIndex() {
        var info = PARSER.parse("mark  ");
        var command = new MarkTaskCommand(TASK_LIST, info);
        assertThrows(CommandError.class, command::execute);
    }

    @Test
    public void testExecuteWithNonIntegerIndex() {
        var info = PARSER.parse("mark bad");
        var command = new MarkTaskCommand(TASK_LIST, info);
        assertThrows(CommandError.class, command::execute);
    }

    @Test
    public void testExecuteWithNegativeIndex() {
        var info = PARSER.parse("mark -10");
        var command = new MarkTaskCommand(TASK_LIST, info);
        assertThrows(CommandError.class, command::execute);
    }

    @Test
    public void testExecuteWithOutOfBoundsIndex() {
        var info = PARSER.parse("mark 100");
        var command = new MarkTaskCommand(TASK_LIST, info);
        assertThrows(CommandError.class, command::execute);
    }

    @Test
    public void testExecuteWithValidIndex() throws CommandError {
        var task = new ToDo("only task");
        TASK_LIST.addTask(task);
        var info = PARSER.parse("mark 1");
        var command = new MarkTaskCommand(TASK_LIST, info);
        var output = command.execute();
        var expected = new String[]{
            "Nice! I've marked this task as done:",
            task.toString()
        };
        assertArrayEquals(expected, output);
        assertTrue(task.getIsDone());
        assertEquals(LocalDate.now(), task.getCompletedDate());
    }
}
