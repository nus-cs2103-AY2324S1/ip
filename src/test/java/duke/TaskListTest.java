package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import duke.utilities.TaskList;
import org.junit.jupiter.api.Test;

import duke.tasks.Events;
import duke.tasks.Task;

public class TaskListTest {
    @Test
    public void emptyTaskListTest() {
        TaskList a = new TaskList();
        TaskList b = new TaskList();
        assertEquals(a.size(), b.size());
    }

    @Test
    public void nonEmptyListTest() {
        Task temp = new Events("Vacation", "19/02/2022 0000", "20/02/2022 0000");
        Task temp2 = new Events("Vacation", "24/02/2022 1000", "25/02/2022 1000");
        TaskList a = new TaskList();
        TaskList b = new TaskList();
        a.addTask(temp);
        a.addTask(temp2);
        b.addTask(temp2);
        b.addTask(temp);
        assertEquals(a.getTask(0), b.getTask(1));
        assertEquals(a.getTask(1), b.getTask(0));
        assertNotSame(a.getTask(0), b.getTask(0));
    }

}
