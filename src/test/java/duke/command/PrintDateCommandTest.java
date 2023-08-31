package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.StorageStub;
import duke.TaskList;
import duke.UiStub;
import duke.exception.DukeException;
import duke.task.Deadline;


public class PrintDateCommandTest {

    @Test
    public void execute_validCommand_success() {
        PrintDateCommand pd = new PrintDateCommand("deadline /on 2/12/2023");
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();
        TaskList taskList = new TaskList();
        taskList.addTask(new Deadline("",
                LocalDateTime.of(2023, 12, 2, 11, 59)), ui);
        try {
            pd.execute(taskList, ui, storage);
            assert true;
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void execute_emptyList_exceptionThrown() {
        PrintDateCommand pd = new PrintDateCommand("deadline /on 2/12/2023");
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();
        TaskList taskList = new TaskList();
        try {
            pd.execute(taskList, ui, storage);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! There is nothing in the list, yet!", e.getMessage());
        }
    }

    @Test
    public void execute_invalidCommand_exceptionThrown() {
        PrintDateCommand mark = new PrintDateCommand("test");
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();
        TaskList taskList = new TaskList();
        taskList.addTask(new Deadline("",
                LocalDateTime.of(2023, 12, 2, 11, 59)), ui);
        try {
            mark.execute(taskList, ui, storage);
            fail();
        } catch (DukeException e) {
            assert true;
        }
    }

    @Test
    public void execute_noDateFound_exceptionThrown() {
        PrintDateCommand mark = new PrintDateCommand("deadline /on 3/12/2023");
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();
        TaskList taskList = new TaskList();
        taskList.addTask(new Deadline("",
                LocalDateTime.of(2023, 12, 2, 11, 59)), ui);
        try {
            mark.execute(taskList, ui, storage);
            fail();
        } catch (DukeException e) {
            assert true;
        }
    }

    @Test
    public void isExitTest() {
        PrintDateCommand c = new PrintDateCommand("");
        assertFalse(c.isExit());
    }
}
