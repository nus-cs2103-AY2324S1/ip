package shiba.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import shiba.exceptions.EmptyTasksException;
import shiba.exceptions.InvalidCommandException;
import shiba.exceptions.ShibaException;
import shiba.tasks.PersistentTaskList;
import shiba.tasks.TaskListStub;

public class DeleteCommandTest {
    private final PersistentTaskList tasks = new TaskListStub();

    private void addInitialTasks() throws ShibaException {
        new TodoCommand(tasks, "todo Pet a Shiba").execute();
        new DeadlineCommand(tasks, "deadline Feed a Shiba /by 2023-09-22 20:00").execute();
        new EventCommand(tasks, "event Shiba petting fair /from 2023-09-24 10:00 /to 2023-09-24 15:00").execute();
        assertEquals(3, tasks.size());
    }

    @Test
    public void execute_validCommand() throws ShibaException {
        addInitialTasks();
        new DeleteCommand(tasks, "delete 1").execute();
        assertEquals(2, tasks.size());
        assertEquals("[D][ ] Feed a Shiba (by: 22 Sep 2023 8:00PM)", tasks.get(0).toString());
        assertEquals("[E][ ] Shiba petting fair (from: 24 Sep 2023 10:00AM to: 24 Sep 2023 3:00PM)",
                tasks.get(1).toString());
    }

    @Test
    public void execute_validCommand_two() throws ShibaException {
        addInitialTasks();
        new DeleteCommand(tasks, "delete 3").execute();
        assertEquals(2, tasks.size());
        assertEquals("[T][ ] Pet a Shiba", tasks.get(0).toString());
        assertEquals("[D][ ] Feed a Shiba (by: 22 Sep 2023 8:00PM)", tasks.get(1).toString());
    }

    @Test
    public void execute_invalidCommand_indexTooLow() throws ShibaException {
        addInitialTasks();
        assertThrows(InvalidCommandException.class, () -> new DeleteCommand(tasks, "delete 0").execute());
    }

    @Test
    public void execute_invalidCommand_indexTooHigh() throws ShibaException {
        addInitialTasks();
        assertThrows(InvalidCommandException.class, () -> new DeleteCommand(tasks, "delete 4").execute());
    }

    @Test
    public void execute_invalidCommand_indexTooHighNoTasks() {
        assertThrows(EmptyTasksException.class, () -> new DeleteCommand(tasks, "delete 1").execute());
    }
}
