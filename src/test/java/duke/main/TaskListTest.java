package duke.main;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TaskListTest {
    @Test
    public void addTaskTest() {
        TaskList taskList = new TaskList();

        //create tasks
        Task toDo = new ToDo("Buy groceries");
        Task deadline = new Deadline("Sample Deadline Task", "31/08/2023");
        Task event = new Event("Sample Event Task",
                "31/08/2023 1600","31/08/2023 1800");

        taskList.add(toDo);
        taskList.add(deadline);
        taskList.add(event);
        assertEquals(3, taskList.size());
        assertEquals(toDo, taskList.get(0));
        assertEquals(deadline, taskList.get(1));
        assertEquals(event, taskList.get(2));
    }

    @Test
    public void deleteTaskTest() {
        TaskList taskList = new TaskList();
        Task task1 = new ToDo("Complete assignment");
        Task task2 = new ToDo("Read a book");
        taskList.add(task1);
        taskList.add(task2);
        taskList.delete(0);
        assertEquals(1, taskList.size());
        assertEquals(task2, taskList.get(0));
    }

    @Test
    public void markTaskDoneTest() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Go for a run");
        taskList.add(task);
        taskList.markDone(0);
        assertTrue(task.getIsDone());
    }

    @Test
    public void unmarkTaskNotDoneTest() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Write code");
        task.markDone(); // Mark the task as done initially
        taskList.add(task);
        taskList.markNotDone(0);
        assertFalse(task.getIsDone());
    }
}
