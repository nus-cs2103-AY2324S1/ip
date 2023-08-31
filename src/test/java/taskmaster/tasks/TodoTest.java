package taskmaster.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void create_unmarked_todo() {
        Todo todo = new Todo ("Read book", "unmarked");
        String result = todo.toString();
        System.out.println(result);
        assertEquals(result, "[T][ ] Read book");
    }

    @Test
    public void create_marked_todo() {
        Todo todo = new Todo ("Read book", "marked");
        String result = todo.toString();
        System.out.println(result);
        assertEquals(result, "[T][X] Read book");
    }
}
