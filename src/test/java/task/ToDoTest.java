package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void toString_undone_test() {
        ToDo testObj = new ToDo("buy book");
        assertEquals("[T][ ] buy book", testObj.toString());
    }

    @Test
    public void fileFormat_undone_test() {
        ToDo testObj = new ToDo("buy book");
        assertEquals("T|buy book|0", testObj.fileFormat());
    }

    @Test
    public void markAsDone_isDone_test() {
        ToDo testObj = new ToDo("buy book");
        testObj.markAsDone();
        assertTrue(testObj.isDone);
    }

}
