package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTest {

    @Test
    public void toDo_toString_success() {
        ToDo todo = new ToDo("borrow book", TaskType.TODO);
        assertEquals("[T][ ] borrow book", todo.toString());
    }

    @Test
    public void toDoSetMarkAgainError() throws DukeException{
        try {
            ToDo todo = new ToDo("borrow book", TaskType.TODO);
            todo.setMarked();
            todo.setMarked();
        } catch (DukeException e) {
            assertEquals("This task has already been marked as done!\n", e.getMessage());
        }
    }

    @Test
    public void toDoSetUnMarkAgainError() throws DukeException{
        try {
            ToDo todo = new ToDo("borrow book", TaskType.TODO);
            todo.setUnmarked();
            todo.setUnmarked();
        } catch (DukeException e) {
            assertEquals("This task has already been marked as not done!\n", e.getMessage());
        }
    }

}
