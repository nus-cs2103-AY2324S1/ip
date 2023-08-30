package task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class ToDoTest {

    @Test
    public void testString() {
        assertEquals("[T][ ] Read book", new ToDo("Read book").toString());
    }

}
