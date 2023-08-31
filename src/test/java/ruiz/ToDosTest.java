package ruiz;

import org.junit.jupiter.api.Test;
import ruiz.task.ToDos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDosTest {
    @Test
    public void saveToString_success() {
        ToDos test = new ToDos("eat");
        assertEquals("T | 0 | eat", test.saveTaskString());
        test.mark();
        assertEquals("T | 1 | eat", test.saveTaskString());
    }

    @Test
    public void toString_success() {
        ToDos test = new ToDos("read book");
        assertEquals("[T][ ] read book", test.toString());
        test.mark();
        assertEquals("[T][X] read book", test.toString());
    }

}
