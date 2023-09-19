package Duke.Tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ToDoTaskTest {
    @Test
    public void testMarkTask() {
        ToDoTask t = new ToDoTask("homework");
        assertEquals(t.isDone(), false);

        t.markDone();
        assertEquals(t.isDone(),true);
    }
}
