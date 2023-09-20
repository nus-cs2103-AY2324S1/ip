package shiba.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import shiba.exceptions.InvalidCommandException;
import shiba.exceptions.ShibaException;
import shiba.tasks.PersistentTaskList;
import shiba.tasks.TaskListStub;

public class DeadlineCommandTest {
    private final PersistentTaskList tasks = new TaskListStub();

    @Test
    public void execute_validCommand() throws ShibaException {
        new DeadlineCommand(tasks, "deadline Feed a Shiba /by 2023-09-22 20:00").execute();
        assertEquals(1, tasks.size());
        assertEquals("[D][ ] Feed a Shiba (by: 22 Sep 2023 8:00PM)", tasks.get(0).toString());
    }

    @Test
    public void execute_validCommand_spaces() throws ShibaException {
        new DeadlineCommand(tasks, "deadline               Feed a Shiba    /by 2023-09-22").execute();
        assertEquals(1, tasks.size());
        assertEquals("[D][ ] Feed a Shiba (by: 22 Sep 2023)", tasks.get(0).toString());
    }

    @Test
    public void execute_invalidCommand_noName() {
        assertThrows(InvalidCommandException.class, () ->
                new DeadlineCommand(tasks, "deadline /by 2023-09-22 20:00").execute());
    }

    @Test
    public void execute_invalidCommand_noBy() {
        assertThrows(InvalidCommandException.class, () ->
                new DeadlineCommand(tasks, "deadline Feed a Shiba").execute());
    }

    @Test
    public void execute_invalidCommand_wrongCommand() {
        assertThrows(InvalidCommandException.class, () ->
                new DeadlineCommand(tasks, "todo Feed a Shiba").execute());
    }
}
