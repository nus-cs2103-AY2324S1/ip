package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void getList_success() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("read book"));
        TaskList taskList = new TaskList(tasks);
        assertEquals(tasks, taskList.getList());
    }

    @Test
    public void getTask_success() {
        ArrayList<Task> tasks = new ArrayList<>();
        Task todo = new Todo("read book");
        tasks.add(todo);
        TaskList taskList = new TaskList(tasks);
        assertEquals(todo, taskList.getTask(0));
    }

    @Test
    public void addTask_success() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);
        taskList.addTask(new Todo("read book"));
        assertEquals(1, taskList.getSize());
    }

    @Test
    public void deleteTask_success() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);
        Task todo = new Todo("read book");
        taskList.addTask(todo);
        taskList.deleteTask(todo);
        assertEquals(0, taskList.getSize());
    }

    @Test
    public void markTaskAsDone_success() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);
        Task todo = new Todo("read book");
        taskList.addTask(todo);
        taskList.markTask(todo);
        assertTrue(todo.isDone());
    }
}
