package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.components.Status;

public class ToDoTest {
    @Test
    public void convertTask_uncompleted() {
        ToDo todo = new ToDo(Status.NOT_DONE, "bake cookies!");
        String expected = "T | 0 | bake cookies!";
        assertEquals(expected, todo.convertTask());
    }

    @Test
    public void convertTask_completed() {
        ToDo todo = new ToDo(Status.DONE, "bake cookies!");
        String expected = "T | 1 | bake cookies!";
        assertEquals(expected, todo.convertTask());
    }

    @Test
    public void toString_uncompleted() {
        ToDo todo = new ToDo(Status.NOT_DONE, "bake cookies!");
        String expected = "[T] [ ] bake cookies!";
        assertEquals(expected, todo.toString());
    }

    @Test
    public void toString_completed() {
        ToDo todo = new ToDo(Status.DONE, "bake cookies!");
        String expected = "[T] [X] bake cookies!";
        assertEquals(expected, todo.toString());
    }

}
