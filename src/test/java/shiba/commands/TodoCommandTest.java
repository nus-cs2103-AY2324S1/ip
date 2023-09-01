package shiba.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import shiba.exceptions.InvalidCommandException;
import shiba.exceptions.ShibaException;
import shiba.tasks.PersistentTaskList;
import shiba.tasks.TaskListStub;

public class TodoCommandTest {
    private final PersistentTaskList tasks = new TaskListStub();

    @Test
    public void execute_validCommand() throws ShibaException {
        new TodoCommand(tasks, "todo Pet a Shiba").execute();
        assertEquals(1, tasks.size());
        assertEquals("[T][ ] Pet a Shiba", tasks.get(0).toString());
    }

    @Test
    public void execute_validCommand_spaces() throws ShibaException {
        new TodoCommand(tasks, "todo               Pet a Shiba    ").execute();
        assertEquals(1, tasks.size());
        assertEquals("[T][ ]               Pet a Shiba    ", tasks.get(0).toString());
    }

    @Test
    public void execute_invalidCommand_noName() {
        assertThrows(InvalidCommandException.class, () -> new TodoCommand(tasks, "todo").execute());
    }

    @Test
    public void execute_invalidCommand_wrongCommand() {
        assertThrows(InvalidCommandException.class, () -> new TodoCommand(tasks, "mark").execute());
    }
}
