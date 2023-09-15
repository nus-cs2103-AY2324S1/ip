package Eddie;
import Eddie.Tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {
    @Test
    public void testToString() {
        Todo t = new Todo(" Test1");
        t.taskIsDone();

        assertEquals(t.toString(), "[T][x] Test1");
    }
}
