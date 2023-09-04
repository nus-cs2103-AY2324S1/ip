package duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void todo_testToString(){
        assertEquals("[T][ ] todo", new Todo("todo").toString());
    }

    @Test
    public void todo_testGetDescription(){
        assertEquals("T | | todo", new Todo("todo").getDescription());
    }
}
