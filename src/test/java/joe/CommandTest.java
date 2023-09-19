package joe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import joe.commands.DeadlineCommand;
import joe.commands.DeleteCommand;
import joe.commands.EventCommand;
import joe.commands.ListCommand;
import joe.commands.MarkCommand;
import joe.commands.TodoCommand;
import joe.commands.UnmarkCommand;
import joe.exceptions.JoeException;
import joe.stubs.StorageStub;
import joe.stubs.TaskListStub;

public class CommandTest {
    @Test
    public void todo_expectedInput_success() {
        TaskListStub taskListStub = new TaskListStub(0);
        StorageStub storageStub = new StorageStub();
        LocalDateTime dateTime = LocalDateTime.of(2023, 1, 1, 12, 0);

        TodoCommand command = new TodoCommand("My Task");

        String result = command.execute(taskListStub, storageStub);

        assertEquals("Got it, I've added this task:\n"
                + " [T][ ] My Task\n"
                + "Now you have 1 tasks in the list.", result);
        assertEquals(1, taskListStub.size());
    }

    @Test
    public void deadline_expectedInput_success() {
        TaskListStub taskListStub = new TaskListStub(0);
        StorageStub storageStub = new StorageStub();
        LocalDateTime dateTime = LocalDateTime.of(2023, 1, 1, 12, 0);

        DeadlineCommand command = new DeadlineCommand("My Task", dateTime);

        String result = command.execute(taskListStub, storageStub);

        assertEquals("Got it, I've added this task:\n"
                + " [D][ ] My Task (by: 01 Jan 2023 12:00)\n"
                + "Now you have 1 tasks in the list.", result);
        assertEquals(1, taskListStub.size());
    }

    @Test
    public void event_expectedInput_success() {
        TaskListStub taskListStub = new TaskListStub(0);
        StorageStub storageStub = new StorageStub();
        LocalDateTime fromDateTime = LocalDateTime.of(2023, 1, 1, 12, 0);
        LocalDateTime toDateTime = LocalDateTime.of(2023, 1, 2, 12, 0);

        EventCommand command = new EventCommand("My Task", fromDateTime, toDateTime);

        try {
            String result = command.execute(taskListStub, storageStub);
            assertEquals("Got it, I've added this task:\n"
                    + " [E][ ] My Task (from: 01 Jan 2023 12:00 to: 02 Jan 2023 12:00)\n"
                    + "Now you have 1 tasks in the list.", result);
            assertEquals(1, taskListStub.size());
        } catch (JoeException e) {
            fail();
        }
    }

    @Test
    public void event_badDates_success() {
        TaskListStub taskListStub = new TaskListStub(0);
        StorageStub storageStub = new StorageStub();
        LocalDateTime fromDateTime = LocalDateTime.of(2023, 1, 2, 12, 0);
        LocalDateTime toDateTime = LocalDateTime.of(2023, 1, 1, 12, 0);

        EventCommand command = new EventCommand("My Task", fromDateTime, toDateTime);
        assertThrows(JoeException.class, () -> command.execute(taskListStub, storageStub));
    }

    @Test
    public void event_sameDateTime_success() {
        TaskListStub taskListStub = new TaskListStub(0);
        StorageStub storageStub = new StorageStub();
        LocalDateTime dateTime = LocalDateTime.of(2023, 1, 1, 12, 0);

        EventCommand command = new EventCommand("My Task", dateTime, dateTime);

        try {
            String result = command.execute(taskListStub, storageStub);
            assertEquals("Got it, I've added this task:\n"
                    + " [E][ ] My Task (from: 01 Jan 2023 12:00 to: 01 Jan 2023 12:00)\n"
                    + "Now you have 1 tasks in the list.", result);
            assertEquals(1, taskListStub.size());
        } catch (JoeException e) {
            fail();
        }
    }

    @Test
    public void list_expectedInput_success() {
        TaskListStub taskListStub = new TaskListStub(0);
        StorageStub storageStub = new StorageStub();

        ListCommand command = new ListCommand();

        String result = command.execute(taskListStub, storageStub);

        assertEquals("Here are your tasks:\n"
                + "TaskListStub", result);

    }

    @Test
    public void delete_expectedInput_success() {
        TaskListStub taskListStub = new TaskListStub(1);
        StorageStub storageStub = new StorageStub();

        DeleteCommand command = new DeleteCommand(1);

        try {
            String result = command.execute(taskListStub, storageStub);
            assertEquals("Noted. I've removed this task:\n"
                    + " toString\n"
                    + "Now you have 0 tasks in the list.", result);
            assertEquals(0, taskListStub.size());
        } catch (JoeException e) {
            fail();
        }
    }

    @Test
    public void delete_badIndex_success() {
        TaskListStub taskListStub = new TaskListStub(1);
        StorageStub storageStub = new StorageStub();

        DeleteCommand commandTwo = new DeleteCommand(2);
        assertThrows(JoeException.class, () -> commandTwo.execute(taskListStub, storageStub));

        DeleteCommand commandZero = new DeleteCommand(0);
        assertThrows(JoeException.class, () -> commandZero.execute(taskListStub, storageStub));

        DeleteCommand commandNegative = new DeleteCommand(-1);
        assertThrows(JoeException.class, () -> commandNegative.execute(taskListStub, storageStub));
    }

    @Test
    public void mark_expectedInput_success() {
        TaskListStub taskListStub = new TaskListStub(1);
        StorageStub storageStub = new StorageStub();

        MarkCommand command = new MarkCommand(1);

        try {
            String result = command.execute(taskListStub, storageStub);
            assertEquals("Nice! I've marked this task as done:\n"
                    + " toString", result);
            assertEquals(1, taskListStub.size());
        } catch (JoeException e) {
            fail();
        }
    }

    @Test
    public void mark_badIndex_success() {
        TaskListStub taskListStub = new TaskListStub(1);
        StorageStub storageStub = new StorageStub();

        MarkCommand commandTwo = new MarkCommand(2);
        assertThrows(JoeException.class, () -> commandTwo.execute(taskListStub, storageStub));

        MarkCommand commandZero = new MarkCommand(0);
        assertThrows(JoeException.class, () -> commandZero.execute(taskListStub, storageStub));

        MarkCommand commandNegative = new MarkCommand(-1);
        assertThrows(JoeException.class, () -> commandNegative.execute(taskListStub, storageStub));
    }

    @Test
    public void unmark_expectedInput_success() {
        TaskListStub taskListStub = new TaskListStub(1);
        StorageStub storageStub = new StorageStub();

        UnmarkCommand command = new UnmarkCommand(1);

        try {
            String result = command.execute(taskListStub, storageStub);
            assertEquals("OK! I've marked this task as not done:\n"
                    + " toString", result);
            assertEquals(1, taskListStub.size());
        } catch (JoeException e) {
            fail();
        }
    }

    @Test
    public void unmark_badIndex_success() {
        TaskListStub taskListStub = new TaskListStub(1);
        StorageStub storageStub = new StorageStub();

        UnmarkCommand commandTwo = new UnmarkCommand(2);
        assertThrows(JoeException.class, () -> commandTwo.execute(taskListStub, storageStub));

        UnmarkCommand commandZero = new UnmarkCommand(0);
        assertThrows(JoeException.class, () -> commandZero.execute(taskListStub, storageStub));

        UnmarkCommand commandNegative = new UnmarkCommand(-1);
        assertThrows(JoeException.class, () -> commandNegative.execute(taskListStub, storageStub));
    }
}
