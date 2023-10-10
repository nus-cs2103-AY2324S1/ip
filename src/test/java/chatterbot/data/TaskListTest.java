package chatterbot.data;

import chatterbot.exceptions.InvalidDeadlineException;
import chatterbot.storage.Storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

public class TaskListTest {
    private TaskList taskList;
    private Storage mockStorage;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList(new ArrayList<>());
        mockStorage = new Storage("testing");
    }

    @Test
    public void addTask_ValidTask_TaskAddedToList() throws InvalidDeadlineException, IOException {
        // task will be added successfully
        Task task = new Todo("Test Task");
        String response = taskList.addTask(task, mockStorage);

        assertEquals(1, taskList.getSize());
        assertEquals("Got it. I've added this task:\n" + task.formatForFile() + "\nNow you have 1 tasks in the list.", response);
    }

    @Test
    public void deleteTask_ValidIndex_TaskDeletedFromList() throws InvalidDeadlineException, IOException {
        // task will be deleted successfully
        Task task = new Todo("Test Task");
        taskList.addTask(task, mockStorage);

        String response = taskList.deleteTask(0, mockStorage);

        assertEquals(0, taskList.getSize());
        assertEquals("Noted. I've removed this task:\n" + task.formatForFile() + "\nNow you have 0 tasks in the list.", response);
    }

    @Test
    public void isDuplicate_DuplicateDescription_ReturnsTrue() throws InvalidDeadlineException, IOException {
        // duplicate tasks are identified and not added
        Task task1 = new Todo("Test Task");
        Task task2 = new Deadline("Test Task", "2023-12-31");

        taskList.addTask(task1, mockStorage);

        assertTrue(taskList.isDuplicate("Test Task")); // Check for case-insensitive duplicate
    }

    @Test
    public void isDuplicate_NonDuplicateDescription_ReturnsFalse() throws InvalidDeadlineException, IOException {
        // non-duplicate tasks are not wrongly identified as duplicate tasks
        Task task = new Todo("Test Task");
        taskList.addTask(task, mockStorage);

        assertFalse(taskList.isDuplicate("Another Task"));
    }
}
