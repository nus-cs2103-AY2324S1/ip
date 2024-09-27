package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.Todo;
public class TaskListTest {
    @Test
    public void testSize() {
        TaskList tasks = new TaskList();
        assertEquals(0, tasks.getSize());
        tasks.add(new Todo("read book"));
        assertEquals(1, tasks.getSize());
    }

    @Test
    public void testAdd() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("read book"));
        assertEquals("[T][ ] read book", tasks.getTasks().get(0).toString());
    }

    @Test
    public void testDelete_correctIndex_success() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("read book"));
        Task t = tasks.delete(1);
        assertEquals("[T][ ] read book", t.toString());

    }

    @Test
    public void testDelete_wrongIndex_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            tasks.add(new Todo("read book"));
            Task t = tasks.delete(2);
        } catch (Exception e) {
            assertEquals("Index 1 out of bounds for length 1", e.getMessage());
        }
    }

    @Test
    public void testMarkAndUnmark() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("read book"));
        tasks.mark(1);
        assertEquals("[T][X] read book", tasks.getTasks().get(0).toString());
        tasks.unmark(1);
        assertEquals("[T][ ] read book", tasks.getTasks().get(0).toString());
    }
}
