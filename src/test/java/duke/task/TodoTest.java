package duke.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTest extends TaskTest {

    @Test
    public void testToString() {
        Task todo = new Todo("Sleeping");
        assertEquals("[T][ ] Sleeping", todo.toString());
    }

    @Test
    public void testGetStatusIcon() {
        Task todo = new Todo("Read a book");
        todo.mark();
        assertEquals("X", todo.getStatusIcon());
    }
}
