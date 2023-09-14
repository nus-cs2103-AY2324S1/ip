package ren.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void getDescription() {
        assertEquals("read book", new ToDo("read book", false).getDescription());
    }
}
