package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testToString() {
        ToDo todo = new ToDo("Buy groceries");
        assertEquals("[T][ ] Buy groceries", todo.toString());
    }

    @Test
    public void testMarkAsDone() {
        ToDo todo = new ToDo("Buy groceries");
        todo.markAsDone();
        assertEquals("[T][X] Buy groceries", todo.toString());
    }

    @Test
    public void testMarkAsNotDone() {
        ToDo todo = new ToDo("Buy groceries");
        todo.markAsDone();
        todo.markAsNotDone();
        assertEquals("[T][ ] Buy groceries", todo.toString());
    }

    @Test
    public void testCreateTaskFromFormattedString() {
        String formattedString = "T | 1 | Buy groceries";
        Task task = Task.createTaskFromFormattedString(formattedString);
        assertEquals("[T][X] Buy groceries", task.toString());
    }
}