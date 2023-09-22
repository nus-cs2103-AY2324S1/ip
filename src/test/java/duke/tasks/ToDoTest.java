package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    void testToString() {
        Todo t1 = new Todo("kekw");
        Todo t2 = new Todo("kekekw");
        t2.mark();
        assertEquals("[T][ ] kekw", t1.toString(),
                "unmarked task");
        assertEquals("[T][X] kekekw", t2.toString(),
                "marked task");
    }

    @Test
    void testmakeFormat() {
        Todo t = new Todo("reee");
        assertEquals("T|0|reee\n", t.makeFormat(), "formatted task");
    }

}
