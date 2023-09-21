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
        String todoStr = "Yet another task to do, you're amazing. Have added this:\n"
                            + "[T][ ] borrow book\n"
                            + "You now have 1 task(s) to do.";
        assertEquals(todoStr, new TaskList(new StorageStub()).addTodo("borrow book"));
    }
    @Test
    public void deadline_validInput_successMessageShown() {
        String deadlineStr = "No rush on this but do take note of the deadline. Have added this:\n"
                            + "[D][ ] return book (by: Sep 21 2023)"
                            + "\nYou now have 1 task(s) to do.";
        assertEquals(deadlineStr, new TaskList(new StorageStub())
                                .addDeadline("return book", "2023-09-21"));
    }
    @Test
    public void deadline_invalidInput_successMessageShown() {
        assertEquals("It appears you may be missing some details, do kindly enter.",
                new TaskList(new StorageStub()).addDeadline("", "2023-09-21"));
    }
    @Test
    public void event_validInput_successMessageShown() {
        String eventStr = "Events are the norm of the upper echelon. Have added this event:\n"
                            + "[E][ ] meeting (from: Sep 21 2023 to: Sep 22 2023)"
                            + "\nYou now have 1 task(s) to do.";
        assertEquals(eventStr, new TaskList(new StorageStub())
                                .addEvent("meeting", "2023-09-21", "2023-09-22"));
    }
    @Test
    public void event_invalidInput_successMessageShown() {
        assertEquals("It appears you may be missing some details, do kindly enter.",
                new TaskList(new StorageStub()).addEvent("", "2023-09-21", ""));
    }
}