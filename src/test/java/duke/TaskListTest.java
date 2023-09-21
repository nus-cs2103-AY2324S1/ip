package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        int numOfTasks = tl.tasks.size();
        tl.addTask(t);
        assertEquals(numOfTasks + 1, tl.tasks.size());
        assertEquals(t, tl.tasks.get(tl.tasks.size() - 1));
        tl.deleteTask(tl.tasks.size() - 1);
    }
}
