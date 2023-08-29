package pooh;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todo_correctInitialisation_toStringCorrect() {
        Todo todo = new Todo("buy more honey");
        assertEquals("[T][ ] buy more honey", todo.toString());
    }
}
