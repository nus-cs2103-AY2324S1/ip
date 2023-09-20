package peko;

import org.junit.jupiter.api.Test;
import peko.exceptions.InvalidTaskException;
import peko.tasks.ToDos;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskTest {
    @Test
    public void testMarkAsDone() throws InvalidTaskException {
        ToDos todo = new ToDos("return book");
        assert !todo.getStatus();
        todo.setMark();
        assert todo.getStatus();
    }
    @Test
    public void testMarkAsUnDone() throws InvalidTaskException {
        ToDos todo = new ToDos("return book");
        assert !todo.getStatus();
        todo.setMark();
        assert todo.getStatus();
        todo.setUnmark();
        assert !todo.getStatus();
    }


}
