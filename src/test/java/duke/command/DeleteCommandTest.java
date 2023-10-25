package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Todo;
import duke.util.StorageStub;
import duke.util.TaskList;
import duke.util.UiStub;

/**
 * JUnit test class for DeleteCommand.
 */
public class DeleteCommandTest {

    @Test
    public void execute_validCommand_success() {
        DeleteCommand del = new DeleteCommand("1");
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();
        TaskList taskList = new TaskList();
        try {
            taskList.addTask(new Todo("test"), ui);
            del.execute(taskList, ui, storage);
            assert true;
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void execute_emptyList_exceptionThrown() {
        DeleteCommand del = new DeleteCommand("1");
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();
        TaskList taskList = new TaskList();
        try {
            del.execute(taskList, ui, storage);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! There is nothing in the list, yet!", e.getMessage());
        }
    }

    @Test
    public void execute_invalidCommand_exceptionThrown() {
        DeleteCommand del = new DeleteCommand("one");
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();
        TaskList taskList = new TaskList();
        try {
            taskList.addTask(new Todo("test"), ui);
            del.execute(taskList, ui, storage);
            fail();
        } catch (DukeException e) {
            assert true;
        }
    }

    @Test
    public void isExitTest() {
        DeleteCommand del = new DeleteCommand("");
        assertFalse(del.isExit());
    }
}
