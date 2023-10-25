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
 * JUnit test class for ListCommand.
 */
public class ListCommandTest {

    @Test
    public void execute_validList_success() {
        ListCommand list = new ListCommand();
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();
        TaskList taskList = new TaskList();
        try {
            taskList.addTask(new Todo("test"), ui);
            list.execute(taskList, ui, storage);
            assert true;
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void execute_emptyList_exceptionThrown() {
        ListCommand list = new ListCommand();
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();
        TaskList taskList = new TaskList();
        try {
            list.execute(taskList, ui, storage);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! There is nothing in the list, yet!", e.getMessage());
        }
    }

    @Test
    public void isExitTest() {
        ListCommand list = new ListCommand();
        assertFalse(list.isExit());
    }
}
