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
import duke.task.Event;
import duke.task.Todo;


public class FindCommandTest {

    @Test
    public void execute_validCommand_success() {
        FindCommand find = new FindCommand("Book");
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("test"), ui);
        taskList.addTask(new Deadline("return books",
                LocalDateTime.of(2023, 12, 2, 11, 59)), ui);
        try {
            find.execute(taskList, ui, storage);
            assert true;
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void execute_emptyList_exceptionThrown() {
        FindCommand find = new FindCommand("Book");
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();
        TaskList taskList = new TaskList();
        try {
            find.execute(taskList, ui, storage);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! There is nothing in the list, yet!", e.getMessage());
        }
    }

    @Test
    public void execute_noDateFound_exceptionThrown() {
        FindCommand find = new FindCommand("Book");
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();
        TaskList taskList = new TaskList();
        taskList.addTask(new Event("test",
                LocalDateTime.of(2023, 12, 2, 11, 59),
                LocalDateTime.of(2023, 12, 5, 11, 59)),
                ui);
        try {
            find.execute(taskList, ui, storage);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! There is no task with Book.", e.getMessage());
        }
    }

    @Test
    public void isExitTest() {
        FindCommand find = new FindCommand("");
        assertFalse(find.isExit());
    }
}
