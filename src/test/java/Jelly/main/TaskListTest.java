package Jelly.main;


import Jelly.task.Task;
import Jelly.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TaskListTest {
    @Test
    public void markTest() {
        TaskList taskList = new TaskList();
        Task todo = new Todo("Do this");
        taskList.add(todo);
        taskList.markAsDone(0);
        assertTrue(todo.getIsDone());
    }
    @Test
    public void unmarkTest() {
        TaskList taskList = new TaskList();
        Task todo = new Todo("Do this");
        todo.markAsDone();
        taskList.add(todo);
        taskList.markAsUndone(0);
        assertFalse(todo.getIsDone());
    }
}
