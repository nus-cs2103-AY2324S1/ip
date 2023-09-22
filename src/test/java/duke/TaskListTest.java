package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.task.Todo;

// Solution adapted and inspired from https://chat.openai.com/share/a8b02fc2-b4f0-490d-ad3e-a3e06fe3a168
public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void testAddTask() {
        Task task = new Todo("Buy groceries");
        taskList.addTask(task);
        assertEquals(1, taskList.numTasks());
    }

    @Test
    public void testDeleteTask() {
        Task task = new Todo("Read a book");
        taskList.addTask(task);
        taskList.deleteTask(task);
        assertEquals(0, taskList.numTasks());
    }

    @Test
    public void testGetTask() {
        Task task1 = new Todo("Write code");
        Task task2 = new Todo("Study");
        taskList.addTask(task1);
        taskList.addTask(task2);
        assertEquals(task1, taskList.getTask(0));
        assertEquals(task2, taskList.getTask(1));
    }

    @Test
    public void testSetTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Task 1"));
        tasks.add(new Todo("Task 2"));
        taskList.setTasks(tasks);
        assertEquals(2, taskList.numTasks());
    }

    @Test
    public void testNumTasks() {
        assertEquals(0, taskList.numTasks());
        taskList.addTask(new Todo("Task 1"));
        assertEquals(1, taskList.numTasks());
    }

    @Test
    public void testGetAllTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Task 1"));
        tasks.add(new Todo("Task 2"));
        taskList.setTasks(tasks);
        ArrayList<Task> retrievedTasks = taskList.getAllTasks();
        assertEquals(tasks.size(), retrievedTasks.size());
        for (int i = 0; i < tasks.size(); i++) {
            assertEquals(tasks.get(i), retrievedTasks.get(i));
        }
    }
}
