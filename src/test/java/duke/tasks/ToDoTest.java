package duke.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testConvertTask1() {
        ToDo todo = new ToDo(0, "bake cookies!");
        String expected = "T | 0 | bake cookies!";
        assertEquals(expected, todo.convertTask());
    }

    @Test
    public void testConvertTask2() {
        ToDo todo = new ToDo(1, "bake cookies!");
        String expected = "T | 1 | bake cookies!";
        assertEquals(expected, todo.convertTask());
    }

    @Test
    public void testToString1() {
        ToDo todo = new ToDo(0, "bake cookies!");
        String expected = "[T] [ ] bake cookies!";
        assertEquals(expected, todo.toString());
    }

    @Test
    public void testToString2() {
        ToDo todo = new ToDo(1, "bake cookies!");
        String expected = "[T] [X] bake cookies!";
        assertEquals(expected, todo.toString());
    }

}
