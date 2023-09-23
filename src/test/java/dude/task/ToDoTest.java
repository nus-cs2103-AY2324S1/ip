package dude.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void createDeadlineTest() {
        String description = "Homework";
        ToDo todo = new ToDo(description);
        String expected = "[T][ ] Homework";
        assertEquals(expected, todo.toString());
    }
}
