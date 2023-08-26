import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import duke.error.DukeException;
import duke.task.TaskList;
import duke.task.Todo;
import duke.task.Task;

public class TaskListTest {

    @Test
    public void testMarkTask() {
        try {
            TaskList taskList = new TaskList();
            Todo todo = taskList.addTodo("Buy groceries");

            assertEquals(todo.getStatusIcon(), " ");

            taskList.markTask(1);

            assertEquals(todo.getStatusIcon(), "X");
        } catch (DukeException e) {
            fail("Exception should not be thrown for valid input");
        }
    }

    @Test
    public void testMarkTaskInvalidIndex() {
        assertThrows(DukeException.class, () -> {
            TaskList taskList = new TaskList();
            taskList.markTask(1);  // Attempting to mark a task when the list is empty
        });
    }
}
