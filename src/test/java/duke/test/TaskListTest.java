package duke.test;

import duke.DukeException;
import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testAdd() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("a", true));
        assertEquals(1, tasks.listSize());
    }

    @Test
    public void testDelete() throws DukeException {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("a", true));
        tasks.addTask(new Todo("b", true));
        tasks.addTask(new Todo("c", true));
        tasks.deleteTask(1);
        assertEquals(2, tasks.listSize());
    }
}
