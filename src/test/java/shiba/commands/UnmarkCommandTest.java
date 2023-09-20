package shiba.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import shiba.exceptions.EmptyTasksException;
import shiba.exceptions.InvalidCommandException;
import shiba.exceptions.ShibaException;
import shiba.tasks.PersistentTaskList;
import shiba.tasks.TaskListStub;

public class UnmarkCommandTest {
    private final PersistentTaskList tasks = new TaskListStub();

    private void addInitialTasksAndMark() throws ShibaException {
        new TodoCommand(tasks, "todo Pet a Shiba").execute();
        new DeadlineCommand(tasks, "deadline Feed a Shiba /by 2023-09-22 20:00").execute();
        new EventCommand(tasks, "event Shiba petting fair /from 2023-09-24 10:00 /to 2023-09-24 15:00").execute();
        assertEquals(3, tasks.size());
        tasks.get(0).markDone();
        tasks.get(1).markDone();
        tasks.get(2).markDone();
    }

    @Test
    public void execute_validCommand() throws ShibaException {
        addInitialTasksAndMark();
        new UnmarkCommand(tasks, "unmark 1").execute();
        assertEquals("[T][ ] Pet a Shiba", tasks.get(0).toString());
        assertEquals("[D][X] Feed a Shiba (by: 22 Sep 2023 8:00PM)", tasks.get(1).toString());
        assertEquals("[E][X] Shiba petting fair (from: 24 Sep 2023 10:00AM to: 24 Sep 2023 3:00PM)",
                tasks.get(2).toString());
    }

    @Test
    public void execute_validCommand_two() throws ShibaException {
        addInitialTasksAndMark();
        new UnmarkCommand(tasks, "unmark 3").execute();
        assertEquals("[T][X] Pet a Shiba", tasks.get(0).toString());
        assertEquals("[D][X] Feed a Shiba (by: 22 Sep 2023 8:00PM)", tasks.get(1).toString());
        assertEquals("[E][ ] Shiba petting fair (from: 24 Sep 2023 10:00AM to: 24 Sep 2023 3:00PM)",
                tasks.get(2).toString());
    }

    @Test
    public void execute_validCommand_alreadyUnmarked() throws ShibaException {
        addInitialTasksAndMark();
        new UnmarkCommand(tasks, "unmark 1").execute();
        new UnmarkCommand(tasks, "unmark 1").execute();
        assertEquals("[T][ ] Pet a Shiba", tasks.get(0).toString());
        assertEquals("[D][X] Feed a Shiba (by: 22 Sep 2023 8:00PM)", tasks.get(1).toString());
        assertEquals("[E][X] Shiba petting fair (from: 24 Sep 2023 10:00AM to: 24 Sep 2023 3:00PM)",
                tasks.get(2).toString());
    }

    @Test
    public void execute_validCommand_alreadyUnmarked2() throws ShibaException {
        addInitialTasksAndMark();
        new UnmarkCommand(tasks, "unmark 3").execute();
        new UnmarkCommand(tasks, "unmark 3").execute();
        assertEquals("[T][X] Pet a Shiba", tasks.get(0).toString());
        assertEquals("[D][X] Feed a Shiba (by: 22 Sep 2023 8:00PM)", tasks.get(1).toString());
        assertEquals("[E][ ] Shiba petting fair (from: 24 Sep 2023 10:00AM to: 24 Sep 2023 3:00PM)",
                tasks.get(2).toString());
    }

    @Test
    public void execute_invalidCommand_indexTooLow() throws ShibaException {
        addInitialTasksAndMark();
        assertThrows(InvalidCommandException.class, () -> new UnmarkCommand(tasks, "unmark 0").execute());
    }

    @Test
    public void execute_invalidCommand_indexTooHigh() throws ShibaException {
        addInitialTasksAndMark();
        assertThrows(InvalidCommandException.class, () -> new UnmarkCommand(tasks, "unmark 4").execute());
    }

    @Test
    public void execute_invalidCommand_indexTooHighNoTasks() {
        assertThrows(EmptyTasksException.class, () -> new UnmarkCommand(tasks, "unmark 1").execute());
    }
}
