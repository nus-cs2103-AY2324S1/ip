package bee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void todoTest() {
        Todo todo = new Todo("Test");
        assertEquals("[T][ ] Test", todo.toString());
    }

    @Test
    public void todoTest2() {
        Todo todo = new Todo("Test", true);
        assertEquals("[T][X] Test", todo.toString());
    }

    @Test
    public void todoTest3() {
        Todo todo = new Todo("Test", false);
        assertEquals("[T][ ] Test", todo.toString());
    }
}
