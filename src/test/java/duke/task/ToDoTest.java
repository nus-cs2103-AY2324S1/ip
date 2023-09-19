package duke.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ToDoTest {
    @Test
    public void testStringConversion() {
        Assertions.assertEquals("[T][ ] Test TODO",
                new ToDo("Test TODO").toString());
    }

    @Test
    public void testFileStringConversion() {
        Assertions.assertEquals("T | 0 | NEW TODO",
                new ToDo("NEW TODO").toStringFile());
    }


}
