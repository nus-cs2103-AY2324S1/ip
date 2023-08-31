package monday;

import monday.task.ToDo;
import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testMarkAsDone() {
        ToDo todo = new ToDo("return book");
        assert todo.getStatusIcon().equals(" ");

        todo.markAsDone();;
        assert todo.getStatusIcon().equals("X");
    }

    @Test
    public void testUnMark() {
        ToDo todo = new ToDo("return book");

        todo.markAsDone();;
        assert todo.getStatusIcon().equals("X");
        todo.unMark();
        assert todo.getStatusIcon().equals(" ");
    }

    @Test
    public void testToString() {
        ToDo todo = new ToDo("return book");
        assert todo.toString().equals("[T][ ] return book");
        todo.markAsDone();
        assert todo.toString().equals("[T][X] return book");
    }
}
