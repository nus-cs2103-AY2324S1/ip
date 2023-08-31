package Duke.tasks;
import static org.junit.jupiter.api.Assertions.*;

import core.DukeException;
import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testTodoCreation() throws DukeException {
        ToDos todo = new ToDos("Sample Task");

        assertEquals("Sample Task", todo.getDescription());
        assertFalse(todo.isCompleted(), "New todo should not be marked as done");
    }

    @Test
    public void testToDoCreationWithInvalidInput() {
        assertThrows(DukeException.class, () -> {
            new ToDos("");
        });
    }
}
