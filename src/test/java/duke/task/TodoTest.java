package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TodoTest {

    @Test
    public void toStringTest() {
        //Correct String representation of the Todo.
        Todo todo = new Todo("go for run");
        assertEquals("[T][ ] go for run", todo.toString());
    }

    @Test
    public void markDoneTest() {
        //Correct String representation of the Todo after marked as done.
        Todo todo = new Todo("go for walk");
        todo.markAsDone();
        assertEquals("[T][X] go for walk", todo.toString());
    }
}
