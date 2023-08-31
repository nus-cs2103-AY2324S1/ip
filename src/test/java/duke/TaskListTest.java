package duke;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Todo;

public class TaskListTest {

    @Test
    public void deleteTask_validIndex_success() {
        TaskList taskList = new TaskList();
        UiStub ui = new UiStub();
        try {
            taskList.addTask(new Todo("test"), ui);
            taskList.deleteTask(0, ui);
            assert true;
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void deleteTask_invalidIndex_exceptionThrown() {
        TaskList taskList = new TaskList();
        UiStub ui = new UiStub();
        try {
            taskList.addTask(new Todo("test"), ui);
            taskList.deleteTask(-1, ui);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! There is no task 0 to delete", e.getMessage());
        }
    }

    @Test
    public void deleteTask_emptyList_exceptionThrown() {
        TaskList taskList = new TaskList();
        UiStub ui = new UiStub();
        try {
            taskList.deleteTask(1, ui);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! There is nothing in the list, yet!", e.getMessage());
        }
    }

    @Test
    public void listTask_emptyList_exceptionThrown() {
        TaskList taskList = new TaskList();
        UiStub ui = new UiStub();
        try {
            taskList.listTask(ui);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! There is nothing in the list, yet!", e.getMessage());
        }
    }

    @Test
    public void listTask_nonEmptyList_success() {
        TaskList taskList = new TaskList();
        UiStub ui = new UiStub();
        taskList.addTask(new Todo("test"), ui);
        try {
            taskList.listTask(ui);
            assert true;
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void markTask_validIndex_success() {
        TaskList taskList = new TaskList();
        UiStub ui = new UiStub();
        try {
            taskList.addTask(new Todo("test"), ui);
            taskList.markTask(0, Keyword.MARK, ui);
            assert true;
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void markTask_invalidIndex_exceptionThrown() {
        TaskList taskList = new TaskList();
        UiStub ui = new UiStub();
        try {
            taskList.addTask(new Todo("test"), ui);
            taskList.markTask(-1, Keyword.UNMARK, ui);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! There is no task 0 to unmark", e.getMessage());
        }
    }

    @Test
    public void markTask_emptyList_exceptionThrown() {
        TaskList taskList = new TaskList();
        UiStub ui = new UiStub();
        try {
            taskList.markTask(0, Keyword.MARK, ui);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! There is nothing in the list, yet!", e.getMessage());
        }
    }

    @Test
    public void manipulateAllTask_validIndex_success() {
        TaskList taskList = new TaskList();
        UiStub ui = new UiStub();
        try {
            taskList.addTask(new Todo("test"), ui);
            taskList.manipulateAllTask(Keyword.DELETE, ui);
            assert true;
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void manipulateAllTask_invalidCommand_exceptionThrown() {
        TaskList taskList = new TaskList();
        UiStub ui = new UiStub();
        try {
            taskList.addTask(new Todo("test"), ui);
            taskList.manipulateAllTask(Keyword.BYE, ui);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! This is not a valid command.", e.getMessage());
        }
    }

    @Test
    public void manipulateAllTask_emptyList_exceptionThrown() {
        TaskList taskList = new TaskList();
        UiStub ui = new UiStub();
        try {
            taskList.manipulateAllTask(Keyword.DELETE, ui);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! There are no tasks to delete.", e.getMessage());
        }
    }

    @Test
    public void printDateTask_emptyList_exceptionThrown() {
        TaskList taskList = new TaskList();
        UiStub ui = new UiStub();
        try {
            taskList.printDateTask(Keyword.DEADLINE, Time.parseDate("1/1/2023"), ui);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! There is nothing in the list, yet!", e.getMessage());
        }
    }

    @Test
    public void printDateTask_noTaskToPrint_exceptionThrown() {
        TaskList taskList = new TaskList();
        UiStub ui = new UiStub();
        try {
            taskList.addTask(new Deadline("test", Time.parseDateTime("1/1/2023 10:00")), ui);
            taskList.printDateTask(Keyword.DEADLINE, Time.parseDate("2/1/2023"), ui);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! There is nothing happening on 2 Jan 2023.", e.getMessage());
        }
    }
}
