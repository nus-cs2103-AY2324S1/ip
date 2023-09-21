package commands;

import duke.DukeException;
import data.Actions;
import org.junit.jupiter.api.Test;
import tasks.Todo;
import ui.UI;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoCommandTest {
    @Test
    public void testExecuteCommand_emptyDescription_throwsDukeException() {
        TodoCommand todo = new TodoCommand("");
        UI ui = new UI();
        Actions actions = new Actions();
        assertThrows(DukeException.class, () -> todo.executeCommand(ui, actions));
    }

    @Test
    public void testExecuteCommand_validDescription_taskAdded() {
        String description = "Todo Test Task";
        TodoCommand todo = new TodoCommand(description);
        UI ui = new UI();
        Actions actions = new Actions();
        try {
            todo.executeCommand(ui, actions);
        } catch (DukeException thrown) {
            throw new AssertionError("DukeException was thrown with a valid description.", thrown);
        }
    }
}