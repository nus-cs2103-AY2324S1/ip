package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import duke.exception.DukeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setup() {
        taskList = new TaskList(new ArrayList<>());
    }

    @Test
    public void testAddTask() {
        Task task = new ToDo("Test Task");
        taskList.addTask(task);
        assertEquals(1, taskList.getTasks().size());
    }

    @Test
    public void testDeleteTask() throws DukeException {
        Task task = new ToDo("Test Task");
        taskList.addTask(task);

        taskList.deleteTask(0);
        assertEquals(0, taskList.getTasks().size());
    }

    @Test
    public void testDeleteInvalidTask() {
        assertThrows(DukeException.class, () -> taskList.deleteTask(0));
    }

    @Test
    public void testGetTask() throws DukeException {
        Task task = new ToDo("Test Task");
        taskList.addTask(task);

        Task retrievedTask = taskList.get(0);
        assertEquals("[T][ ] Test Task", retrievedTask.toString());
    }

    @Test
    public void testGetInvalidTask() {
        assertThrows(DukeException.class, () -> taskList.get(0));
    }
}