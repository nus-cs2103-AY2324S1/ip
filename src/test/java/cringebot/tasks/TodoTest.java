package cringebot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void toStringTest1() {
        Todo newTodo = new Todo("read book");
        assertEquals("[T][ ] read book", newTodo.toString());
    }

    @Test
    public void markEvent1() {
        Todo newTodo = new Todo("read book");
        newTodo.markTask();
        assertEquals("[T][X] read book", newTodo.toString());
    }

    @Test
    public void unmarkEvent1() {
        Todo newTodo = new Todo("read book");
        newTodo.markTask();
        assertEquals("[T][X] read book", newTodo.toString());
        newTodo.unMarkTask();
        assertEquals("[T][ ] read book", newTodo.toString());
    }
}
