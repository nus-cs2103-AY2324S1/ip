package chatbot;

import chatbot.tasks.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoTest {
    @Test
    void testToString() {
        Todo todo = new Todo("todo run");
        String expected = "[T][ ] todo run";
        String actual = todo.toString();
        assertEquals(expected, actual);
    }
}

