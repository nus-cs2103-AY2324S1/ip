package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    // test correct string 
    @Test
    public void toString_arbitraryValue_correctOutput() {
        assertEquals("[T][ ] test", new ToDo ("test").toString());
    }

    @Test
    public void saveString_arbitraryValue_correctOutput() {
        assertEquals("T | 0 | test", new ToDo ("test").saveString());
    }
}
