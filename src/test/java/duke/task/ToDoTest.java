package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ToDoTest {
    @Test
    public void ToDoTest() {
        ToDo todo = new ToDo("read book");
        assertEquals("[T] [ ] read book", todo.toString());

        todo.markDone();
        assertEquals("[T] [X] read book", todo.toString());

        todo.markNotDone();
        assertEquals("[T] [ ] read book", todo.toString());
    }
}
