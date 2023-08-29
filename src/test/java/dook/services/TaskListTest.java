package dook.services;

import dook.DookException;
import dook.task.Deadline;
import dook.task.Event;
import dook.task.Task;
import dook.task.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(expected
        , taskList.toString());
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

    @Test
    public void getTasksBefore_validInput_correctResult1() {
        ArrayList<Task> testList = new ArrayList<Task>();
        TaskList taskList = new TaskList(testList);
        taskList.addTask(new Event(
                "AAA", "20-02-2001", "22-02-2001", false));
        taskList.addTask(new Event(
                "BBB", "20-02-2001", "21-02-2001", false));
        taskList.addTask(new Deadline(
                "CCC", "23-02-2001", false));
        taskList.addTask(new Deadline(
                "DDD", "19-02-2001", false));
        assertDoesNotThrow(() -> taskList.getTasksBefore(LocalDate.of(2003, 3, 10)));
        String expected = "2. [E][ ] BBB (from: 20-02-2001 to: 21-02-2001)\n" +
                "4. [D][ ] DDD (by: 19-02-2001)\n";
        assertEquals(expected,
                taskList.getTasksBefore(LocalDate.of(2001, 2, 22)));
    }

    @Test
    public void getTasksBefore_validInput_correctResult2() {
        ArrayList<Task> testList = new ArrayList<Task>();
        TaskList taskList = new TaskList(testList);
        taskList.addTask(new Event(
                "AAA", "20-02-2001", "22-02-2001", false));
        taskList.addTask(new Event(
                "BBB", "20-01-2001", "21-01-2001", false));
        taskList.addTask(new Deadline(
                "CCC", "2001-01-01", false));
        taskList.addTask(new Deadline(
                "DDD", "26-02-2002", false));
        assertDoesNotThrow(() -> taskList.getTasksBefore(LocalDate.of(2003, 3, 10)));
        String expected = "2. [E][ ] BBB (from: 20-01-2001 to: 21-01-2001)\n" +
                "3. [D][ ] CCC (by: 01-01-2001)\n";
        assertEquals(expected,
                taskList.getTasksBefore(LocalDate.of(2001, 1, 22)));
    }

}
