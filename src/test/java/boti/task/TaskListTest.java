package boti.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * The testing class for TaskList
 */
public class TaskListTest {
    private TaskList sampleTasks;

    /**
     * Sets things up before every tests
     */
    @BeforeEach
    public void setup() {
        sampleTasks = new TaskList(new ArrayList<>());
        sampleTasks.addTask(new ToDoSample());
        sampleTasks.addTask(new ToDoSample());
    }

    /**
     * Tests the method size of list of two tasks in TaskList
     */
    @Test
    public void size_twoTasks_success() {
        assertEquals(sampleTasks.getSize(), 2);
    }

    /**
     * Tests the method addTask in TaskList
     */
    @Test
    public void addTask_success() {
        sampleTasks.addTask(new ToDoSample());
        assertEquals(sampleTasks.getSize(), 3);
    }

    /**
     * Tests the method removeTask in TaskList
     */
    @Test
    public void removeTask_success() {
        sampleTasks.removeTask(1);
        assertEquals(sampleTasks.getSize(), 1);
    }

    /**
     * Tests the method markTask in TaskList
     */
    @Test
    public void markTask_success() {
        sampleTasks.markTask(1);
        assertTrue(sampleTasks.getTask(1).getMark());
    }
}
