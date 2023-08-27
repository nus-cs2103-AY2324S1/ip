package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    @Test
    public void testMarkAsDone() {
        Task task = new ToDo("Buy groceries");
        task.markAsDone();
        assertEquals("[T][X] Buy groceries", task.toString());
    }

    @Test
    public void testMarkAsNotDone() {
        Task task = new ToDo("Read a book");
        task.markAsDone();

        task.markAsNotDone();
        assertEquals("[T][ ] Read a book", task.toString());
    }
}
