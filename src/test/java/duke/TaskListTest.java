package duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests TaskList class.
 */
public class TaskListTest {

    /**
     * Tests addTask function.
     */
    @Test
    public void addTask() {
        Task t = new Task("test");
        TaskList tl = new TaskList(new ArrayList<>(), new Storage("data/tasks.txt"));
        tl.addTask(t);
        assertEquals(t, tl.tasks.get(tl.tasks.size() - 1));
    }
}
