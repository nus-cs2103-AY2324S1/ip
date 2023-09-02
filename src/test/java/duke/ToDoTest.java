package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void toDoTest1() {
        ToDo test = new ToDo("Read 1, 2, 3");
        test.markAsDone();
        assertEquals(test.isDone, true);
        test.markAsUndone();
        assertEquals(test.isDone, false);
    }
}
