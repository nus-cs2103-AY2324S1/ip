package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void convertTask_uncompleted() {
        ToDo todo = new ToDo(0, "bake cookies!");
        String expected = "T | 0 | bake cookies!";
        assertEquals(expected, todo.convertTask());
    }

    @Test
    public void convertTask_completed() {
        ToDo todo = new ToDo(1, "bake cookies!");
        String expected = "T | 1 | bake cookies!";
        assertEquals(expected, todo.convertTask());
    }

    @Test
    public void toString_uncompleted() {
        ToDo todo = new ToDo(0, "bake cookies!");
        String expected = "[T] [ ] bake cookies!";
        assertEquals(expected, todo.toString());
    }

    @Test
    public void toString_completed() {
        ToDo todo = new ToDo(1, "bake cookies!");
        String expected = "[T] [X] bake cookies!";
        assertEquals(expected, todo.toString());
    }

}
