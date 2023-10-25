package duke.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.util.StorageStub;
import duke.util.TaskList;
import duke.util.UiStub;

/**
 * JUnit test class for FindCommand.
 */
public class HelpCommandTest {

    @Test
    public void execute_validCommandList_success() {
        HelpCommand h = new HelpCommand();
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();
        TaskList taskList = new TaskList();
        try {
            h.execute(taskList, ui, storage);
            assert true;
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void execute_validCommand_success() {
        HelpCommand h = new HelpCommand("todo");
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();
        TaskList taskList = new TaskList();
        try {
            h.execute(taskList, ui, storage);
            assert true;
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void execute_unknownCommand_exceptionThrown() {
        HelpCommand h = new HelpCommand("test");
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();
        TaskList taskList = new TaskList();
        try {
            h.execute(taskList, ui, storage);
            fail();
        } catch (DukeException e) {
            assert true;
        }
    }

    @Test
    public void execute_invalidCommand_exceptionThrown() {
        HelpCommand h = new HelpCommand("help");
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();
        TaskList taskList = new TaskList();
        try {
            h.execute(taskList, ui, storage);
            fail();
        } catch (DukeException e) {
            assert true;
        }
    }

    @Test
    public void isExitTest() {
        FindCommand find = new FindCommand("");
        assertFalse(find.isExit());
    }
}
