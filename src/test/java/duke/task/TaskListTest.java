package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        // Initialize the taskList before each test
        ArrayList<Task> tasks = new ArrayList<>();
        // Add tasks into the array list
        tasks.add(new MockTodo("Todo Task"));
        tasks.add(new MockDeadline("Deadline Task"));
        tasks.add(new MockEvent("Event Task"));
        taskList = new TaskList(tasks);
    }

    @Test
    public void addTask_validInput_success() {
        Task task = new MockTodo("Sample Todo Task");
        taskList.addTask(task);

        // Check the new task is added to the end
        assertEquals(4, taskList.getLength());
        assertEquals(task, taskList.getTask(3));
    }

    @Test
    public void deleteTask_validInput_success() {
        int initialSize = taskList.getLength();
        taskList.deleteTask(0);

        // Check the task is deleted and list size is reduced
        assertEquals(initialSize - 1, taskList.getLength());
    }

    @Test
    public void deleteTask_invalidIndex_failure() {
        // Try to delete from an invalid index
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.deleteTask(10);
        });
    }

    @Test
    public void deleteTask_emptyList_failure() {
        // Create an empty list
        TaskList emptyList = new TaskList(new ArrayList<>());

        // Try to delete from an empty list
        assertThrows(IndexOutOfBoundsException.class, () -> {
            emptyList.deleteTask(0);
        });
    }

    @Test
    public void getTask_validInput_success() {
        Task task = taskList.getTask(1);
        assertEquals("Deadline Task", task.description);
    }

    @Test
    public void getTask_invalidIndex_failure() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.getTask(10);
        });
    }

    @Test
    public void getLength_emptyList_success() {
        taskList = new TaskList(new ArrayList<>());
        assertEquals(0, taskList.getLength());
    }

    @Test
    public void getLength_nonEmptyList_success() {
        taskList = new TaskList(new ArrayList<>());
        for (int i = 0; i < 5; i++) {
            taskList.addTask(new MockTodo("Test Task"));
        }
        assertEquals(5, taskList.getLength());
    }

    @Test
    public void isEmpty_success() {
        assertFalse(taskList.isEmpty());
        taskList.deleteTask(0);
        taskList.deleteTask(0);
        taskList.deleteTask(0);
        assertTrue(taskList.isEmpty());
    }

    @Test
    public void isEmpty_failure() {
        assertFalse(taskList.isEmpty());
        taskList.deleteTask(0);
        assertFalse(taskList.isEmpty());
    }
}


