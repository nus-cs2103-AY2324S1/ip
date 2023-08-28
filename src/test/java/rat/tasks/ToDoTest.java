package rat.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void testToDo_create() {
        ToDo tdTest = new ToDo("test");
        assertEquals("[T][ ] test", tdTest.toString());
    }

    @Test
    public void testToDo_formatForFile() {
        ToDo tdTest = new ToDo("test");
        assertEquals("T, 0, test", tdTest.formatForFile());
    }

    @Test
    public void testToDo_markDone() {
        ToDo tdTest = new ToDo("test");
        tdTest.markDone();
        assertEquals("[T][X] test", tdTest.toString());
    }

    @Test
    public void testToDo_unmarkDone() {
        ToDo tdTest = new ToDo("test");
        tdTest.markDone();
        tdTest.unmarkDone();
        assertEquals("[T][ ] test", tdTest.toString());
    }
}
