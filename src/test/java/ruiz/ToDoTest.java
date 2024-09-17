package ruiz;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ruiz.task.ToDo;

public class ToDoTest {
    @Test
    public void testMarkedSaveToString_success() {
        ToDo test = new ToDo("eat", "home");
        assertEquals("T | 0 | eat | home", test.formatSaveTaskString());
        test.mark();
        assertEquals("T | 1 | eat | home", test.formatSaveTaskString());
    }

    @Test
    public void testMarkTodo_success() {
        ToDo test = new ToDo("read book", "library");
        assertEquals("[T][ ] read book at: library", test.toString());
        test.mark();
        assertEquals("[T][X] read book at: library", test.toString());
    }

}
