package martin.commands;

import martin.exceptions.MartinException;
import martin.task.Task;
import martin.task.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TodoCommandTest {
    private TaskList list;
    private TodoCommand command;

    @BeforeEach
    public void setUp() {
        list = new TaskList();
    }

    @Test
    public void execute_addTask_taskAdded() throws MartinException {
        command = new TodoCommand("todo Sample task", list.getTasks());
        command.execute();
        assertEquals(1, list.getTasks().size());
        assertTrue(list.getTasks().get(0) instanceof Task);
    }
}
