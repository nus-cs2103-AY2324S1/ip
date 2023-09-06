package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.exception.EmptyDateException;
import duke.exception.EmptyTaskException;
import duke.exception.NoEndDateException;


/**
 * The {@code TaskListTest} class contains JUnit tests for the {@link TaskList} class.
 * It tests the functionality of managing tasks in the task list.
 */
public class TaskListTest {

    /**
     * Tests adding tasks to the task list.
     *
     * @throws DukeException If there is an issue with the creation of tasks.
     */
    @Test
    public void testAddTask() throws DukeException {
        TaskList taskList = new TaskList();

        String test1 = "todo Buy groceries";
        String output1 = "Got it. I've added this task:\n " + "[T][ ] Buy groceries"
                + "\nNow you have 1 tasks in the list.";
        assertEquals(output1, taskList.addTask(test1));

        String test2 = "deadline Finish project /by 2023-12-31 1800";
        String output2 = "Got it. I've added this task:\n " + "[D][ ] Finish project (by: Dec 31 2023 6pm)"
                + "\nNow you have 2 tasks in the list.";
        assertEquals(output2, taskList.addTask(test2));

        String test3 = "todo";
        assertThrows(EmptyTaskException.class, () -> taskList.addTask(test3));

        String test4 = "dealine Finish Meeting /by";
        assertThrows(EmptyDateException.class, () -> taskList.addTask(test4));

        String test5 = "event Meeting /from Mon 2pm";
        assertThrows(NoEndDateException.class, () -> taskList.addTask(test5));

    }

}
