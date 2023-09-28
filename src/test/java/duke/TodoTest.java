package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void anotherDummyTest(){
        assertEquals(4, 4);
    }

    @Test
    public void testToDataString() {
        Todo todo = new Todo("Finish iP soon!");
        String dataString = todo.toDataString();

        assertEquals("TODO | Finish iP soon!", dataString);
    }
}
