package duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class TaskListTest {
    @Test
    public void taskList_testAddTask() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList(ui);

        Todo todo = new Todo("todo");
        taskList.addTask(todo);
        assertTrue(taskList.getTasks().contains(todo));

        Deadline deadline = new Deadline("deadline", "2023-09-02 16:00");
        taskList.addTask(deadline);
        assertTrue(taskList.getTasks().contains(deadline));

        Event event = new Event("event", "Sep 4th 5pm", "7pm");
        taskList.addTask(event);
        assertTrue(taskList.getTasks().contains(event));
    }

    @Test
    public void taskList_testDeleteTask() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList(ui);

        Todo todo = new Todo("todo");
        taskList.addTask(todo);
        assertTrue(taskList.getTasks().contains(todo));

        taskList.deleteTask(0);
        assertFalse(taskList.getTasks().contains(todo));
    }

    @Test
    public void taskList_testMarkTaskAsDone() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList(ui);

        Todo todo = new Todo("todo");
        taskList.addTask(todo);
        assertTrue(taskList.getTasks().contains(todo));

        taskList.markTaskAsDone(0);
        assertTrue(taskList.getTasks().get(0).isDone);
    }

    @Test
    public void taskList_testMarkTaskAsUnDone() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList(ui);

        Todo todo = new Todo("todo");
        taskList.addTask(todo);
        assertTrue(taskList.getTasks().contains(todo));

        taskList.markTaskAsDone(0);
        assertTrue(taskList.getTasks().get(0).isDone);

        taskList.markTaskAsUnDone(0);
        assertFalse(taskList.getTasks().get(0).isDone);
    }
}
