package avalon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toString_validDescription_success() {
        ToDo todo = new ToDo("Buy food");

        assertEquals("[T][ ]  Buy food", todo.toString());

        todo.markDone();

        assertEquals("[T][X]  Buy food", todo.toString());
    }
}
