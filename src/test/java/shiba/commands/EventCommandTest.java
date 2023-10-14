package shiba.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import shiba.exceptions.InvalidCommandException;
import shiba.exceptions.ShibaException;
import shiba.tasks.PersistentTaskList;
import shiba.tasks.TaskListStub;

public class EventCommandTest {
    private final PersistentTaskList tasks = new TaskListStub();

    @Test
    public void execute_validCommand() throws ShibaException {
        new EventCommand(tasks, "event Shiba petting fair /from 2023-09-24 10:00 /to 2023-09-24 15:00").execute();
        assertEquals(1, tasks.size());
        assertEquals("[E][ ] Shiba petting fair (from: 24 Sep 2023 10:00AM to: 24 Sep 2023 3:00PM)",
                tasks.get(0).toString());
    }

    @Test
    public void execute_validCommand_spaces() throws ShibaException {
        new EventCommand(tasks,
                "event               Shiba petting fair    /from 2023-09-24     /to 2023-09-25").execute();
        assertEquals(1, tasks.size());
        assertEquals("[E][ ] Shiba petting fair (from: 24 Sep 2023 to: 25 Sep 2023)", tasks.get(0).toString());
    }

    @Test
    public void execute_validCommand_datesSwapped() throws ShibaException {
        new EventCommand(tasks, "event Shiba petting fair /to 2023-09-24 15:00 /from 2023-09-24 10:00").execute();
        assertEquals(1, tasks.size());
        assertEquals("[E][ ] Shiba petting fair (from: 24 Sep 2023 10:00AM to: 24 Sep 2023 3:00PM)",
                tasks.get(0).toString());
    }

    @Test
    public void execute_invalidCommand_noName() {
        assertThrows(InvalidCommandException.class, () ->
                new EventCommand(tasks, "event /from 2023-09-24 10:00 /to /2023-09-24 15:00").execute());
    }

    @Test
    public void execute_invalidCommand_noFrom() {
        assertThrows(InvalidCommandException.class, () ->
                new EventCommand(tasks, "event Shiba petting fair /to 2023-09-24 15:00").execute());
    }

    @Test
    public void execute_invalidCommand_noTo() {
        assertThrows(InvalidCommandException.class, () ->
                new EventCommand(tasks, "event Shiba petting fair /to 2023-09-24 15:00").execute());
    }

    @Test
    public void execute_invalidCommand_wrongCommand() {
        assertThrows(InvalidCommandException.class, () ->
                new EventCommand(tasks, "deadline Pet a Shiba /by 2023-09-22 20:00").execute());
    }
}
