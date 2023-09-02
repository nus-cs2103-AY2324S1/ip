package gbot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fails;

public class TaskListTest {
    @Test
    public void invalidMarkTaskTest1() {
        try {
            assertEquals("", new TaskList(new StorageStub()).markTask(1));
            fails();
        } catch (TaskException e) {
            assertEquals("Invalid task provided.", e.getMessage());
        }
    }
    @Test
    public void invalidMarkTaskTest2() {
        try {
            assertEquals("", new TaskList(new StorageStub()).markTask(-1));
            fails();
        } catch (TaskException e) {
            assertEquals("Invalid task provided.", e.getMessage());
        }
    }
    @Test
    public
    @Test
    public void invalidUnmarkTaskTest1() {
        try {
            assertEquals("", new TaskList(new StorageStub()).unmarkTask(1));
            fails();
        } catch (TaskException e) {
            assertEquals("Invalid task provided.", e.getMessage());
        }
    }
    @Test
    public void invalidUnmarkTaskTest2() {
        try {
            assertEquals("", new TaskList(new StorageStub()).unmarkTask(-1));
            fails();
        } catch (TaskException e) {
            assertEquals("Invalid task provided.", e.getMessage());
        }
    }
    @Test
    public void invalidDeleteTaskTest1() {
        try {
            assertEquals("", new TaskList(new StorageStub()).deleteTask(1));
            fails();
        } catch (TaskException e) {
            assertEquals("Invalid task provided.", e.getMessage());
        }
    }
    @Test
    public void invalidDeleteTaskTest2() {
        try {
            assertEquals("", new TaskList(new StorageStub()).deleteTask(-1));
            fails();
        } catch (TaskException e) {
            assertEquals("Invalid task provided.", e.getMessage());
        }
    }
    @Test
    public void addTodoTest() {
        String todoStr = "Got it. I've added this task:\n"
                            + "[T][ ] borrow book" +
                            + "\nNow you have 1 tasks to do.";
        assertEquals(todoStr, new TaskList(new StorageStub).addTodo("read book"));
    }
    @Test
    public void addDeadlineTest() {
        String deadlineStr = "Got it. I've added this task:\n"
                            + "[D][ ] return book (by: Sep 21 2023)"
                            + "\nNow you have 1 tasks to do.";
        assertEquals(deadlineStr, new TaskList(new StorageStub)
                                .addDeadline("return book /by 2023-09-21"));
    }
    @Test
    public void addEventTest() {
        String eventStr = "Got it. I've added this task:\n"
                            + "[T][ ] meeting (from: Sep 21 2023 to: Sep 22 2023"
                            +"\nNow you have 1 tasks to do.";
        assertEquals(eventStr, new TaskList(new StorageStub)
                                .addTodo("meeting /from 2023-09-21 /to 2023-09-22"));
    }
}