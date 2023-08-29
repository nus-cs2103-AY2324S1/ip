package seedu.duke;

import duke.task.TaskList;
import duke.task.ToDoTask;
import org.junit.jupiter.api.Test;

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
        assert taskList.size() == 0;
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
        assert taskList.size() == 1;
    }

    @Test
    public void getList_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDoTask("test"));
        assert taskList.getList().get(0).getDescription().equals("test");
    }

    @Test
    public void listToString_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDoTask("test"));
        assert taskList.toString().trim().equals("1. [T] [✘] test");
    }

}
