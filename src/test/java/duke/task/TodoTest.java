package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TodoTest {

    @Test
    public void toStringTest() {
        Todo todo = new Todo("go for run");
        assertEquals("[T][ ] go for run", todo.toString());
    }

    @Test
    public void markDoneTest() {
        Todo todo = new Todo("go for walk");
        todo.markAsDone();
        assertEquals("[T][X] go for walk", todo.toString());
    }
}
