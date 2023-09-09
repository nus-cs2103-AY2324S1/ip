package thea;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void toMemoryFormatTest() {
        ToDo todo = new ToDo("Buy Textbook");
        assertEquals("T | 0 | Buy Textbook",
                todo.toMemoryFormat());
    }

    @Test
    public void toStringTest() {
        ToDo todo = new ToDo("Buy Textbook");
        assertEquals("[T][ ] Buy Textbook",
                todo.toString());
    }

    @Test
    public void markAsDoneTest() {
        ToDo todo = new ToDo("Buy Textbook");
        todo.markAsDone();
        assertEquals("[T][X] Buy Textbook",
                todo.toString());
    }

    @Test
    public void unmarkAsDoneTest() {
        ToDo todo = new ToDo("Buy Textbook");
        todo.markAsDone();
        todo.unmarkAsDone();
        assertEquals("[T][ ] Buy Textbook",
                todo.toString());
    }
}
