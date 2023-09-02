package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void testToString() {
        Todo todo1 = new Todo("test description");
        String tString1 = todo1.toString();
        assertEquals("[T][ ] test description", tString1);

        Todo todo2 = new Todo("description with numbers 12 3 4");
        String tString2 = todo2.toString();
        assertEquals("[T][ ] description with numbers 12 3 4", tString2);
    }
}
