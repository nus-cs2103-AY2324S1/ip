package duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toStringTest(){
        assertEquals("[T][ ] Test message", (new ToDos("Test message", false)).toString());
    }

    @Test
    public void markAsDone(){
        ToDos todo2 = new ToDos("Test marked message", true);
        todo2.markTask();
        assertEquals("[T][X] Test marked message", todo2.toString());
    }

    @Test
    public void markUndone() {
        ToDos todo3 = new ToDos("Test unmarked message", true);
        todo3.markTask();
        todo3.unmarkTask();
        assertEquals("[T][ ] Test unmarked message", todo3.toString());
    }
}
