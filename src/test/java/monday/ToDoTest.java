package monday;

import monday.task.ToDo;
import org.junit.jupiter.api.Test;

/**
 * The ToDoTest class is responsible for testing the functionality of the ToDo class.
 */
public class ToDoTest {
    /**
     * Tests the mark method.
     */
    @Test
    public void testMarkAsDone() {
        ToDo todo = new ToDo("return book");
        assert todo.getStatusIcon().equals(" ");
        todo.markAsDone();
        assert todo.getStatusIcon().equals("X");
    }

    /**
     * Tests the unMark method.
     */
    @Test
    public void testUnMark() {
        ToDo todo = new ToDo("return book");
        todo.markAsDone();
        assert todo.getStatusIcon().equals("X");
        todo.unMark();
        assert todo.getStatusIcon().equals(" ");
    }

    /**
     * Tests the toString method.
     */
    @Test
    public void testToString() {
        ToDo todo = new ToDo("return book");
        assert todo.toString().equals("[T][ ] return book");
        todo.markAsDone();
        assert todo.toString().equals("[T][X] return book");
    }
}