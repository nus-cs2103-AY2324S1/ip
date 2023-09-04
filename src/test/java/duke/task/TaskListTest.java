package duke.task;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    private TaskList taskList = new TaskList();

    @Test
    public void testAddTask() {
        taskList.addTask(new ToDo("Test"));
        taskList.addTask(new Event("Test2", "Mon 12pm", "Tues 6pm"));
        assertEquals(2, taskList.getSize());
    }

    @Test
    public void testRemoveTask() {
        taskList.addTask(new Event("Test2", "Mon 12pm", "Tues 6pm"));
        taskList.removeTask(0);
        assertEquals(0, taskList.getSize());
    }

    @Test
    public void testGetTaskValidIndex() {
        taskList.addTask(new ToDo("Test"));
        taskList.addTask(new Event("Test2", "Mon 12pm", "Tues 6pm"));
        taskList.addTask(new Deadline("homework", "12/09/2080 1718"));

        assertEquals("Test", taskList.getTask(0).getDescription());
        assertEquals("Test2", taskList.getTask(1).getDescription());
        assertEquals("homework", taskList.getTask(2).getDescription());
    }

    @Test
    public void testGetTaskInvalidIndex() {
        taskList.addTask(new ToDo("Test"));
        taskList.addTask(new Event("Test2", "Mon 12pm", "Tues 6pm"));
        taskList.addTask(new Deadline("homework", "12/09/2080 1718"));

        assertThrows(IndexOutOfBoundsException.class, () -> taskList.getTask(3));
    }

    @Test
    public void testGetAllTasks() {
        taskList.addTask(new ToDo("Test"));
        taskList.addTask(new Event("Test2", "Mon 12pm", "Tues 6pm"));
        taskList.addTask(new Deadline("homework", "12/09/2080 1718"));

        ArrayList<Task> allTasks = taskList.getAllTasks();

        assertNotNull(allTasks);
        assertEquals("Test", allTasks.get(0).getDescription());
        assertEquals("Test2", allTasks.get(1).getDescription());
        assertEquals("homework", allTasks.get(2).getDescription());
        assertEquals(3, allTasks.size());
    }

}
