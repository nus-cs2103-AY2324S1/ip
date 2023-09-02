package aichan;

import aichan.task.ToDo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the TaskList class.
 */
public class TaskListTest {

    /**
     * Check the correctness of the method getSize().
     */
    @Test
    public void testGetSize() {
        TaskList tasks = new TaskList();
        ToDo todo1 = new ToDo("return book");
        ToDo todo2 = new ToDo("buy milk");
        tasks.addTask(todo1);
        tasks.addTask(todo2);
        assertEquals(2, tasks.getSize());
    }

    /**
     * Check the correctness of the method getTask().
     */
    @Test
    public void testGetTask() {
        TaskList tasks = new TaskList();
        ToDo todo1 = new ToDo("return book");
        ToDo todo2 = new ToDo("buy milk");
        tasks.addTask(todo1);
        tasks.addTask(todo2);
        assertEquals(todo2, tasks.getTask(2));
    }
}
