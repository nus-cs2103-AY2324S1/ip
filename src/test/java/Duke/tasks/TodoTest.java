package Duke.tasks;
import static org.junit.jupiter.api.Assertions.*;

import core.DukeException;
import org.junit.jupiter.api.Test;

public class TodoTest {

    /**
     * Tests the creation of a Todos.
     * Ensures that new event has correct description and is unmarked.
     *
     * @throws DukeException if there is an error during creation.
     */
    @Test
    public void createToDo_WithValidInputs_ReturnsCorrectMarkStatus() throws DukeException {
        ToDos todo = new ToDos("Sample Task");

        assertEquals("Sample Task", todo.getDescription());
        assertFalse(todo.isCompleted(), "New todo should not be marked as done");
    }

    /**
     * Tests the creation of a Todos.
     * Ensures that exception is thrown if empty description in creation of Todos.
     */
    @Test
    public void createToDo_WithEmptyDescription_ThrowsException() {
        assertThrows(DukeException.class, () -> {
            new ToDos("");
        });
    }
}
