package noelPackage.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {

    @Test
    public void testStringFormat() {
        ToDos toDoTest1 = new ToDos("read book");
        String expectedOutput = "[T][ ] read book";
        assertEquals(expectedOutput, toDoTest1.toString());
    }

    @Test
    public void testFileFormat() {
        ToDos toDoTest2 = new ToDos("read book");
        String expectedOutput = "T | 0 | read book";
        assertEquals(expectedOutput, toDoTest2.toFileString());
    }
}
