package ruiz;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ruiz.task.ToDo;

public class ToDosTest {
    @Test
    public void saveToString_success() {
        ToDo test = new ToDo("eat");
        assertEquals("T | 0 | eat", test.formatSaveTaskString());
        test.mark();
        assertEquals("T | 1 | eat", test.formatSaveTaskString());
    }

    @Test
    public void toString_success() {
        ToDo test = new ToDo("read book");
        assertEquals("[T][ ] read book", test.toString());
        test.mark();
        assertEquals("[T][X] read book", test.toString());
    }

}
