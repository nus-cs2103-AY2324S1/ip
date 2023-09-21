package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.ToDos;
import duke.utilities.TaskList;

public class TaskListTest {
    @Test
    public void emptyTaskListTest() {
        TaskList a = new TaskList();
        TaskList b = new TaskList();
        assertEquals(a.getSize(), b.getSize());
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
        assertNotEquals(a.getTask(0), b.getTask(0));
    }

    @Test
    public void markTaskTest() {
        TaskList a = new TaskList();
        a.addTask(new ToDos("Complete my problem set."));
        a.addTask(new ToDos("Do my laundry."));
        a.addTask(new ToDos("Watch my lectures."));
        a.addTask(new ToDos("Walk my dog."));
        a.addTask(new ToDos("Buy groceries."));
        a.markAsDone(3);
        assertEquals(a.getTask(3).getStatusIcon(), "X");
        assertNotEquals(a.getTask(1).getStatusIcon(), "X");
    }

    @Test
    public void deleteTask() throws DukeException {
        TaskList a = new TaskList();
        a.addTask(new ToDos("Complete my problem set."));
        a.addTask(new ToDos("Do my laundry."));
        a.addTask(new ToDos("Watch my lectures."));
        assertEquals(a.getSize(), 3);
        a.deleteTask(1);
        a.deleteTask(1);
        assertEquals(a.getSize(), 1);
    }
}
