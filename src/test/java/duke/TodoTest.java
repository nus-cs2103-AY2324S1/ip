package duke;

import duke.task.Todo;
import org.junit.jupiter.api.Test;


public class TodoTest {
    @Test
    public void testToString() {
        Todo task1 = new Todo("eat potato");
        assert task1.toString().equals("[T][ ] eat potato");
    }

    @Test
    public void testMarkAsDone() {
        Todo task1 = new Todo("eat potato");
        task1.markAsDone();
        assert task1.toString().equals("[T][X] eat potato");
    }

    @Test
    public void testMarkAsNotDone() {
        Todo task1 = new Todo("eat potato");
        task1.markAsDone();
        assert task1.toString().equals("[T][X] eat potato");
        task1.markAsNotDone();
        assert task1.toString().equals("[T][ ] eat potato");
    }
}
