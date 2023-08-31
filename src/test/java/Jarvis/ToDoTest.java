package Jarvis;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void todo_correctInitialisation_toStringCorrect() {
        ToDo todo = new ToDo("Mark XXVI is ready for you sir");
        assertEquals("[T][ ] Mark XXVI is ready for you sir", todo.toString());
    }
}
