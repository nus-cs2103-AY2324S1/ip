package ruiz;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ruiz.task.ToDos;

public class ToDosTest {
    @Test
    public void testMarkedSaveToString_success() {
        ToDos test = new ToDos("eat");
        assertEquals("T | 0 | eat", test.saveTaskString());
        test.mark();
        assertEquals("T | 1 | eat", test.saveTaskString());
    }

    @Test
    public void testMarkTodo_success() {
        ToDos test = new ToDos("read book");
        assertEquals("[T][ ] read book", test.toString());
        test.mark();
        assertEquals("[T][X] read book", test.toString());
    }

}
