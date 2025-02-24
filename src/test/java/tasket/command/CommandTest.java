package tasket.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static tasket.commons.Messages.MESSAGE_EMPTY_DEADLINE;
import static tasket.commons.Messages.MESSAGE_EMPTY_DESCRIPTION;
import static tasket.commons.Messages.MESSAGE_EMPTY_END;
import static tasket.commons.Messages.MESSAGE_EMPTY_START;
import static tasket.commons.Messages.MESSAGE_EXCEEDS_LIST;
import static tasket.commons.Messages.MESSAGE_LESS_THAN_ONE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tasket.data.TaskList;
import tasket.storage.StorageStub;
import tasket.ui.Ui;

public class CommandTest {

    private static Ui ui;
    private static StorageStub storageStub;
    private static TaskList taskList;

    @BeforeEach
    public void setup() {
        ui = new Ui();
        storageStub = new StorageStub();
        taskList = new TaskList(storageStub.load());
    }

    @Test
    public void addCommand_addTodoWithoutDescription_exceptionThrown() {
        try {
            Command command = new AddCommand("todo", "");
            command.execute(taskList, ui, storageStub);
            fail();
        } catch (Exception e) {
            assertEquals(String.format(MESSAGE_EMPTY_DESCRIPTION, "todo"), e.getMessage());
        }
    }

    @Test
    public void addCommand_addDeadlineWithoutDescription_exceptionThrown() {
        try {
            Command command = new AddCommand("deadline", "");
            command.execute(taskList, ui, storageStub);
            fail();
        } catch (Exception e) {
            assertEquals(String.format(MESSAGE_EMPTY_DESCRIPTION, "deadline"), e.getMessage());
        }
    }

    @Test
    public void addCommand_addDeadlineWithoutDeadline_exceptionThrown() {
        try {
            Command command = new AddCommand("deadline", "return book");
            command.execute(taskList, ui, storageStub);
            fail();
        } catch (Exception e) {
            assertEquals(MESSAGE_EMPTY_DEADLINE, e.getMessage());
        }
    }

    @Test
    public void addCommand_addEventWithoutDescription_exceptionThrown() {
        try {
            Command command = new AddCommand("event", "");
            command.execute(taskList, ui, storageStub);
            fail();
        } catch (Exception e) {
            assertEquals(String.format(MESSAGE_EMPTY_DESCRIPTION, "event"), e.getMessage());
        }
    }

    @Test
    public void addCommand_addEventWithoutStartDate_exceptionThrown() {
        try {
            Command command = new AddCommand("event", "project meeting");
            command.execute(taskList, ui, storageStub);
            fail();
        } catch (Exception e) {
            assertEquals(MESSAGE_EMPTY_START, e.getMessage());
        }
    }

    @Test
    public void addCommand_addEventWithoutEndDate_exceptionThrown() {
        try {
            Command command = new AddCommand("event", "project meeting /from Sun 8pm");
            command.execute(taskList, ui, storageStub);
            fail();
        } catch (Exception e) {
            assertEquals(MESSAGE_EMPTY_END, e.getMessage());
        }
    }

    @Test
    public void markCommand_inputLessThanOne_exceptionThrown() {
        try {
            Command command = new MarkCommand("0");
            command.execute(taskList, ui, storageStub);
            fail();
        } catch (Exception e) {
            assertEquals(MESSAGE_LESS_THAN_ONE, e.getMessage());
        }

    }

    @Test
    public void markCommand_inputLargerThanSize_exceptionThrown() {
        try {
            Command command = new MarkCommand("4");
            command.execute(taskList, ui, storageStub);
            fail();
        } catch (Exception e) {
            assertEquals(MESSAGE_EXCEEDS_LIST, e.getMessage());
        }
    }
}
