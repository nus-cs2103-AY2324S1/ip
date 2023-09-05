package test.java;

import main.java.Todo;
import main.java.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTest {
    @Test
    public void todo_markTest_success() {
        Task t = new Todo("test 1");
        t.markDone();
        assertEquals("X", t.getStatusIcon());

    }

    @Test
    public void todo_unmarkTest_success() {
        Task t = new Todo("test 2", true);
        t.markUndone();
        assertEquals(" ", t.getStatusIcon());
    }

    @Test
    public void todo_printString_correctFormat() {
        Task t = new Todo("test 3");
        assertEquals("[T][ ] test 3", t.toString());
    }
}
