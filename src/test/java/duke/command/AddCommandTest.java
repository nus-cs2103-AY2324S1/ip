package duke.command;

import duke.Keyword;
import duke.StorageStub;
import duke.TaskList;
import duke.UiStub;
import duke.exception.DukeException;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class AddCommandTest {

    @Test
    public void execute_validCommand_success() {
        AddCommand add = new AddCommand(Keyword.TODO ,"test");
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();
        ArrayList<Task> list = new ArrayList<>();
        TaskList taskList = new TaskList(list);
        try {
            add.execute(taskList, ui, storage);
            assertEquals(1, list.size());
            new AddCommand(Keyword.DEADLINE ,"test /by 1/1/2023 11:00").execute(taskList, ui, storage);
            assertEquals(2, list.size());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void execute_invalidCommand_exceptionThrown() {
        AddCommand add = new AddCommand(Keyword.DEADLINE ,"test");
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();
        TaskList taskList = new TaskList();
        try {
            add.execute(taskList, ui, storage);
            fail();
        } catch (DukeException e) {
            assert true;
        }
        add = new AddCommand(Keyword.EVENT ,"test /from today /to tomorrow");
        try {
            add.execute(taskList, ui, storage);
            fail();
        } catch (DukeException e) {
            assert true;
        }
    }

    @Test
    public void isExitTest() {
        AddCommand add = new AddCommand(Keyword.TODO ,"");
        assertFalse(add.isExit());
    }
}
