package duke.command;

import duke.Keyword;
import duke.StorageStub;
import duke.TaskList;
import duke.UiStub;
import duke.exception.DukeException;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class MarkCommandTest {

    @Test
    public void execute_validCommand_success() {
        MarkCommand mark = new MarkCommand(Keyword.MARK, "1");
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("test"), ui);
        try {
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
        taskList.addTask(new Todo("test"), ui);
        try {
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
