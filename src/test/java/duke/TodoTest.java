package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.ronaldo.TaskType;
import duke.tasks.ToDo;
public class TodoTest {
    @Test
    public void toDo_toString_success() {
        ToDo todo = new ToDo("borrow book", TaskType.TODO);
        assertEquals("[T][ ] borrow book", todo.toString());
    }
    @Test
    public void toDo_setMarkAgain_exception() {
        try {
            ToDo todo = new ToDo("borrow book", TaskType.TODO);
            todo.setMarked();
            todo.setMarked();
        } catch (DukeException e) {
            assertEquals("SUI, This task has already been marked as done!\n", e.getMessage());
        }
    }
    @Test
    public void toDo_setUnMarkAgain_exception() {
        try {
            ToDo todo = new ToDo("borrow book", TaskType.TODO);
            todo.setUnmarked();
            todo.setUnmarked();
        } catch (DukeException e) {
            assertEquals("SUI, This task has already been marked as not done!\n", e.getMessage());
        }
    }
}
