package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Todo;
import duke.util.Keyword;
import duke.util.StorageStub;
import duke.util.TaskList;
import duke.util.UiStub;

/**
 * JUnit test class for MarkCommand.
 */
public class MarkCommandTest {

    @Test
    public void execute_validCommand_success() {
        MarkCommand mark = new MarkCommand(Keyword.MARK, "1");
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();
        TaskList taskList = new TaskList();
        try {
            taskList.addTask(new Todo("test"), ui);
            mark.execute(taskList, ui, storage);
            assert true;
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void execute_emptyList_exceptionThrown() {
        MarkCommand mark = new MarkCommand(Keyword.MARK, "1");
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();
        TaskList taskList = new TaskList();
        try {
            mark.execute(taskList, ui, storage);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! There is nothing in the list, yet!", e.getMessage());
        }
    }

    @Test
    public void execute_invalidCommand_exceptionThrown() {
        MarkCommand mark = new MarkCommand(Keyword.DELETE, "1");
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();
        TaskList taskList = new TaskList();
        try {
            taskList.addTask(new Todo("test"), ui);
            mark.execute(taskList, ui, storage);
            fail();
        } catch (DukeException e) {
            assert true;
        }
        mark = new MarkCommand(Keyword.MARK, "2");
        try {
            mark.execute(taskList, ui, storage);
            fail();
        } catch (DukeException e) {
            assert true;
        }
    }

    @Test
    public void isExitTest() {
        MarkCommand mark = new MarkCommand(Keyword.MARK, "");
        assertFalse(mark.isExit());
    }
}
