package zean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import zean.exception.DukeException;

public class TaskListTest {

    @Test
    public void invalidTaskIndexTest1() {
        Exception exception = assertThrows(DukeException.class, () ->
                new TaskList(new StorageStub()).markTaskDone(1));
        assertEquals("\tHmm, this task does not exist :|",
                exception.getMessage());
    }

    @Test
    public void invalidTaskIndexTest2() {
        Exception exception = assertThrows(DukeException.class, () ->
                new TaskList(new StorageStub()).markTaskDone(-1));
        assertEquals("\tHmm, this task does not exist :|",
                exception.getMessage());
    }

    @Test
    public void invalidTaskIndexTest3() {
        Exception exception = assertThrows(DukeException.class, () ->
                new TaskList(new StorageStub()).markTaskDone(0));
        assertEquals("\tHmm, this task does not exist :|",
                exception.getMessage());
    }

    @Test
    public void invalidTaskIndexTest4() {
        Exception exception = assertThrows(DukeException.class, () ->
                new TaskList(new StorageStub()).markTaskNotDone(1));
        assertEquals("\tHmm, this task does not exist :|",
                exception.getMessage());
    }

    @Test
    public void invalidTaskIndexTest5() {
        Exception exception = assertThrows(DukeException.class, () ->
                new TaskList(new StorageStub()).markTaskNotDone(-1));
        assertEquals("\tHmm, this task does not exist :|",
                exception.getMessage());
    }

    @Test
    public void invalidTaskIndexTest6() {
        Exception exception = assertThrows(DukeException.class, () ->
                new TaskList(new StorageStub()).markTaskNotDone(0));
        assertEquals("\tHmm, this task does not exist :|",
                exception.getMessage());
    }

    @Test
    public void invalidTaskIndexTest7() {
        Exception exception = assertThrows(DukeException.class, () ->
                new TaskList(new StorageStub()).deleteTask(1));
        assertEquals("\tHmm, this task does not exist :|",
                exception.getMessage());
    }

    @Test
    public void invalidTaskIndexTest8() {
        Exception exception = assertThrows(DukeException.class, () ->
                new TaskList(new StorageStub()).deleteTask(-1));
        assertEquals("\tHmm, this task does not exist :|",
                exception.getMessage());
    }

    @Test
    public void invalidTaskIndexTest9() {
        Exception exception = assertThrows(DukeException.class, () ->
                new TaskList(new StorageStub()).deleteTask(0));
        assertEquals("\tHmm, this task does not exist :|",
                exception.getMessage());
    }

    @Test
    public void printAddTaskTest1() {
        assertEquals("\tGot it. I've added this task:\n\t  "
                        + "[T][ ] read book\n"
                        + "\tNow you have 1 task in the list.",
                new TaskList(new StorageStub()).add("read book"));
    }

    @Test
    public void printAddTaskTest2() {
        assertEquals("\tGot it. I've added this task:\n\t  "
                        + "[D][ ] some deadline task (by: Aug 31 2023)\n"
                        + "\tNow you have 1 task in the list.",
                new TaskList(new StorageStub()).add("some deadline task", "2023-08-31"));
    }

    @Test
    public void printAddTaskTest3() {
        assertEquals("\tGot it. I've added this task:\n\t  "
                        + "[E][ ] some event task (from: Aug 31 2023 to: Sep 01 2023)\n"
                        + "\tNow you have 1 task in the list.",
                new TaskList(new StorageStub()).add("some event task", "2023-08-31", "2023-09-01"));
    }

    @Test
    public void printDeleteTaskTest1() {
        TaskList tasks = new TaskList(new StorageStub());
        String add = tasks.add("read book");
        assertEquals("\tNoted. I've removed this task.\n"
                        + "\tNow you have 0 task in the list.",
                tasks.deleteTask(1));
    }

    @Test
    public void printDeleteTaskTest2() {
        TaskList tasks = new TaskList(new StorageStub());
        String add = tasks.add("some deadline task", "2023-08-31");
        assertEquals("\tNoted. I've removed this task.\n"
                        + "\tNow you have 0 task in the list.",
                tasks.deleteTask(1));
    }

    @Test
    public void printDeleteTaskTest3() {
        TaskList tasks = new TaskList(new StorageStub());
        String add = tasks.add("some event task", "2023-08-31", "2023-09-01");
        assertEquals("\tNoted. I've removed this task.\n"
                        + "\tNow you have 0 task in the list.",
                tasks.deleteTask(1));
    }
}
