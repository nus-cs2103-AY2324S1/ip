package task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ToDoTest {
    public ToDoTest() {
    }

    @Test
    public void toStringTest() {
        ToDo test = new ToDo("go gym");
        Assertions.assertEquals("[T] [ ] go gym", test.toString());
    }

    @Test
    public void toFileString() {
        ToDo test = new ToDo("go gym");
        Assertions.assertEquals("T | 0 | go gym", test.toFileString());
    }
}