package bongo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import bongo.helper.BongoException;
import bongo.helper.Parser;

public class TodoTest {
    @Test
    public void testTodoString() {
        Todo sampleTodo = new Todo("make coffee");
        String expectedOutput = "[T][ ] make coffee";
        assertEquals(expectedOutput, sampleTodo.toString());
    }

    @Test
    public void testEmptyTodoDescription() {
        Exception exception = assertThrows(BongoException.class, () -> {
            Parser.parse("todo");
        });
        String expectedMessage = "Please include the description of your todo.";
        assertEquals(expectedMessage, exception.getMessage());
    }
}
