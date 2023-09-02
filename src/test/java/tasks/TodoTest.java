package tasks;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTest {

    @Test
    public void toString_success() {
        Todo todo = new Todo("eat food");
        assertEquals("[T][ ] eat food", todo.toString());
    }

    @Test
    public void markedToString_success() {
        Todo todo = new Todo("eat food");
        todo.markDone();
        assertEquals("[T][X] eat food", todo.toString());
    }

    @Test
    public void markedUnmarkedToString_success() {
        Todo todo = new Todo("eat food");
        todo.markDone();
        todo.markNotDone();
        assertEquals("[T][ ] eat food", todo.toString());
    }

}
