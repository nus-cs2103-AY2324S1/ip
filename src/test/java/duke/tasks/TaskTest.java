package duke.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    // Testing the creation of a task and its default values.
    @Test
    public void createTaskTest() {
        Task task = new Task(TaskType.TODO, "Test description");

        assertEquals(TaskType.TODO, task.getTaskType());
        assertEquals("Test description", task.getDescription());
        assertFalse(task.isDone()); // Ensure that by default, tasks are not done.
    }

    // Testing the string representation of a task.
    @Test
    public void toStringTest() {
        Task todoTask = new Task(TaskType.TODO, "Todo task");
        assertEquals("[T][ ] Todo task", todoTask.toString());

        Task eventTask = new Task(TaskType.EVENT, "Event task");
        assertEquals("[E][ ] Event task", eventTask.toString());

        Task deadlineTask = new Task(TaskType.DEADLINE, "Deadline task");
        assertEquals("[D][ ] Deadline task", deadlineTask.toString());
    }

    // Testing marking a task as done.
    @Test
    public void markTaskTest() {
        Task task = new Task(TaskType.TODO, "Test description");

        task.mark();
        assertTrue(task.isDone());
        assertEquals("[T][X] Test description", task.toString());
    }

    // Testing unmarking a task.
    @Test
    public void unmarkTaskTest() {
        Task task = new Task(TaskType.TODO, "Test description");

        task.mark();
        task.unmark();
        assertFalse(task.isDone());
        assertEquals("[T][ ] Test description", task.toString());
    }

    // Testing the transformFormat method.
    @Test
    public void transformFormatTest() {
        Task todoTask = new Task(TaskType.TODO, "Todo task");
        assertEquals("T | false | Todo task", todoTask.transformFormat());

        Task eventTask = new Task(TaskType.EVENT, "Event task");
        assertEquals("E | false | Event task", eventTask.transformFormat());

        Task deadlineTask = new Task(TaskType.DEADLINE, "Deadline task");
        assertEquals("D | false | Deadline task", deadlineTask.transformFormat());
    }
}
