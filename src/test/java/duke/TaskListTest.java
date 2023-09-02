package duke;

import duke.Storage;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        // Create a temporary test directory
        String testDir = "test_data";
        taskList = new TaskList(new Storage(testDir + "/test.txt"));
    }

    @Test
    public void testAddTask() throws DukeException {
        Task task = new ToDo("Sample ToDo", "0");
        taskList.addTask(task);

        List<Task> tasks = taskList.getTasks();
        assertEquals(1, tasks.size());
        assertEquals(task, tasks.get(0));
    }

    @Test
    public void testRemoveTask() throws DukeException {
        Task task1 = new ToDo("Task 1", "0");
        Task task2 = new ToDo("Task 2", "0");
        taskList.addTask(task1);
        taskList.addTask(task2);

        taskList.removeTask(1);

        List<Task> tasks = taskList.getTasks();
        assertEquals(2, tasks.size());
        assertEquals(task2, tasks.get(1));
    }

    @Test
    public void testMarkTaskAsDone() throws DukeException {
        ToDo task = new ToDo("Sample Task", "0");
        taskList.addTask(task);

        taskList.markTaskAsDone(2);

        List<Task> tasks = taskList.getTasks();
        assertEquals(3, tasks.size());
        assertEquals("1", tasks.get(2).getDataStatus());
    }

    @Test
    public void testMarkTaskAsDone_InvalidIndex() {
        assertThrows(DukeException.class, () -> taskList.markTaskAsDone(10));
    }


}


