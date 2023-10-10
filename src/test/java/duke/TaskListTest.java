package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList(new Storage("testfile.txt"));
    }

    @Test
    public void testMark() {
        Task task = new ToDo("Test task 1");
        taskList.addToList(task, 0);

        // Mark the task as done
        String response = taskList.mark("mark 1");

        // Check if the response is correct
        String expectedResponse = "Great job! You've completed the following task:\n[T][X] Test task 1";
        assertEquals(expectedResponse, response);

        // You should check if the task was marked as done in the list.
        assertTrue(taskList.getTasks().get(0).isMarked());

        // You don't need to check if the task was saved to the file here.
    }

    @Test
    public void testUnMark() {
        // Add a task to the list and mark it as done
        Task task = new ToDo("Test task 1");
        taskList.addToList(task, 0);
        taskList.mark("mark 1");

        // Unmark the task as done
        String response = taskList.unMark("unmark 1");

        // Check if the response is correct
        String expectedResponse = "You've marked the following task as incomplete:\n[T][ ] Test task 1";
        assertEquals(expectedResponse, response);

        // You should check if the task was unmarked as done in the list.
        assertFalse(taskList.getTasks().get(0).isMarked());

        // You don't need to check if the task was saved to the file here.
    }
}
