package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    void testadd() {
        TaskList list = new TaskList();
        list.add(new Todo("a"));
        list.add(new Todo("b"));
        list.add(new Todo("c"));
        list.add(new Todo("d"));
        assertEquals(4, list.getSize(), "all elements added");
    }

    @Test
    void testget() {
        TaskList list = new TaskList();
        list.add(new Todo("a"));
        list.add(new Todo("b"));
        list.add(new Todo("c"));
        Task t = new Todo("d");
        list.add(t);
        assertEquals(t, list.get(4), "get function works correctly");
    }

    @Test
    void testtoString() {
        TaskList list = new TaskList();
        list.add(new Todo("a"));
        list.add(new Todo("b"));
        assertEquals("1. [T][ ] a\n2. [T][ ] b\n", list.toString(), "toString works correctly");
    }

}
