package seedu.duke;

import org.junit.jupiter.api.Test;

import duke.task.TaskList;
import duke.task.ToDoTask;


public class TaskListTest {
    @Test
    public void addTask_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDoTask("test"));
        assert taskList.getTask(0).getDescription().equals("test");
    }

    @Test
    public void deleteTask_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDoTask("test"));
        taskList.deleteTask(0);
        assert taskList.getSize() == 0;
    }

    @Test
    public void getTask_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDoTask("test"));
        assert taskList.getTask(0).getDescription().equals("test");
    }

    @Test
    public void markTask_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDoTask("test"));
        taskList.markTask(0);
        assert taskList.getTask(0).getStatusIcon().equals("\u2713");
    }

    @Test
    public void unmarkTask_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDoTask("test"));
        taskList.unmarkTask(0);
        assert taskList.getTask(0).getStatusIcon().equals("\u2718");
    }

    @Test
    public void size_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDoTask("test"));
        assert taskList.getSize() == 1;
    }

    @Test
    public void getList_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDoTask("test"));
        assert taskList.getTasks().get(0).getDescription().equals("test");
    }

    @Test
    public void listToString_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDoTask("test"));
        assert taskList.toString().trim().equals("1. [T] [✘] test");
    }

}
