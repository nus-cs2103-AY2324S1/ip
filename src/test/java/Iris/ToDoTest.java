package Iris;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void todo_correctInitialisation_toStringCorrect() {
        Todo todo = new Todo("test task");
        assertEquals("[T][ ] test task", todo.toString());
    }
}