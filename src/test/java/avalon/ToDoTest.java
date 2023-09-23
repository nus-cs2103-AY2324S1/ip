package avalon;

import avalon.task.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void toString_validDescription_success() {
        ToDo todo = new ToDo("Buy food");

        assertEquals("[T][0][ ]  Buy food", todo.toString());

        todo.markDone();

        assertEquals("[T][0][X]  Buy food", todo.toString());
    }
}
