package ruiz;

import org.junit.jupiter.api.Test;
import ruiz.task.ToDos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDosTest {
    @Test
    public void saveToString_success() {
        assertEquals("T | 0 | eat", new ToDos("eat").saveTaskString());
    }

    @Test
    public void saveToString_success_marked() {
        ToDos test = new ToDos("eat");
        test.mark();
        assertEquals("T | 1 | eat", test.saveTaskString());
    }

    @Test
    public void toString_success() {
        assertEquals("[T][ ] read book", new ToDos("read book").toString());
    }

    @Test
    public void toString_success_marked() {
        ToDos test = new ToDos("read book");
        test.mark();
        assertEquals("[T][X] read book", test.toString());
    }

}
