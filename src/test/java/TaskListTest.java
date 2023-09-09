import gbot.StorageStub;
import gbot.TaskList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import exceptions.TaskException;


public class TaskListTest {
    @Test
    public void mark_invalidTaskIndex_taskExceptionThrown () {
        try {
            Assertions.assertEquals("", new TaskList(new StorageStub()).markTask(1));
            fail();
        } catch (TaskException e) {
            assertEquals("Invalid task provided.", e.getMessage());
        }
    }
    @Test
    public void mark_negativeTaskIndex_taskExceptionThrown() {
        try {
            assertEquals("", new TaskList(new StorageStub()).markTask(-1));
            fail();
        } catch (TaskException e) {
            assertEquals("Invalid task provided.", e.getMessage());
        }
    }

    @Test
    public void unmark_invalidTaskIndex_taskExceptionThrown() {
        try {
            assertEquals("", new TaskList(new StorageStub()).unmarkTask(1));
            fail();
        } catch (TaskException e) {
            assertEquals("Invalid task provided.", e.getMessage());
        }
    }
    @Test
    public void unmark_negativeTaskIndex_taskExceptionThrown() {
        try {
            assertEquals("", new TaskList(new StorageStub()).unmarkTask(-1));
            fail();
        } catch (TaskException e) {
            assertEquals("Invalid task provided.", e.getMessage());
        }
    }
    @Test
    public void delete_invalidTaskIndex_taskExceptionThrown() {
        try {
            assertEquals("", new TaskList(new StorageStub()).deleteTask(1));
            fail();
        } catch (TaskException e) {
            assertEquals("Invalid task provided.", e.getMessage());
        }
    }
    @Test
    public void delete_negativeTaskIndex_taskExceptionThrown() {
        try {
            assertEquals("", new TaskList(new StorageStub()).deleteTask(-1));
            fail();
        } catch (TaskException e) {
            assertEquals("Invalid task provided.", e.getMessage());
        }
    }
    @Test
    public void todo_validInput_successMessageShown() {
        String todoStr = "Got it. I've added this task:\n"
                            + "[T][ ] borrow book\n"
                            + "Now you have 1 tasks to do.";
        assertEquals(todoStr, new TaskList(new StorageStub()).addTodo("borrow book"));
    }
    @Test
    public void deadline_validInput_successMessageShown() {
        String deadlineStr = "Got it. I've added this task:\n"
                            + "[D][ ] return book (by: Sep 21 2023)"
                            + "\nNow you have 1 tasks to do.";
        assertEquals(deadlineStr, new TaskList(new StorageStub())
                                .addDeadline("return book /by 2023-09-21"));
    }
    @Test
    public void event_validInput_successMessageShown() {
        String eventStr = "Got it. I've added this task:\n"
                            + "[E][ ] meeting (from: Sep 21 2023 to: Sep 22 2023)"
                            + "\nNow you have 1 tasks to do.";
        assertEquals(eventStr, new TaskList(new StorageStub())
                                .addEvent("meeting /from 2023-09-21 /to 2023-09-22"));
    }
}