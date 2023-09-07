package chatterchicken.tasklist;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import chatterchicken.data.task.Task;
import chatterchicken.data.task.ToDo;
import chatterchicken.data.exception.CCException;
import chatterchicken.parser.Parser;
import chatterchicken.ui.Ui;

public class TaskListTest {

    @Test
    public void deleteTask_invalidIndex_throwsCCException() {
        TaskList taskList = new TaskList(new ArrayList<>(), new Ui());
        try {
            taskList.deleteTask("-1");
        } catch (CCException e) {
            return;
        }
        fail("Invalid index was deleted from task list.");
    }

    @Test
    public void deleteTask_validIndex_taskDeletedFromList() {
        Parser parser = new Parser();
        TaskList taskList = new TaskList(new ArrayList<>(), new Ui());
        ToDo toBeDeleted = new ToDo("delete this");
        taskList.addTask(new ToDo("read book 1"));
        taskList.addTask(new ToDo("read book 2"));
        taskList.addTask(toBeDeleted);
        taskList.addTask(new ToDo("read book 3"));
        try {
            taskList.deleteTask("3");
        } catch (CCException e) {
            fail(e.getMessage());
        }
        for (Task task : taskList) {
            assertFalse(task.equals(toBeDeleted));
        }
    }
}