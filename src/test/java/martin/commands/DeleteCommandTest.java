package martin.commands;

import martin.exceptions.InvalidTaskNumberException;
import martin.exceptions.MartinException;
import martin.task.Task;
import martin.task.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteCommandTest {
    private TaskList list;
    private DeleteCommand command;

    @BeforeEach
    public void setUp() {
        list = new TaskList();
        list.getTasks().add(new Task("Sample Task 1"));
    }

    @Test
    public void execute_deleteTask_taskRemoved() throws MartinException {
        command = new DeleteCommand("delete 1", list.getTasks());
        command.execute();
        assertEquals(0, list.getTasks().size());
    }

    @Test
    public void execute_invalidIndex_exceptionThrown() {
        command = new DeleteCommand("delete 3", list.getTasks());
        assertThrows(InvalidTaskNumberException.class, () -> {
            command.execute();
        });
    }
}
