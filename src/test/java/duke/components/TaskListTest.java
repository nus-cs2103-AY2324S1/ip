package duke.components;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.tasks.Task;
import duke.tasks.Todo;

public class TaskListTest {
    private Task test = new Todo("test");
    private ArrayList<Task> list = new ArrayList<>();
    @Test
    public void testAddTask() {
        TaskList tasks = new TaskList(list);
        tasks.addTask(test);
        assertEquals(1, tasks.getSize());
        assertEquals("[T][ ] test", tasks.getTask(0).toString());
    }

    @Test
    public void testMark() throws DukeException {
        TaskList tasks = new TaskList(list);
        tasks.addTask(test);
        tasks.mark(1);
        assertEquals("[T][X] test", tasks.getTask(0).toString());
    }

    @Test
    public void testUnmark() throws DukeException {
        TaskList tasks = new TaskList(list);
        tasks.addTask(test);
        tasks.mark(1);
        assertEquals("[T][X] test", tasks.getTask(0).toString());
        tasks.unmark(1);
        assertEquals("[T][ ] test", tasks.getTask(0).toString());
    }

    @Test
    public void testDelete() throws DukeException {
        TaskList tasks = new TaskList(list);
        tasks.addTask(test);
        assertEquals("[T][ ] test", tasks.getTask(0).toString());
        tasks.delete(1);
        assertEquals(0, tasks.getSize());
    }

    @Test
    public void testFilter() throws DukeException {
        TaskList tasks = new TaskList(list);
        tasks.addTask(test);
        tasks.addTask(new Todo("fake"));
        assertEquals("Here are the tasks in your list containing test:\n1. [T][ ] test\n",
                tasks.filter("test"));
        assertEquals("There is nothing on your list currently that matches the keyword \"keyword\". "
                        + "Perhaps you might want to add a new task or try a different keyword?",
                tasks.filter("keyword"));
    }

}
