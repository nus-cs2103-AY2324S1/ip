package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.Duke;
import duke.exception.DukeException;

public class TaskListTest {
    private Duke duke;
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        duke = new Duke("./data/test.txt"); // Use a test file path
        taskList = duke.getTaskList();
    }

    @Test
    public void testAddTask() {
        Task task = new ToDoTask("Task", Task.Priority.LOW);
        taskList.addTask(task);
        ArrayList<Task> tasks = taskList.getTasks();

        assertEquals(1, tasks.size());
        assertEquals(task, tasks.get(0));
    }

    @Test
    public void deleteTask_indexWithinBounds_success() {
        Task task1 = new ToDoTask("Task1", Task.Priority.LOW);
        Task task2 = new ToDoTask("Task2", Task.Priority.LOW);
        taskList.addTask(task1);
        taskList.addTask(task2);
        ArrayList<Task> tasks = taskList.getTasks();

        try {
            taskList.deleteTask(1);
            assertFalse(tasks.contains(task2));

            taskList.deleteTask(0);
            assertFalse(tasks.contains(task1));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void deleteTask_indexOutOfBounds_exceptionThrown() {
        Task task1 = new ToDoTask("Task1", Task.Priority.LOW);
        Task task2 = new ToDoTask("Task2", Task.Priority.LOW);
        taskList.addTask(task1);
        taskList.addTask(task2);

        try {
            taskList.deleteTask(2);
            fail();
        } catch (DukeException e) {
            assertEquals("Please enter a valid number.", e.getMessage());
        }
    }

    @Test
    public void markTask_indexOutOfBounds_exceptionThrown() {
        Task task = new ToDoTask("Task", Task.Priority.LOW);
        taskList.addTask(task);

        try {
            taskList.markTaskAsDone(1);
            fail();
        } catch (DukeException e) {
            assertEquals("Please enter a valid number.", e.getMessage());
        }
    }

    @Test
    public void markTask_indexWithinBounds_success() {
        Task task = new ToDoTask("Task", Task.Priority.LOW);
        taskList.addTask(task);
        ArrayList<Task> tasks = taskList.getTasks();

        try {
            taskList.markTaskAsDone(0);
            assertTrue(tasks.get(0).isDone());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void unmarkTask_indexOutOfBounds_exceptionThrown() {
        Task task = new ToDoTask("Task", Task.Priority.LOW);
        taskList.addTask(task);

        try {
            taskList.unmarkTask(1);
            fail();
        } catch (DukeException e) {
            assertEquals("Please enter a valid number.", e.getMessage());
        }
    }

    @Test
    public void unmarkTask_indexWithinBounds_success() {
        Task task = new ToDoTask("Task", Task.Priority.LOW);
        taskList.addTask(task);
        ArrayList<Task> tasks = taskList.getTasks();

        try {
            taskList.markTaskAsDone(0);
            taskList.unmarkTask(0);
            assertFalse(tasks.get(0).isDone());
        } catch (DukeException e) {
            fail();
        }
    }
}
