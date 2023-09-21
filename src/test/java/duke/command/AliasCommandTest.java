package duke.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.util.Alias;
import duke.util.StorageStub;
import duke.util.TaskList;
import duke.util.UiStub;

/**
 * JUnit test class for AliasCommand.
 */
public class AliasCommandTest {

    @Test
    public void execute_validCommandEmpty_success() {
        Alias.initAlias();
        AliasCommand a = new AliasCommand();
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();
        TaskList taskList = new TaskList();
        try {
            a.execute(taskList, ui, storage);
            assert true;
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void execute_validCommandDelete_success() {
        Alias.initAlias(); // create default alias (include: t -> todo)
        AliasCommand a = new AliasCommand("t");
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();
        TaskList taskList = new TaskList();
        try {
            a.execute(taskList, ui, storage);
            assert true;
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void execute_validCommandAdd_success() {
        Alias.initAlias(); // create default alias
        AliasCommand a = new AliasCommand("a alias");
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();
        TaskList taskList = new TaskList();
        try {
            a.execute(taskList, ui, storage);
            assert true;
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void execute_noAliasFound_exceptionThrown() {
        Alias.initAlias();
        AliasCommand a = new AliasCommand("!@#$%^&*()_+");
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();
        TaskList taskList = new TaskList();
        try {
            a.execute(taskList, ui, storage);
            fail();
        } catch (DukeException e) {
            assert true;
        }
    }

    @Test
    public void execute_aliasChain_exceptionThrown() {
        Alias.initAlias(); // create default alias (include: t -> todo)
        AliasCommand a = new AliasCommand("to t");
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();
        TaskList taskList = new TaskList();
        try {
            a.execute(taskList, ui, storage);
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
