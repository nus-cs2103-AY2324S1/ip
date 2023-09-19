package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void toString_correctFormat() {
        Todo todo = new Todo("Buy groceries");
        String expectedOutput = "[T][ ] Buy groceries";
        assertEquals(expectedOutput, todo.toString());
    }

    @Test
    public void toFileString_correctFormat() {
        Todo todo = new Todo("Buy groceries");
        String expectedOutput = "T | 0 | Buy groceries";
        assertEquals(expectedOutput, todo.toFileString());
    }
}
