package dook.services;



import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dook.DookException;
import dook.task.Task;
import dook.task.Todo;


public class TaskListTest {
    @Test
    public void markTask_invalidInput_throwsException() {
        ArrayList<Task> testList = new ArrayList<Task>();
        TaskList taskList = new TaskList(testList);
        taskList.addTask(new Todo("AAA", false));
        assertThrows(DookException.class, () -> taskList.markTask(5, true));
        assertThrows(DookException.class, () -> taskList.markTask(2, true));
        assertDoesNotThrow(() -> taskList.markTask(1, true));
    }

    @Test
    public void markTask_validInput_correctResult() {
        ArrayList<Task> testList = new ArrayList<Task>();
        TaskList taskList = new TaskList(testList);
        taskList.addTask(new Todo("BBB", false));
        assertDoesNotThrow(() -> taskList.markTask(1, true));
        String expected = "1. [T][X] BBB\nYou have 1 task in the list.";
        assertEquals(expected,
                taskList.toString());
    }

    @Test
    public void unmarkTask_validInput_correctResult() {
        ArrayList<Task> testList = new ArrayList<Task>();
        TaskList taskList = new TaskList(testList);
        taskList.addTask(new Todo("CCC", true));
        assertDoesNotThrow(() -> taskList.markTask(1, false));
        String expected = "1. [T][ ] CCC\nYou have 1 task in the list.";
        assertEquals(expected,
                taskList.toString());
    }

}
