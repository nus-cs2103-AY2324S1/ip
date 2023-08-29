package com.mimi.main;

import com.mimi.tasks.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTest {
    @Test
    public void eventCodeTest() {
        Todo todo = new Todo("taskName");

        assertEquals( "T", todo.eventCode());
    }

    @Test
    public void toStringTaskIncompleteTest() {
        Todo todo = new Todo("test");

        assertEquals("[T][] test", todo.toString());
    }

    @Test
    public void toStringTaskCompleteTest() {
        Todo todo = new Todo("test");
        todo.toggleDone();

        assertEquals("[T][X] test", todo.toString());
    }

}