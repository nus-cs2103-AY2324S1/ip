package tong.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void getEncodedString() {
        ToDo todo = new ToDo("test_todo");
        assertEquals("T | 0 | test_todo", todo.getEncodedString());
    }
}
